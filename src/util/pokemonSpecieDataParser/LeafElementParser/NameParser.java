package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;
/**
 * This class parses the name of a pokemon specie
 * @author Dan Sun
 *
 */
public class NameParser extends LeafElementParser{
    private static String nameTag = "name";
    
    public static String parse(Element rootNode) {
	String content = parseContent(rootNode, nameTag);
	return content;
    }
}
