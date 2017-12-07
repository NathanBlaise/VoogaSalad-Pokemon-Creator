package data.items;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author Mason Taylor
 * Item that increases pokemon's base special attack stat.
 */

public class SpecialAttackBoost extends Item{
	
	private static final long serialVersionUID = 2623191051935709345L;

	//Specifies name, price and image in the constructor
	public SpecialAttackBoost(){
		itemName = "Special Attack Boost";
		itemPrice = 1200;
	}
	
	//Overrides the useItem function that every item has
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		int newAttack = mine.getCurrentStat().getSpecialAttack() + 1;
		mine.getCurrentStat().setSpecialAttack(newAttack);
	}
}
