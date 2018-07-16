package com.ctf.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import com.ctf.wx.controller.WxController;

import net.sf.json.JSONObject;

public class PropertiesUtil {

	public static String getTemplate_Token(String key){
		Properties prop=new Properties();
		InputStream in=new PropertiesUtil().getClass().getResourceAsStream("/template_token.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (String)prop.get(key);
	}
	
	public static JSONObject getUserInfo_Token(){
		
		 Properties prop = new Properties();  
         InputStream fis = WxController.class.getClassLoader().getResourceAsStream("/userinfo_token.properties");  
         try {
				prop.load(fis);
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
         JSONObject result=new JSONObject();
         Set<Object> keys = prop.keySet();//返回属性key的集合
         for (Object key: keys) {
             String value = (String) prop.get(key);
             result.put(key, value);
         }
         return result;
	}
}
