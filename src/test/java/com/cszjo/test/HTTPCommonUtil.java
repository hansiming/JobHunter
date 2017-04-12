package com.cszjo.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import com.google.common.collect.Maps;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;

/**
 * Created by Han on 2017/4/11.
 */
public class HTTPCommonUtil {
    public static void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[] { new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            } }, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    public static Object getHttpHeaders(URL url, int timeout) {
        try {
            trustEveryone();
            Map<String, String> data = Maps.newHashMap();
            data.put("first", "true");
            data.put("pn", "1");
            data.put("kd", "php");
            Connection conn = HttpConnection.connect(url);
            conn.timeout(timeout);
            conn.header("Accept-Encoding", "gzip, deflate, br");
            conn.header("Connection", "keep-alive");
            conn.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.header("Referer", "https://www.lagou.com/jobs/list_php?city=%E6%88%90%E9%83%BD&cl=false&fromSearch=true&labelWords=&suginput=");
            conn.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
            conn.header("Origin", "https://www.lagou.com");
            conn.header("X-Anit-Forge-Code", "0");
            conn.header("X-Anit-Forge-Token", "None");
            conn.header("X-Requested-With", "XMLHttpRequest");
            //conn.cookies("user_trace_token=20160821185106-85f17b62695544629f059c2855aca300; LGUID=20160821185106-29797195-678d-11e6-ac36-525400f775ce; JSESSIONID=21DC10B903BCA12CA259E64927DA8B98; TG-TRACK-CODE=search_code; _ga=GA1.2.480897432.1471776666; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1490788738,1491893662,1491896021; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1491896515; LGSID=20170411153342-2ff06a4d-1e89-11e7-8513-525400f775ce; LGRID=20170411154158-5781cd3c-1e8a-11e7-9db9-5254005c3644; SEARCH_ID=66bd6945cf464771a35411088d3ae5d8");
            conn.data(data);
            conn.post();
            Map<String, String> result = conn.response().headers();
            result.put("boby", conn.response().body());
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            URL url = new URL("https", "www.lagou.com", 443, "/jobs/positionAjax.json?city=%E6%88%90%E9%83%BD&needAddtionalResult=false");
            System.out.println(getHttpHeaders(url, 10000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}