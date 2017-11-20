package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;

public class specialDefenseParser extends LeafElementParser {
    private static String specialDefenseTag = "specialDefense";
    
    public static double parse(Element rootNode) {
	String content = parseContent(rootNode, specialDefenseTag);
	return Double.parseDouble(content);
    }
}
