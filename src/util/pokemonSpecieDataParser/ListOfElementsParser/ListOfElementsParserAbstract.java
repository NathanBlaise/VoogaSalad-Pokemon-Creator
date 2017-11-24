package util.pokemonSpecieDataParser.ListOfElementsParser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class is the abstract class for all parser classes 
 * that parse a list of element specified in the pokemon species file.
 * A list of elements looks like this:
 * <ListName>
 * 	<element>
 * 		<property1>..</property1>
 * 		<property2>..</property2>
 * 		..
 * 	</element>
 * 	<element>
 * 	..
 * 	</element>
 * 	...
 * </ListName>
 * Examples include pokemonLevelMoves,pokemonLevelStats,
 * pokemonLevelExp, and pokemonLevelEvolutionImagePath
 * @author DanSun
 *
 */
public abstract class ListOfElementsParserAbstract {
    /**
     * This method gets the root element of the desired list.
     * For example, it gets the element enclosed by <ListName>
     * as specified in the example for the class.
     * @param rootNode The documenet element that contains all properties
     * of a pokemon
     * @param tag The tag name of the list
     * @return
     */
    protected static Element getListRootElement(Element rootNode, String tag) {
	return (Element) rootNode.getElementsByTagName(tag).item(0);
    }
    
    /**
     * Return all elements contained in the list as a node list.
     * Essentially, this return a node list of <element> as given in the documentation 
     * of the this class. 
     * @param listRoot
     * @param elementTag
     * @return
     */
    protected static NodeList getAllElementsInList(Element listRoot,String elementTag) {
	return listRoot.getElementsByTagName(elementTag);
    }
    
    /**
     * This method checks the node given to see if it is an element node.
     * If so, return it as an element node.
     * @param node The general node that is supposed to be an element node
     * @return The node as an element
     */
    protected static Element checkAndConvertNodeToElement(Node node) {
	assert(node.getNodeType() == Node.ELEMENT_NODE);
	return (Element) node;
    }
}
