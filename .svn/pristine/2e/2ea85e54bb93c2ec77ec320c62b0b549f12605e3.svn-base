/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.client;

import com.google.gson.Gson;
import ir.aria.pelaksefid.client.consume.woocommerce.ManaTecNewsRes;
import ir.aria.pelaksefid.client.consume.woocommerce.NewRes;
import ir.aria.pelaksefid.client.consume.woocommerce.NewsRes;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

/**
 *
 * @author Mana2
 */
public class ManaTecServiceClient {

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

    private static final Gson gson = new Gson();
    private static final String BASE_URL = "https://eestgah.ir/";
    private static final String GET_NEWS = "/wp-json/wp/v2/posts?per_page=";
    private static final String OFFSET = "&offset=";
    private static final String SUFFIX = "&_embed";

    private static ManaTecNewsRes getConnection(String urlParameters) {

        String semiUrl = urlParameters;
        ManaTecNewsRes res = new ManaTecNewsRes();

        HttpURLConnection connection = null;
        try {
            URL url = new URL(BASE_URL + semiUrl + SUFFIX);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
//            connection.setRequestProperty("content-type", "application/json");
            connection.setDoOutput(true);
            //Get Response 
            InputStream is = connection.getInputStream();
            String count = "";
            if (connection.getHeaderFields().get("X-WP-TotalPages") != null) {
                count = connection.getHeaderFields().get("X-WP-TotalPages").get(0);
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            res.setResponse(response);
            res.setCount(count);
            return res;
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static NewsRes getNews(String perPage, String pn) {
        NewsRes result = new NewsRes();
        try {
            String offset = "0";
            if (!pn.equals("1")) {
                Integer page = (Integer.valueOf(pn) - 1) * (Integer.valueOf(perPage));
                offset = String.valueOf(page);
            }
            ManaTecNewsRes res = getConnection(GET_NEWS + perPage + OFFSET + offset);
            NewRes[] rslt = gson.fromJson(res.getResponse().toString(), NewRes[].class);
            result.setNews(rslt);
            result.setCount(res.getCount());
            return result;
        } catch (Exception e) {
            NewsRes arr = new NewsRes();
            return arr;
        }
    }
}
