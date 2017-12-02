package data.player;

import java.io.Serializable;
import java.util.ArrayList;

import data.items.Item;
import data.model.Pokemon;
import engine.movement.Direction;

/**
 * The main character which the user plays the role of
 * 
 * @author 
 *
 */
public class Player implements Serializable{
	private static final long serialVersionUID = 556462866183029469L;

	
	private final static int speed = 3;
	//only holds these attributes for now
	private int posX, posY;
	public transient int rightspeed, leftspeed, downspeed, upspeed;
	private Pokemon[] pokemons; //the pokemons for battle, no pokemon in the warehouse for now
	private ArrayList<Item> items; //Items held by the player
	public transient Direction direction;
	//private Map<Item,Integer> Bag;  // a map from the item to the number of item
	
	public Player(){
		posX=50;
		posY=50;
		rightspeed = speed;
		leftspeed = speed;
		upspeed = speed;
		downspeed = speed;
//		direction = DOWN;
		pokemons = new Pokemon[6];
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
//	/*
//	 * Processes input using the input class
//	 */
//	public void processInput(Input input) {
//		if(canMove) {
//			if( input.isMoveUp()) {
//				moveUp();
//	        } else if( input.isMoveDown()) {
//	        		moveDown();
//	        } 
//			
//			if( input.isMoveLeft()) {
//				moveLeft();
//	        } else if( input.isMoveRight()) {
//	        		moveRight();
//	        } 
//		}
//	}
	
	// add bound over here
	
	public void moveUp() {
		if (posY <= 45) {
			
		}
		
		else {
		posY = posY - upspeed;
		}
	}
	
	public void moveDown() {
		if (posY >= 480) {
			
		}
		
		else {
		posY = posY + downspeed;
		}
	}
	
	public void moveLeft() {
		
		if (posX <= 40) {
			
		}
		
		else {
		posX = posX - leftspeed;
		}
	}
	
	public void moveRight() {
		if (posX >= 720  ) {
			
		}
		
		else {
		posX = posX + rightspeed;
		}
	}


	public Pokemon[] getPokemons() {
		return pokemons;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public void setPokemons(Pokemon[] pokemons) {
		for(int i=0;i<6 && i<pokemons.length;i++){
			if(pokemons[i]!=null){
				this.pokemons[i] = new Pokemon(pokemons[i]);
			}
		}
	}
	
}
