package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;
/**
 * This class parses the HP information
 * @author DanSun
 *
 */
public class HpParser extends LeafElementParser {
    private static String HpTag = "HP";
    /**
     * Gets the HP stored in a stat element
     * @param rootNode The HP element
     * @return The value of HP element contained within as a double
     */
    public static double parse(Element rootNode) {
	String content = parseContent(rootNode, HpTag);
	return Double.parseDouble(content);
    }
}
