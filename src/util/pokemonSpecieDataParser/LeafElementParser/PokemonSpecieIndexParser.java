package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;
/**
 * This class parses the name of a pokemon specie
 * @author Dan Sun
 *
 */
public class PokemonSpecieIndexParser extends LeafElementParser{
    private static String nameTag = "index";
    /**
     * Parses the name of a pokemon specie
     * @param rootNode the name element of the pokemon specie
     * @return A String that is the name of the pokemon specie
     */
    public static int parse(Element rootNode) {
	String content = parseContent(rootNode, nameTag);
	return Integer.parseInt(content);
    }
}