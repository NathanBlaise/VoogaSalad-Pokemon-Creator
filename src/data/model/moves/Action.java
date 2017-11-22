package data.model.moves;

import java.io.Serializable;

/**
 * 
 * @author cy122
 * 
 * This is an interface that holds a lambda that actually takes a action from a friend Pokemon to enermy Pokemon
 * it gives back the statuses of both pokemons, where Pokemon[0] should be friend Pokemon, Pokemon[1] should be enermy Pokemon
 * 
 * Every battle should not call resetCurrentStat in the middle of battling!
 *
 */

@FunctionalInterface
public interface Action<Friend, Enemy> extends Serializable{
	/**
	 * 
	 * IMPORTANT!
	 * Every battle should not call resetCurrentStat in the middle of battling!
	 * @param friend - friend Pokemon
	 * @param enemy - enemy Pokemon
	 */
	void move(Friend friend, Enemy enemy);
}
