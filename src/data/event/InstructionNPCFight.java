package data.event;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import data.map.GameMap;
import data.model.NPC;
import data.model.Pokemon;
import data.player.Player;
import engine.battle.NPCBattleHelper;
import engine.game.GameScene;

/**
 * this is a instruction for fighting with NPC, it holds a private NPC member and an array of Pokemon
 * @see Instruction
 * 
 * @author cy122
 *
 *
 */

public class InstructionNPCFight  extends Instruction{
	private static final long serialVersionUID = -7111511237505842406L;
	private static final int pokemonNum = 6; //the number of available Pokemon
	private NPC npc; //the NPC himself/herself
	private Pokemon[] pokemons = new Pokemon[pokemonNum]; //the pokemons belong to NPC

	/**
	 * WARNING!
	 * 
	 * this is only for serialization, it shouldn't be called for any intention else.
	 */
	@Deprecated
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
		return npc;
	}

	/**
	 * 
	 * @param npc - the holding NPC will be a copy based on the param npc
	 */
	public void setNpc(NPC npc) {
		this.npc = npc;
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
	
	public static int getPokemonNum(){
		return pokemonNum;
	}

	@Override
	public void execute(int SCREEN_WIDTH, int SCREEN_HEIGHT, Player mainPlayer,
			GameMap mainMap, Event event, GameScene gameScene) {
		NPCBattleHelper npcHelper = new NPCBattleHelper(SCREEN_WIDTH, SCREEN_HEIGHT, Color.WHITE, gameScene, gameScene.getInputList());
		((Stage)gameScene.getScene().getWindow()).setScene(npcHelper.getScene());
		npcHelper.startTimer();
	}

}
