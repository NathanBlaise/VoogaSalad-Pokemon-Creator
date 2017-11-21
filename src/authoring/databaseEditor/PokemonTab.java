package authoring.databaseEditor;

import java.util.List;

import data.model.Pokemon;
import data.model.PokemonSpecie;

public class PokemonTab {
	PokemonTab(List<PokemonSpecie> pokemonSpecies, PokemonSpecie selectedPokemon){
		
	}

	private void createPokemonText(PokemonSpecie currentPokemonSpecie, String name, int currentLevel){
		Pokemon currentPokemon = new Pokemon(currentPokemonSpecie, name, currentLevel);
	}
	
}
