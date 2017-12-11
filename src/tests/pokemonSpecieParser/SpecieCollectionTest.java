package tests.pokemonSpecieParser;

import static org.junit.Assert.*;

import org.junit.Test;

import data.database.PokemonSpecieCollection;
import data.model.Model;
/**
 * This tests the functionality of the PokemonSpecieCollection
 * It initializes the class and asks it to pass the species
 * read in to the Model class
 * @author DanSun
 *
 */
public class SpecieCollectionTest {

    @Test
    public void test() {
	Model model = new Model();
	PokemonSpecieCollection species = 
		new PokemonSpecieCollection();
	species.passSpeciesToModel(model);
	assertTrue(model.getPokemonSpecies() != null);
    }

}
