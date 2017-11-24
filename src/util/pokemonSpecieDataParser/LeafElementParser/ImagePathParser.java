package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;
/**
 * This class parses the image path inside an evolutionImage element
 * @author Dan Sun
 *
 */
public class ImagePathParser extends LeafElementParser{
    private static String iamgePathTag = "imagePath";
    /**
     * Gets the evolution image path as a string
     * @param rootNode the imagePath element
     * @return The string representing the relative image path of the pokemon
     */
    public static String parse(Element rootNode) {
	String content = parseContent(rootNode, iamgePathTag);
	return content;
    }
}
