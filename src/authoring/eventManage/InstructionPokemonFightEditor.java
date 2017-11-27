package authoring.eventManage;

import javafx.scene.Node;
import javafx.util.Callback;
import data.event.InstructionPokemonFight;

public class InstructionPokemonFightEditor implements InstructionEditor{

	//private Callback<InstructionPokemonFight, Integer> saver;
	
	public InstructionPokemonFightEditor(InstructionPokemonFight selectedPokemon, Callback<InstructionPokemonFight, Integer> saver) {
		//this.saver = saver;
	}

	@Override
	public Node showEditor() {
		return null;
	}
	
}
