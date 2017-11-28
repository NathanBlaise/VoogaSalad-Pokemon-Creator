package data.event;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * event holds a image which presents itself and a series of instructions which will be triggered after it's notified
 * 
 * instruction is a small segment of a whole event, i.e.:
 * a event include: 1. talk with the NPC 2. battle with the NPC
 * 
 * The the number of instructions here are 2:
 * The first is to begin a dialogue
 * The second is to begin a battle with an NPC
 * 
 * @author cy122
 * @see Instruction
 * 
 */

public abstract class Event implements Serializable{
	private static final long serialVersionUID = -1088455784075495760L;
	private ArrayList<Instruction> instructions = new ArrayList<Instruction>(); // a series of instructions that will be triggered by the event
	//since javafx's image cannot be serialized, here it stores the path (prefer absolute path) of the image
	private String imagePath;
	
	/**
	 * set imagePath and instructions to be default empty value
	 * ONLY USED FOR Serialization!
	 */
	@Deprecated
	public Event(){
		instructions = new ArrayList<Instruction>();
		imagePath = new String("");
	}
	
	public Event(String imagePath){
		this.imagePath = new String(imagePath);
	}
	
	/**
	 * 
	 * @return - the instructions that will be triggered by the event
	 */
	public ArrayList<Instruction> getInstructions() {
		return new ArrayList<Instruction>(instructions);
	}

	/**
	 * 
	 * @param instructions - the instructions that will be triggered by the event
	 */
	public void setInstructions(ArrayList<Instruction> instructions) {
		this.instructions = new ArrayList<Instruction>(instructions);
	}

	
	/**
	 * 
	 * @return - the path of the image of the event
	 */
	public String getImagePath() {
		return new String(imagePath);
	}

	/**
	 * 
	 * @param imagePath - the path of the image of the event
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = new String(imagePath);
	}
	
	/**
	 * 
	 * @return - the names of instructions which the user can choose from
	 */
	public abstract ArrayList<String> getAvailableInstructions();
	
	/**
	 * 
	 * @return - whether the length of instruction array list can be flexible
	 */
	public abstract boolean instructionAddable();
}
