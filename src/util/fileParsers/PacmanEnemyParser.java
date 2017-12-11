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
import util.pokemonSpecieDataParser.LeafElementParser.SpeedParser;
import data.model.NPC;
import data.model.PacmanEnemy;
import data.model.PokemonSpecie;

/**
 * This class is able to parse a Pacman Enemy xml file
 * and create the corresponding PacmanEnemy class
 * @author cy122 Dan Sun
 * 
 *
 */
public class PacmanEnemyParser extends XMLFileParserAbstract{
    //fields needed for PacmanEnemy
    private String imagePath;
    private int speed;

    /**
     * Gets the PacmanEnemy from the File given
     * @param file The xml file specifying a pokemon specie 
     * @return The PacmanEnemy object described by the file
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public PacmanEnemy parseFile(File file) 
	    throws ParserConfigurationException, 
	    SAXException, IOException {
	super.fillDataFromFileIntoFields(file);
	return new PacmanEnemy(imagePath,speed);
    }

    /**
     * Gets the PacmanEnemy from the File path given
     * @param filePath the absolute path of the file
     * @return The PacmanEnemy object described by the file
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public PacmanEnemy parseFile(String filePath) throws ParserConfigurationException, SAXException, IOException {
	File file = new File(filePath);
	//TODO: do this later to check for file not existing and throw an exception
	//if (!file.exists()) throws Exception;
	return this.parseFile(file);
    }

    @Override
    protected void reinitializeObjectData() {
	speed = 1;
	imagePath = "";
    }
    @Override
    protected void parseData() {
	speed = (int)SpeedParser.parse(rootNode);//NameParser.parse(rootNode);
	imagePath = ImagePathParser.parse(rootNode);
    }
}
