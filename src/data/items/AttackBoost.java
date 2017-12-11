package data.items;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author Mason Taylor
 * Item that increases pokemon's base normal attack stat.
 */

public class AttackBoost extends Item{
	
	private static final long serialVersionUID = -2798938829777415612L;

	//Specifies name, price and image in the constructor
	public AttackBoost(){
		itemName = "AttackBoost";
		itemPrice = 50;
	}
	
	//Overrides the useItem function that every item has
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		int newAttack = mine.getCurrentStat().getNormalAttack() + 1;
		mine.getCurrentStat().setNormalAttack(newAttack);
	}
}
