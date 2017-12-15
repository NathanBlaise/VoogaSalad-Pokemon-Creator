package data.database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import data.model.Model;
import data.model.PokemonSpecie;
import util.FilePathConverter;
import util.fileParsers.PokemonSpecieFileParser;

/**
 * This class stores pokemon species that are avialable in a game
 * including species that are provided and species created by the user
 * @author Dan Sun
 *
 */
public class PokemonSpecieCollection extends DataCollectionAbstract{

    private static String defaultSpeciesRelativePath = 
	    "src/resources/defaultPokemonSpecies";
    //stores mapping from name to class
    private Map<String,PokemonSpecie> species; 
    private PokemonSpecieFileParser fileParser;

    /**
     * Constructor for the class.
     * Initializes instance variables used by this class,
     * and reads default specie files from the folder
     * that is holding them
     * 
     */
    public PokemonSpecieCollection() {
	species = new HashMap<>();
	fileParser = new PokemonSpecieFileParser();
	readDefaultSpecies();
    }
    
    /**
     * This method hands all the pokemon species it 
     * has to the model given
     * @param model The receiver of all the pokemon species
     */
    public void passSpeciesToModel(Model model) {
	ArrayList<PokemonSpecie> specieList = new ArrayList<>();
	for(String name:species.keySet()) {
	    specieList.add(species.get(name));
	}
	model.setPokemonSpecies(specieList);
    }

    private void readDefaultSpecies() {
	File[] files = getFilesWithinAsArray(
		FilePathConverter.getAbsolutePath(defaultSpeciesRelativePath));
	for(File specieFile:files) {
	    PokemonSpecie specie = null;
	    try {
		specie = fileParser.parseFile(specieFile);
	    } catch (ParserConfigurationException | SAXException | IOException e) {
		System.out.println("Failed to parse specie from file " + 
			specieFile.getAbsolutePath());
		e.printStackTrace();//handled by exiting the program
		System.exit(1);
	    }
	    species.put(specie.getSpecieName(), specie);
	}
    }


}
