package tests.authoring;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import authoring.eventManage.InstructionNPCFightEditor;
import data.event.InstructionNPCFight;
import data.model.NPC;
import data.model.PokemonSpecie;

public class InstructionNPCFightEditorTest extends Application implements Callback<InstructionNPCFight, Integer>{

	private Stage stage;
	
	@Override
	public Integer call(InstructionNPCFight param) {
		System.out.printf("%s\n", param.getPokemons().toString());
		stage.sizeToScene();
		return null;
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		NPC npc = new NPC("images/CaptainMap.png", "Walter White");
		List<PokemonSpecie> pokemonSpecies = new ArrayList<PokemonSpecie>();
		pokemonSpecies.add(new Specie1());
		pokemonSpecies.add(new Specie2());
		InstructionNPCFightEditor instructionNPCDialogueEditor = new InstructionNPCFightEditor(npc, pokemonSpecies, this);
		Group root = new Group();
		root.getChildren().add(instructionNPCDialogueEditor.showEditor());
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
