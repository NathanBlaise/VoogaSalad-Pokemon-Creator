package util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import util.pokemonSpecieDataParser.LeafElementParser.LeafElementParser;
import util.pokemonSpecieDataParser.LeafElementParser.NameParser;
import data.model.NPC;

/**
 * This class is able to parse a npc xml file
 * and create the corresponding npc class
 * @author cy122 Dan Sun
 * 
 *
 */
public class NPCFileParser {
	private String name;
	private String imagePath;
    //file for this parser
    private File xmlFile;
    //root node of the xml file
    private Element rootNode;
    /**
     * Gets the PokemonSpecie from the File given
     * @param file The xml file specifying a pokemon specie 
     * @return The Pokemon Specie Class described by the file
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public NPC parseFile(File file) 
	    throws ParserConfigurationException, 
	    SAXException, IOException {
		xmlFile = file;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		rootNode = doc.getDocumentElement();
		rootNode.normalize();
		reinitializeNPCData();
		parseNPCData();
		//read from the xml file and fill in the necessary fields 
		//needed by the constructor of pokemonSpecies
		return new NPC(imagePath,name);
    }
    
    private void reinitializeNPCData() {
	    name = "";
	    imagePath = "";
    }
    
    private void parseNPCData() {
		name = NameParser.parse(rootNode);
		imagePath = ImagePathParser.parse(rootNode);
    }
    
    private static class ImagePathParser extends LeafElementParser {
        private static String imagePathTag = "imagePath";
        /**
         * 
         * @param rootNode
         * @return image path of the NPC, such as images/captainMap.png
         */
        public static String parse(Element rootNode) {
        	String content = parseContent(rootNode, imagePathTag);
        	return content;
        }
    }
}
