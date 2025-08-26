package ir.aria.pelaksefid.client.telegram;

import ir.aria.pelaksefid.client.SigmaServiceClient;
import ir.aria.pelaksefid.client.consume.sigma.Advertise;
import ir.aria.pelaksefid.client.consume.sigma.AdvertiseRes;
import ir.aria.pelaksefid.client.consume.sigma.Document;
import ir.aria.pelaksefid.utility.ValidationUtil;
import ir.aria.pelaksefid.web.v1.model.request.AdvertiseFilterReq;
import lombok.Getter;
import lombok.Setter;
import net.coobird.thumbnailator.Thumbnails;
import okhttp3.*;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.*;
import java.nio.file.Files;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.*;

@Component
public class TelegramClient {

    // ─────────────── ثابت‌ها ───────────────
    private static final String SIGMA_BASE
            = System.getenv().getOrDefault("SIGMA_BASE", "https://sigmatec.ir:8083");

    private static final String TELEGRAM_TOKEN = "6551007913:AAFY0fYkNsY37LnKhOEzPs1z6347HFXAEBQ";

    private static final String TELEGRAM_CHAT_ID = "@sigmatec_ir";

    private static final String TELEGRAM_ROUTE_API_KEY
            = System.getenv().getOrDefault("ADVERTISE_TO_TELEGRAM_API_KEY", "telegram_bot_sender");

    private static OkHttpClient UNSAFE_HTTP;

    static {
        try {
            UNSAFE_HTTP = buildUnsafeOkHttp();
        } catch (Exception e) {
            UNSAFE_HTTP = new OkHttpClient();
        }
    }

