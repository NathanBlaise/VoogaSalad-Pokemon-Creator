package util.pokemonSpecieDataParser;

import org.w3c.dom.Element;

public class ElementalParser extends LeafElementParser {
    private static String elementalTag = "elemental";
    
    public static String parse(Element rootNode) {
	String content = parseContent(rootNode, elementalTag);
	return content;
    }
}
