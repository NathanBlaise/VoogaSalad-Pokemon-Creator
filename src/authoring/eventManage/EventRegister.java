package authoring.eventManage;

import java.util.Vector;

import javafx.scene.image.Image;

/**
 * 
 * @author cy122
 * 
 * This provides a data manager to make the UI saves the data of event into this register
 * 
 * Every time the data of event changes in the user interface, the UI should call the related EventRegister to this event
 *
 */

public class EventRegister {
	private String imagePath;
	// the instructions of event, such as activating a battle, or begin a conversation
	private Vector<String> instructions=new Vector<String>();
	
	public EventRegister(){
		//TODO: set all the data related to the event to be default value and add this object to the related save backup of game.
	}
	
	public void setImagePath(String imagePath){
		this.imagePath= new String(imagePath);
	}
	
	public String getImagePath(){
		return new String(imagePath);
	}
	
	public void addInstruction(int index, String instruction){
		instructions.add(index, instruction);
	}
	
	public void removeInstruction(int index){
		instructions.remove(index);
	}
	
	public Iterable<String> copyInstruction(){
		return new Vector<String>(instructions);
	}
	
}
