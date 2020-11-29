package hu.domparse.gkdm8d;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//Az adott xml dokumentumot beolvassa, és kiírja a console-ba
public class DOMReadGKDM8D
{
	public static void main(String[] args)
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
		try
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			try
			{
				try
				{
					Document document = builder.parse("XMLGKDM8D.xml");
					Element rootNode = document.getDocumentElement();
					printNode(rootNode, "", rootNode.getNodeName());
				}
				catch(SAXException e)
				{
					e.printStackTrace();
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		catch(ParserConfigurationException e)
		{
			e.printStackTrace();
		}	
	}	

	//kiírató függvény
	public static void printNode(Node rootNode, String spacer, String rootName)
	{
		if(rootNode.getNodeType() == Node.ELEMENT_NODE)
		{
			if(rootNode.getParentNode().getNodeName().equals(rootName))
				System.out.println(spacer);
			System.out.print(spacer + rootNode.getNodeName());
			if(!(rootNode.getNodeName().equals(rootName)))
			{
				System.out.print(rootNode.hasAttributes()?" -":": ");
				System.out.println(rootNode.hasAttributes()?printAttributes(rootNode):rootNode.getTextContent());
			}
			else
				System.out.println();
		}
		NodeList nodeList = rootNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
			printNode(nodeList.item(i), spacer + "  |", rootName);
	}

	//attribútumokat lekérdező függvény
	public static String printAttributes(Node mainNode)
	{
		NamedNodeMap attributeList = mainNode.getAttributes();
        String attributes = " " + attributeList.item(0).getNodeName() + ":" + attributeList.item(0).getNodeValue();
        for(int i = 1; i < attributeList.getLength(); i++)
        {
            Node attribute = attributeList.item(i);
            attributes += (", " + attribute.getNodeName() + ":" + attribute.getNodeValue());
        }
        return attributes;
    }
}