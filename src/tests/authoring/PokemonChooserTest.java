package tests.authoring;

import java.util.ArrayList;
import java.util.List;

import data.model.Pokemon;
import data.model.PokemonSpecie;
import authoring.databaseEditor.PokemonChooser;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PokemonChooserTest extends Application implements Callback<Pokemon, Integer>{
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage){
		List<PokemonSpecie> pokemonSpecies = new ArrayList<PokemonSpecie>();
		pokemonSpecies.add(new Specie1());
		pokemonSpecies.add(new Specie2());
		primaryStage.setScene(new Scene(new Group(new PokemonChooser(pokemonSpecies, new Pokemon(new Specie1(),"Jason") ,this).showPokemon())));
		primaryStage.show();
	}

	@Override
	public Integer call(Pokemon param) {
		// TODO Auto-generated method stub
		return null;
	}
}