package tests.authoring;

import java.util.ArrayList;
import java.util.List;

import data.model.PokemonSpecie;
import authoring.databaseEditor.PokemonTab;
import javafx.application.Application;
import javafx.stage.Stage;

public class PokemonTabTest extends Application{
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage){
		List<PokemonSpecie> pokemonSpecies = new ArrayList<PokemonSpecie>();
		pokemonSpecies.add(new Specie1());
		pokemonSpecies.add(new Specie2());
		new PokemonTab(pokemonSpecies, new Specie1());
	}
}
