package util.pokemonSpecieDataParser.ListOfElementsParser;

import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import util.pokemonSpecieDataParser.LeafElementParser.ImagePathParser;
import util.pokemonSpecieDataParser.LeafElementParser.LevelParser;
import util.pokemonSpecieDataParser.LeafElementParser.NeededExpParser;

/**
 * This class parses the pokemon evolution image specified
 * @author Dan Sun
 *
 */
public class PokemonLevelImagesParser extends ListOfElementsParserAbstract{
    private static String levelEvolutioinImagesTag = "levelEvolutionImages";
    private static String evolutionImageTag = "evolutionImage";
    
    /**
     * This method parses the document element and gets all the evolution image paths
     *  for this pokemon
     * @param rootNode the document element of the pokemon species xml file 
     * @return A Map<Integer,String> that specifies the evolution image paths
     * of this pokemon
     */
    public static Map<Integer, String> parse(Element rootNode){
	Map<Integer, String> pathsMap = new HashMap<>();
	Element levelEvolutionImages = getListRootElement(rootNode,levelEvolutioinImagesTag);
	NodeList allImagePaths = getAllElementsInList(levelEvolutionImages,evolutionImageTag);
	addAllImagePathsToMap(allImagePaths,pathsMap);
	return pathsMap;
    }
    
    private static void addAllImagePathsToMap(NodeList allImagePaths, Map<Integer, String> pathsMap) {
	for(int i = 0; i < allImagePaths.getLength(); i++) {
	    parseAndAddImagePathElementToMap(
		    checkAndConvertNodeToElement(allImagePaths.item(i)),
		    pathsMap);
	}
	
    }

    private static void parseAndAddImagePathElementToMap(Element currentImagePathElement, Map<Integer, String> pathsMap) {
	Integer level = LevelParser.parse(currentImagePathElement);
	String path = ImagePathParser.parse(currentImagePathElement);
	pathsMap.put(level, path);
    }
    

}
