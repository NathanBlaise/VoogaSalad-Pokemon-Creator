package data.model;

import java.io.Serializable;

/**
 * Model for enemy in pacman game
 * @author nathanlewis
 *
 */

public class PacmanEnemy implements Serializable {

	private static final long serialVersionUID = 2190427143985953595L;
	private String imagePath; // the path of image of NPC shown on the screen
	private int speed;
	
	/**
	 * set the imagePath and speed to be empty value
	 */
	@Deprecated
	public PacmanEnemy(){
		this.imagePath = new String("");
		this.speed = 0;
	}
	
	/**
	 * 
	 * @param imagePath - the path of image of enemy shown on the screen
	 * @param speed - speed of enemy
	 */
	public PacmanEnemy(String imagePath, int speed){
		this.imagePath = new String(imagePath);
		this.speed = speed;
	}
	
	/**
	 * copy from the enemy
	 * @param enemy - the original enemy that the holding enemy will copy from
	 */
	public PacmanEnemy(PacmanEnemy enemy){
		this.imagePath = new String(enemy.imagePath);
		this.speed =  enemy.speed;
	}
	
	/**
	 * 
	 * @return - the path of image of enemy shown on the screen
	 */
	public String getImagePath() {
		return new String(imagePath);
	}

	/**
	 * 
	 * @param imagePath - the path of image of enemy shown on the screen
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = new String(imagePath);
	}

	/**
	 * 
	 * @return - speed of the enemy
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * 
	 * @param speed - speed of NPC
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
