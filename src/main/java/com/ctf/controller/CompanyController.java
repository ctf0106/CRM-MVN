package com.ctf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ctf.entity.Company;
import com.ctf.entity.PageBean;
import com.ctf.service.CompanyService;
import com.ctf.util.DateUtil;
import com.ctf.util.ResponseUtil;
import com.ctf.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 客户Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/company")
public class CompanyController {
	
	@Resource
	private CompanyService companyService;
	
	
	/**
	 * 分页条件查询客户
	 * @param page
	 * @param rows
	 * @param s_company
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,Company s_company,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", StringUtil.formatLike(s_company.getName()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Company> companyList=companyService.find(map);
		Long total=companyService.getTotal(map);
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(companyList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 添加或者修改客户
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(Company company,HttpServletResponse response)throws Exception{
		int resultTotal=0; // 操作的记录条数
		if(company.getId()==null){
			resultTotal=companyService.add(company);
		}else{
			resultTotal=companyService.update(company);
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
	
	
	/**
	 * 删除客户
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			companyService.delete(Integer.parseInt(idsStr[i]));
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 通过ID查找实体
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findById")
	public String findById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		Company company=companyService.findById(Integer.parseInt(id));
		JSONObject jsonObject=JSONObject.fromObject(company);
		ResponseUtil.write(response, jsonObject);
		return null;
	}
	
}
