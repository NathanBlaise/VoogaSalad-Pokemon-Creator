package data.items;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author nathanlewis
 * Potion to increase HP by 200
 */

public class HyperPotion extends Item{
	
	private static final long serialVersionUID = -4505067090640977063L;

	//Specifies name, price and image in the constructor
	public HyperPotion(){
		itemName = "HyperPotion";
		itemPrice = 50;
	}
	
	//Overrides the useItem function that every item has
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		int newHP = mine.getCurrentStat().getHP() + 200;
		mine.getCurrentStat().setHP(newHP);
	}
}
