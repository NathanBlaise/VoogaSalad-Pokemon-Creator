package data.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import data.model.Pokemon;

/**
 * The main character which the user plays the role of
 * 
 * @author cy122 nathan
 *
 */
public class Player extends PacmanPlayer{
	private static final long serialVersionUID = 556462866183029469L;
	private Pokemon[] pokemons;
	private double currency;
	private Map<String, Integer> items = new HashMap<String, Integer>();  // a map from the item to the number of item, String stands for the class name of item
	private Set<String> keyItems = new HashSet<String>();
	private ArrayList<Pokemon> warehouse;
	private String currentMapName = "";
	
	public Player(){
		super();
		pokemons = new Pokemon[6];
		currency = 0;
		warehouse = new ArrayList<Pokemon>();
		keyItems = new HashSet<String>();
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

	public void addItem(String itemName) {
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
	public boolean deleteItem(String itemName){
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
	
	public void addPokemon(Pokemon pokemon) {
		for(int i=0;i<6;i++){
			if(pokemons[i]==null){
				this.pokemons[i] = pokemon;
				break;
			}
			// if the bag of pokemon is full, the pokemon goes to the warehouse
			if(i==5){
				warehouse.add(pokemon);
			}
		}
		
	}
	
	public void deletePokemon(Pokemon pokemon) {
		boolean remove=false;
		for(int i=0;i<6;i++){
			if (remove==true) {
				pokemons[i-1]=pokemons[i];
			}
			else if(pokemons[i]!=null && pokemons[i].getNickName().equals(pokemon.getNickName())){
			
				this.pokemons[i] = null;
				remove=true;
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
	
	public Set<String> getKeyItems() {
		return keyItems;
	}

	public void setKeyItems(Set<String> keyItems) {
		this.keyItems = keyItems;
	}

	public String getCurrentMapName() {
		return currentMapName;
	}

	public void setCurrentMapName(String currentMapName) {
		this.currentMapName = currentMapName;
	}

	public int itemsSize(){
		int number = 0;
		for(String key: items.keySet()){
			number += items.get(key);
		}
		return number;
	}

}