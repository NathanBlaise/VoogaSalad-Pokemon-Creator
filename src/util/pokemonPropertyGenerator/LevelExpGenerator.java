package util.pokemonPropertyGenerator;
/**
 * This class make it easier for developers to 
 * create levelExps for the pokemon xml file
 * @author Dan Sun
 *
 */
public class LevelExpGenerator extends LevelSpecificStatsGenerator {
    
    
    private static String propertyTag = "levelExps";
    private static String elementTag = "exp";
    private static String neededExpTag = "neededExp";
   
    public String generateLevelSpecificContent(int maxLevel) {
	return super.generateLevelSpecificContent(maxLevel,propertyTag, elementTag);
    }
    
    @Override
    protected void createElementSpecificContent(
	    StringBuilder stringBuilder, int level) {
	createLevelWithinElement(stringBuilder, level);
	createNeededExpWithinElement(stringBuilder, level);
    }

    private void createNeededExpWithinElement(StringBuilder stringBuilder, int level) {
	stringBuilder.append(LEAF_TAG_PREFIX);
	stringBuilder.append(convertTextToTag(
	    neededExpTag,false));
	stringBuilder.append(getExpForLevel(level));
	stringBuilder.append(convertTextToTag(
	    neededExpTag,true));
	stringBuilder.append("\n");
    }

    private int getExpForLevel(int level) {
	return 100*level;
    }
    
}
