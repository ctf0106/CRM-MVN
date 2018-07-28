package com.ctf.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ctf.entity.PageBean;
import com.ctf.entity.User;
import com.ctf.service.UserService;
import com.ctf.util.HttpClientUtils;
import com.ctf.util.ResponseUtil;
import com.ctf.util.StringUtil;
import com.ctf.wx.util.AccessTokenUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 用户操作
 * @author C
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	
	/**
	 * 分页条件查询用户
	 * @param page
	 * @param rows
	 * @param s_user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,User s_user,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userName", StringUtil.formatLike(s_user.getUserName()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<User> userList=userService.find(map);
		Long total=userService.getTotal(map);
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(userList);
		
		
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			
			String ticket = getQrCodeTicket(jsonObject.getInt("id"));
			
			jsonObject.put("wechat", ticket);
		}
		
		
		
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	public String getQrCodeTicket(int scene_id) {
		String accessToken = AccessTokenUtil.getAccessToken();
		String url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken+"";
		String param="{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+scene_id+"}}}";
		try {
			//{"ticket":"gQHm8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAycG9VTE03ODNjTm0xMDAwMDAwM2IAAgQ5701bAwQAAAAA","url":"http:\/\/weixin.qq.com\/q\/02poULM783cNm10000003b"}
			String ticketStr = HttpClientUtils.doPost(url, param);
			JSONObject ticketObj = JSONObject.fromObject(ticketStr);
			String ticket = ticketObj.getString("ticket");
			String paramUrl="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket+"";
			return paramUrl;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	/**
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request)throws Exception{
		
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(user.getUserName(), user.getPassword());
		try{
			subject.login(token); // 登录验证
			return "redirect:/main.jsp";
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("user", user);
			request.setAttribute("errorMsg", "用户名或密码错误");
			return "login";
		}
	}
	
	@RequestMapping("/isExistName")
	public void isExistName(String name,HttpServletResponse response)throws Exception{
		User resultUser=userService.getByUserName(name);
		if(resultUser==null){
			ResponseUtil.write(response,true);
		}else{
			ResponseUtil.write(response,false);
		}
	}
	/**
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(User user,HttpServletResponse response)throws Exception{
		int resultTotal=0;
		if(user.getId()==null){
			resultTotal=userService.add(user);
		}else{
			resultTotal=userService.update(user);
		}
		JSONObject result=new JSONObject();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			userService.delete(Integer.parseInt(idsStr[i]));
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifyPassword")
	public String modifyPassword(Integer id,String newPassword,HttpServletResponse response)throws Exception{
		User user=new User();
		user.setId(id);
		user.setPassword(newPassword);
		int resultTotal=userService.update(user);
		JSONObject result=new JSONObject();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session)throws Exception{
		SecurityUtils.getSubject().logout();
		return "redirect:/login.jsp";
	}
}
