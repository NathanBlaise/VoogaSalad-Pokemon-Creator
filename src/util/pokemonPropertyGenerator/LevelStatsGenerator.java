package util.pokemonPropertyGenerator;
/**
 * This class is responsible for generating a string
 * that corresponds to the levelStats section of a pokemon
 * Species
 * @author DanSun
 *
 */
public class LevelStatsGenerator extends LevelSpecificStatsGeneratorAbstract{

    
    private static final String propertyTag = "levelStats";
    private static final String elementTag = "stat";
    private static final String speedTag = "speed";
    private static final String specialAttackTag = "specialAttack";
    private static final String specialDefenseTag = "specialDefense";
    private static final String normalAttackTag = "normalAttack";
    private static final String normalDefenseTag = "normalDefense";
    private static final String maxHpTag = "maxHP";
    //A discussino of EV and IV can be found at https://www.gamefaqs.com/boards/989552-pokemon-black-version/58673641
    //However, use representative constant values for now.
    private static final int EV = 85;
    private static final int IV = 15;
    
    
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
	//see https://www.gamefaqs.com/boards/989552-pokemon-black-version/58673641
	return ((maxHp * 2 + IV + EV/4) * level / 100) + 10 + level;
    }
    private int getNormalDefenseForLevel(int normalDefense, int level) {
	return calculateOtherStats(normalDefense, level);
    }
    private int getNormalAttackForLevel(int normalAttack, int level) {
	return calculateOtherStats(normalAttack, level);
    }
    private int getSpecialDefenseForLevel(int specialDefense, int level) {
	return calculateOtherStats(specialDefense, level);
    }
    
    private int getSpecialAttackForLevel(int specialAttack, int level) {
	return calculateOtherStats(specialAttack, level);
    }

    private int getSpeedForLevel(int speed, int level) {
	return calculateOtherStats(speed,level);
    }
    
    private int calculateOtherStats(int base, int level) {
	//see https://www.gamefaqs.com/boards/989552-pokemon-black-version/58673641
	int NMod = 1;
	return ((base*2 + IV + EV/4) *level/100) + 5 * NMod;
    }
    
}
