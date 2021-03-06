package data.event;

import java.util.ArrayList;

import data.model.Pokemon;

/**
 * This is a event for pokemon.
 * The available instructions are 1: "BeginMonsterBattleInstruction";
 * For the case that there is only one type of instruction for now, 
 * the class automatically creates a instruction for fighting in the constructor
 * 
 * @see Event
 * 
 * @author cy122
 * 
 *
 */
public class EventPokemon extends Event{
	private static final long serialVersionUID = 1562499630824551623L;
	private static final ArrayList<String> availableInstructions = new ArrayList<String>();
	private Pokemon pokemon;
	private static final boolean instructionAddable = false; //whether the length of instruction array list can be flexible
	
	
	{
		availableInstructions.add("InstructionPokemonFight");
	}
	
	/**
	 * this constructor is only for serialization, it shouldn't be called for any intention else.
	 */
	@Deprecated
	public EventPokemon(){
		super();
	}
	
	/**
	 * 
	 * @param imagePath - the path of event image
	 * @param pokemon - the pokemon for fighting
	 */
	public EventPokemon(Pokemon pokemon){
		//super(new PathReader().getString("DefaultImage"));
		super(pokemon.getCurrentImagePath());
		this.pokemon=pokemon;
		ArrayList<Instruction> instructions = new ArrayList<Instruction>();
		instructions.add(new InstructionPokemonFight(pokemon));
		super.setInstructions(instructions);
	}
	
	/**
	 * 
	 * @return - a copy of the holding pokemon
	 */
	public Pokemon getPokemon() {
		return pokemon;
	}

	/**
	 * 
	 * @param pokemon - a pokemon that the holding pokemon will copy based on 
	 */
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	@Override
	/**
	 * @see Event#getAvailableInstructions()
	 */
	public ArrayList<String> getAvailableInstructions() {
		return new ArrayList<String>(availableInstructions);
	}

	@Override
	public boolean instructionAddable() {
		return instructionAddable;
	}
}
