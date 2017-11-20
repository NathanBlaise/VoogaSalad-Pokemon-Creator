package util.pokemonSpecieDataParser;

import org.w3c.dom.Element;

public class MaxLevelParser extends LeafElementParser {
    private static String maxLevelTag = "maxLevel";
    
    public static int parse(Element rootNode) {
	String content = parseContent(rootNode, maxLevelTag);
	return Integer.parseInt(content);
    }
}
