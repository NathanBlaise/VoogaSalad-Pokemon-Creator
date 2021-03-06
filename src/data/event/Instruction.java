package data.event;

import java.io.Serializable;

import data.map.GameMap;
import data.player.Player;
import engine.game.GameScene;

/**
 * instruction is a small segment of a whole event, i.e.:
 * a event include: 1. talk with the NPC 2. battle with the NPC
 * 
 * The the number of instructions here are two:
 * One is to begin a dialogue
 * Another is to begin a battle with an NPC
 * @author cy122
 *
 *
 */
public abstract class Instruction implements Serializable{
	
	private static final long serialVersionUID = 3405957759448205372L;
	private boolean goNextInstruction = false;

	/**
	 * for serialization
	 */
	public Instruction(){
		
	}
	
	/**
	 * the execute instruction should call the related API in engine.
	 *	For example, if an instruction is to begin a battle, 
	 *	it should call the API of Engine related with beginning the battle in execute(Object...) 
	 * @param gameScene
	 * @param event 
	 * @param mainMap 
	 * @param mainPlayer 
	 * @param SCREEN_HEIGHT 
	 * @param SCREEN_WIDTH 
	 */
	public abstract void execute(int SCREEN_WIDTH, int SCREEN_HEIGHT, Player mainPlayer, GameMap mainMap, Event event, GameScene gameScene);

	public boolean isGoNextInstruction() {
		return goNextInstruction;
	}

	public void setGoNextInstruction(boolean goNextInstruction) {
		this.goNextInstruction = goNextInstruction;
	}
}
