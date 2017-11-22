package data.model.moves;

import data.model.Pokemon;

/**
 * 
 * @author cy122
 * This is an example of how to use Action
 * 
 */
public class ActionExample implements Action<Pokemon, Pokemon>{
	private static final long serialVersionUID = -333059440502600274L;//needed for serialization
	
	public ActionExample(){
		
	}

	@Override
	/**
	 * for this move, the friend Pokemon attack the enemy Pokemon by reducing 5 in the HP of enemy
	 */
	public void move(Pokemon friend, Pokemon enemy) {
		Pokemon[] result = new Pokemon[2];
		enemy.getCurrentStat().setHP(enemy.getCurrentStat().getHP()-5);
		result[0]=friend;
		result[1]=enemy;
	}
	
}
