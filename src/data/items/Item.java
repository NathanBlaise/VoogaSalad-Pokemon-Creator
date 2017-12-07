package data.items;

import java.io.Serializable;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author nathanlewis
 * This abstract class is extended by all specific item subclass - which are used in reflection
 */

public abstract class Item implements Serializable {

	private static final long serialVersionUID = -4411861881244476839L;
	protected int itemPrice;
	protected String itemName;
	private String imagePath;
	
	public Item(){
		
	}
	
	public Item(String itemName, int itemPrice, String imagePath){
		this.itemName = new String(itemName);
		this.itemPrice = itemPrice;
		this.imagePath = new String(imagePath);
	}
	
	public abstract void useItem(Player player, Pokemon mine, Pokemon Enemy);

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
		
}
