package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;

public class NormalAttackParser extends LeafElementParser {
    private static String HpTag = "HP";
    
    public static double parse(Element rootNode) {
	String content = parseContent(rootNode, HpTag);
	return Double.parseDouble(content);
    }
}
