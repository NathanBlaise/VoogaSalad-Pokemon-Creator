package data.items;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author Mason Taylor
 * Item that does 5 points of damage that can't be defended against.
 */

public class Claw extends Item{
	
	private static final long serialVersionUID = -5516124310392605466L;

	//Specifies name, price and image in the constructor
	public Claw(){
		itemName = "Claw";
		itemPrice = 50;
	}
	
	//Overrides the useItem function that every item has
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		int newHP = Enemy.getCurrentStat().getHP() - 5;
		Enemy.getCurrentStat().setHP(newHP);
	}
}
