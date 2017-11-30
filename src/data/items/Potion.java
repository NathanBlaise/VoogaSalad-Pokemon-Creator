package data.items;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author Mason Taylor
 * Potion to increase HP by 50
 */

public class Potion extends Item{
	
	//Specifies name, price and image in the constructor
	public Potion(){
		itemName = "Potion";
		itemPrice = 1200;
	}
	
	//Overrides the useItem function that every item has
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		int newHP = mine.getCurrentStat().getHP() + 50;
		mine.getCurrentStat().setHP(newHP);
	}
}
