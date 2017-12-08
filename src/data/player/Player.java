package data.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import data.items.Item;
import data.model.Pokemon;

/**
 * The main character which the user plays the role of
 * 
 * @author cy122 nathan
 *
 */
public class Player implements Serializable{
	private static final long serialVersionUID = 556462866183029469L;
	private int posX, posY;
	private Pokemon[] pokemons;
	private double currency;
	private Map<String, Integer> items;  // a map from the item to the number of item
	private ArrayList<Pokemon> warehouse;
	
	public Player(){
		posX=0;
		posY=0;
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
	
	public Map<String, Integer> getItems() {
		return items;
	}

	public void setItems(Map<String, Integer> items) {
		this.items = items;
	}

	public void addItem(Item item) {
		String itemName = item.getItemName();
		if(items.containsKey(itemName)){
			items.put(itemName, items.get(itemName)+1);
		}else{
			items.put(itemName, 1);
		}
	}
	
	/**
	 * 
	 * @param item - the goal item to be deleted
	 * @return true if the item exists in the items and be deleted successfully.
	 */
	public boolean deleteItem(Item item){
		String itemName = item.getItemName();
		if(items.containsKey(itemName)){
			if((items.get(itemName)-1)<0){
				items.put(itemName, 0);
				return false;
			}else{
				items.put(itemName, items.get(itemName)-1);
				return true;
			}
		}else{
			return false;
		}
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
	
	public int itemsSize(){
		int number = 0;
		for(String key: items.keySet()){
			number += items.get(key);
		}
		return number;
	}
	
	
}
