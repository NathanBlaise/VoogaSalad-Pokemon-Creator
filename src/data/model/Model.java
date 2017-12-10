package data.model;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import util.FilePathConverter;
import data.items.Item;

/**
 * Model holds the data which is unrelated with the actual movement/action of screen.
 * @author cy122
 *
 */

public class Model implements Serializable{
	private static final long serialVersionUID = -3434227257089326223L; //used for serialization
	private ArrayList<NPC> NPCs= new ArrayList<NPC>(); // the NPC models
	private ArrayList<PokemonSpecie> PokemonSpecies= new ArrayList<PokemonSpecie>(); // the pokemon models
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	private transient static final ArrayList<Item> items;
	
	static{
		File folder = new File(FilePathConverter.getAbsolutePath("src/data/items"));
		assert(folder.exists());
		assert(folder.isDirectory());
		File[] files = folder.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.endsWith("java")&&(!name.equals("Item.java"));
		    }
		});
		List<String> itemNames =  new ArrayList<String>();
		for(File itemFile:files) {
			String name = itemFile.getName();
			int pos = name.lastIndexOf(".");
			if (pos > 0) {
			    name = name.substring(0, pos);
			}
			itemNames.add(name);
		}
		items = new ArrayList<Item>();
		for(String name: itemNames){
			try {
				Class<?> itemClass = Class.forName("data.items."+name);
				Constructor<?> itemConstructor = itemClass.getConstructor(new Class[]{});
				Item tempItem = (Item) itemConstructor.newInstance();
				items.add(tempItem);
			} catch (ClassNotFoundException | NoSuchMethodException
					| SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				System.out.printf("wrong item class!");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * WARNING!
	 * this is only used for serialization, it should not be used for any other intention.
	 */
	public Model(){
	}
	
	/**
	 * 
	 * @param NPCs - the NPC models
	 * @param PokemonSpecies- the pokemon models
	 */
	public Model(ArrayList<NPC> NPCs, ArrayList<PokemonSpecie> PokemonSpecies, ArrayList<Tile> tiles){
		this.NPCs = new ArrayList<NPC>(NPCs);
		this.PokemonSpecies = new ArrayList<PokemonSpecie>(PokemonSpecies);
		this.tiles = new ArrayList<Tile>(tiles);
	}
	
	
	
	public ArrayList<NPC> getNPCs() {
		return new ArrayList<NPC>(NPCs);
	}

	public void setNPCs(ArrayList<NPC> NPCs) {
		this.NPCs = new ArrayList<NPC>(NPCs);
	}

	public ArrayList<PokemonSpecie> getPokemonSpecies() {
		return new ArrayList<PokemonSpecie>(PokemonSpecies);
	}

	public void setPokemonSpecies(ArrayList<PokemonSpecie> pokemonSpecies) {
		PokemonSpecies = new ArrayList<PokemonSpecie>(pokemonSpecies);
	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}

	public ArrayList<Item> getItems() {
		return items;
	}
	
	
}
