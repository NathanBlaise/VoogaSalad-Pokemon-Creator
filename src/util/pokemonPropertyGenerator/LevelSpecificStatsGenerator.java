package util.pokemonPropertyGenerator;


/**
 * This class provides methods that will be used
 * across generators that help developers to create
 * level specific statistics (such as levelExp and levelStats)
 * with ease
 * @author DanSun
 *
 */
public abstract class LevelSpecificStatsGenerator {
    protected static final String PROPERTY_PREFIX = "\t";    
    protected static final String ELEMENT_TAG_PREFIX = "\t\t";
    protected static final String LEAF_TAG_PREFIX = "\t\t\t";
    protected static String levelTag = "level";
    
    /**
     * This method return a string that describes
     * a property of the pokemon needed
     * for the XML file. 
     * @param maxLevel The max level of the pokemon
     * @param propertyTag  The tag of this property
     * @param elementTag The tag of second-level 
     * elements within the proprety
     * @return A String that describes this property
     * in accordance with the xml file format
     */
    protected String generateLevelSpecificContent(int maxLevel, 
	     String propertyTag, String elementTag) {
	StringBuilder stringBuilder = new StringBuilder();
	createPropertyTag(stringBuilder,false, propertyTag);
	for(int level = 1; level <= maxLevel; level++) {
	    createTextForElement(stringBuilder, level,
		    elementTag);
	}
	createPropertyTag(stringBuilder,true,propertyTag);
	return stringBuilder.toString();
    }
    
    /**
     * This method specifies how each different class
     * should create their element. It must be overriden
     * by child classes to provide substantial functionality
     * @param stringBuilder
     * @param level
     */
    abstract protected void createElementSpecificContent(
	    StringBuilder stringBuilder, int level);
    
    private void createTextForElement(StringBuilder stringBuilder, int level, String elementTag) {
	createElementTagText(stringBuilder,false, elementTag);
	createElementSpecificContent(stringBuilder, level);
	createElementTagText(stringBuilder,true, elementTag);
    }

    private void createElementTagText(
	    StringBuilder stringBuilder, boolean ending, 
	    String elementTag) {
	stringBuilder.append(ELEMENT_TAG_PREFIX);
	stringBuilder.append(
		convertTextToTag(elementTag,ending));
	stringBuilder.append("\n");
    }
    /**
     * This method adds the level tag to the 
     * StringBuilder provided
     * @param stringBuilder Where text to be added to
     * @param level The level within tags
     */
    protected void createLevelWithinElement(StringBuilder stringBuilder, int level) {
	stringBuilder.append(LEAF_TAG_PREFIX);
	stringBuilder.append(convertTextToTag(
		levelTag,false));
	stringBuilder.append(level);
	stringBuilder.append(convertTextToTag(
		levelTag,true));
	stringBuilder.append("\n");
    }

    private void createPropertyTag(
	    StringBuilder stringBuilder,boolean ending, 
	    String propertyTag) {
	stringBuilder.append(PROPERTY_PREFIX);
	stringBuilder.append(convertTextToTag(propertyTag, ending));
	stringBuilder.append("\n");
    }

    protected String convertTextToTag(String str, boolean ending) {
	return "<" + (ending? "/" : "") + str + ">";
    }

    
}
