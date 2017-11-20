package data.event;

import java.io.Serializable;

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
public interface Instruction extends Serializable{

	/**
	 * the execute instruction should call the related API in engine.
	 *	For example, if an instruction is to begin a battle, 
	 *	it should call the API of Engine related with beginning the battle in execute(Object...) 
	 * @param parameters - will be discussed later
	 */
	public abstract void execute(Object... parameters);
}
