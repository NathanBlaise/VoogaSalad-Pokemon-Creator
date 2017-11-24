package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;
/**
 * This class parses the elemental property of a pokemon
 * @author DanSun
 *
 */
public class ElementalParser extends LeafElementParser {
    private static String elementalTag = "elemental";
    /**
     * Method for getting the elemental property from the "element"
     * element
     * @param rootNode The "element" element 
     * @return The String representing the elemental property
     * of the pokemon, such as lightning, fire, water, etc.
     */
    public static String parse(Element rootNode) {
	String content = parseContent(rootNode, elementalTag);
	return content;
    }
}
