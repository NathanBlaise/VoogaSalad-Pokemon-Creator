package data.database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import util.FilePathConverter;
import util.TileFileParser;
import data.model.Model;
import data.model.Tile;

public class TileCollection {
	private static String defaultTilesRelativePath = "src/resources/defaultTiles";
    //stores mapping from name to class
    private Map<String,Tile> Tiles; 
    private TileFileParser fileParser;

    /**
     * Constructor for the class.
     * Initializes instance variables used by this class,
     * and reads default Tile files from the folder
     * that is holding them
     * 
     */
    public TileCollection() {
		Tiles = new HashMap<>();
		fileParser = new TileFileParser();
		readDefaultTiles();
    }
    
    /**
     * This method hands all the Tiles it 
     * has to the model given
     * @param model The receiver of all the Tiles
     */
    public void passTileToModel(Model model) {
		ArrayList<Tile> TileList = new ArrayList<>();
		for(String name:Tiles.keySet()) {
		    TileList.add(Tiles.get(name));
		}
		model.setTiles(TileList);
    }

    private void readDefaultTiles() {
		File[] files = getFilesWithinAsArray(FilePathConverter.getAbsolutePath(defaultTilesRelativePath));
		for(File TileFile:files) {
		    Tile Tile = null;
		    try {
		    	Tile = fileParser.parseFile(TileFile);
		    } catch (ParserConfigurationException | SAXException | IOException e) {
				//TODO: handle exception better
				System.out.println("Failed to parse Tile from file " + 
					TileFile.getAbsolutePath());
				e.printStackTrace();
				System.exit(1);
		    }
		    Tiles.put(Tile.getName(), Tile);
		}
    }

    //adopted from https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
    private File[] getFilesWithinAsArray(File folder) {
		assert(folder.exists());
		assert(folder.isDirectory());
		File[] listOfFiles = folder.listFiles();
		return listOfFiles;
    }

    private File[] getFilesWithinAsArray(String folder) {
		File folderFile = new File(folder);
		return getFilesWithinAsArray(folderFile);
    }
}
