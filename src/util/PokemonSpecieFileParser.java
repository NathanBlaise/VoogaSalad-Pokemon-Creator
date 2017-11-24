package util;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import data.model.PokemonSpecie;
import data.model.PokemonStat;
import data.model.moves.Move;
import util.pokemonSpecieDataParser.LeafElementParser.ElementalParser;
import util.pokemonSpecieDataParser.LeafElementParser.MaxLevelParser;
import util.pokemonSpecieDataParser.LeafElementParser.NameParser;
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
    private Map<Integer,Double> levelExp; 
    private Map<Integer,String> levelEvolutionImagePath; 
    //file for this parser
    private File xmlFile;
    //root node of the xml file
    private Element rootNode;
    private PokemonLevelMovesParser levelMovesParser;
    /**
     * Gets the level moves specified in the xml file
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
		levelMoves,levelStats,levelExp,levelEvolutionImagePath);
    }
    
    private void parsePokemonData() {
	specieName = NameParser.parse(rootNode);
	elemental = ElementalParser.parse(rootNode);
	maxLevel = MaxLevelParser.parse(rootNode);
	levelMoves = levelMovesParser.parse(rootNode);
	levelStats = PokemonLevelStatsParser.parse(rootNode);

	
    }

    private void reinitializePokemonData() {
	specieName = "";
	elemental = "";
	maxLevel = -1;
	levelMoves = new HashMap<>();
	levelStats = new HashMap<>();
	levelExp = new HashMap<>();
	levelEvolutionImagePath = new HashMap<>();
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
