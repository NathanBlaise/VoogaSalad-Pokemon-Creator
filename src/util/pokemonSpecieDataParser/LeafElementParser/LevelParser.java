package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;
/**
 * This class parses the level element
 * @author DanSun
 *
 */
public class LevelParser extends LeafElementParser {
    private static String levelTag = "level";
    /**
     * Gets the level specified in the element
     * @param rootNode The level element
     * @return The level value specified inside as an int
     */
    public static int parse(Element rootNode) {
	String content = parseContent(rootNode, levelTag);
	return Integer.parseInt(content);
    }
}
