package util.pokemonSpecieDataParser.ListOfElementsParser;

import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import util.pokemonSpecieDataParser.LeafElementParser.LevelParser;
import util.pokemonSpecieDataParser.LeafElementParser.NeededExpParser;

/**
 * This class parses pokemon level experiences specified
 * Level experience refers to the experience that is required
 * for the pokemon to upgrade to a higher level.
 * For example, if level experience is 200 for level 1,
 * the pokemon upgrades to level 2 after it gets 200 experience 
 * @author Dan Sun
 *
 */
public class PokemonLevelExpsParser extends ListOfElementsParserAbstract{
    private static String levelExpsTag = "levelExps";
    private static String expTag = "exp";
    
    /**
     * This method parses the document element and gets all the level exps for this pokemon
     * @param rootNode the document element of the pokemon species xml file 
     * @return A Map<Integer,Double> that specifies the experience needed of this 
     * pokemon for every level to upgrade
     */
    public static Map<Integer, Double> parse(Element rootNode){
	Map<Integer, Double> expsMap = new HashMap<>();
	Element levelExps = getListRootElement(rootNode,levelExpsTag);
	NodeList allExps = getAllElementsInList(levelExps,expTag);
	addAllExpsToMap(allExps,expsMap);
	return expsMap;
    }
    
    private static void addAllExpsToMap(NodeList allExps, Map<Integer, Double> expsMap) {
	for(int i = 0; i < allExps.getLength(); i++) {
	    parseAndAddExpElementToMap(
		    checkAndConvertNodeToElement(allExps.item(i)),
		    expsMap);
	}
	
    }

    private static void parseAndAddExpElementToMap(Element currentExpElement, Map<Integer, Double> expsMap) {
	Integer level = LevelParser.parse(currentExpElement);
	Double expNeeded = NeededExpParser.parse(currentExpElement);
	expsMap.put(level, expNeeded);
    }
    

}
