package com.ctf.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.ctf.entity.Promote;
import com.ctf.service.UserService;
import com.ctf.service.WxService;

@Service("WxService")
public class WxServiceImpl implements WxService{
	@Resource
	private UserService userService;
	
	private Logger logger = Logger.getLogger(WxServiceImpl.class);
	@Override
	public boolean checkData(String token, String timestamp, String nonce, String signature) {
		String[] arr = {token, timestamp, nonce};
		Arrays.sort(arr);
		String str = "";
		for (int i = 0; i < arr.length; i++) {
			str += arr[i];
		}
		String pwd = SHA1(str);
		if (pwd.equals(signature)) {
			return true;
		} else {
			return false;
		}
	}
	public static String SHA1(String strs) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(strs.getBytes());
			byte messageDigest[] = digest.digest();
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Map<String, String> parseXml(HttpServletRequest request) {
		// 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = null;
		try {
			document = reader.read(inputStream);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		
		
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        @SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());
        // 释放资源
        try {
			inputStream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        inputStream = null;
        return map;
		
	}
	public void map2Data(Map<String, String> wxdata) {
		if(wxdata.get("MsgType")!=null){
            if("event".equals(wxdata.get("MsgType"))){
                if( "subscribe".equals(wxdata.get("Event"))){
                	logger.info("用户未关注时，进行关注后的事件推送");
                	String result = "开发者微信号:"+wxdata.get("ToUserName")+"关注者的openid:"+wxdata.get("FromUserName")
                	+ "消息创建时间 :"+ wxdata.get("CreateTime")+"事件KEY值:"+ wxdata.get("EventKey");
                	logger.info(result);
                	String userOpenID = wxdata.get("FromUserName");
                	String eventKey=wxdata.get("EventKey");
                	//EventKey   qrscene_15
                	String[] promote = eventKey.split("_");
                	Promote promoteObj=new Promote(promote[1],userOpenID);
                	userService.addPromote(promoteObj);
                }else if("unsubscribe".equals(wxdata.get("Event"))){
                	logger.info("用户取消后的事件推送");
                	String result = "开发者微信号:"+wxdata.get("ToUserName")+"关注者的openid:"+wxdata.get("FromUserName")
                	+ "消息创建时间 :"+ wxdata.get("CreateTime")+"事件KEY值:"+ wxdata.get("EventKey");
                	String userOpenID = wxdata.get("FromUserName");
                	userService.deletePromoteByOpenID(userOpenID);
                	logger.info(result);
                }else if("SCAN".equals(wxdata.get("Event"))){
                	logger.info("用户已关注时的事件推送");
                	String result = "开发者微信号:"+wxdata.get("ToUserName")+"关注者的openid:"+wxdata.get("FromUserName")
                	+ "消息创建时间 :"+ wxdata.get("CreateTime")+"事件KEY值:"+ wxdata.get("EventKey");
                	
                	logger.info(result);
                }
            }
        }
		
	}

}
