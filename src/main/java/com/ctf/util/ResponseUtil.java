package com.ctf.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import freemarker.template.Template;


public class ResponseUtil {

	public static void write(HttpServletResponse response,Object o)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}

	public static void export(HttpServletResponse response, Template freemark, String fileName) throws IOException {
		response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"iso8859-1"));
		response.setContentType("application/ynd.ms-word;charset=UTF-8");
		OutputStream out=response.getOutputStream();
		out.flush();
		out.close();
		
	}
}
