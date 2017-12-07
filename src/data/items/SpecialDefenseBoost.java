package data.items;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author Mason Taylor
 * Item that increases pokemon's base special defense stat.
 */

public class SpecialDefenseBoost extends Item{
	
	private static final long serialVersionUID = -2731818860770379521L;

	//Specifies name, price and image in the constructor
	public SpecialDefenseBoost(){
		itemName = "Special Defense Boost";
		itemPrice = 1200;
	}
	
	//Overrides the useItem function that every item has
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		int newDefense = mine.getCurrentStat().getSpecialDefense() + 1;
		mine.getCurrentStat().setSpecialDefense(newDefense);
	}
}
