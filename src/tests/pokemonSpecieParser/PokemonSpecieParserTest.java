package tests.pokemonSpecieParser;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import data.model.PokemonSpecie;
import util.FilePathConverter;
import util.PokemonSpecieFileParser;

public class PokemonSpecieParserTest {

    private String testSpecieFilePath = "src/resources/defaultPokemonSpecies/Charmander.xml";
    @Test
    public void test() throws ParserConfigurationException, SAXException, IOException {
	//create new parser for testing
	PokemonSpecieFileParser specieParser = new PokemonSpecieFileParser();
	PokemonSpecie specie = specieParser.parseFile(
		FilePathConverter.getAbsolutePath(testSpecieFilePath));
	assertTrue(true);
    }

}
