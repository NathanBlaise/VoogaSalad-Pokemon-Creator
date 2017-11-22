package data.model.moves;

import java.io.Serializable;

/**
 * 
 * 
 * This is an interface that holds a lambda that actually takes a action from a friend Pokemon to enermy Pokemon
 * 
 * Every battle should not call pokemon.resetCurrentStat in the middle of battling!
 * @author cy122
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
