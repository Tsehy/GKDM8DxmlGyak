package hu.domparse.gkdm8d;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//A beolvasott xml fájban megkeresi az adott node-ot, és új értéket ad meg neki
public class DOMModifyGKDM8D
{
	public static void main(String[] args)
	{
		String parentName = "card";
		String nodeName = "cardName";
		String newValue = "Infuriate";
		String id = "02";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
		try
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			try
			{
				try
				{
					File xmlFile = new File("XMLGKDM8D.xml");
					Document document = builder.parse(xmlFile);
					Element rootNode = document.getDocumentElement();
					searchParentNode(rootNode, parentName, nodeName, id, newValue);
					modifyDocument(document, xmlFile);
					
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
	
	//Szülő megkeresése
	public static void searchParentNode(Node rootNode, String parentName, String nodeName, String id, String newValue)
	{
		if(rootNode.getNodeType() == Node.ELEMENT_NODE)
		{
			if((rootNode.getNodeName().equals(parentName)))
			{
				NamedNodeMap attributeList = rootNode.getAttributes();
				if(attributeList.item(0).getNodeValue().equals(id))
				{
					searchNode(rootNode, nodeName, newValue);
				}
			}
		}
		NodeList nodeList = rootNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
			searchParentNode(nodeList.item(i), parentName, nodeName, id, newValue);
	}
	
	//node megkeresése és adatcsere
	public static void searchNode(Node rootNode, String nodeName, String newValue)
	{
		if(rootNode.getNodeType() == Node.ELEMENT_NODE)
		{
			if((rootNode.getNodeName().equals(nodeName)))
			{
				rootNode.setTextContent(newValue);
			}
		}
		NodeList nodeList = rootNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
			searchNode(nodeList.item(i), nodeName, newValue);
	}
	
	//dokumentum módosító függvény
	public static void modifyDocument(Document document, File xmlFile)
	{
		try
		{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(xmlFile);

		transformer.transform(source, new StreamResult(System.out));
		transformer.transform(source, result);
		}
		catch(TransformerException e)
		{
			e.printStackTrace();
		}
	}
}