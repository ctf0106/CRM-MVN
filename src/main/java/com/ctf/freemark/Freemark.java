package com.ctf.freemark;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import sun.misc.BASE64Encoder;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * ʹ��freemark����word
 * @author stormwy
 *
 */
public class Freemark {
	
	public static void main(String[] args){
		Freemark freemark = new Freemark("template/");
//		freemark.setTemplateName("wordTemplate.ftl");
		freemark.setTemplateName("����-����ʦ.ftl");
		freemark.setFileName("doc_"+new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date())+".doc");
		freemark.setFilePath("D:\\");
		freemark.createWord();
	}
	
	private void createWord(){
		
		Template t = null;
		try {
			t = configuration.getTemplate(templateName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File outFile = new File(filePath+fileName);
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		
		map.put("NAME", "������");
		map.put("image", getImageStr());
		
		map.put("SEX", "��");
		map.put("BIRTH", "1987-08");
		map.put("ZZMM", "��Ա");
		map.put("MZ", "��");
		map.put("JG", "�ӱ�ʡ");
		map.put("JKZK", "����");
		map.put("SG", "173cm");
		map.put("HYZK", "�ѻ�");
		map.put("XL", "����");
		map.put("BYYX", "�ӱ���ҵ��ѧ");
		map.put("ZY", "�������");
		map.put("ZP", "��Ƭ//todo");
		map.put("QZYX", "���������н��1w����");
		map.put("JYSH1", "06-09-01~10-06-20");
		map.put("JYYZ1", "�ӱ���ҵ��ѧ �������");
		map.put("JYXW1", "ѧʿѧλ");
		map.put("JYKC1", "������̡�΢���֡�������̹������ݿ�ԭ��ȵ�");
		
		map.put("JYSH2", "10-07-01~����");
		map.put("JYYZ2", "ABCDE��ѧ");
		map.put("JYXW2", "XYZѧλ");
		map.put("JYKC2", "POI�γ�");
		
		map.put("ZYJN", "����������������");
		
		map.put("GZSH1", "10-07-01~11-12-09");
		map.put("GZDZ1", "�������");
		map.put("GZGY1", "�����������ʦ");
		
		map.put("GZSH2", "11-12-15~14-4-05");
		map.put("GZDZ2", "��������");
		map.put("GZGY2", "�߼��������ʦ");
		
		map.put("JLQK", "�����Ƚ�ѧ�𡢸�ʿͨ��ѧ���");
		map.put("ZWPJ", "��Ȥ�ḻ��������ǿ�����о�����");
		
		map.put("DH", "0312-3132098");
		map.put("SJ", "15033768387");
		map.put("YJ", "hanmanyifengyi@163.com");
		map.put("DZ", "�ӱ�ʡ������");
		map.put("YB", "071000");
		
		try {
			t.process(map, out);
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * freemark��ʼ��
	 * @param templatePath ģ���ļ�λ��
	 */
	public Freemark(String templatePath) {
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		configuration.setClassForTemplateLoading(this.getClass(),templatePath);		
	}	
	
	private String getImageStr() {
        String imgFile = "D:/111.png";
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
	
	/**
	 * freemarkģ������
	 */
	private Configuration configuration;
	/**
	 * freemarkģ�������
	 */
	private String templateName;
	/**
	 * �����ļ���
	 */
	private String fileName;
	/**
	 * �����ļ�·��
	 */
	private String filePath;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
}
