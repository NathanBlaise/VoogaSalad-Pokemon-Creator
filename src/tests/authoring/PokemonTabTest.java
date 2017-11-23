package tests.authoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.model.PokemonSpecie;
import data.model.moves.Move;
import data.model.moves.MoveDatabase.MoveGrowl;
import data.model.moves.MoveDatabase.MoveQuickAttack;
import data.model.moves.MoveDatabase.MoveTailWhip;
import data.model.moves.MoveDatabase.MoveThunderShock;
import authoring.databaseEditor.PokemonTab;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
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
//		pokemonSpecies.add(new Specie1());
//		Map<String, Integer> tempMap = new HashMap<String, Integer>();
//		tempMap.put("speed", 1);
//		tempMap.put("maxHP", 1);
//		tempMap.put("specialAttack", 1);
//		tempMap.put("specialDefense", 1);
//		tempMap.put("normalAttack", 1);
//		tempMap.put("normalDefense", 1);
//		GridPane root = new GridPane();
//		Scene scene = new Scene(root);
//        root.add((new PokemonTab(pokemonSpecies, new Specie1()).showStatList(tempMap)),3,1,1,3);
//        Move[] moves = new Move[4];
//        moves[0] = new MoveGrowl();
//        moves[1] = new MoveQuickAttack();
//        moves[2] = new MoveTailWhip();
//        moves[3] = new MoveThunderShock();
//        root.add(new PokemonTab(pokemonSpecies, new Specie1()).showMoveList(moves), 1, 2,2,2);
//        root.add(new PokemonTab(pokemonSpecies, new Specie1()).showImage("images/earth.png"), 1, 0, 1, 2);
//        root.add(new PokemonTab(pokemonSpecies, new Specie1()).showSpecieName("Pikachu"), 2, 0, 1, 1);
//        root.add(new PokemonTab(pokemonSpecies, new Specie1()).showNickName("Walter White"), 2, 1, 1, 1);
//        root.add(new PokemonTab(pokemonSpecies, new Specie1()).showLevelPicker(100), 3, 0, 1, 1);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        scene.getStylesheets().add("resources/sceneStyle.css");
	}
}
