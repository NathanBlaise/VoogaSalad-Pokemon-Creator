package tests.authoring;

import java.util.ArrayList;
import java.util.List;

import data.event.EventPokemon;
import data.model.PokemonSpecie;
import authoring.eventManage.PokemonEventEditor;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PokemonEventEditorTest extends Application implements Callback<EventPokemon, Integer>{
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage){
		List<PokemonSpecie> pokemonSpecies = new ArrayList<PokemonSpecie>();
		pokemonSpecies.add(new Specie1());
		pokemonSpecies.add(new Specie2());
		new PokemonEventEditor(pokemonSpecies, new Specie1(), this);
	}

	@Override
	public Integer call(EventPokemon param) {
		// TODO Auto-generated method stub
		return null;
	}
}
