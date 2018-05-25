package com.ctf.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ctf.entity.Company;
import com.ctf.entity.Customer;
import com.ctf.entity.CustomerVo;
import com.ctf.entity.PageBean;
import com.ctf.service.CompanyService;
import com.ctf.service.CustomerService;
import com.ctf.util.DateUtil;
import com.ctf.util.ResponseUtil;
import com.ctf.util.StringUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import sun.misc.BASE64Encoder;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Resource
	private CustomerService customerService;
	
	@Resource
	private CompanyService companyService;
	
	
	/**
	 * @param page
	 * @param rows
	 * @param s_customer
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,Customer s_customer,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("khno", StringUtil.formatLike(s_customer.getKhno()));
		map.put("name", StringUtil.formatLike(s_customer.getName()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Customer> customerList=customerService.find(map);
		Long total=customerService.getTotal(map);
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(customerList);
		JSONArray updateJSON=new JSONArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Integer companyID = jsonObject.getInt("companyID");
			if(companyID!=null && companyID!=0){
				Company company = companyService.findById(companyID);
				jsonObject.put("companyName", company.getName());
			}else{
				jsonObject.put("companyName", null);
			}
			
			updateJSON.add(jsonObject);
		}
		result.put("rows", updateJSON);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(Customer customer,HttpServletResponse response,HttpServletRequest request)throws Exception{
		int resultTotal=0; 
		if(customer.getId()==null){
			customer.setKhno("KH"+DateUtil.getCurrentDateStr()); 
			customer.setGmt_create(DateUtil.getCurrentDate());
			customer.setGmt_modified(DateUtil.getCurrentDate());
			resultTotal=customerService.add(customer);
		}else{
			resultTotal=customerService.update(customer);
		}
		String qrCode = this.createQrCode(customer.getKhno(), BarcodeFormat.QR_CODE,request, response);
		Customer updateObj=new Customer();
		updateObj.setQrcode(qrCode);
		updateObj.setId(customer.getId());
		/**
		 * 更新二维码
		 */
		customerService.update(updateObj);
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
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			customerService.delete(Integer.parseInt(idsStr[i]));
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findById")
	public String findById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		Customer customer=customerService.findById(Integer.parseInt(id));
		JSONObject jsonObject=JSONObject.fromObject(customer);
		ResponseUtil.write(response, jsonObject);
		return null;
	}
	
	
	@RequestMapping("/findByKhno")
	public String findByKhno(@RequestParam(value="khno")String khno,HttpServletResponse response)throws Exception{
		CustomerVo customerVo=customerService.findByKhno(khno);
		JSONObject jsonObject=JSONObject.fromObject(customerVo);
		ResponseUtil.write(response, jsonObject);
		return null;
	}
	
	public String createQrCode(String khno,BarcodeFormat codeFormat,HttpServletRequest request,HttpServletResponse response)throws Exception{
		if(!khno.isEmpty()){
			String filePath=request.getServletContext().getRealPath("/");
			String qrCodeName=khno;
			int width = 300;  
	        int height = 300;  
	        String format = "png";  
	        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
	        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
	        BitMatrix bitMatrix = null;  
	        try {  
	            bitMatrix = new MultiFormatWriter().encode(khno, codeFormat, width, height, hints);  
	        } catch (WriterException e) {  
	            e.printStackTrace();  
	        }  
	        File outputFile = new File(filePath+"static/qrCodeImage/"+qrCodeName+"."+format);
	        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
	        //读取二维码
	        OutputStream os1 = new FileOutputStream(filePath+"static/qrCodeImage/"+qrCodeName+"."+format);  
	        MatrixToImageWriter.writeToStream(bitMatrix, format, os1);  
	        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);  
	        ByteArrayOutputStream os = new ByteArrayOutputStream();
	        ImageIO.write(image, format, os);
	        byte b[] = os.toByteArray();
	        String str = new BASE64Encoder().encode(b);  
	        return "data:image/gif;base64,"+str;	
		}
		return null;
	}
}
