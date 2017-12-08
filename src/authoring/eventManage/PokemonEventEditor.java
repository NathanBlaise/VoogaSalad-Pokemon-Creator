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
	
	public PokemonEventEditor(List<PokemonSpecie> pokemonSpecies, Pokemon selectedPokemon, Callback<EventPokemon, Integer> saver){
		this.saver=saver;
		stage = new Stage();
		editPokemon(pokemonSpecies, selectedPokemon);
		stage.show();
	}
	
	private void editPokemon(List<PokemonSpecie> pokemonSpecies, Pokemon selectedPokemon){
		GridPane gridPane = new PokemonChooser(pokemonSpecies, selectedPokemon, this).showPokemon();
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(gridPane);	
		stage.setScene(new Scene(borderPane));
	}

	@Override
	public Integer call(Pokemon param) {
		pokemon = new Pokemon(param);
		saver.call(new EventPokemon(pokemon));
		stage.close();
		return null;
	}
}
