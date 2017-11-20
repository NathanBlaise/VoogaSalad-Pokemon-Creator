package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import util.FilePathConverter;
/**
 * This tests file IO operation
 * XML parser part adopted from https://www.tutorialspoint.com/java_xml/java_dom_query_document.htm
 * @author Dan Sun
 *
 */
public class PokemonSpecieXMLReaderTest {
    private String relativePath = "/src/resources/" + "defaultPokemonSpecies/Pikachu.xml";
    private Document doc;

    @Test
    public void test() throws ParserConfigurationException, SAXException, IOException {
	//	System.out.println("Present Project Directory : "+ System.getProperty("user.dir")); //adopted from https://stackoverflow.com/questions/13011892/how-to-locate-the-path-of-the-current-project-in-java-eclipse
	String inputFilePath = FilePathConverter.getAbsolutePath(relativePath);
	File inputFile = new File(inputFilePath);
	assertTrue(inputFile.isFile());
	System.out.println(inputFile.toString());
	System.out.println(inputFile.getAbsolutePath());
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	doc = dBuilder.parse(inputFile);
	Element rootNode = doc.getDocumentElement();
	rootNode.normalize();
	System.out.print("Root element: ");
	System.out.println(rootNode.getNodeName());
	parseAndPrintElement(rootNode, 1);
//	parseRoot(rootNode);
	assertTrue(inputFile!=null);
    }
//    private void parseRoot(Element rootNode) {
//	Node currentChild = rootNode.getFirstChild();
//	while(currentChild.getNodeType() != Node.ELEMENT_NODE)
//	    currentChild = currentChild.getNextSibling();
//	System.out.println(currentChild.getNodeName());
//	Element e = rootNode.get
//
//    }
    private void parseAndPrintElement(Element element, int numberOfTabs) {
	NodeList children = element.getChildNodes();
	if(!hasChildElements(element)) {
	    String nodeName = element.getNodeName();
	    String content = element.getTextContent();
	    String printed = nodeName + " : " + content + "\n";
	    addTabAndPrint(numberOfTabs, printed);
	    return;
	}
	addTabAndPrint(numberOfTabs, element.getNodeName() + " :\n");
	int nextLevelTabs = numberOfTabs+1;
	for(int i = 0; i < children.getLength(); i++) {
	    Node currentNode = children.item(i);
	    if(currentNode.getNodeType() == Node.ELEMENT_NODE) {
		Element childElement = (Element) currentNode;
		parseAndPrintElement(childElement,nextLevelTabs);
	    }

	}

    }




    private void addTabAndPrint(int numberOfTabs, String content) {
	for(int i = 0; i < numberOfTabs; i++) {
	    System.out.print("\t");
	}
	System.out.print(content);

    }

    public static boolean hasChildElements(Node el) {
	NodeList children = el.getChildNodes();
	for (int i = 0;i < children.getLength();i++) {
	    if (children.item(i).getNodeType() == Node.ELEMENT_NODE) 
		return true;
	}
	return false;
    }
}