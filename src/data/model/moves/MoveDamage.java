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
	
	public MoveDamage(String moveName, String elemental, int maxPP, int power){
		super(moveName, elemental, maxPP, (friend, enemy)->damage(friend, enemy, elemental, power));
	}


	private static void damage(Pokemon friend, Pokemon enemy, String moveElemental, int power) {
		int damage = DamageCalculator.getDamage(friend, enemy, moveElemental, power);
		PokemonStat newEnemyCurrentStat = enemy.getCurrentStat();
		newEnemyCurrentStat.setHP(newEnemyCurrentStat.getHP()-damage);
		enemy.setCurrentStat(newEnemyCurrentStat);
	}

	
}
