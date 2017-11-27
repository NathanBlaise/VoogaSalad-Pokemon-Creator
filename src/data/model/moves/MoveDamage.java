package data.model.moves;

import data.model.Pokemon;
import data.model.PokemonStat;

/**
 * a set of moves which has the certain power and takes damage to the opponent Pokemon
 * @author cy122
 *
 */
public class MoveDamage extends Move{
	private static final long serialVersionUID = 2692337947083408494L;
	
	/**
	 * only for serialization
	 */
	@Deprecated
	public MoveDamage(){
		
	}
	
	/**
	 * 
	 * @param moveName - the name of move
	 * @param elemental - such as Fire, Water
	 * @param maxPP - the maximum PP number
	 * @param power - the power of move, related to the actual damage to the opponent, 
	 * where the damage is debuffed or buffed power according to the elemental
	 */
	public MoveDamage(String moveName, String elemental, int maxPP, int power){
		super(moveName, elemental, maxPP, new Action<Pokemon, Pokemon>(){
			private static final long serialVersionUID = -1569968083239145204L;
			@Override
			public void move(Pokemon friend, Pokemon enemy) {
				damage(friend, enemy, elemental, power);
			}
		});
	}

	/**
	 * a function that will used during the battle and is encapsulated as Action
	 * @param friend - the friend Pokemon
	 * @param enemy - the enemy Pokemon
	 * @param moveElemental - the elemental of move
	 * @param power - the power of move, related to the actual damage to the opponent, 
	 * where the damage is debuffed or buffed power according to the elemental
	 */
	private static void damage(Pokemon friend, Pokemon enemy, String moveElemental, int power) {
		int damage = DamageCalculator.getDamage(friend, enemy, moveElemental, power);
		PokemonStat newEnemyCurrentStat = enemy.getCurrentStat();
		newEnemyCurrentStat.setHP(newEnemyCurrentStat.getHP()-damage);
		enemy.setCurrentStat(newEnemyCurrentStat);
	}

	
}
