package data.items;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author nathanlewis
 * Potion to increase HP by 200
 */

public class HyperPotion extends Item{
	
	//Specifies name, price and image in the constructor
	public HyperPotion(){
		itemName = "Hyper Potion";
		itemPrice = 1200;
	}
	
	//Overrides the useItem function that every item has
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		mine.getCurrentStat().setHP(200);
	}
}
