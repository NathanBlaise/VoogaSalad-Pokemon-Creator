package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;
/**
 * Gets the special defense property of a pokemon
 * @author DanSun
 *
 */
public class SpecialDefenseParser extends LeafElementParser {
    private static String specialDefenseTag = "specialDefense";
    /**
     * Gets the special defense value of a pokemon specie as specified
     * @param rootNode The specialDefense Element in an XML file
     * @return A double that represents the special defense value of a pokemon specie
     */
    public static double parse(Element rootNode) {
	String content = parseContent(rootNode, specialDefenseTag);
	return Double.parseDouble(content);
    }
}
