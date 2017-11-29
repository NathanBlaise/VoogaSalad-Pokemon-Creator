package data.items;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author Mason Taylor
 * Item that increases pokemon's base Defense stat.
 */

public class DefenseBoost extends Item{
	
	//Specifies name, price and image in the constructor
	public DefenseBoost(){
		itemName = "Defense Boost";
		itemPrice = 1200;
	}
	
	//Overrides the useItem function that every item has
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		int newDefense = mine.getCurrentStat().getNormalDefense() + 1;
		mine.getCurrentStat().setNormalDefense(newDefense);
	}
}
