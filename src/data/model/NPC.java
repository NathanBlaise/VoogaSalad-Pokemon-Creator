package data.model;

import java.io.Serializable;

/**
 * NPC is the same meaning to trainer in Pokemon, the NPC don't need to stay in the Map:
 * they are just a part of model, 
 * and NPC is waiting for the Event to put NPC in the Instructions such as beginning a dialogue, or a battle
 * @author cy122
 *
 */

public class NPC implements Serializable{
	private static final long serialVersionUID = -5722924115188028268L;
	private String imagePath; // the path of image of NPC shown on the screen
	private String name; //name of NPC
	
	/**
	 * set the imagePath and name to be empty value
	 */
	@Deprecated
	public NPC(){
		this.imagePath = new String("");
		this.name = new String("");
	}

	/**
	 * 
	 * @param imagePath - the path of image of NPC shown on the screen
	 * @param name - name of NPC
	 */
	public NPC(String imagePath, String name){
		this.imagePath = new String(imagePath);
		this.name = new String(name);
	}
	
	/**
	 * copy from the npc
	 * @param npc - the original npc that the holding npc will copy from
	 */
	public NPC(NPC npc){
		this.imagePath = new String(npc.imagePath);
		this.name = new String(npc.name);
	}
	
	/**
	 * 
	 * @return - the path of image of NPC shown on the screen
	 */
	public String getImagePath() {
		return new String(imagePath);
	}

	/**
	 * 
	 * @param imagePath - the path of image of NPC shown on the screen
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = new String(imagePath);
	}

	/**
	 * 
	 * @return - name of NPC
	 */
	public String getName() {
		return new String(name);
	}

	/**
	 * 
	 * @param name - name of NPC
	 */
	public void setName(String name) {
		this.imagePath = new String(imagePath);
	}
	
}
