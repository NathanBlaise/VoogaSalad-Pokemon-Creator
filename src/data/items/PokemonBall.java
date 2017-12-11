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
	private boolean caught=false;

	public PokemonBall(){
		itemName = "PokemonBall";
		itemPrice = 50;
	}
	
	@Override
	public void useItem(Player player, Pokemon mine, Pokemon Enemy) {
		Double num = new Random().nextDouble();
		if(num<0.3){
			player.getWarehouse().add(new Pokemon(Enemy));
			player.addPokemon(Enemy);
			caught=true;
			
		}
	}
	
	public boolean getCaught() {
		return caught;
	}

}
