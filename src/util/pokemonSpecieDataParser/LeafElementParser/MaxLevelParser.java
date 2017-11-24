package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;
/**
 * This class parses the maxLevel element
 * @author DanSun
 *
 */
public class MaxLevelParser extends LeafElementParser {
    private static String maxLevelTag = "maxLevel";
    /**
     * Parses the maxLevel element
     * @param rootNode the maxLevel element
     * @return an int specifying the max level of the pokemon specie
     */
    public static int parse(Element rootNode) {
	String content = parseContent(rootNode, maxLevelTag);
	return Integer.parseInt(content);
    }
}
