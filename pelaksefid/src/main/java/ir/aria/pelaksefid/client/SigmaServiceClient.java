/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.client;

import com.google.gson.Gson;
import ir.aria.pelaksefid.client.consume.sigma.AdvertiseReq;
import ir.aria.pelaksefid.client.consume.sigma.AdvertiseRes;
import ir.aria.pelaksefid.client.consume.sigma.ManaPriceRes;
import ir.aria.pelaksefid.client.consume.sigma.SigmaToken;
import ir.aria.pelaksefid.web.v1.model.request.AdvertiseFilterReq;
import ir.aria.pelaksefid.web.v1.model.request.base.BaseReq;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mana2
 */
@Component
public class SigmaServiceClient {

    // <editor-fold defaultstate="collapsed" desc="disableSslVerification">
    static {
        disableSslVerification();
    }

    private static void disableSslVerification() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {

                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }
    // </editor-fold> 
    private static Gson GSON = new Gson();
    private static SigmaToken token = null;

    private final static String BASE = "https://sigmatec.ir:8083/";
    private final static String TOKEN_URL = BASE + "token";
    private final static String GET_ALL_ADVERTISES = BASE + "api/v1/salesorders/getSalesOrdersWithFilterForClient";
    private final static String GET_MANA_PRICES = BASE + "api/v1/manaprices/getManaPrices";
    private final static String TOKEN_REQUEST = "{\"username\":\"NEW_PLK_SFD\",\"password\":\"NEW_PLK_SFD\"}";
    private static SigmaToken TOKEN = null;

    private static SigmaToken getNewToken() {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(TOKEN_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Length", Integer.toString(TOKEN_REQUEST.getBytes().length));
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(TOKEN_REQUEST);
            wr.close();
            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            TOKEN = GSON.fromJson(response.toString(), SigmaToken.class);
            TOKEN.setExpireDate();
            return TOKEN;
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static AdvertiseRes getAllAdvertises(AdvertiseFilterReq filter) {

        AdvertiseReq req = new AdvertiseReq();
        String urlParameters = GSON.toJson(req);
        HttpURLConnection connection = null;
        try {
            if (token == null || token.isExpired()) {
                token = getNewToken();
            }
            //Create connection
            URL url = new URL(GET_ALL_ADVERTISES);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("content-type", "application/json");
            connection.setRequestProperty("authorization", token.getToken());
            connection.setDoOutput(true);
            //Send request
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            bw.write(urlParameters);
            bw.flush();
            bw.close();
            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return GSON.fromJson(response.toString(), AdvertiseRes.class);
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static ManaPriceRes getManaPrices(BaseReq req) {

        String urlParameters = GSON.toJson(req);
        HttpURLConnection connection = null;
        try {
            if (token == null || token.isExpired()) {
                token = getNewToken();
            }
            //Create connection
            URL url = new URL(GET_MANA_PRICES);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("content-type", "application/json");
            connection.setRequestProperty("authorization", token.getToken());
            connection.setDoOutput(true);
            //Send request
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            bw.write(urlParameters);
            bw.flush();
            bw.close();
            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return GSON.fromJson(response.toString(), ManaPriceRes.class);
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
