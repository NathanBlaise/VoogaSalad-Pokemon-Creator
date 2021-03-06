package util.fileParsers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import util.pokemonSpecieDataParser.LeafElementParser.LeafElementParser;
import util.pokemonSpecieDataParser.ListOfElementsParser.ListOfElementsParserAbstract;
import data.model.Tile;
/**
 * parse the Tile from the file
 * @author cy122 Dan Sun
 *
 */
public class TileFileParser extends XMLFileParserAbstract{
    private final static String nameTag = "name";
    private final static String obstacleTag = "obstacle";
    private final static String heightTag = "height";
    private final static String widthTag = "width";
    private final static String imagePathsTag = "imagePaths";
    private final static String wholePicTag = "wholePic";

    private String name;
    private boolean obstacle;
    private int height;
    private int width;
    private ArrayList<String> imagePaths; //will be used if height>1 or width>1
    private String wholePic;


    /**
     * get the tile according to the file
     * @param file - the file
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public Tile parseFile(File file) throws ParserConfigurationException, 
    	SAXException, IOException {
	super.fillDataFromFileIntoFields(file);
	return new Tile(name, obstacle, height, width, wholePic, imagePaths);
    }
    @Override
    protected void reinitializeObjectData() {
	name = "";
	obstacle = false;
	height = 1;
	width = 1;
	imagePaths = new ArrayList<String>(); //will be used if height>1 or width>1
	wholePic = "";
    }
    @Override
    protected void parseData() {
	name = tileAttributeParser.parse(rootNode, nameTag, "");
	obstacle = Boolean.valueOf(tileAttributeParser.parse(rootNode, obstacleTag, "false"));
	height = Integer.valueOf(tileAttributeParser.parse(rootNode, heightTag, "1"));
	width = Integer.valueOf(tileAttributeParser.parse(rootNode, widthTag, "1"));
	wholePic = tileAttributeParser.parse(rootNode, wholePicTag, "");
	imagePaths = imagePathsParser.parse(rootNode, imagePathsTag, wholePic); //will be used if height>1 or width>1
    }

    private static class tileAttributeParser extends LeafElementParser {
	/**
	 * 
	 * @param rootNode - the root node
	 * @param attribute - the name of attribute
	 * @return
	 */
	public static String parse(Element rootNode, String attribute, String defaultValue) {
	    try {
		String content = parseContent(rootNode, attribute);
		return content;
	    } catch (NullPointerException e) {
		return defaultValue;
	    }
	}
    }

    private static class imagePathsParser extends ListOfElementsParserAbstract {
	/**
	 * 
	 * @param rootNode - the root node
	 * @param attribute - the name of attribute
	 * @return
	 */
	public static ArrayList<String> parse(Element rootNode, String attribute, String defaultPic) {
	    ArrayList<String> result = new ArrayList<String>();
	    NodeList pathList = getAllElementsInList(rootNode, attribute);
	    for(int i=0; i<pathList.getLength(); i++){
		Node node = pathList.item(i);
		result.add(((Element)node).getTextContent());
	    }
	    if(result.size()==0){
		result.add(defaultPic);
	    }
	    return result;
	}
    }
}
