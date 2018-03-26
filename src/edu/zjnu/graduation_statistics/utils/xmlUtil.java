package edu.zjnu.graduation_statistics.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.xml.xpath.XPath;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import edu.zjnu.graduation_statistics.domain.Professional;

public class xmlUtil {
	public static String parseAtrById(String xmlPath,String nodeType,String id) throws DocumentException {
		String path = "/edu/zjnu/graduation_statistics/resource/";
		InputStream input = xmlUtil.class.getResourceAsStream(path+xmlPath);
		// 创建解析器
		SAXReader saxReader = new SAXReader();
		// 得到docuemnt
		Document document = saxReader.read(input);
		List<Node> nodeLists = document.selectNodes("//"+nodeType+"/value[@id='"+id+"']");
		String name ="";
		for (Node node : nodeLists) {
			Element element = (Element) node;
			Attribute nameAtr = element.attribute("name");
			name = nameAtr.getText();
		}
		return name;
	}
	
	public static List<Professional> parseAtrById(String xmlPath,String nodeType) throws DocumentException {
		String path = "/edu/zjnu/graduation_statistics/resource/";
		InputStream input = xmlUtil.class.getResourceAsStream(path+xmlPath);
		// 创建解析器
		SAXReader saxReader = new SAXReader();
		// 得到docuemnt
		Document document = saxReader.read(input);
		List<Node> nodeLists = document.selectNodes("//"+nodeType+"/value");
		String name ="";
		String id= "";
		List<Professional> professionalLists = new ArrayList<Professional>();
		for (Node node : nodeLists) {
			Professional professional = new Professional();
			Element element = (Element) node;
			Attribute nameAtr = element.attribute("name");
			Attribute idAtr = element.attribute("id");
			id = idAtr.getText();
			name = nameAtr.getText();
			professional.setP_Type(id);
			professional.setTypeName(name);
			professional.setP_Level(id);
			professional.setLevelName(name);
			professionalLists.add(professional);
		}
		return professionalLists;
	}
}
