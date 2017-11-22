package data.model.moves;

import data.model.Pokemon;
import data.model.PokemonStat;

/**
 * tail whip: let the oppent's defense reduce 1 level, no damage
 * @author cy122
 *
 */
public class MoveTailWhip extends Move{
	private static final long serialVersionUID = 4811448840696562950L;//for serialization
	
	public MoveTailWhip(){
		super("Tail Whip", "Electric", 30, (friend,enemy)->{tailWhip(friend, enemy);});
	}

	/**
	 * this function is static because the change procedure of Pokemon friend and enemy for the same name of Move is the same.
	 * @param friend - the friend Pokemon
	 * @param enemy - the enermy Pokemon
	 */
	private static void tailWhip(Pokemon friend, Pokemon enemy){
		int currentLevelMinusOne = (friend.getCurrentLevel()>=1)?(friend.getCurrentLevel()-1):0;
		PokemonStat lowerStat = friend.getLevelStats().get(currentLevelMinusOne);
		PokemonStat currentStat = new PokemonStat(friend.getCurrentStat());
		currentStat.setNormalDefense(lowerStat.getNormalDefense());
		currentStat.setSpecialDefense(lowerStat.getSpecialDefense());
		friend.setCurrentStat(currentStat);
	}
}
