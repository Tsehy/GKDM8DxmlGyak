import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

public class XpathParseGKDM8D {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("studentGKDM8D.xml");

        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();

        XPathExpression firstNameExpr = xpath.compile("//student/firstname/text()");
        XPathExpression lastNameExpr = xpath.compile("//student/lastname/text()");
        XPathExpression nickNameExpr = xpath.compile("//student/nickname/text()");
        XPathExpression marksExpr = xpath.compile("//student/marks/text()");

        Object firstNameResult = firstNameExpr.evaluate(doc, XPathConstants.NODESET);
        Object lastNameResult = lastNameExpr.evaluate(doc, XPathConstants.NODESET);
        Object nickNameResult = nickNameExpr.evaluate(doc, XPathConstants.NODESET);
        Object marksResult = marksExpr.evaluate(doc, XPathConstants.NODESET);

        NodeList firstNameNodes = (NodeList) firstNameResult;
        NodeList lastNameNodes = (NodeList) lastNameResult;
        NodeList nickNodes = (NodeList) nickNameResult;
        NodeList marksNodes = (NodeList) marksResult;
        System.out.println( "-------------------------------");

        for (int i = 0; i < firstNameNodes.getLength(); i++) {
            System.out.println( firstNameNodes.item(i).getNodeValue());
            System.out.println(lastNameNodes.item(i).getNodeValue());
            System.out.println( nickNodes.item(i).getNodeValue());
            System.out.println( marksNodes.item(i).getNodeValue());
            System.out.println( "-------------------------------");

        }
    }
}