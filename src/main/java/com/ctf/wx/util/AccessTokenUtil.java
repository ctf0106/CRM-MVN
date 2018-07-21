package com.ctf.wx.util;  
  
import javax.net.ssl.HttpsURLConnection;

import com.ctf.util.HttpClientUtils;

import net.sf.json.JSONObject;

import java.io.*;  
  
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Properties;  
  
public  class AccessTokenUtil {  
	
    public synchronized static String getAccessToken() {  
        String FileName = "template_token.properties";  
        try {  
            Properties prop = new Properties();  
            InputStream fis = AccessTokenUtil.class.getClassLoader().getResourceAsStream(FileName);  
            prop.load(fis);
            fis.close();  
            String APPID = prop.getProperty("APPID");  
            String APPSECRET = prop.getProperty("APPSECRET");  
            String access_token = prop.getProperty("access_token");  
            String expires_in = prop.getProperty("expires_in");  
            String last_time = prop.getProperty("last_time");
            int int_expires_in = 0;  
            long long_last_time = 0;  
  
            try {  
                int_expires_in = Integer.parseInt(expires_in);  
                long_last_time = Long.parseLong(last_time);  
  
            } catch (Exception e) {  
  
            }  
            long current_time = System.currentTimeMillis();  
  
            if ((current_time - long_last_time) / 1000 >= int_expires_in) {  
                String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="  
                        + APPID + "&secret=" + APPSECRET;  
                String doPost = HttpClientUtils.doPost(url, null);
                JSONObject jobject = JSONObject.fromObject(doPost);
                String  j_access_token = (String) jobject.get("access_token");  
                String  j_expires_in = jobject.get("expires_in").toString();  
                if (j_access_token != null && j_expires_in != null) {  
                    prop.setProperty("access_token", j_access_token);  
                    prop.setProperty("expires_in", j_expires_in);  
                    prop.setProperty("last_time", System.currentTimeMillis() + "");  
                    URL url_ = AccessTokenUtil.class.getClassLoader().getResource(FileName);  
                    FileOutputStream fos = new FileOutputStream(new File(url_.toURI()));  
                    prop.store(fos, null);  
                    fos.close();
                }  
                return j_access_token;  
            } else {  
                return access_token;  
            }  
        } catch (Exception e) { 
        	e.printStackTrace();
            return null;  
        }  
  
  
    }  
   
}  