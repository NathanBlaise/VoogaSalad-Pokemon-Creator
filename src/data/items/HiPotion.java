package data.items;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author Mason Taylor
 * Potion to increase HP by 100
 */

public class HiPotion extends Item{
	
	//Specifies name, price and image in the constructor
	public HiPotion(){
		itemName = "Hi Potion";
		itemPrice = 1200;
	}
	
	//Overrides the useItem function that every item has
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		int newHP = mine.getCurrentStat().getHP() + 100;
		mine.getCurrentStat().setHP(newHP);
	}
}
