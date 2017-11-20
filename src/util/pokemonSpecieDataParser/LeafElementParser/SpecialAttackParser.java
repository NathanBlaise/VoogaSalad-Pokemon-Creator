package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;
/**
 * Gets the special attack property of a pokemon
 * @author DanSun
 *
 */
public class SpecialAttackParser extends LeafElementParser {
    private static String specialAttackTag = "specialAttack";
    /**
     * Gets the special attack value of a pokemon specie as specified
     * @param rootNode The specialAttack Element in an XML file
     * @return A double that represents the special attack value of a pokemon specie
     */
    public static double parse(Element rootNode) {
	String content = parseContent(rootNode, specialAttackTag);
	return Double.parseDouble(content);
    }
}
