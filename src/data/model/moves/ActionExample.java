package data.model.moves;

import data.model.PokemonStat;

/**
 * 
 * @author cy122
 * This is an example of how to use Action
 * 
 */
public class ActionExample implements Action{
	private static final long serialVersionUID = -333059440502600274L;//needed for serialization
	
	public ActionExample(){
		
	}

	@Override
	/**
	 * for this move, the friend Pokemon attack the enemy Pokemon by reducing 5 in the HP of enemy
	 */
	public PokemonStat[] move(PokemonStat friend, PokemonStat enermy) {
		PokemonStat[] result = new PokemonStat[2];
		enermy.setHP(enermy.getHP()-5);
		result[0]=friend;
		result[1]=enermy;
		return result;
	}
	
}
