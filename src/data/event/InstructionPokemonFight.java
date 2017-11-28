package data.event;

import data.model.Pokemon;

/**
 * this is a instruction for fighting with wild pokemon, it holds a private Pokemon member 
 * @see Instruction
 * 
 * @author cy122
 *
 *
 */
public class InstructionPokemonFight implements Instruction{
	
	private static final long serialVersionUID = -7387342417939089291L;
	private Pokemon pokemon;

	/**
	 * WARNING!
	 * 
	 * this is only for serialization, it shouldn't be called for any intention else.
	 */
	public InstructionPokemonFight(){
	}
	
	public InstructionPokemonFight(Pokemon pokemon){
		this.pokemon = pokemon;
	}
	
	/**
	 * 
	 * @return a copy of the holding pokemon, containing the same value, different reference
	 */
	public Pokemon getPokemon() {
		return pokemon;
	}

	/**
	 * 
	 * @param pokemon - the pokemon which the private pokemon in class will copy from
	 */
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	@Override
	/**
	 * @see Instruction#execute
	 */
	public void execute(Object... parameters) {
		// TODO call the API in Engine to begin a battle with pokemon
		
	}

}
