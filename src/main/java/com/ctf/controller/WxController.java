package com.ctf.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ctf.entity.CustomServiceMessage;
import com.ctf.entity.TransInfo;
import com.ctf.entity.User;
import com.ctf.service.UserService;
import com.ctf.util.HttpClientUtils;
import com.ctf.util.ResponseUtil;
import com.ctf.wx.util.AccessTokenUtil;
import com.ctf.wx.util.CheckSignatureUtil;
import com.ctf.wx.util.MessageUtil;
import com.ctf.wx.util.WxSendUtil;
import com.ctf.wx.util.XmlUtil;

import net.sf.json.JSONArray;
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
	private static final String APPID="wx7a9a0aebc4bf0ad2";
	private static final String APPSECRET="3fa4cded023ad1a358dd0fd40934e87f";
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/wx",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public void wx(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取微信服务器传来的相关参数
		String method = request.getMethod();
		/**
		 * 如果是get请求则是验证服务器配置
		 * 如果是post请求则是消息请求
		 */
		
		if(method!=null && method.equals("GET")){
			String signature = request.getParameter("signature");
	        String timestamp = request.getParameter("timestamp");
	        String nonce = request.getParameter("nonce");
	        String echostr = request.getParameter("echostr");
	        PrintWriter out = response.getWriter();
	        //调用比对signature的方法，实现对token和传入的参数进行hash算法后的结果比对
	        if(CheckSignatureUtil.checkSignature(signature, timestamp, nonce)){
	            out.print(echostr);
	        } 
		}else if(method!=null && method.equals("POST")){
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String message = "success";
				//把微信返回的xml信息转义成map
				Map<String, String> map = XmlUtil.xmlToMap(request);
				String fromUserName = map.get("FromUserName");//消息来源用户标识
				String toUserName = map.get("ToUserName");//消息目的用户标识
				String msgType = map.get("MsgType");//消息类型
				String content = map.get("Content");//消息内容
				
				String eventType = map.get("Event");
				if(MessageUtil.MSGTYPE_EVENT.equals(msgType)){//如果为事件类型
					if(MessageUtil.MESSAGE_SUBSCIBE.equals(eventType)){//处理订阅事件
						message = MessageUtil.subscribeForText(toUserName, fromUserName);
					}else if(MessageUtil.MESSAGE_UNSUBSCIBE.equals(eventType)){//处理取消订阅事件
						message = MessageUtil.unsubscribe(toUserName, fromUserName);
					}if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
						//客服系统
						String eventKey= map.get("EventKey");
						if(eventKey.equals("customer")){
							String onlineCustomer = this.getOnlineCustomer();
							System.out.println(onlineCustomer);
							if(onlineCustomer!=null && !onlineCustomer.equals("")){
								CustomServiceMessage cus=new CustomServiceMessage();
				            	cus.setToUserName(fromUserName);  
				            	cus.setFromUserName(toUserName);  
				            	cus.setCreateTime(new Date().getTime());  
				            	cus.setMsgType(MessageUtil.TRANSFER_CUSTOMER_SERVICE);  
				            	TransInfo t=new TransInfo();
				            	t.setKfAccount(onlineCustomer);
				            	cus.setTransInfo(t);
				            	message=MessageUtil.customMessageToXml(cus);
							}else{
								//没有在线的客服人员！
								message= MessageUtil.customer(toUserName, fromUserName);
							}
							
						}
					}
				}
				out.println(message);
				if(out!=null){
					out.close();
				}
		}
       
		
	}
	
	private String getCustomerWx(String kf_id){
		String accessToken = AccessTokenUtil.getAccessToken();
		String url="https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token="+accessToken+"";
		try {
			String result = HttpClientUtils.doGet(url);
			JSONObject resultObj = JSONObject.fromObject(result);
			if(resultObj!=null){
				if(!resultObj.has("errcode")){
					JSONArray jsonArray = resultObj.getJSONArray("kf_list");
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						if(kf_id.equals(jsonObject.getString("kf_id"))){
							return jsonObject.getString("kf_wx");
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取所有的客服，如果有在线的客服返回查询到的第一个在线客服。
	 * @return
	 */
	private String getOnlineCustomer(){
		String accessToken = AccessTokenUtil.getAccessToken();
		String url="https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token="+accessToken+"";
		try {
			String result = HttpClientUtils.doGet(url);
			JSONObject resultObj = JSONObject.fromObject(result);
			if(resultObj!=null){
				System.out.println(result);
				if(!resultObj.has("errcode")){
					JSONArray jsonArray = resultObj.getJSONArray("kf_online_list");
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						String status = jsonObject.getString("status");
						if(status.equals("1")){
							String kf_id = jsonObject.getString("kf_id");
							return this.getCustomerWx(kf_id);
							
						}
					}
				}
				
			}
		} catch (IOException e) {
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
		String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri=http://jdzpost.jxpost.com/wx/mLogin.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
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
	public void sendTemplateMsg(int id,HttpServletResponse response) {
		User user=userService.getById(id);
		if(user!=null){
			WxSendUtil.sendMsg(user.getOpenid(), user.getTrueName(), "2018年", "你的微信昵称是"+user.getNickname());	
		}else{
			System.out.println("用户为空...........");
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	@RequestMapping("/createMenu")
	public void createMenu(HttpServletResponse response) {
		String accessToken = AccessTokenUtil.getAccessToken();
		String createMeunUrl="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken+"";
		String param="{\"button\":[{\"name\":\"注册绑定\",\"type\":\"view\",\"url\":\"http://jdzpost.jxpost.com/wx/initLogin.html\"},{\"type\":\"click\",\"name\":\"联系客服\",\"key\":\"customer\"}]}\"";
		try {
			String result = HttpClientUtils.doPost(createMeunUrl, param);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
