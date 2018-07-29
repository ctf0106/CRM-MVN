package com.ctf.wx.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ctf.entity.TextMessage;
import com.thoughtworks.xstream.XStream;

public class XmlUtil {
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException{
		HashMap<String, String> map = new HashMap<String,String>();
		SAXReader reader = new SAXReader();
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Document doc = null;
		try {
			doc = reader.read(inputStream);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
 
		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> list = (List<Element>)root.elements();
 
		for(Element e:list){
			map.put(e.getName(), e.getText());
		}
		inputStream.close();
		return map;
	}
	public static String textMsgToxml(TextMessage textMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	
}
