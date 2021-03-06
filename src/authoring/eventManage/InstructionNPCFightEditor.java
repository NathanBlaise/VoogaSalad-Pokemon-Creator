package authoring.eventManage;

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
import data.model.Pokemon;
import data.model.PokemonSpecie;
/**
 * the editor to choose the pokemon of the NPC to fight
 * @author cy122
 *
 */
public class InstructionNPCFightEditor implements InstructionEditor{
	private Callback<Instruction, Integer> saver;
	private InstructionNPCFight npcFight;
	private Map<Button, Pokemon> buttonMap = new HashMap<Button, Pokemon>();
	private List<PokemonSpecie> pokemonSpecies;
	BorderPane pokemonList = new BorderPane();
	
	public InstructionNPCFightEditor(InstructionNPCFight npcFight, List<PokemonSpecie> pokemonSpecies, Callback<Instruction, Integer> saver){
		this.npcFight = npcFight;
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
			if(npcFight.getPokemons()[i]!=null){
				tempButton.setText(npcFight.getPokemons()[i].getNickName());
				buttonMap.put(tempButton, npcFight.getPokemons()[i]);
			}else{
//				Pokemon newPokemon = new Pokemon(pokemonSpecies.get(0), pokemonSpecies.get(0).getSpecieName());
//				tempButton.setText(newPokemon.getNickName());
//				buttonMap.put(tempButton, newPokemon);
			    	//do not fill player or NPC with random pokemons
			    	tempButton.setText("New Pokemon");
			    	buttonMap.put(tempButton, null);
			}
			tempButton.setPrefSize(70, 230/(pokemonNum/gridWidth));
			tempButton.setOnMouseClicked(e->{
			    	Pokemon defaultPokemon = new Pokemon(pokemonSpecies.get(0), pokemonSpecies.get(0).getSpecieName());
				Pokemon selectedPokemon = buttonMap.get(tempButton)==null? defaultPokemon: buttonMap.get(tempButton);
				pokemonList.setRight(new PokemonChooser(pokemonSpecies, selectedPokemon, tempButton).showPokemon());
				pokemonList.autosize();
				tempButton.call(selectedPokemon);
			});
			buttonPane.getChildren().add(tempButton);
		}
		BorderPane buttonList = new BorderPane();
		buttonList.setCenter(buttonPane);
		pokemonList.setCenter(buttonList);
		return pokemonList;
	}
	
	private class PokemonButton extends Button implements Callback<Pokemon, Integer>{

		@Override
		public Integer call(Pokemon param) {
			this.setText(param.getNickName());
			buttonMap.put(this, param);
			Pokemon[] pokemons = new Pokemon[InstructionNPCFight.getPokemonNum()];
			int i=0;
			for(Button button: buttonMap.keySet()){
				Pokemon tempPokemon = buttonMap.get(button);
				if((tempPokemon!=null)&&(tempPokemon.getName()!=null)&&(!tempPokemon.getName().equals(""))){
					pokemons[i] = tempPokemon;
					i++;
				}
			}
			npcFight.setPokemons(pokemons);
			saver.call(npcFight);
			return null;
		}
		
	}

	@Override
	public Node showEditor() {
		return createPokemonList();
	}
}
