package com.ctf.wx.controller;

import java.io.IOException;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ctf.util.HttpClientUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wx")
public class WxController {

	@RequestMapping("/wx")
	@ResponseBody
	public void wx(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		System.out.println(signature);
		System.out.println(timestamp);
		System.out.println(nonce);
		System.out.println(echostr);
		boolean f = check("weixin", timestamp, nonce, signature);
		response.setCharacterEncoding("UTF-8");
		Writer w;
		if (f) {
			w = response.getWriter();
			w.write(echostr);// 接入指南让返回这个字符串。
			w.close();
		}
	}

	private static boolean check(String token, String timestamp, String nonce, String signature) {
		String[] arr = { token, timestamp, nonce };
		Arrays.sort(arr);
		String str = "";
		for (int i = 0; i < arr.length; i++) {
			str += arr[i];
		}
		String pwd = SHA1(str);
		System.out.println("pwd:" + pwd);
		System.out.println("ooo:" + signature);
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
		return "";
	}
	
	/**
	 *第一步重定向到微信请求
	 * http://1432816034.free.ngrok.cc/wx/initLogin.html
	 * @return
	 */
	@RequestMapping("/initLogin")
	public String initLogin(){
		String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2195d53af725c53e&redirect_uri=http://1432816034.free.ngrok.cc/wx/mLogin.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		return "redirect:"+url;
	}
	
	/**
	 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2195d53af725c53e&redirect_uri=http://1432816034.free.ngrok.cc/wx/mLogin.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
	 * @param request
	 * @return
	 */
	@RequestMapping("/mLogin")
	public String mLogin(HttpServletRequest request) {
		String code = request.getParameter("code");
		String appid="wx2195d53af725c53e";
		String secret="4df490d350c1dded8a68ae875bb8bf79";
		JSONObject access_Token = getAccess_Token(appid, secret, code);
		String userInfo = getUserInfo(access_Token.getString("access_token"), access_Token.getString("openid"));
		System.out.println(userInfo);
		return "mlogin/index";

	}
	
	/**
	 * 通过code获取access_token
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	public JSONObject getAccess_Token(String appid,String secret,String code){
		String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
		String result = null;
		try {
			result = HttpClientUtils.doGet(url, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject access_token = JSONObject.fromObject(result);
		return access_token;
	}
	/**
	 * 获取用户信息
	 * @param access_token
	 * @param openid
	 * @return
	 */
	public String getUserInfo(String access_token, String openid) {
		String url="https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
		String result = null;
		try {
			result = HttpClientUtils.doPost(url, null);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
