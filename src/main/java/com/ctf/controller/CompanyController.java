package com.ctf.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctf.entity.Company;
import com.ctf.entity.PageBean;
import com.ctf.service.CompanyService;
import com.ctf.util.ResponseUtil;
import com.ctf.util.StringUtil;
import com.ctf.util.WordGenerator;
import com.ctf.util.WordUtil;

import freemarker.template.Template;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * �ͻ�Controller��
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/company")
public class CompanyController {
	
	@Resource
	private CompanyService companyService;
	
	
	/**
	 * ��ҳ������ѯ�ͻ�
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
	 * ��ӻ����޸Ŀͻ�
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(Company company,HttpServletResponse response)throws Exception{
		int resultTotal=0; // �����ļ�¼����
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
	 * ɾ���ͻ�
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
	 * ͨ��ID����ʵ��
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
	
	@RequestMapping("/comboList")
	public void comboList(HttpServletResponse response)throws Exception{
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("name", "请选择....");
		jsonArray.add(jsonObject);
		List<Company> companyList=companyService.findAllList();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{" "});
		JSONArray rows=JSONArray.fromObject(companyList, jsonConfig);
		jsonArray.addAll(rows);
		ResponseUtil.write(response, jsonArray);
	}
	
	
	@RequestMapping("/export")
	public void export(HttpServletResponse response,HttpServletRequest request)throws Exception{
		
		request.setCharacterEncoding("utf-8");  
        Map<String, Object> map = new HashMap<String, Object>();  
//        Enumeration<String> paramNames = request.getParameterNames();  
//        while(paramNames.hasMoreElements()) {  
//            String key = paramNames.nextElement();  
//            String value = request.getParameter(key);  
//            map.put(key, value);  
//        }  
        map.put("title","标题" );  
        File file = null;  
        InputStream fin = null;  
        ServletOutputStream out = null;  
        try {  
            file = WordGenerator.createDoc(map, "resume");  
            fin = new FileInputStream(file);  
            response.setCharacterEncoding("utf-8");  
            response.setContentType("application/msword");  
            response.addHeader("Content-Disposition", "attachment;filename=resume.doc");  
            out = response.getOutputStream();  
            byte[] buffer = new byte[512]; 
            int bytesToRead = -1;  
            while((bytesToRead = fin.read(buffer)) != -1) {  
                out.write(buffer, 0, bytesToRead);  
            }  
        } finally {  
            if(fin != null) fin.close();  
            if(out != null) out.close();  
            if(file != null) file.delete(); 
        }  
    } 
	
}
