package data.items;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author Mason Taylor
 * Item that increases pokemon's max HP (and fills HP).
 */

public class SuperPotion extends Item{
	

	private static final long serialVersionUID = -9005950347526973001L;

	//Specifies name, price and image in the constructor
	public SuperPotion(){
		itemName = "Super Potion";
		itemPrice = 1200;
	}
	
	//Overrides the useItem function that every item has
	@SuppressWarnings("deprecation")
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		int newMaxHP = mine.getCurrentStat().getMaxHP() + 10;
		mine.getCurrentStat().setMaxHP(newMaxHP);
		mine.fillCurrentHP();
	}
}
