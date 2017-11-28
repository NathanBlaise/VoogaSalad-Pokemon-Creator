package data.items;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author Mason Taylor
 * Item that increases pokemon's max HP (and fills HP).
 */

public class SuperPotion extends Item{
	
	//Specifies name, price and image in the constructor
	public SuperPotion(){
		itemName = "Super Potion";
		itemPrice = 1200;
	}
	
	//Overrides the useItem function that every item has
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		int newMaxHP = mine.getCurrentStat().getMaxHP() + 10;
	}
}
