package com.ctf.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.ctf.service.UserService;
import com.ctf.service.WxService;
import com.ctf.util.HttpClientUtils;
import com.ctf.util.ResponseUtil;
import com.ctf.wx.util.MessageUtil;
import com.ctf.wx.util.WxSendUtil;
import com.ctf.wx.util.XmlUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wx")
public class WxController {
	/**
	 * 需求
	 * 
	 * 1.注册及绑定功能。
	 * 2.推送功能。
	 * 3.服务号关注的时候能带参数
	 */
	private static final String APPID="wx2195d53af725c53e";
	private static final String APPSECRET="3fa4cded023ad1a358dd0fd40934e87f";
	
	@Resource
	private UserService userService;
	
	@Resource
	private WxService wxService;
	
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
		boolean verify = wxService.checkData("weixin", timestamp, nonce, signature);
		
		
		Map<String, String> wxdata=wxService.parseXml(request);
		wxService.map2Data(wxdata);
		
		
		if (verify) {
			if(echostr!=null){
				try {
					ResponseUtil.write(response, echostr);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();
//		String message = "success";
//		try {
//			//把微信返回的xml信息转义成map
//			Map<String, String> map = XmlUtil.xmlToMap(request);
//			String fromUserName = map.get("FromUserName");//消息来源用户标识
//			String toUserName = map.get("ToUserName");//消息目的用户标识
//			String msgType = map.get("MsgType");//消息类型
//			String content = map.get("Content");//消息内容
//			
//			String eventType = map.get("Event");
//			if(MessageUtil.MSGTYPE_EVENT.equals(msgType)){//如果为事件类型
//				if(MessageUtil.MESSAGE_SUBSCIBE.equals(eventType)){//处理订阅事件
//					message = MessageUtil.subscribeForText(toUserName, fromUserName);
//				}else if(MessageUtil.MESSAGE_UNSUBSCIBE.equals(eventType)){//处理取消订阅事件
//					message = MessageUtil.unsubscribe(toUserName, fromUserName);
//				}
//			}
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}finally{
//			out.println(message);
//			if(out!=null){
//				out.close();
//			}
//		}

		
		
	}

	
	/**
	 *第一步重定向到微信请求
	 * http://1432816034.free.ngrok.cc/wx/initLogin.html
	 * @return
	 */
	@RequestMapping("/initLogin")
	public String initLogin(){
		String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri=http://1432816034.free.ngrok.cc/wx/mLogin.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
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
		if(code!=null && !code.equals("")){
			JSONObject access_Token = getAccess_Token(code);
			String userInfo = getUserInfo(access_Token.getString("access_token"), access_Token.getString("openid"));
			JSONObject userObj = JSONObject.fromObject(userInfo);
			String openid = userObj.getString("openid");
			String nickname = userObj.getString("nickname");
			mav.addObject("openid", openid);
			mav.addObject("nickname", nickname);
			mav.setViewName("mlogin/index");
		}else{
			mav.setViewName("mlogin/error.jsp");
		}
		return mav;

	}
	@RequestMapping("/sendTemplateMsg")
	public void sendTemplateMsg(int id) {
		
		
		WxSendUtil.sendMsg("oCMMx1n19zgaWBrv17bUA5UxZAw8", "关键词1", "关键词2", "keyword3", "keyword4");
	}
	
	/**
	 * 通过code获取access_token
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	public JSONObject getAccess_Token(String code){
    	String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+APPSECRET+"&code="+code+"&grant_type=authorization_code";
        String doPost = null;
		try {
			doPost = HttpClientUtils.doGet(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
        JSONObject resutl = JSONObject.fromObject(doPost);
        return resutl;  
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
