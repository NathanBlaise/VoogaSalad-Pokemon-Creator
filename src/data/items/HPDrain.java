package data.items;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author Mason Taylor
 * Item that absorbs 10 points of HP from enemy.
 */

public class HPDrain extends Item{
	
	private static final long serialVersionUID = -5735649083685458381L;

	//Specifies name, price and image in the constructor
	public HPDrain(){
		itemName = "HPDrain";
		itemPrice = 50;
	}
	
	//Overrides the useItem function that every item has
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		int newHPEnemy = Enemy.getCurrentStat().getHP() - 10;
		int newHPMine = mine.getCurrentStat().getHP() + 10; 
		Enemy.getCurrentStat().setHP(newHPEnemy);
		mine.getCurrentStat().setHP(newHPMine);
	}
}
