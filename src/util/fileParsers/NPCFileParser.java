package util.fileParsers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import util.pokemonSpecieDataParser.LeafElementParser.ImagePathParser;
import util.pokemonSpecieDataParser.LeafElementParser.LeafElementParser;
import util.pokemonSpecieDataParser.LeafElementParser.NameParser;
import data.model.NPC;
import data.model.PacmanEnemy;

/**
 * This class is able to parse a npc xml file
 * and create the corresponding npc class
 * @author cy122 Dan Sun
 * 
 *
 */
public class NPCFileParser extends XMLFileParserAbstract{
    //fields needed for NPC
    private String name;
    private String imagePath;
    
    /**
     * Gets the NPC from the File given
     * @param file The xml file specifying a pokemon specie 
     * @return The NPC Object described by the file
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public NPC parseFile(File file) 
	    throws ParserConfigurationException, 
	    SAXException, IOException {
	super.fillDataFromFileIntoFields(file);
	return new NPC(imagePath,name);
    }

    /**
     * Gets the NPC from the File path given
     * @param filePath the absolute path of the file
     * @return The NPC object described by the file
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public NPC parseFile(String filePath) throws ParserConfigurationException, SAXException, IOException {
	File file = new File(filePath);
	//TODO: do this later to check for file not existing and throw an exception
	//if (!file.exists()) throws Exception;
	return this.parseFile(file);
    }
    
    @Override
    protected void reinitializeObjectData() {
	name = "";
	imagePath = "";
    }
    @Override
    protected void parseData() {
	name = NameParser.parse(rootNode);
	imagePath = ImagePathParser.parse(rootNode);
    }
}
