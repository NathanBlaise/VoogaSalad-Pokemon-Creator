package data.model.moves;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import data.model.Pokemon;
import data.model.PokemonStat;

/**
 * a set of Move which change the Stat of the Pokemon
 * @author cy122
 *
 */

public class MoveStatChange extends Move{
	private static final long serialVersionUID = 6971424179721908962L;

	/**
	 * only for serialization
	 */
	@Deprecated
	public MoveStatChange(){
		
	}
	
	/**
	 * 
	 * @param moveName - the name of move
	 * @param elemental - like Water, Fire
	 * @param maxPP - the maximum energy that a Pokémon requires in order to perform a move.
	 * @param changeFriendStat - true if the move wants to change the stat of frined pokemon
	 * @param level - - if level = 2, it means set the related property to be two levels upper the current level, 
	 * if level = -2, it means set the related property to be two levels lower than the current level
	 * @param properties - the properties which is going to be changed, like ["SpecialDefense", "NormalDefense"]
	 */
	public MoveStatChange(String moveName, String elemental, int maxPP, Boolean changeFriendStat, int level, List<String> properties){
		super(moveName, elemental, maxPP, new Action<Pokemon, Pokemon>(){
			private static final long serialVersionUID = -2791697233159877468L;
			@Override
			public void move(Pokemon friend, Pokemon enemy) {
				changeStat(friend, enemy, changeFriendStat, level, properties);	
			}		
		});
	}
	
	/**
	 * 
	 * @param friend - the friend pokemon
	 * @param enemy - the enemy pokemon
	 * @param changeFriendStat - true if the move wants to change the stat of frined pokemon
	 * @param level - if level = 2, it means set the related property to be two levels upper the current level, 
	 * if level = -2, it means set the related property to be two levels lower than the current level, 
	 * @param properties - like ["SpecialDefense", "NormalDefense"]
	 */
	private static void changeStat(Pokemon friend, Pokemon enemy, Boolean changeFriendStat, int level, List<String> properties){
		Pokemon targetPokemon;
		if(changeFriendStat){
			targetPokemon = friend;
		}else{
			targetPokemon = enemy;
		}
		try {
			int targetLevel = (targetPokemon.getCurrentLevel()+level<=targetPokemon.getMaxLevel())?
				(targetPokemon.getCurrentLevel()+level):targetPokemon.getMaxLevel();
			targetLevel = (targetLevel>=1)?targetLevel:1;
			PokemonStat targetStat = targetPokemon.getLevelStats().get(targetLevel);
			PokemonStat currentStat = targetPokemon.getCurrentStat();
			for(String property: properties){
			    	System.out.println("Changing property " + property ) ;
				Method getPropertyMethod = PokemonStat.class.getMethod("get"+property);
				int propertyTargetValue = (int)getPropertyMethod.invoke(targetStat);
				System.out.println("TargetValue: " + propertyTargetValue);
				Method setPropertyMethod = PokemonStat.class.getMethod("set"+property, int.class);
				setPropertyMethod.invoke(currentStat, propertyTargetValue);
				
			}
//			targetPokemon.setCurrentStat(currentStat);
		} catch (NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
			// DO NOTHING
		    	System.out.println("Change Stat Failed!");
			e.printStackTrace();
			System.exit(1);
		} 
	}
}
