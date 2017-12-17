package data.event;

import java.util.ArrayList;

import data.model.PacmanEnemy;

/**
 * 
 * @author nathanlewis
 *
 */

public class EventPacmanEnemy extends Event {
	
	private static final long serialVersionUID = 4841562235517072808L;

	
	private PacmanEnemy pacmanEnemy;
	private double xPos, yPos;

	/**
	 * set PacmanEnemy in default empty value
	 * This is only used for serialization!
	 */
	@Deprecated
	public EventPacmanEnemy(){
	}
	
	/**
	 * @param npc - the NPC that the npc included in that event will copy based on
	 */
	public EventPacmanEnemy(PacmanEnemy pacmanEnemy){
		super(pacmanEnemy.getImagePath());
		this.pacmanEnemy = pacmanEnemy;
		xPos = 0;
		yPos = 0;
	}

	/**
	 * 
	 * @return - a copy of the npc included in that event
	 */
	public PacmanEnemy getPacmanEnemy() {
		return pacmanEnemy;
	}

	/**
	 * 
	 * @param npc - the NPC that the npc included in that event will copy based on
	 */
	public void setPacmanEnemy(PacmanEnemy pacmanEnemy) {
		this.pacmanEnemy = pacmanEnemy;
	}
	
	public void setXPos(double x) {
		xPos = x;
	}
	
	public void setYPos(double y) {
		yPos = y;
	}
	
	public double getXPos() {
		return xPos;
	}

	public double getYPos() {
		return yPos;
	}

	@Override
	public ArrayList<String> getAvailableInstructions() {
		return null;
	}

	@Override
	public boolean instructionAddable() {
		return false;
	}

}
