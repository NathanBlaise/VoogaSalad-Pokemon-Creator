package util.pokemonSpecieDataParser;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import data.database.moves.Move;
import util.PokemonMovesFactory;

public class PokemonLevelMovesParser {
    private static String levelMovesTag = "LevelMoves";
    private static String moveTag = "move";
    private static String defaultLookupPath = "";
    
    private String lookupPath;
    private PokemonMovesFactory movesFactory;
    
    public PokemonLevelMovesParser() {
	this(defaultLookupPath);
    }
    
    /**
     * 
     * @param path The path to use for the file to
     * look up for reflection
     */
    public PokemonLevelMovesParser(String path) {
	lookupPath = path;
	movesFactory = new PokemonMovesFactory(path);
	
    }
    
    public Map<Integer,Move> parse(Element rootNode){
	Map<Integer, Move> movesMap = new HashMap<>();
	Element levelMoves = getLevelMovesElement(rootNode);
	NodeList allMoves = getAllMoves(levelMoves);
	addAllMovesToMap(allMoves,movesMap);
	return movesMap;
    }
    
    private void addAllMovesToMap(NodeList allMoves, Map<Integer, Move> movesMap) {
	for(int i = 0; i < allMoves.getLength(); i++) {
	    Node currentMoveNode = allMoves.item(i);
	    assert(currentMoveNode.getNodeType() == Node.ELEMENT_NODE);
	    Element currentMoveElement = (Element)currentMoveNode;
	    parseAndAddMoveElementToMap(currentMoveElement,movesMap);
	}
	
    }

    private void parseAndAddMoveElementToMap(Element currentMoveElement, Map<Integer, Move> movesMap) {
	Integer level = LevelParser.parse(currentMoveElement);
	String moveName = NameParser.parse(currentMoveElement);
	Move move = null;
	try {
	    move = movesFactory.getMove(moveName);
	} catch (Exception e) {
	    // TODO add additional steps to handle exception
	    System.out.println("Failed to get the correct move!");
	    e.printStackTrace();
	    java.lang.System.exit(1);
	}
	assert(move != null);
	movesMap.put(level, move);	
    }

    private NodeList getAllMoves(Element levelMoves) {
	return levelMoves.getElementsByTagName(moveTag);
    }

    private Element getLevelMovesElement(Element rootNode) {
	NodeList levelMoves = rootNode.getElementsByTagName(levelMovesTag);
	Element levelMovesElement = (Element)levelMoves.item(0);
	return levelMovesElement;
    }
}
