package util.pokemonSpecieDataParser.LeafElementParser;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class is the super class for pokemon specie
 * parsers that parse a single element that only
 * contains text.
 * For example: <tag>value</tag> is a leaf element
 * </tag><subtag>text</subtag></tag> is not
 * @author DanSun
 *
 */
public abstract class LeafElementParser {
    /**
     * Gets the text content of a leaf element (i.e.
     * an element with no child)
     * @param rootNode The leaf element 
     * @param tag The tag of the element
     * @return The text content of the element as a String
     */
    protected static String parseContent(Element rootNode, String tag) {
	NodeList names = rootNode.getElementsByTagName(tag);
	Element name = (Element)names.item(0);
	return name.getTextContent();
    }
}