    private static OkHttpClient createSimpleClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build();
    }

    // ─────────────── وظیفه‌ی زمان‌بندی‌شده ───────────────
    @Transactional
    @Scheduled(cron = "0 1/2 * * * *")
    public void advertiseToTelegram() {
        try {
            advertiseToTelegram(TELEGRAM_ROUTE_API_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ─────────────── گرفتن آگهی‌ها از سیگما ───────────────
    private List<SalesOrder> getSalesOrders() throws IOException {
        AdvertiseFilterReq req = new AdvertiseFilterReq();
        AdvertiseRes ads = SigmaServiceClient.getAllAdvertises(req);

        List<SalesOrder> out = new ArrayList<>();
        if (ads != null) {
            for (Advertise n : ads.getSalesOrders()) {
                List<SalesOrderDocument> docs = new ArrayList<>();
                if (n.getSalesOrderDocuments() != null) {
                    for (Document di : n.getSalesOrderDocuments()) {
                        SalesOrderDocument doc = new SalesOrderDocument();
                        doc.setImage_id(di.getDocId());
                        docs.add(doc);
                    }
                }
                SalesOrder so = new SalesOrder();
                so.setId(n.getId());
                so.setCarModelDescription(n.getCarModelDescription());
                so.setBrandDescription(n.getBrandDescription());
                so.setCarTypeDescription(n.getCarTypeDescription());
                so.setMileage(!ValidationUtil.isEmpty(n.getMileage()) ? Long.valueOf(n.getMileage()) : 0);
                so.setMiladiYear(!ValidationUtil.isEmpty(n.getMiladiYear()) ? Long.valueOf(n.getMiladiYear()) : 0);
                so.setPersianYear(n.getPersianYear());
                so.setColorDescription(n.getColorDescription());
                so.setTrimColorDescription(n.getTrimColorId());
                so.setAdvertiseAmount(!ValidationUtil.isEmpty(n.getAdvertiseAmount()) ? Long.valueOf(n.getAdvertiseAmount()) : 0);
                so.setCityDescription(n.getCityDescription());
                so.setProvinceDescription(n.getProvinceDescription());
                so.setSalesOrderDocuments(docs.toArray(new SalesOrderDocument[0]));
                out.add(so);
            }
        }
        return out;
    }

    // ─────────────── ارسال عکس به تلگرام (از طریق Proxy خودت) ───────────────
    
    
    
        private boolean telegramSendPhoto(File file, String captionHtml) throws IOException {
        // استفاده مستقیم از Bot API
        HttpUrl url = Objects.requireNonNull(HttpUrl.parse("https://pelaksefid-1.onrender.com/sigmatec/v1/advertise_to_telegram"));
        MultipartBody.Builder mb = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("chat_id", TELEGRAM_CHAT_ID)
                .addFormDataPart("parse_mode", "HTML")
                .addFormDataPart("caption", captionHtml)
                .addFormDataPart("photo", file.getName(), RequestBody.create(file, okhttp3.MediaType.parse("image/png")));

        Request req = new Request.Builder().url(url).post(mb.build()).build();
        try (Response resp = new OkHttpClient().newCall(req).execute()) {
            return resp.isSuccessful();
        }
    }

        
        
        
        
        
//    private boolean telegramSendPhoto(File file, String captionHtml) {
//        String url = "https://pelaksefid-1.onrender.com/sigmatec/v1/advertise_to_telegram";
//
//        if (!file.exists() || file.length() == 0) {
//            System.err.println("File is missing or empty: " + file.getAbsolutePath());
//            return false;
//        }
//
//        try {
//            MultipartBody.Builder mb = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                    .addFormDataPart("chat_id", TELEGRAM_CHAT_ID)
//                    .addFormDataPart("parse_mode", "HTML")
//                    .addFormDataPart("caption", captionHtml)
//                    .addFormDataPart("photo", file.getName(),
//                            RequestBody.create(file, okhttp3.MediaType.parse("image/jpeg")));
//
//            Request req = new Request.Builder()
//                    .url(url)
//                    .addHeader("X-API-KEY", TELEGRAM_ROUTE_API_KEY)
//                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/139.0.0.0 Safari/537.36")
//                    .post(mb.build())
//                    .build();
//
//            try ( Response resp = UNSAFE_HTTP.newCall(req).execute()) {
//                if (!resp.isSuccessful()) {
//                    System.err.println("Telegram send failed: HTTP " + resp.code() + " - " + resp.message());
//                    System.err.println("Response body: " + (resp.body() != null ? resp.body().string() : "empty"));
//                }
//                return resp.isSuccessful();
//            }
//
//        } catch (IOException e) {
//            System.err.println("IOException during telegramSendPhoto: " + e.getMessage());
//            e.printStackTrace();
//            return false;
//        }
//    }

    // ─────────────── دانلود و resize تصویر ───────────────
    private File downloadAndResize(String imageId) throws IOException {
        String url = SIGMA_BASE + "/documents/getImageById/" + imageId;

        File tmpFile = Files.createTempFile("sig-", imageId + ".jpeg").toFile();
        tmpFile.length();
        Request request = new Request.Builder().url(url).build();
        try ( Response response = UNSAFE_HTTP.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Download failed: HTTP " + response.code());
            }
            ResponseBody body = response.body();

            try ( InputStream in = body.byteStream();  FileOutputStream out = new FileOutputStream(tmpFile)) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        }

        File resizedFile = new File(tmpFile.getParentFile(),
                tmpFile.getName().replace(".jpeg", "-900x600.jpeg"));
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

    // ─────────────── Proxy endpoint اصلی ───────────────
    @GetMapping(path = "/sigmatec/v1/advertise_to_telegram", produces = MediaType.APPLICATION_JSON_VALUE)
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

                String imageId = so.getSalesOrderDocuments()[0].getImage_id();
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
                img.delete();
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

    // ─────────────── OkHttp بدون SSL Check ───────────────
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

        return new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                .hostnameVerifier((hostname, session) -> true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS)
                .build();
    }

    // ─────────────── مدل‌ها ───────────────
    @Getter
    @Setter
    private static class SalesOrder {

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
    private static class SalesOrderDocument {

        private String image_id;
    }
}
