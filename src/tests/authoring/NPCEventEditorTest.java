package tests.authoring;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Callback;
import authoring.eventManage.NPCEventEditor;
import data.event.Event;
import data.event.EventNPC;
import data.event.Instruction;
import data.model.NPC;
import data.model.PokemonSpecie;

public class NPCEventEditorTest  extends Application implements Callback<Event, Integer>{
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage){
		List<PokemonSpecie> pokemonSpecies = new ArrayList<PokemonSpecie>();
		pokemonSpecies.add(new Specie1());
		pokemonSpecies.add(new Specie2());
		new NPCEventEditor(new NPC("images/CaptainMap.png","Jason"), pokemonSpecies, this);
	}

	@Override
	public Integer call(Event param) {
		System.out.printf("%s ", "I'm called!");
		System.out.printf("%s ", ((EventNPC)param).getNpc().getName());
		for(Instruction temp: param.getInstructions()){
			System.out.printf("%s ", temp.getClass().getName());
		}
		System.out.printf("\n");
		return null;
	}
}
