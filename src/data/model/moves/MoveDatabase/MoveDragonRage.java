package data.model.moves.MoveDatabase;

import data.model.Pokemon;
import data.model.moves.Move;
/**
 * http://pokemon.wikia.com/wiki/Dragon_Rage
 * @author cy122
 *
 */
public class MoveDragonRage extends Move{
	private static final long serialVersionUID = 7043137833593052558L;

	public MoveDragonRage(){
		super("Dragon Rage", "Dragon", 10, (friend, enemy)->damage(friend, enemy));
	}

	private static void damage(Pokemon friend, Pokemon enemy) {
	    	//inflicts a constant 40 points of damage to the enemy pokemon
		enemy.getCurrentStat().setHP(enemy.getCurrentStat().getHP()-40);
	}
}
