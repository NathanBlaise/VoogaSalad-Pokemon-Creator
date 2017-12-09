package util;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import data.model.PokemonSpecie;
import data.model.PokemonStat;
import data.model.moves.Move;
import util.pokemonSpecieDataParser.LeafElementParser.ElementalParser;
import util.pokemonSpecieDataParser.LeafElementParser.MaxLevelParser;
import util.pokemonSpecieDataParser.LeafElementParser.NameParser;
//import util.pokemonSpecieDataParser.LeafElementParser.PokemonSpecieIndexParser;
import util.pokemonSpecieDataParser.ListOfElementsParser.PokemonLevelExpsParser;
import util.pokemonSpecieDataParser.ListOfElementsParser.PokemonLevelImagesParser;
import util.pokemonSpecieDataParser.ListOfElementsParser.PokemonLevelMovesParser;
import util.pokemonSpecieDataParser.ListOfElementsParser.PokemonLevelStatsParser;

/**
 * This class is able to parse a pokemon specie xml file
 * and create the corresponding species class
 * @author DanSun
 *
 */
public class PokemonSpecieFileParser {
    //data required to initialize a pokemonSpecie
    private String specieName;
    private String elemental;
    private int maxLevel;
    private Map<Integer,Move> levelMoves; 
    private Map<Integer,PokemonStat> levelStats; 
    private Map<Integer,Double> levelExps; 
    private Map<Integer,String> levelEvolutionImagePaths; 
    //file for this parser
    private File xmlFile;
    //root node of the xml file
    private Element rootNode;
    private PokemonLevelMovesParser levelMovesParser;
    /**
     * Gets the PokemonSpecie from the File given
     * @param file The xml file specifying a pokemon specie 
     * @return The Pokemon Specie Class described by the file
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public PokemonSpecie parseFile(File file) 
	    throws ParserConfigurationException, 
	    SAXException, IOException {
	xmlFile = file;
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(xmlFile);
	rootNode = doc.getDocumentElement();
	rootNode.normalize();
	reinitializePokemonData();
	parsePokemonData();
	//read from the xml file and fill in the necessary fields 
	//needed by the constructor of pokemonSpecies
	return new PokemonSpecie(specieName,elemental,maxLevel,
		levelMoves,levelStats,levelExps,levelEvolutionImagePaths);
    }
    
    /**
     * Gets the PokemonSpecie from the File path given
     * @param filePath the absolute path of the file
     * @return The pokemon specie described by the file
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public PokemonSpecie parseFile(String filePath) throws ParserConfigurationException, SAXException, IOException {
	File file = new File(filePath);
	//TODO: do this later to check for file not existing and throw an exception
	//if (!file.exists()) throws Exception;
	return this.parseFile(file);
    }
    
    
    
    private void parsePokemonData() {
	specieName = NameParser.parse(rootNode);
	elemental = ElementalParser.parse(rootNode);
	maxLevel = MaxLevelParser.parse(rootNode);
	levelMoves = levelMovesParser.parse(rootNode);
	levelStats = PokemonLevelStatsParser.parse(rootNode);
	levelExps = PokemonLevelExpsParser.parse(rootNode);
	levelEvolutionImagePaths = PokemonLevelImagesParser.parse(rootNode);
    }

    private void reinitializePokemonData() {
	specieName = "";
	elemental = "";
	maxLevel = -1;
	levelMoves = new HashMap<>();
	levelStats = new HashMap<>();
	levelExps = new HashMap<>();
	levelEvolutionImagePaths = new HashMap<>();
	levelMovesParser = new PokemonLevelMovesParser();
    }
}
