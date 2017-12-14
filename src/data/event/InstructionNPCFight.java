package data.event;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import data.map.GameMap;
import data.model.NPC;
import data.model.Pokemon;
import data.player.Player;
import engine.battle.BattleScene;
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
	private final int BATTLE_SCREEN_WIDTH = 720;
	private final int BATTLE_SCREEN_HEIGHT = 480;
	private static final int experienceLevel = 50;
	private static final int currency = 50;
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
	public void execute(int SCREEN_WIDTH, int SCREEN_HEIGHT, Player mainPlayer, GameMap mainMap, Event event, GameScene gameScene) {
		beginBattle(livePokemon(), mainPlayer, gameScene, gameScene.getStage(), e->{
			for(Pokemon pokemon: mainPlayer.getPokemons()){
				if(pokemon!=null){
					pokemon.absorbExperience(experienceLevel);
				}
			}
			mainPlayer.setCurrency(mainPlayer.getCurrency()+currency);
			super.setGoNextInstruction(true);
			gameScene.changeBackScene();
			return null;
		}, h->{
			refillAllPokemons(pokemons);
			gameScene.changeBackScene();
			return null;
		});
	}
	
	private Pokemon livePokemon(){
		for(Pokemon pokemon: pokemons){
			if((pokemon!=null)&&(pokemon.getCurrentStat().getHP()>0)){
				return pokemon;
			}
		}
		return null;
	}
	
	public static void refillAllPokemons(Pokemon[] pokemons){
		for(Pokemon pokemon: pokemons){
			if(pokemon!=null){
				pokemon.fillCurrentHP();
			}
		}
	}
	
	/**
	 * 
	 * @param livePokemon
	 */
	public Integer beginBattle(Pokemon livePokemon, Player player, GameScene gameScene, Stage stage, Callback<Integer, Integer> winAction, Callback<Integer, Integer> loseAction){
		if(livePokemon!=null){
			BattleScene battle = new BattleScene(BATTLE_SCREEN_WIDTH,BATTLE_SCREEN_HEIGHT,Color.WHITE,player,livePokemon, gameScene, gameScene.getStage(), e->{
				return beginBattle(livePokemon(), player, gameScene, stage, winAction, loseAction);
			}, h->{
				return loseAction.call(0);
			});
			stage.setScene(battle.getScene());
			return null;
		}else{
			return winAction.call(0);
		}
	}

}
