package util.pokemonSpecieDataParser.ListOfElementsParser;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import data.model.moves.Move;
import util.FilePathConverter;
import util.PokemonMovesFactory;
import util.pokemonSpecieDataParser.LeafElementParser.LevelParser;
import util.pokemonSpecieDataParser.LeafElementParser.NameParser;
/**
 * This class parses pokemon level moves specified 
 * @author Dan Sun
 *
 */
public class PokemonLevelMovesParser extends ListOfElementsParserAbstract{
    private static String levelMovesTag = "levelMoves";
    private static String moveTag = "move";
    private static String defaultLookupPath = "src/resources/MovesLookup.properties";
    
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
	lookupPath = FilePathConverter.getAbsolutePath(path);
	movesFactory = new PokemonMovesFactory(lookupPath);
	
    }
    /**
     * This method parses the document element and gets all the moves for this pokemon
     * @param rootNode the document element of the pokemon species xml file 
     * @return A Map<Integer,Move> that specifies the moves of this pokemon
     */
    public Map<Integer,Move> parse(Element rootNode){
	Map<Integer, Move> movesMap = new HashMap<>();
	Element levelMoves = getListRootElement(rootNode,levelMovesTag);//getLevelMovesElement(rootNode);
	NodeList allMoves = getAllElementsInList(levelMoves,moveTag);//getAllMoves(levelMoves);
	addAllMovesToMap(allMoves,movesMap);
	return movesMap;
    }
    
    private void addAllMovesToMap(NodeList allMoves, Map<Integer, Move> movesMap) {
	for(int i = 0; i < allMoves.getLength(); i++) {
	    parseAndAddMoveElementToMap(checkAndConvertNodeToElement(allMoves.item(i)),
		    movesMap);
	}
	
    }

    private void parseAndAddMoveElementToMap(Element currentMoveElement, Map<Integer, Move> movesMap) {
	Integer level = LevelParser.parse(currentMoveElement);
	String moveName = NameParser.parse(currentMoveElement);
	Move move = null;
	try {
	    move = movesFactory.getMove(moveName);
	} catch (Exception e) {
	    System.out.println("Failed to get the correct move!");
	    e.printStackTrace(); //handled by exiting the program
	    java.lang.System.exit(1);
	}
	assert(move != null);
	movesMap.put(level, move);	
    }
}
