package data.database;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * This class stores pokemon species that are avialable in a game
 * including species that are provided and species created by the user
 * @author Dan Sun
 *
 */
public class PokemonSpecieCollection {
    
    private static String defaultSpeciesRelativePath = 
	    "src/resources/defaultPokemonSpeices";
    //stores mapping from name to class
    private Map<String,PokemonSpecie> species; 
    
    public PokemonSpecieCollection() {
	species = new HashMap<>();
	readDefaultSpecies();
    }
    
    private void readDefaultSpecies() {
	File[] files = getFilesWithinAsArray(defaultSpeciesRelativePath);
	for(File specieFile:files) {
	    
	}
	//get name of the pokemon
	//create Species class according to that specified in the xml file
	//add the <name,class> pair to the map
    }
    //adopted from https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
    private File[] getFilesWithinAsArray(File folder) {
	File[] listOfFiles = folder.listFiles();
	return listOfFiles;
    }
    
    private File[] getFilesWithinAsArray(String folder) {
	File folderFile = new File(folder);
	return getFilesWithinAsArray(folderFile);
    }
}
