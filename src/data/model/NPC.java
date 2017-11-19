package data.model;

import java.io.Serializable;

// the same meaning to trainer in Pokemon, the NPC don't need to stay in the Map:
// they are just a part of database, 
// and waiting for the Event to put NPC in the Instructions such as beginning a dialogue, or a battle

public class NPC implements Serializable{
	private static final long serialVersionUID = -5722924115188028268L;
	private String imagePath;
	private String name;
	
	public NPC(){
		this.imagePath = new String("");
		this.name = new String("");
	}

	NPC(String imagePath, String name){
		this.imagePath = new String(imagePath);
		this.name = new String(name);
	}
	
	public NPC(NPC npc){
		this.imagePath = new String(npc.imagePath);
		this.name = new String(npc.name);
	}
	
	public String getImagePath() {
		return new String(imagePath);
	}

	public void setImagePath(String imagePath) {
		this.imagePath = new String(imagePath);
	}

	public String getName() {
		return new String(name);
	}

	public void setName(String name) {
		this.imagePath = new String(imagePath);
	}
	
}
