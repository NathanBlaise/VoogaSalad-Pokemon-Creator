package data.event;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import data.map.GameMap;
import data.model.Pokemon;
import data.player.Player;
import engine.battle.BattleScene;
import engine.game.GameScene;

/**
 * this is a instruction for fighting with wild pokemon, it holds a private Pokemon member 
 * @see Instruction
 * 
 * @author cy122
 *
 *
 */
public class InstructionPokemonFight extends Instruction{
	
	private static final long serialVersionUID = -7387342417939089291L;
	private final int BATTLE_SCREEN_WIDTH = 720;
	private final int BATTLE_SCREEN_HEIGHT = 480;
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
	public void execute(int SCREEN_WIDTH, int SCREEN_HEIGHT, Player mainPlayer,
			GameMap mainMap, Event event, GameScene gameScene) {
		BattleScene battle = new BattleScene(BATTLE_SCREEN_WIDTH,BATTLE_SCREEN_HEIGHT,Color.WHITE,mainPlayer,null,pokemon, gameScene, gameScene.getStage());
		// Change the battle scene here
		((Stage) gameScene.getScene().getWindow()).setScene(battle.getScene());					
//		ArrayList<Instruction> newInstructions = event.getInstructions();
//		newInstructions.remove(this);
//		event.setInstructions(newInstructions);
	}

}
