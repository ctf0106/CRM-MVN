package com.ctf.util;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import sun.misc.BASE64Encoder;



public class ChartUtil {
	
	public static String createChart(HttpServletRequest request,DefaultCategoryDataset dataset,Integer id,String title,String xName,String yName) throws Exception {
		//创建主题样式  
       StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
       //设置标题字体  
       standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));  
       //设置图例的字体  
       standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));  
       //设置轴向的字体  
       standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));  
       //应用主题样式  
       ChartFactory.setChartTheme(standardChartTheme);  
		JFreeChart chart=ChartFactory.createBarChart3D("员工基金统计图",xName, yName, dataset,
				PlotOrientation.VERTICAL, true, true, true);
		 String saveAsFile = saveAsFile(chart,id,500, 300,request); 
		 return saveAsFile;
	}
	
	
	
	public static String saveAsFile(JFreeChart chart,Integer id,int weight, int height,HttpServletRequest request) throws Exception {
		String filePath=request.getServletContext().getRealPath("/");
        FileOutputStream out = null;  
        try {  
            String currentDate = DateUtil.getCurrentDateStr();
            out = new FileOutputStream(filePath+"static/chart/"+currentDate+"_"+id+".png");  
            // 保存为PNG文件  
            ChartUtilities.writeChartAsPNG(out, chart, 600, 350);  
            // 保存为JPEG文件  
            //ChartUtilities.writeChartAsJPEG(out, chart, 500, 400);  
            out.flush();  
            
            InputStream inputStream = null;
            byte[] data = null;
            try {
                inputStream = new FileInputStream(filePath+"static/chart/"+currentDate+"_"+id+".png");
                data = new byte[inputStream.available()];
                inputStream.read(data);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 加密
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(data).toString();
            
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (out != null) {  
                try {  
                    out.close();  
                } catch (IOException e) {  
                }  
            }  
        }  
        return null;
    }  
	
	
}
