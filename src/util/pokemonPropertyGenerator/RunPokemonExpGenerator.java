package util.pokemonPropertyGenerator;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * This class is used to run the PokemonExpGenerator
 * Developers could modify that class to automatically
 * generate exp for each level, and copy the text generated 
 * into the desired file.
 * @author Dan Sun
 *
 */
public class RunPokemonExpGenerator {

    @Test
    public void test() {
	LevelExpGenerator gen = new LevelExpGenerator();
	String text = gen.generateLevelSpecificContent(75);
	System.out.println(text);
	assertTrue(true);
    }

}
