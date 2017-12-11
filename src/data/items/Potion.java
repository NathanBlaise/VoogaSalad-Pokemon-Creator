package data.items;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author Mason Taylor
 * Potion to increase HP by 50
 */

public class Potion extends Item{

	private static final long serialVersionUID = 3284561105645468766L;

	//Specifies name, price and image in the constructor
	public Potion(){
		itemName = "Potion";
		itemPrice = 50;
	}
	
	//Overrides the useItem function that every item has
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		int newHP = mine.getCurrentStat().getHP() + 50;
		int maxHP=mine.getLevelStats().get(mine.getCurrentLevel()).getMaxHP();
	
		if (newHP>maxHP) {
			mine.getCurrentStat().setHP(maxHP);
			return;
		}
		mine.getCurrentStat().setHP(newHP);
	}
}
