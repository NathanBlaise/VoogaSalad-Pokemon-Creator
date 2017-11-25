package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;
/**
 * Gets the speed property of a pokemon
 * @author DanSun
 *
 */
public class SpeedParser extends LeafElementParser {
    private static String speedTag = "speed";
    /**
     * Gets the speed value of a pokemon specie as specified
     * @param rootNode The speed Element in an XML file
     * @return A double that represents the speed value of a pokemon specie
     */
    public static double parse(Element rootNode) {
	String content = parseContent(rootNode, speedTag);
	return Double.parseDouble(content);
    }
}
