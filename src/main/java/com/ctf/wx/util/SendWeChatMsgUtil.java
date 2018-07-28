package com.ctf.wx.util;

import java.io.IOException;

import com.ctf.util.HttpClientUtils;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class SendWeChatMsgUtil {
	
	
	/**
	 * 封装模板消息
	 * @param first
	 * @return
	 */
	public static JSONObject pack2JsonMsg(String first,String keyword1,String keyword2,String remark){
        JSONObject json = new JSONObject();
        try {
            JSONObject jsonFirst = new JSONObject();
            jsonFirst.put("value", first);
            jsonFirst.put("color", "#173177");
            JSONObject keyword1Obj = new JSONObject();
            keyword1Obj.put("value", keyword1);
            keyword1Obj.put("color", "#173177");
            
            JSONObject keyword2Obj = new JSONObject();
            keyword2Obj.put("value", keyword2);
            keyword2Obj.put("color", "#173177");
            
            JSONObject remarkObj = new JSONObject();
            remarkObj.put("value", remark);
            remarkObj.put("color", "#173177");
            
            json.put("first", jsonFirst);
            json.put("keyword1", keyword1Obj);
            json.put("keyword2", keyword2Obj);
            json.put("remark", remarkObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
	
	/**
	 * 发送模板消息
	 * @param touser 接收模板消息的用户
	 * @param templat_id  模板id
	 * @param clickurl    点击详情的路径
	 * @param topcolor    颜色
	 * @param data		模板数据
	 * @return
	 * @throws IOException 
	 */
	 public static String sendWechatmsgToUser(String touser, String templat_id, String clickurl, String topcolor, JSONObject data) throws IOException{
	        String tmpurl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	        String token = AccessTokenUtil.getAccessToken();
	        String url = tmpurl.replace("ACCESS_TOKEN", token);
	        JSONObject json = new JSONObject();
	        try {
	            json.put("touser", touser);
	            json.put("template_id", templat_id);
	            json.put("url", clickurl);
	            json.put("topcolor", topcolor);
	            json.put("data", data);
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        String result = HttpClientUtils.doPost(url, json.toString());
	        try {
	        	 JSONObject resultJson=JSONObject.fromObject(result);
	        	String errmsg = (String) resultJson.get("errmsg");
	            if(!"ok".equals(errmsg)){  //如果为errmsg为ok，则代表发送成功，公众号推送信息给用户了。
	                return "error";
	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        return "success";
	    }
}
