package com.ctf.wx.util;
import java.io.IOException;
import net.sf.json.JSONObject;

public class WxSendUtil {

	/**
	 * 
	 * @param touser   微信用户OPENID,推送给谁
	 * @param keyword1	来访单位
	 * @param keyword2	客人姓名
	 * @param keyword3	客人手机
	 * @param keyword4	到访时间
	 * @param customerID 顾客唯一标识用来查询详情页面
	 * @return
	 */
	public static String sendMsg(String touser,String keyword1,String keyword2,String remark){
		String first="恭喜你成功注册成为景德镇邮政会员！";
		/**
		 * 将数据转换为微信模版消息json
		 */
		JSONObject packJsonmsg = SendWeChatMsgUtil.pack2JsonMsg(first,keyword1,keyword2,remark);
		/**
		 * 使用的模版ID取的微信模版
		 */
		String templat_id="nzw1KDMKqK4vRUdjjOiOPBKpaFUfvUvrAUruVM5sD94";
		/**
		 * 点击详情的URL
		 */
		String clickurl="#";
		String topcolor="#FF0000";
		String sendWechatmsgToUser = null;
		try {
			sendWechatmsgToUser = SendWeChatMsgUtil.sendWechatmsgToUser(touser, templat_id, clickurl, topcolor, packJsonmsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject result=new JSONObject();
		
		if(sendWechatmsgToUser!=null && sendWechatmsgToUser.equals("success")){
			result.put("success",true);
		}else{
			result.put("success",false);
		}
		return result.toString();
	}
}
