package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;

public class MaxHpParser extends LeafElementParser {
    private static String specialAttackTag = "specialAttack";
    
    public static double parse(Element rootNode) {
	String content = parseContent(rootNode, specialAttackTag);
	return Double.parseDouble(content);
    }
}
