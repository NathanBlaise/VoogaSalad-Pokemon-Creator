package data.event;

import java.util.ArrayList;
import data.model.Pokemon;

/**
 * 
 * @author cy122
 * 
 * This is a event for pokemon.
 * The available instructions are 1: "BeginMonsterBattleInstruction";
 * For the case that there is only one type of instruction for now, 
 * the class automatically creates a instruction for fighting in the constructor
 *
 */
public class EventPokemon extends Event{
	private static final long serialVersionUID = 1562499630824551623L;
	private Pokemon pokemon;
	
	/**
	 * this constructor is only for serialization, it shouldn't be called for any intention else.
	 */
	public EventPokemon(){
		setAvailableInstructions();
	}
	
	public EventPokemon(Pokemon pokemon){
		this.pokemon=pokemon;
		ArrayList<Instruction> instructions = new ArrayList<Instruction>();
		instructions.add(new InstructionPokemonFight(pokemon));
		super.setInstructions(instructions);
	}
	
	private void setAvailableInstructions(){
		ArrayList<String> result = new ArrayList<String>();
		result.add("BeginMonsterBattleInstruction");
		super.setAvailableInstructions(result);
	}
}
