package com.xml.parse.laptop;

import com.xml.parse.GetXMLDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class ParseLaptopXml {
    public ParseLaptopXml(){
        //executeLaptop();
    }

    public static List<Laptop> executeLaptop(){
        Document document = GetXMLDocument.getDocument("laptops.xml");
        NodeList nodeLists = GetXMLDocument.getRootNodeLists(document, "laptop");
        List<Laptop> laptops = new ArrayList<>();

        for (int i = 0; i < nodeLists.getLength(); i++) {
            Laptop laptop = new Laptop();
            Node node = nodeLists.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println(element.getAttribute("name"));
                Laptop.setField("name",element.getAttribute("name"),laptop);

                NodeList nodeListDetails = GetXMLDocument.getChildNodes(element);
                for (int j = 0; j < nodeListDetails.getLength(); j++) {
                    Node details = nodeListDetails.item(j);
                    if (details.getNodeType() == Node.ELEMENT_NODE) {
                        Element detailElement = (Element) details;
                        //System.out.println("   " + detailElement.getTagName() + " :" + detailElement.getAttribute("value"));
                        Laptop.setField(detailElement.getTagName(),detailElement.getAttribute("value"),laptop);
                    }
                }
            }
            laptops.add(laptop);
        }
        System.out.println(laptops);
        return  laptops;
    }

}
