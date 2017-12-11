package data.items;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author Mason Taylor
 * Potion to increase HP by 100
 */

public class HiPotion extends Item{
	
	private static final long serialVersionUID = 7173105637066176780L;

	//Specifies name, price and image in the constructor
	public HiPotion(){
		itemName = "HiPotion";
		itemPrice = 50;
	}
	
	//Overrides the useItem function that every item has
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		int newHP = mine.getCurrentStat().getHP() + 100;
		mine.getCurrentStat().setHP(newHP);
	}
}
