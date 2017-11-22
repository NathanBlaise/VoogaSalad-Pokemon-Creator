package data.model.moves;

import data.model.Pokemon;
import data.model.PokemonStat;

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
	 * this function is static because the change procedure of Pokemon friend and enemy for the same name of Move is the same.
	 * This move reduce the defense of enemy to the one level lower than the current level
	 * @param friend - the friend Pokemon
	 * @param enemy - the enermy Pokemon
	 */
	public void move(Pokemon friend, Pokemon enemy){
		int currentLevelMinusOne = (friend.getCurrentLevel()>=1)?(friend.getCurrentLevel()-1):0;
		PokemonStat lowerStat = friend.getLevelStats().get(currentLevelMinusOne);
		PokemonStat currentStat = new PokemonStat(friend.getCurrentStat());
		currentStat.setNormalDefense(lowerStat.getNormalDefense());
		currentStat.setSpecialDefense(lowerStat.getSpecialDefense());
		friend.setCurrentStat(currentStat);
	}
	
}
