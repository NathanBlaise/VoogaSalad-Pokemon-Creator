package tests.pacmanFileParser;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import data.model.PacmanEnemy;
import data.model.PokemonSpecie;
import util.FilePathConverter;
import util.fileParsers.PacmanEnemyParser;
import util.fileParsers.PokemonSpecieFileParser;

public class PacmanEnemyParserTest {

    private String testFileRelativePath = "src/resources/defaultPacmanEnemies/Enemy1.xml";
    @Test
    public void test() throws ParserConfigurationException, SAXException, IOException {
	//create new parser for testing
	PacmanEnemyParser parser = new PacmanEnemyParser();
	PacmanEnemy data = parser.parseFile(
		FilePathConverter.getAbsolutePath(testFileRelativePath));
	System.out.println(data.getSpeed() + "\n" + data.getImagePath());
	assertTrue(true);
    }

}
