/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Mana2
 */
public class SmsServiceClient2 {

    private static final String API_KEY = "4275535979512B4448504A6872384E6D56756879515248664F5A3341626E59324D4E6F386B4B2F664E7A4D3D";
    private static final String OTP_CODE = "/verify/lookup.json?receptor=%s&token=%s&template=pelaksefidotp";
//    private static final String BODY = "{\"pattern_code\":\"%s\",\"originator\":\"+983000505\",\"recipient\":\"%s\",\"values\":%s}";
    private static final String SEND_PATTERN_URL = "https://api.kavenegar.com/v1/";

    public static Boolean sendSms(String cellNumber, String values) {

        String urlParameters = String.format(SEND_PATTERN_URL + API_KEY + OTP_CODE, cellNumber, values);
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlParameters);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("content-type", "application/json");
            connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
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
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }

}
