package util.pokemonPropertyGenerator;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * This class is used to run the PokemonStatsGenerator
 * Developers could modify that class to automatically
 * generate stats for each level, and copy the text generated 
 * into the desired file.
 * The printed text corresponds to the levelStats section
 * in the xml file
 * @author Dan Sun
 *
 */
public class RunPokemonStatsGenerator {

    @Test
    public void test() {
	
	final int baseSpeed = 70;
	final int baseSpecialAttack = 31;
	final int baseSpecialDefense = 31;
	final int baseNormalAttack = 60;
	final int baseNormalDefense = 30;
	final int baseMaxHp = 40;
	
	LevelStatsGenerator gen = new LevelStatsGenerator(baseSpeed,
		baseSpecialAttack, baseSpecialDefense,
		baseNormalAttack, baseNormalDefense,
		baseMaxHp);
	String text = gen.generateLevelSpecificContent(75);
	System.out.println(text);
	assertTrue(true);
    }

}
