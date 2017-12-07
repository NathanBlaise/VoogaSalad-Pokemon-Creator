package data.items;

import java.util.Random;

import data.model.Pokemon;
import data.player.Player;

/**
 * catch the enemy pokemon
 * @author cy122
 *
 */
public class PokemonBall extends Item{

	private static final long serialVersionUID = 1291677902844899580L;

	public PokemonBall(){
		itemName = "Pokemon Ball";
		itemPrice = 1200;
	}
	
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		Double num = new Random().nextDouble();
		if(num<0.3){
			player.getWarehouse().add(new Pokemon(Enemy));
			Enemy.getCurrentStat().setHP(0);;
		}
	}

}