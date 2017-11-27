package util.pokemonPropertyGenerator;

public class LevelStatsGenerator extends LevelSpecificStatsGenerator{

    private static String propertyTag = "levelStats";
    private static String elementTag = "stat";
    private static String speedTag = "speed";
    private static String spacialAttackTag = "specialAttack";
    private static String specialDefenseTag = "specialDefense";
    private static String normalAttackTag = "normalAttack";
    private static String normalDefenseTag = "normalDefense";
    private static String maxHpTag = "maxHP";
    
    public String generateLevelSpecificContent(int maxLevel) {
	return super.generateLevelSpecificContent(maxLevel,propertyTag, elementTag);
    }
    
    @Override
    protected void createElementSpecificContent(StringBuilder stringBuilder, int level) {
	createLevelWithinElement(stringBuilder, level);
	
    }
    

}
