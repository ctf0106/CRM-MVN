package com.ctf.wx.util;

import java.util.ArrayList;
import java.util.Collections;

public class CheckSignatureUtil {
	//定义微信接口配置的token，同微信账号页面中配置的token值保持一致
	public static final String token = "weixin";
	//实现对回传参数的hash，然后同回传参数signature比对
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		ArrayList<String> list = new ArrayList<String>();
		list.add(token);
		list.add(timestamp);
		list.add(nonce);
		//对参数进行升序排列
		Collections.sort(list);
		StringBuilder content = new StringBuilder();
		for(String str:list){
			content.append(str);
		}
		//调用hash算法，对相关参数hash
		return signature.equals(HashUtil.hash(content.toString(),"SHA1"));
	}
}
