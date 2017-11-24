package authoring.eventManage;

import java.util.List;

import authoring.databaseEditor.PokemonChooser;
import data.event.EventPokemon;
import data.model.Pokemon;
import data.model.PokemonSpecie;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PokemonEventEditor implements Callback<Pokemon, Integer>{
	private Stage stage;
	private Pokemon pokemon;
	private Callback<EventPokemon, Integer> saver;
	
	public PokemonEventEditor(List<PokemonSpecie> pokemonSpecies, PokemonSpecie selectedPokemonSpecie, Callback<EventPokemon, Integer> saver){
		stage = new Stage();
		editPokemon(pokemonSpecies, selectedPokemonSpecie);
		this.saver=saver;
		stage.show();
	}
	
	private void editPokemon(List<PokemonSpecie> pokemonSpecies, PokemonSpecie selectedPokemonSpecie){
		GridPane gridPane = new PokemonChooser(pokemonSpecies, new Pokemon(selectedPokemonSpecie, ""), this).showPokemon();
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(gridPane);	
		stage.setScene(new Scene(borderPane));
	}

	@Override
	public Integer call(Pokemon param) {
		pokemon = new Pokemon(param);
		saver.call(new EventPokemon(pokemon));
		return null;
	}
}
