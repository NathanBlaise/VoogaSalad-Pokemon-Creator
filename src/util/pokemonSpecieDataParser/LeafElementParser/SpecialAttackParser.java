package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;

public class SpecialAttackParser extends LeafElementParser {
    private static String speedTag = "speed";
    
    public static double parse(Element rootNode) {
	String content = parseContent(rootNode, speedTag);
	return Double.parseDouble(content);
    }
}
