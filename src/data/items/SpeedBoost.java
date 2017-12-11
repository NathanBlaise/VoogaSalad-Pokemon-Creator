package data.items;

import data.model.Pokemon;
import data.player.Player;

/**
 * 
 * @author Mason Taylor
 * Item that increases pokemon's base speed stat.
 */

public class SpeedBoost extends Item{
	
	private static final long serialVersionUID = -5438630976235840138L;

	//Specifies name, price and image in the constructor
	public SpeedBoost(){
		itemName = "SpeedBoost";
		itemPrice = 50;
	}
	
	//Overrides the useItem function that every item has
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		int newSpeed = mine.getCurrentStat().getSpeed() + 1;
		mine.getCurrentStat().setSpeed(newSpeed);
	}
}
