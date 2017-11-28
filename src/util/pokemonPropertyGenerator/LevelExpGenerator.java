package util.pokemonPropertyGenerator;
/**
 * This class make it easier for developers to 
 * create levelExps for the pokemon xml file
 * @author Dan Sun
 *
 */
public class LevelExpGenerator extends LevelSpecificStatsGeneratorAbstract {
    
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

    private void createNeededExpWithinElement(
	    StringBuilder stringBuilder, int level) {
	int exp = getExpForLevel(level);
	createLeafWithinElement(stringBuilder,neededExpTag,
		Integer.toString(exp));
    }

    private int getExpForLevel(int level) {
	return 100*level;
    }
    
}
