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
/**
 * This tests file IO operation
 * XML parser part adopted from https://www.tutorialspoint.com/java_xml/java_dom_query_document.htm
 * @author Dan Sun
 *
 */
public class ReadFileTest {

    @Test
    public void test() throws ParserConfigurationException, SAXException, IOException {
//	System.out.println("Present Project Directory : "+ System.getProperty("user.dir")); //adopted from https://stackoverflow.com/questions/13011892/how-to-locate-the-path-of-the-current-project-in-java-eclipse
	String projectDir = System.getProperty("user.dir");
	File inputFile = new File(projectDir + "/src/resources/defaultPokemonSpecies.xml");
	assertTrue(inputFile.isFile());
	System.out.println(inputFile.toString());
	System.out.println(inputFile.getAbsolutePath());
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(inputFile);
	doc.getDocumentElement().normalize();
	System.out.print("Root element: ");
	System.out.println(doc.getDocumentElement().getNodeName());
	NodeList nList = doc.getElementsByTagName("specie");
	System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {
	    Node nNode = nList.item(temp);
	    System.out.print("\nCurrent Element :");
	    System.out.println(nNode.getNodeName());

	    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		Element eElement = (Element) nNode;
		System.out.print("name : ");
		System.out.println(eElement.getElementsByTagName("name").item(0).getTextContent());
		System.out.print("elemental : ");
		System.out.println(eElement.getElementsByTagName("elemental").item(0).getTextContent());
	    }
	}
	assertTrue(inputFile!=null);
    } 




}
