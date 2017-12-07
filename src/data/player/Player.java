package data.player;

import java.io.Serializable;
import java.util.ArrayList;

import data.items.Item;
import data.model.Pokemon;

/**
 * The main character which the user plays the role of
 * 
 * @author 
 *
 */
public class Player implements Serializable{
	private static final long serialVersionUID = 556462866183029469L;
	private int posX, posY;
	private Pokemon[] pokemons;
	private ArrayList<Item> items;
	private double currency;
	//private Map<Item,Integer> Bag;  // a map from the item to the number of item
	private ArrayList<Pokemon> warehouse;
	
	public Player(){
		posX=50;
		posY=50;
		pokemons = new Pokemon[6];
		currency = 0;
		warehouse = new ArrayList<Pokemon>();
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

	public Pokemon[] getPokemons() {
		return pokemons;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}

	public void setPokemons(Pokemon[] pokemons) {
		for(int i=0;i<6 && i<pokemons.length;i++){
			if(pokemons[i]!=null){
				this.pokemons[i] = new Pokemon(pokemons[i]);
			}
		}
	}

	public double getCurrency() {
		return currency;
	}

	public void setCurrency(double currency) {
		this.currency = currency;
		if(this.currency<0){
			this.currency = 0;
		}
	}

	public ArrayList<Pokemon> getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(ArrayList<Pokemon> warehouse) {
		this.warehouse = warehouse;
	}
	
	
	
}
