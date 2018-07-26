package com.ctf.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface WxService {
	public boolean checkData(String string, String timestamp, String nonce, String signature);

	public Map<String, String> parseXml(HttpServletRequest request);

	public void map2Data(Map<String, String> wxdata);

}
