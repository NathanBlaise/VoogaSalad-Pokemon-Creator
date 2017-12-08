package data.items;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author Mason Taylor
 * Item that increases pokemon's base Defense stat.
 */

public class DefenseBoost extends Item{
	
	private static final long serialVersionUID = -7113219972405967861L;

	//Specifies name, price and image in the constructor
	public DefenseBoost(){
		itemName = "DefenseBoost";
		itemPrice = 50;
	}
	
	//Overrides the useItem function that every item has
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		int newDefense = mine.getCurrentStat().getNormalDefense() + 1;
		mine.getCurrentStat().setNormalDefense(newDefense);
	}
}
