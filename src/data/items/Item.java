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
	
	public abstract void useItem(Player player, Pokemon mine, Pokemon Enemy);
	
	public String getItemName() {
		return itemName;
	}
		
}
