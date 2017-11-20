package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;
/**
 * Gets the normal attack of a pokemon
 * @author DanSun
 *
 */
public class NormalAttackParser extends LeafElementParser {
    private static String normalAttackTag = "normalAttack";
    /**
     * Gets the normal attack value of a pokemon specie as specified
     * @param rootNode The normalAttack Element in an XML file
     * @return A double that represents the normal attack value of a pokemon specie
     */
    public static double parse(Element rootNode) {
	String content = parseContent(rootNode, normalAttackTag);
	return Double.parseDouble(content);
    }
}
