package data.event;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author 
 *
 * event holds a image which presents itself and a series of instructions which will be triggered after it's notified
 *
 */

public class Event implements Serializable{
	private static final long serialVersionUID = -1088455784075495760L;
	private ArrayList<Instruction> instructions;
	private ArrayList<String> availableInstructions;
	//since javafx's image cannot be serialized, here it stores the path (prefer absolute path) of the image
	private String imagePath;
	
	public Event(){
		instructions = new ArrayList<Instruction>();
		availableInstructions = new ArrayList<String>();
		imagePath = new String("");
	}
	
	public ArrayList<Instruction> getInstructions() {
		return instructions;
	}
	public void setInstructions(ArrayList<Instruction> instructions) {
		this.instructions = new ArrayList<Instruction>(instructions);
	}
	public ArrayList<String> getAvailableInstructions() {
		return availableInstructions;
	}
	public void setAvailableInstructions(ArrayList<String> availableInstructions) {
		this.availableInstructions = new ArrayList<String>(availableInstructions);
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = new String(imagePath);
	}
	
	
}
