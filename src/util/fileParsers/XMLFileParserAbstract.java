package util.fileParsers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Abstract class for XML File parsers
 * Includes methods common to all XML File parsers 
 * used in this project 
 * @author Dan Sun
 *
 */
public abstract class XMLFileParserAbstract {
    //file for this parser
    protected File xmlFile;
    //root node of the xml file
    protected Element rootNode;
    
    /**
     * Fills data from xml File into specific fields in child classes
     * @throws IOException 
     * @throws SAXException 
     * @throws ParserConfigurationException 
     */
    protected void fillDataFromFileIntoFields(File file) 
	    throws ParserConfigurationException, SAXException, IOException {
	getRootElementFromFile(file);
	reinitializeObjectData();
	parseData();
    }
    
    /**
     * Gets the rootnode from the XML FIle
     * @param file The XML file to be parsed
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    protected void getRootElementFromFile(File file) throws ParserConfigurationException, SAXException, IOException {
	xmlFile = file;
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(xmlFile);
	rootNode = doc.getDocumentElement();
	rootNode.normalize();
    }
    
    /**
     * Reinitializes data needed for generating the object 
     * that is the target of a particular parser. (E.g. PokemonSpecie
     * for PokemonSpecieParser, NPC for NPCParser, etc.)
     */
    protected abstract void reinitializeObjectData();
    
    /**
     * Parse data from the fiel into specific fields in child classes
     */
    protected abstract void parseData();
}
