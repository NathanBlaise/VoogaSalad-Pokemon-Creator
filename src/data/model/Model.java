package data.model;

import java.io.Serializable;
import java.util.ArrayList;

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
	
	
}
