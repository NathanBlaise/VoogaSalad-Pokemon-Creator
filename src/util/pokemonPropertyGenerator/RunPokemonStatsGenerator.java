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
	
	final int baseSpeed = 43;
	final int baseSpecialAttack = 50;
	final int baseSpecialDefense = 64;
	final int baseNormalAttack = 48;
	final int baseNormalDefense = 65;
	final int baseMaxHp = 15;
	
	LevelStatsGenerator gen = new LevelStatsGenerator(baseSpeed,
		baseSpecialAttack, baseSpecialDefense,
		baseNormalAttack, baseNormalDefense,
		baseMaxHp);
	String text = gen.generateLevelSpecificContent(75);
	System.out.println(text);
	assertTrue(true);
    }

}
