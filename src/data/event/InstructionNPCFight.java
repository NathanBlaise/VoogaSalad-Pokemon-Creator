package data.event;

import data.model.NPC;
import data.model.Pokemon;

/**
 * 
 * @see Instruction
 * 
 * @author cy122
 * 
 * this is a instruction for fighting with NPC, it holds a private NPC member and an array of Pokemon
 *
 */

public class InstructionNPCFight  implements Instruction{
	private static final long serialVersionUID = -7111511237505842406L;
	private NPC npc = new NPC(); //the NPC himself/herself
	private Pokemon[] pokemons = new Pokemon[6]; //the pokemons belong to NPC

	/**
	 * WARNING!
	 * 
	 * this is only for serialization, it shouldn't be called for any intention else.
	 */
	public InstructionNPCFight(){
	}
	
	public InstructionNPCFight(NPC npc, Pokemon[] pokemons){
		this.npc = npc;
		this.pokemons = pokemons;
	}
	
	/**
	 * 
	 * @return - a copy of holding NPC
	 */
	public NPC getNpc() {
		return new NPC(npc);
	}

	/**
	 * 
	 * @param npc - the holding NPC will be a copy based on the param npc
	 */
	public void setNpc(NPC npc) {
		this.npc = new NPC(npc);
	}

	/**
	 * 
	 * @return - the pokemon belonging to the NPC
	 */
	public Pokemon[] getPokemons() {
		return pokemons;
	}

	/**
	 * 
	 * @param pokemons - the pokemon belonging to the NPC
	 */
	public void setPokemons(Pokemon[] pokemons) {
		this.pokemons = pokemons;
	}

	@Override
	/**
	 * @see Instruction#execute
	 */
	public void execute(Object... parameters) {
		// TODO call the API in Engine to begin a battle with npc
		
	}

}
