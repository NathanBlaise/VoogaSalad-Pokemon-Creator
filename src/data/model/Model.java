package data.model;

import java.io.Serializable;
import java.util.ArrayList;

// database holds the data which is unrelated with the actual movement/action of screen.

public class Model implements Serializable{
	private static final long serialVersionUID = -3434227257089326223L;
	private ArrayList<NPC> NPCs= new ArrayList<NPC>();
	private ArrayList<PokemonSpecie> PokemonSpecies= new ArrayList<PokemonSpecie>();
	
	public Model(){
	}
	
	public ArrayList<NPC> getNPCs() {
		return new ArrayList<NPC>(NPCs);
	}

	public void setNPCs(ArrayList<NPC> NPCs) {
		this.NPCs = NPCs;
	}

	public ArrayList<PokemonSpecie> getPokemonSpecies() {
		return new ArrayList<PokemonSpecie>(PokemonSpecies);
	}

	public void setPokemonSpecies(ArrayList<PokemonSpecie> pokemonSpecies) {
		PokemonSpecies = pokemonSpecies;
	}
}
