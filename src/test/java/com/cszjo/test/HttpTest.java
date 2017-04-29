package com.cszjo.test;

import com.google.common.collect.Maps;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;

import javax.net.ssl.*;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Set;

/**
 * Created by Han on 2017/4/11.
 */
public class HttpTest {

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
            Connection conn = HttpConnection.connect("https://www.lagou.com/jobs/positionAjax.json?city=%E6%88%90%E9%83%BD&needAddtionalResult=false");
            conn.timeout(timeout);
            conn.header("Accept", "application/json, text/javascript, */*; q=0.01");
            conn.header("Accept-Encoding", "gzip, deflate, br");
            conn.header("Accept-Language", "zh-CN,zh;q=0.8");
            conn.header("Connection", "keep-alive");
            conn.header("Content-Length", "22");
//            conn.ignoreContentType();
            conn.header("Cookie", "user_trace_token=20160821185106-85f17b62695544629f059c2855aca300; LGUID=20160821185106-29797195-678d-11e6-ac36-525400f775ce; JSESSIONID=21DC10B903BCA12CA259E64927DA8B98; TG-TRACK-CODE=search_code; SEARCH_ID=b2a85ebc9f8b4e2ca335aa2c15bd4f74");
            conn.header("Host", "www.lagou.com");
            conn.header("Origin", "https://www.lagou.com");
            conn.header("Referer", "https://www.lagou.com/jobs/list_php?city=%E6%88%90%E9%83%BD&cl=false&fromSearch=true&labelWords=&suginput=");
            conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
            conn.header("X-Anit-Forge-Code", "0");
            conn.header("X-Anit-Forge-Token", "None");
            conn.header("X-Requested-With", "XMLHttpRequest");
            conn.data(data);
            conn.post();
            Map<String, String> result = conn.response().headers();
            result.put("boby", conn.response().body());
            Map<String, String> cookies = conn.response().cookies();
            Set<String> keys = cookies.keySet();
            for (String key: keys) {
                result.put(key, cookies.get(key));
            }
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
