package com.xml.parse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class GetXMLDocument {

	public static Document getDocument(String fileName) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			Document document = documentBuilder.parse(new File(fileName));
			document.getDocumentElement().normalize();
			return document;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static NodeList getRootNodeLists(Document document, String ElementTagName){
		return document.getElementsByTagName(ElementTagName);
	}

	public static NodeList getChildNodes(Element element){
		return element.getChildNodes();
	}
}
