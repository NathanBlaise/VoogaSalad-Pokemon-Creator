package data.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

// the same meaning to trainer in Pokemon, the NPC don't need to stay in the Map:
// they are just a part of database, 
// and waiting for the Event to put NPC in the Instructions such as beginning a dialogue, or a battle

public class NPC implements Serializable{
	private static final long serialVersionUID = -5722924115188028268L;
	private String imagePath;
	private Set<String> availableInstructions;
	
	NPC(){
		this.imagePath = new String();
		this.availableInstructions = new HashSet<String>();
		addInstructions();
	}

	NPC(String imagePath){
		this.imagePath = new String(imagePath);
		addInstructions();
	}
	
	private void addInstructions(){
		availableInstructions.add("BeginDialogueInstruction");
		availableInstructions.add("BeginNPCBattleInstruction");
	}
	
	public String getImagePath() {
		return new String(imagePath);
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Set<String> getAvailableInstructions() {
		return availableInstructions;
	}

	public void setAvailableInstructions(Set<String> availableInstructions) {
		this.availableInstructions = availableInstructions;
	}
	
	
}
