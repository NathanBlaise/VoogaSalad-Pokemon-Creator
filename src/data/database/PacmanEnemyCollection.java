package data.database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import util.FilePathConverter;
import util.fileParsers.PacmanEnemyParser;
import data.model.Model;
import data.model.PacmanEnemy;

/**
 * for giving the model the PacmanEnemies read from files
 * @author cy122
 * @author Dan Sun
 *
 */
public class PacmanEnemyCollection extends DataCollectionAbstract{
    private static String defaultPacmanEnemiessRelativePath = 
	    "src/resources/defaultPacmanEnemies";
    //stores mapping from name to class
    private List<PacmanEnemy> pacmanEnemies;
    private PacmanEnemyParser fileParser;

    /**
     * Constructor for the class.
     * Initializes instance variables used by this class,
     * and reads default NPC files from the folder
     * that is holding them
     * 
     */
    public PacmanEnemyCollection() {
	pacmanEnemies = new ArrayList<>();
	fileParser = new PacmanEnemyParser();
	readDefaultPacmanEnemies();
    }

    /**
     * This method hands all the NPCs it 
     * has to the model given
     * @param model The receiver of all the NPCs
     */
    public void passPacmanEnemiesToModel(Model model) {
	ArrayList<PacmanEnemy> newList = new ArrayList<>(pacmanEnemies);
	model.setPacmanEnemies(newList);
    }

    private void readDefaultPacmanEnemies() {
	File[] files = getFilesWithinAsArray(
		FilePathConverter.getAbsolutePath(defaultPacmanEnemiessRelativePath));
	for(File pacmanEnemyFile:files) {
	    PacmanEnemy pacmanEnemy = null;
	    try {
		pacmanEnemy = fileParser.parseFile(pacmanEnemyFile);
	    } catch (ParserConfigurationException | SAXException | IOException e) {
		//TODO: handle exception better
		System.out.println("Failed to parse PacmenEnemy from file " + 
			pacmanEnemyFile.getAbsolutePath());
		e.printStackTrace();//handled by exiting the program
		System.exit(1);
	    }
	    pacmanEnemies.add(pacmanEnemy);
	}
    }

}
