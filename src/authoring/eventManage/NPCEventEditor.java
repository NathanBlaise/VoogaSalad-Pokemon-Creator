package authoring.eventManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import authoring.databaseEditor.NPCChooser;
import data.LanguageReader;
import data.event.Event;
import data.event.EventNPC;
import data.event.Instruction;
import data.model.NPC;
import data.model.PokemonSpecie;

public class NPCEventEditor{
		private Stage stage;
		private EventNPC eventNPC;
		private Callback<Event, Integer> saver;
		private List<PokemonSpecie> pokemonSpecies;
		
		public NPCEventEditor(NPC selectedNPC, List<PokemonSpecie> pokemonSpecies, Callback<Event, Integer> saver){
			stage = new Stage();
			stage.show();
			eventNPC = new EventNPC(new NPC(selectedNPC));
			this.pokemonSpecies = new ArrayList<PokemonSpecie>(pokemonSpecies);
			this.saver = saver;
			editNPC(selectedNPC);
		}
		
		private void editNPC(NPC selectedNPC){
			BorderPane npcPane = new NPCChooser(new NPC(selectedNPC), new NPCEditor(eventNPC, saver)).showNPC();
			BorderPane borderPane = new BorderPane();
			borderPane.setCenter(npcPane);
			Button next = new Button(">");
			next.setOnMouseClicked(e->{
				editInstructions(null);
			});
			borderPane.setBottom(next);		
			stage.setScene(new Scene(borderPane));
		}
		
		private void editInstructions(Callback<List<Instruction>, Integer> saver){
			stage.setScene(new Scene(showEvent(eventNPC)));
		}
		
		private BorderPane showEvent(EventNPC eventNPC){
			BorderPane result =new BorderPane();
			Map<String, String> name2instruction = new HashMap<String, String>();
			for(String s: eventNPC.getAvailableInstructions()){
				name2instruction.put(new String(LanguageReader.convertLanguage("English", s)), new String(s));
			}
			Map<String, Function<Callback<Instruction, Integer>, Integer>> reactions = createReaction(result);
			result.setCenter(new EventInstructions(name2instruction, reactions, new InstructionListEditor(eventNPC, saver)).getList());
			return result;
		}
		
		private Map<String, Function<Callback<Instruction, Integer>, Integer>> createReaction(BorderPane instructionPane){
			Map<String, Function<Callback<Instruction, Integer>, Integer>> result = new HashMap<String, Function<Callback<Instruction, Integer>, Integer>>();
			result.put("InstructionNPCDialogue", e->{
				instructionPane.setRight(new InstructionNPCDialogueEditor(eventNPC.getNpc(), e).showEditor());
				stage.sizeToScene();
				return null;
			});
			result.put("InstructionNPCFight", e->{
				instructionPane.setRight(new InstructionNPCFightEditor(eventNPC.getNpc(), pokemonSpecies, e).showEditor());
				stage.sizeToScene();
				return null;
			});
			return result;
		}
		
		private class NPCEditor implements Callback<NPC, Integer>{

			private EventNPC event;
			private Callback<Event, Integer> saver;
			
			public NPCEditor(EventNPC event, Callback<Event, Integer> saver){
				this.event = event;
				this.saver = saver;
			}

			@Override
			public Integer call(NPC param) {
				event.setNpc(param);;
				saver.call(event);
				return null;
			}	
		}
		
		private class InstructionListEditor implements Callback<List<Instruction>, Integer>{

			private Event event;
			private Callback<Event, Integer> saver;
			
			public InstructionListEditor(Event event, Callback<Event, Integer> saver){
				this.event = event;
				this.saver = saver;
			}
			
			@Override
			public Integer call(List<Instruction> param) {
				event.setInstructions(new ArrayList<Instruction>(param));
				saver.call(event);
				return null;
			}	
		}
}
