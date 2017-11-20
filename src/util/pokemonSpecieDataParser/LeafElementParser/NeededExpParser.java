package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;
/**
 * This class parses the neededExpElement
 * @author DanSun
 *
 */
public class NeededExpParser extends LeafElementParser {
    private static String neededExpTag = "neededExp";
    /**
     * Gets the neededExp value from the element
     * @param rootNode the neededExp Element
     * @return The needed experience to go to the next level as a double
     */
    public static double parse(Element rootNode) {
	String content = parseContent(rootNode, neededExpTag);
	return Double.parseDouble(content);
    }
}
