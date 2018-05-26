package com.ctf.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class WordUtil {
	
	public static File createDoc(Map<?, ?> dataMap, String type) {
		//freemarker配置文件类
		Configuration configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		configuration.setClassForTemplateLoading(WordUtil.class, "/com/ctf/ftl");
		Map<String, Template> allTemplates = new HashMap<>();	
		try {
			allTemplates.put(type, configuration.getTemplate("word.ftl"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = "temp" + (int) (Math.random() * 100000) + ".doc";
		File f = new File(name);
		Template t = allTemplates.get(type);
		try {
			Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
			t.process(dataMap, w);
			w.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return f;
	}

}
