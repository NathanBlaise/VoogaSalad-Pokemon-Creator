package authoring.eventManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.databaseEditor.PokemonChooser;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.util.Callback;
import data.event.Instruction;
import data.event.InstructionNPCFight;
import data.model.NPC;
import data.model.Pokemon;
import data.model.PokemonSpecie;

public class InstructionNPCFightEditor implements InstructionEditor{
	private Callback<Instruction, Integer> saver;
	private NPC npc;
	private Map<Button, Pokemon> buttonMap = new HashMap<Button, Pokemon>();
	private List<PokemonSpecie> pokemonSpecies;
	BorderPane pokemonList = new BorderPane();
	
	public InstructionNPCFightEditor(NPC npc, List<PokemonSpecie> pokemonSpecies, Callback<Instruction, Integer> saver){
		this.npc = npc;
		this.pokemonSpecies = pokemonSpecies;
		this.saver = saver;
		
	}
	
	private BorderPane createPokemonList(){
		pokemonList.getChildren().clear();
		TilePane buttonPane = new TilePane();
		int pokemonNum = InstructionNPCFight.getPokemonNum();
		int gridWidth = (int)Math.round(Math.sqrt(pokemonNum));
		buttonPane.setPrefColumns(gridWidth);
		for(int i=0;i<pokemonNum;i++){
			PokemonButton tempButton = new PokemonButton();
			tempButton.setPrefSize(70, 230/(pokemonNum/gridWidth));
			tempButton.setOnMouseClicked(e->{
				pokemonList.setRight(new PokemonChooser(pokemonSpecies, buttonMap.get(tempButton), tempButton).showPokemon());
				tempButton.call(buttonMap.get(tempButton));
			});
			buttonPane.getChildren().add(tempButton);
		}
		
		pokemonList.setCenter(buttonPane);
		return pokemonList;
	}
	
	private class PokemonButton extends Button implements Callback<Pokemon, Integer>{

		@Override
		public Integer call(Pokemon param) {
			this.setText(param.getNickName());
			buttonMap.put(this, param);
			saver.call(new InstructionNPCFight(npc, new ArrayList<Pokemon>().toArray(new Pokemon[InstructionNPCFight.getPokemonNum()])));
			return null;
		}
		
	}

	@Override
	public Node showEditor() {
		return createPokemonList();
	}
}
