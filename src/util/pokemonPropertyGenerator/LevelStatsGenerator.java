package util.pokemonPropertyGenerator;
/**
 * This class is responsible for generating a string
 * that corresponds to the levelStats section of a pokemon
 * Species
 * @author DanSun
 *
 */
public class LevelStatsGenerator extends LevelSpecificStatsGeneratorAbstract{

    private static String propertyTag = "levelStats";
    private static String elementTag = "stat";
    private static String speedTag = "speed";
    private static String specialAttackTag = "specialAttack";
    private static String specialDefenseTag = "specialDefense";
    private static String normalAttackTag = "normalAttack";
    private static String normalDefenseTag = "normalDefense";
    private static String maxHpTag = "maxHP";
    
    private int baseSpeed;
    private int baseSpecialAttack;
    private int baseSpecialDefense;
    private int baseNormalAttack;
    private int baseNormalDefense;
    private int baseMaxHp;
    /**
     * Constructor for the class, passes pokemon
     * base stats to the generator
     * @param speed Base speed of the pokemon
     * @param specialAttack Base special attack of the pokemon
     * @param specialDefense Base special defense of the pokemon
     * @param normalAttack base normal attack of the pokemon
     * @param normalDefense base normal defense of the pokemon
     * @param maxHp base max hp of the pokemon
     */
    public LevelStatsGenerator(int speed, int specialAttack, 
	    int specialDefense, int normalAttack,
	    int normalDefense, int maxHp) {
	baseSpeed = speed;
	baseSpecialAttack = specialAttack;
	baseSpecialDefense = specialDefense;
	baseNormalAttack = normalAttack;
	baseNormalDefense = normalDefense;
	baseMaxHp = maxHp;
    }
    /**
     * 
     * @param maxLevel the max level of the pokemon.
     * This has to be the same as specified in the xml file
     * @return The string corresponding to the levelStats 
     * section of the XML file
     */
    public String generateLevelSpecificContent(int maxLevel) {
	return super.generateLevelSpecificContent(maxLevel,propertyTag, elementTag);
    }
    
    @Override
    protected void createElementSpecificContent(StringBuilder stringBuilder, int level) {
	createLevelWithinElement(stringBuilder, level);
	createSpeedWithinElement(stringBuilder, level);
	createSpecialAttackWithinElement(stringBuilder, level);
	createSpecialDefenseWithinElement(stringBuilder, level);
	createNormalAttackWithinElement(stringBuilder, level);
	createNormalDefenseWithinElement(stringBuilder, level);
	createMaxHpWithinElement(stringBuilder, level);
    }
    
    private void createMaxHpWithinElement(
	    StringBuilder stringBuilder, int level) {
	int maxHp = getMaxHpForLevel(baseMaxHp, level);
	createLeafWithinElement(stringBuilder,maxHpTag,
		Integer.toString(maxHp));
    }

    private void createNormalDefenseWithinElement(
	    StringBuilder stringBuilder, int level) {
	int normalDefense = getNormalDefenseForLevel(
		baseNormalDefense, level);
	createLeafWithinElement(stringBuilder,normalDefenseTag,
		Integer.toString(normalDefense));
    }

    private void createNormalAttackWithinElement(
	    StringBuilder stringBuilder, int level) {
	int normalAttack = getNormalAttackForLevel(
		baseNormalAttack, level);
	createLeafWithinElement(stringBuilder,normalAttackTag,
		Integer.toString(normalAttack));
    }

    private void createSpecialDefenseWithinElement(
	    StringBuilder stringBuilder, int level) {
	int specialDefense = getSpecialDefenseForLevel(
		baseSpecialDefense, level);
	createLeafWithinElement(stringBuilder,specialDefenseTag,
		Integer.toString(specialDefense));
    }

    private void createSpecialAttackWithinElement(StringBuilder stringBuilder, int level) {
	int specialAttack = getSpecialAttackForLevel(
		baseSpecialAttack, level);
	createLeafWithinElement(stringBuilder,specialAttackTag,
		Integer.toString(specialAttack));
	
    }
    private void createSpeedWithinElement(
	    StringBuilder stringBuilder, int level) {
	int speed = getSpeedForLevel(baseSpeed, level);
	createLeafWithinElement(stringBuilder,speedTag,
		Integer.toString(speed));
    }
    private int getMaxHpForLevel(int maxHp, int level) {
	return maxHp + 5 * level;
    }
    private int getNormalDefenseForLevel(int normalDefense, int level) {
	return normalDefense + level;
    }
    private int getNormalAttackForLevel(int normalAttack, int level) {
	return normalAttack + level;
    }
    private int getSpecialDefenseForLevel(int specialDefense, int level) {
	return specialDefense + level;
    }
    
    private int getSpecialAttackForLevel(int specialAttack, int level) {
	return specialAttack + level;
    }

    private int getSpeedForLevel(int speed, int level) {
	return speed + level;
    }
    
}
