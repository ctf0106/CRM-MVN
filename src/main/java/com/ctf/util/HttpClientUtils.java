package com.ctf.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author Administrator
 *
 */
public class HttpClientUtils {
	
	
	/**
	 * 执行post请求，并返回响应
	 * 
	 * @param url
	 * @param paras
	 * @return
	 * @throws IOException
	 */
	public static String doPost(String url,String param) throws IOException {
		CloseableHttpClient httpClient=HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()    
		        .setConnectTimeout(100000).setConnectionRequestTimeout(100000)    
		        .setSocketTimeout(100000).build();    
		HttpPost httpPost=new HttpPost(url);
		httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
		httpPost.setConfig(requestConfig);
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
		CloseableHttpResponse httpResponse=null;
		String result=null;
		if(null!=param && !"".equals(param)){
			StringEntity entityParam = new StringEntity(param,"UTF-8");
			entityParam.setContentEncoding("utf-8");
			entityParam.setContentType("application/json");
	        httpPost.setEntity(entityParam);
		}
		try {
		 httpResponse=httpClient.execute(httpPost);
		 System.out.println("Status----------------------:"+httpResponse.getStatusLine().getStatusCode());
		 HttpEntity entity=httpResponse.getEntity();
		 result=EntityUtils.toString(entity,"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("请求的url异常了....:"+url);
		}
		return result;
	}
	
	public static String doGet(String url) throws IOException {
		CloseableHttpClient httpClient=HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()    
		        .setConnectTimeout(10000).setConnectionRequestTimeout(10000)    
		        .setSocketTimeout(10000).build();    
		HttpGet httpGet=new HttpGet(url);
		httpGet.setConfig(requestConfig);
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
		CloseableHttpResponse httpResponse=null;
		String result=null;
		try {
		 httpResponse=httpClient.execute(httpGet);
		 System.out.println("Status----------------------:"+httpResponse.getStatusLine().getStatusCode());
		 HttpEntity entity=httpResponse.getEntity();
		 result=EntityUtils.toString(entity,"UTF-8");
		} catch (IOException e) {
			System.out.println("异常的url......:"+url);
		}
		return result;
	}

}