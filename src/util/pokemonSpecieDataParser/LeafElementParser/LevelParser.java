package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;

public class LevelParser extends LeafElementParser {
    private static String levelTag = "level";
    
    public static int parse(Element rootNode) {
	String content = parseContent(rootNode, levelTag);
	return Integer.parseInt(content);
    }
}
