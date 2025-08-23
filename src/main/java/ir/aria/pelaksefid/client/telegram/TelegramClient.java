package ir.aria.pelaksefid.client.telegram;

import ir.aria.pelaksefid.client.SigmaServiceClient;
import ir.aria.pelaksefid.client.consume.sigma.Advertise;
import ir.aria.pelaksefid.client.consume.sigma.AdvertiseRes;
import ir.aria.pelaksefid.client.consume.sigma.Document;
import ir.aria.pelaksefid.client.consume.sigma.ManaPrice;
import ir.aria.pelaksefid.client.consume.sigma.ManaPriceRes;
import ir.aria.pelaksefid.utility.ValidationUtil;
import ir.aria.pelaksefid.web.v1.model.request.AdvertiseFilterReq;
import ir.aria.pelaksefid.web.v1.model.request.base.BaseListReq;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import lombok.Getter;
import lombok.Setter;
import net.coobird.thumbnailator.Thumbnails;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
public class TelegramClient {

//    // <editor-fold defaultstate="collapsed" desc="disableSslVerification">
//    static {
//        disableSslVerification();
//    }
//
//    private static void disableSslVerification() {
//        try {
//            // Create a trust manager that does not validate certificate chains
//            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
//
//                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//
//                public void checkClientTrusted(X509Certificate[] certs, String authType) {
//                }
//
//                public void checkServerTrusted(X509Certificate[] certs, String authType) {
//                }
//            }
//            };
//
//            // Install the all-trusting trust manager
//            SSLContext sc = SSLContext.getInstance("SSL");
//            sc.init(null, trustAllCerts, new java.security.SecureRandom());
//            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//
//            // Create all-trusting host name verifier
//            HostnameVerifier allHostsValid = new HostnameVerifier() {
//
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//            };
//
//            // Install the all-trusting host verifier
//            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        }
//    }
//    // </editor-fold> 
    private static final String SIGMA_BASE = System.getenv().getOrDefault("SIGMA_BASE", "https://sigmatec.ir:8083");

    private static final String TELEGRAM_TOKEN = System.getenv().getOrDefault("TELEGRAM_BOT_TOKEN", "REPLACE_ME");
    private static final String TELEGRAM_CHAT_ID = System.getenv().getOrDefault("TELEGRAM_CHAT_ID", "@sigmatec_ir");

    private static final String TELEGRAM_ROUTE_API_KEY = System.getenv().getOrDefault("ADVERTISE_TO_TELEGRAM_API_KEY", "telegram_bot_sender");

    private static OkHttpClient UNSAFE_HTTP;

    static {
        try {
            UNSAFE_HTTP = buildUnsafeOkHttp();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            UNSAFE_HTTP = new OkHttpClient();
        }
    }

