package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;
/**
 * Gets the normal defense of a pokemon
 * @author DanSun
 *
 */
public class NormalDefenseParser extends LeafElementParser {
    private static String normalDefenseTag = "normalDefense";
    /**
     * Gets the normal defense value of a pokemon specie as specified
     * @param rootNode The normalDefense Element in an XML file
     * @return A double that represents the normal defense value of a pokemon specie
     */
    public static double parse(Element rootNode) {
	String content = parseContent(rootNode, normalDefenseTag);
	return Double.parseDouble(content);
    }
}
