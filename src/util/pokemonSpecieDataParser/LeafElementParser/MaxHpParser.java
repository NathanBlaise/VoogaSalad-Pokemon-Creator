package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;
/**
 * Gets the MaxHp stored in the maxHP element
 * @author DanSun
 *
 */
public class MaxHpParser extends LeafElementParser {
    private static String maxHpTag = "maxHP";
    /**
     * Gets the MaxHp stored in the maxHP element
     * @param rootNode the maxHP element
     * @return The double representing the maxHp specified
     */
    public static double parse(Element rootNode) {
	String content = parseContent(rootNode, maxHpTag);
	return Double.parseDouble(content);
    }
}
