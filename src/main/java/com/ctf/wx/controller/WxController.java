package com.ctf.wx.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ctf.util.HttpClientUtils;
import com.ctf.wx.util.AccessTokenUtil;
import com.ctf.wx.util.WxSendUtil;

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
		return null;
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
	public ModelAndView mLogin(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		String code = request.getParameter("code");
		/**
		 * 获取token
		 */
		JSONObject access_Token = getAccess_Token(code);
		String userInfo = getUserInfo(access_Token.getString("access_token"), access_Token.getString("openid"));
		JSONObject userObj = JSONObject.fromObject(userInfo);
		String openid = userObj.getString("openid");
		String nickname = userObj.getString("nickname");
		mav.addObject("openid", openid);
		mav.addObject("nickname", nickname);
		mav.setViewName("mlogin/index");
		return mav;

	}
	@RequestMapping("/sendTemplateMsg")
	public void sendTemplateMsg(int id) {
		WxSendUtil.sendMsg("oCMMx1n19zgaWBrv17bUA5UxZAw8", "keyword1", "keyword2", "keyword3", "keyword4");
	}
	
	/**
	 * 通过code获取access_token
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	public JSONObject getAccess_Token(String code){
		
		  String FileName = "userinfo_token.properties";  
	        try {  
	            Properties prop = new Properties();  
	            InputStream fis = WxController.class.getClassLoader().getResourceAsStream(FileName);  
	            prop.load(fis);
	            fis.close();  
	            String APPID = prop.getProperty("APPID");  
	            String SECRET = prop.getProperty("SECRET");  
	            String access_token = prop.getProperty("access_token");  
	            String expires_in = prop.getProperty("expires_in");  
	            String last_time = prop.getProperty("last_time");
	            String openid = prop.getProperty("openid");
	            int int_expires_in = 0;  
	            long long_last_time = 0;  
	            try {  
	                int_expires_in = Integer.parseInt(expires_in);  
	                long_last_time = Long.parseLong(last_time);  
	            } catch (Exception e) {  
	            	e.printStackTrace();
	            }  
	            long current_time = System.currentTimeMillis();  
	            if ((current_time - long_last_time) / 1000 >= int_expires_in) {  
	            	String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+SECRET+"&code="+code+"&grant_type=authorization_code";
	                String doPost = HttpClientUtils.doGet(url);
	                JSONObject jobject = JSONObject.fromObject(doPost);
	                String  j_access_token = (String) jobject.get("access_token");  
	                String  j_openid = (String) jobject.get("openid"); 
	                String  j_expires_in = jobject.get("expires_in").toString();  
	                if (j_access_token != null && j_expires_in != null) {  
	                    prop.setProperty("access_token", j_access_token);  
	                    prop.setProperty("expires_in", j_expires_in);  
	                    prop.setProperty("openid", j_openid);  
	                    prop.setProperty("last_time", System.currentTimeMillis() + "");  
	                    URL url_ = WxController.class.getClassLoader().getResource(FileName);  
	                    FileOutputStream fos = new FileOutputStream(new File(url_.toURI()));  
	                    prop.store(fos, null);  
	                    fos.close();
	                }  
	                JSONObject j_result=new JSONObject();
	                j_result.put("access_token", j_access_token);
	                j_result.put("openid", j_openid);
	                return j_result;  
	            } else { 
	            	JSONObject result=new JSONObject();
	            	result.put("access_token", access_token);
	            	result.put("openid", openid);
	                return result;  
	            }  
	        } catch (Exception e) { 
	        	e.printStackTrace();
	            return null;  
	        }  
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
			result = HttpClientUtils.doGet(url);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