    private static final OkHttpClient createSimpleClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    @Transactional
    @Scheduled(cron = "0 1/2 * * * *")
    public void advertiseToTelegram() {
        try {
            advertiseToTelegram(TELEGRAM_ROUTE_API_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    @Transactional
//    @Scheduled(cron = "0 1/2 * * * *")
//    public void sendPriceToTelegram() {
//        try {
//            priceToTelegram(TELEGRAM_ROUTE_API_KEY);
//        } catch (Exception e) {
//        }
//    }

    private List<SalesOrder> getSalesOrders() throws IOException {
        AdvertiseFilterReq req = new AdvertiseFilterReq();
        AdvertiseRes ads = SigmaServiceClient.getAllAdvertises(req);

        List<SalesOrder> out = new ArrayList<>();
        if (ads != null) {
            for (Advertise n : ads.getSalesOrders()) {
                SalesOrderDocument[] docs = new SalesOrderDocument[n.getSalesOrderDocuments().length];
                Document[] d = n.getSalesOrderDocuments();
                int cntr = 0;
                if (d != null) {
                    for (Document di : d) {
                        SalesOrderDocument doc = new SalesOrderDocument();
                        doc.setImage_id(di.getDocId());
                        docs[cntr++] = (doc);
                    }
                }
                SalesOrder salesOrder = new SalesOrder();
                salesOrder.setId(n.getId());
                salesOrder.setCarModelDescription(n.getCarModelDescription());
                salesOrder.setBrandDescription(n.getBrandDescription());
                salesOrder.setCarTypeDescription(n.getCarTypeDescription());
                salesOrder.setMileage(!ValidationUtil.isEmpty(n.getMileage()) ? Long.valueOf(n.getMileage()) : 0);
                salesOrder.setMiladiYear(!ValidationUtil.isEmpty(n.getMiladiYear()) ? Long.valueOf(n.getMiladiYear()) : 0);
                salesOrder.setPersianYear(n.getPersianYear());
                salesOrder.setColorDescription(n.getColorDescription());
                salesOrder.setTrimColorDescription(n.getTrimColorId());
                salesOrder.setAdvertiseAmount(!ValidationUtil.isEmpty(n.getAdvertiseAmount()) ? Long.valueOf(n.getAdvertiseAmount()) : 0);
                salesOrder.setCityDescription(n.getCityDescription());
                salesOrder.setProvinceDescription(n.getProvinceDescription());
                salesOrder.setSalesOrderDocuments(docs);
                out.add(salesOrder);
            }
        }
        return out;
    }

    private boolean telegramSendPhoto(File file, String captionHtml) throws IOException {
//        HttpUrl url = Objects.requireNonNull(HttpUrl.parse("https://api.telegram.org/bot" + TELEGRAM_TOKEN + "/sendPhoto"));
//        MultipartBody.Builder mb = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("chat_id", TELEGRAM_CHAT_ID)
//                .addFormDataPart("parse_mode", "HTML")
//                .addFormDataPart("caption", captionHtml)
//                .addFormDataPart("photo", file.getName(), RequestBody.create(file, okhttp3.MediaType.parse("image/png")));
//
//        Request req = new Request.Builder().url(url).post(mb.build()).build();
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
//                .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
//                .writeTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
//                .build();
//
//        try ( Response resp = client.newCall(req).execute()) {
//            String body = resp.body() != null ? resp.body().string() : "no body";
//            System.out.println("Code: " + resp.code());
//            System.out.println("Body: " + body);
//            return resp.isSuccessful();
//        }
//        
//           try (Response resp = PROXIED_HTTP_CLIENT.newCall(req).execute()) {
//        String body = resp.body() != null ? resp.body().string() : "no body";
//        System.out.println("Code: " + resp.code());
//        System.out.println("Body: " + body);
//        return resp.isSuccessful();
//    }

        HttpUrl url = HttpUrl.parse("https://api.telegram.org/bot" + TELEGRAM_TOKEN + "/sendPhoto");
        MultipartBody.Builder mb = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("chat_id", TELEGRAM_CHAT_ID)
                .addFormDataPart("parse_mode", "HTML")
                .addFormDataPart("caption", captionHtml)
                .addFormDataPart("photo", file.getName(), RequestBody.create(file, okhttp3.MediaType.parse("image/png")));

        Request req = new Request.Builder().url(url).post(mb.build()).build();

        // 🔹 استفاده از کلاینت پروکسی‌شده به جای کلاینت معمولی
        try ( Response resp = createSimpleClient().newCall(req).execute()) {
            String body = resp.body() != null ? resp.body().string() : "no body";
            System.out.println("Code: " + resp.code());
            System.out.println("Body: " + body);
            return resp.isSuccessful();
        }
    }

    private File downloadAndResize(String imageId) throws IOException {
        String url = SIGMA_BASE + "/documents/getImageById/" + imageId;

        File tmpFile = Files.createTempFile("sig-", imageId + ".png").toFile();

        Request request = new Request.Builder().url(url).build();
        try ( Response response = UNSAFE_HTTP.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Download failed: HTTP " + response.code());
            }

            try ( InputStream in = Objects.requireNonNull(response.body()).byteStream();  FileOutputStream out = new FileOutputStream(tmpFile)) {

                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        }

        File resizedFile = new File(tmpFile.getParentFile(),
                tmpFile.getName().replace(".png", "-900x600.png"));
        Thumbnails.of(tmpFile)
                .size(900, 600)
                .keepAspectRatio(true)
                .toFile(resizedFile);

        return resizedFile.exists() ? resizedFile : tmpFile;
    }

    private static String formatCurrency(Long value) {
        if (value == null) {
            return "";
        }
        NumberFormat nf = NumberFormat.getInstance(new Locale("fa", "IR"));
        return nf.format(value) + " تومان";
    }

    @GetMapping(path = "/sigmatec/v1/advertise_to_telgram", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> advertiseToTelegram(@RequestHeader(name = "X-API-KEY", required = false) String apiKey) {
        Map<String, Object> res = new HashMap<>();
        try {
            if (!Objects.equals(apiKey, TELEGRAM_ROUTE_API_KEY)) {
                res.put("status", 401);
                res.put("message", "invalid api key");
                return res;
            }

            List<SalesOrder> orders = getSalesOrders();

            int sent = 0;
            for (SalesOrder so : orders) {
                if (so.getSalesOrderDocuments() == null || so.getSalesOrderDocuments().length == 0) {
                    continue;
                }
                String imageId = so.salesOrderDocuments[0].getImage_id();
                File img = downloadAndResize(imageId);
                String todayFa = ZonedDateTime.now(ZoneId.of("Asia/Tehran")).toLocalDate().toString();

                String caption = "خودرو : <strong>" + safe(so.getBrandDescription()) + " " + safe(so.getCarModelDescription()) + "</strong>\n\n"
                        + "تاریخ انتشار : <strong>" + todayFa + "</strong>\n"
                        + "کارکرد : <strong>" + (so.getMileage() == null ? "-" : (so.getMileage() + " کیلومتر")) + "</strong>\n"
                        + "سال ساخت : <strong>" + safe(so.getPersianYear()) + "</strong>\n"
                        + "رنگ خودرو : <strong>" + safe(so.getColorDescription()) + "</strong>\n"
                        + "رنگ داخل : <strong>" + safe(so.getTrimColorDescription()) + "</strong>\n"
                        + "قیمت : <strong>" + formatCurrency(so.getAdvertiseAmount()) + "</strong>\n\n"
                        + "<a href='https://sigmatec.ir/purchase-car-reserve?ord-" + so.getId() + "'><blockquote>رزرو شوروم</blockquote></a>";

                boolean ok = telegramSendPhoto(img, caption);
                try {
                    img.delete();
                } catch (Exception ignore) {
                }
                if (ok) {
                    sent++;
                }
            }

            res.put("status", 0);
            res.put("sent", sent);
            return res;
        } catch (Exception e) {
            res.put("status", 500);
            res.put("error", e.getMessage());
            return res;
        }
    }

    private static String safe(String s) {
        return s == null ? "-" : s;
    }

    @GetMapping(path = "/sigmatec_price/v1/price_to_telegram", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> priceToTelegram(@RequestHeader(name = "X-API-KEY", required = false) String apiKey) {
        Map<String, Object> res = new HashMap<>();
        try {
            if (!Objects.equals(apiKey, TELEGRAM_ROUTE_API_KEY)) {
                res.put("status", 401);
                res.put("message", "invalid api key");
                return res;
            }
            BaseListReq req = new BaseListReq();
            req.setPl("10000");
            req.setPn("1");
            ManaPriceRes manaPriceRes = SigmaServiceClient.getManaPrices(req);

            String updateDate = manaPriceRes.getManaPrices() != null && manaPriceRes.getManaPrices().length > 0 ? manaPriceRes.getManaPrices()[0].getUpdatePriceDate() : "";

            StringBuilder sb = new StringBuilder();
            sb.append("<strong>قیمت روز خودرو ").append(escapeHtml(updateDate)).append("</strong>\n\n");
            for (ManaPrice c : manaPriceRes.getManaPrices()) {
                String model = c.getCarModel();
                String least = c.getLeastPrice();
                String most = c.getMostPrice();
                sb.append("<strong>").append(escapeHtml(model)).append("</strong> : <strong>")
                        .append(escapeHtml(least)).append("-").append(escapeHtml(most)).append(" تومان</strong>\n\n");
            }
            sb.append("\n<strong>\"قیمت خودرو صفر، میانگین قیمت بازار با نوسان دو درصد است\"</strong>\n\n");
            sb.append("<strong>\"هزینه های مالیات نقل و انتقال و تعویض پلاک بر عهده خریدار است\"</strong>");

            File placeholder = getPlaceholderFromResources();
            boolean ok = telegramSendPhoto(placeholder, sb.toString());
            try {
                placeholder.delete();
            } catch (Exception ignore) {
            }

            res.put("status", ok ? 0 : 1);
            res.put("message", ok ? "sent" : "failed");
            return res;
        } catch (Exception e) {
            res.put("status", 500);
            res.put("error", e.getMessage());
            return res;
        }
    }

    private static String escapeHtml(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    private File getPlaceholderFromResources() throws IOException {
        InputStream is = getClass().getResourceAsStream("/static/images/placeholder.jpeg");
        if (is == null) {
            throw new IOException("File not found in resources");
        }

        File tmpFile = Files.createTempFile("sig-", ".jpeg").toFile();
        try ( FileOutputStream out = new FileOutputStream(tmpFile)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
        return tmpFile;
    }

    // ───────────────────────── Route 3: sigma_ads (اسکلت) ─────────────────────────
    @GetMapping(path = "/sigmatec/v1/sigma_ads", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> sigmaAds(@RequestHeader(name = "X-API-KEY", required = false) String apiKey) {
        Map<String, Object> res = new HashMap<>();
        try {
            String SIGMA_ADS_API_KEY = System.getenv().getOrDefault("SIGMA_ADS_API_KEY",
                    "P10DayvFEGUCfrJ50OxHTv1i5Yt8pHM1994hcHG8M9uaNL6zWipt9NRwccIqU1NcJ7c0vW2n3mbad2Qp9fCwAxrjB3tq2tIk0k6SLDc7ndjwdUXaQ6YmMGw1NDp6mJmB");
            if (!Objects.equals(apiKey, SIGMA_ADS_API_KEY)) {
                res.put("status", 401);
                res.put("message", "invalid api key");
                return res;
            }

            List<SalesOrder> orders = getSalesOrders();

            res.put("status", 0);
            res.put("count", orders.size());
            res.put("message", "skeleton ready – implement DB persistence as needed");
            return res;
        } catch (Exception e) {
            res.put("status", 500);
            res.put("error", e.getMessage());
            return res;
        }
    }

    private static OkHttpClient buildUnsafeOkHttp() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            }
        };
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
        builder.hostnameVerifier((hostname, session) -> true);
        return builder.build();
    }

    @Getter
    @Setter
    private class SalesOrder {

        private String id;
        private String carModelDescription;
        private String brandDescription;
        private String carTypeDescription;
        private Long mileage;
        private Long miladiYear;
        private String persianYear;
        private String colorDescription;
        private String trimColorDescription;
        private Long advertiseAmount;
        private String cityDescription;
        private String provinceDescription;
        private SalesOrderDocument[] salesOrderDocuments;
    }

    @Getter
    @Setter
    private class SalesOrderDocument {

        private String image_id;
    }
}
