package data.player;

import java.io.Serializable;
import java.util.ArrayList;

import data.items.Item;
import data.model.Pokemon;
import engine.movement.Input;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * The main character which the user plays the role of
 * 
 * @author 
 *
 */
public class Player implements Serializable{
	private static final long serialVersionUID = 556462866183029469L;
	
	private final int speed = 4;
	private final int PLAYER_SIZE = 48;
	//only holds these attributes for now
	private int posX, posY, dx, dy;
	private Pokemon[] pokemons; //the pokemons for battle, no pokemon in the warehouse for now
	private ArrayList<Item> items; //Items held by the player
	private Image image;
	private ImageView playerImage;
	//private Map<Item,Integer> Bag;  // a map from the item to the number of item
	
	public Player(){
		posX=300;
		posY=300;
		dx = 0;
		dy = 0;
		pokemons = new Pokemon[6];
		image = new Image("file:images/emerald_down_rest.png");
		playerImage = new ImageView(image);
		playerImage.setFitHeight(PLAYER_SIZE);
		playerImage.setFitWidth(PLAYER_SIZE);
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
	
	public void move() {
		posX += dx;
		posY += dy;
	}
	
	public void drawPlayer() {
		playerImage.setX(posX);
		playerImage.setY(posY);
	}
	
	/*
	 * Change into using Observer/Observable pattern
	 */
	
	public ImageView getPlayerImage() {
		return playerImage;
	}
	
	/*
	 * Processes input using the input class
	 */
	public void processInput(Input input) {
		if( input.isMoveUp()) {
            dy = -speed;
            System.out.println("Up Pressed");
        } else if( input.isMoveDown()) {
            dy = speed;
        } else {
            dy = 0;
        }
		
		if( input.isMoveLeft()) {
            dx = -speed;
        } else if( input.isMoveRight()) {
            dx = speed;
        } else {
            dx = 0;
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
