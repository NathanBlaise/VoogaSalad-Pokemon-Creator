package data.model.moves;

import java.util.HashSet;
import java.util.Set;

import data.model.Pokemon;

/**
 * 
 * @author cy122
 * 
 * This is a class calculating the damage between Pokemons
 *
 */
public class DamageCalculator {

	/**
	 * the set including the types of physical move, this is used for defining whether normal attack or special attack of attacker should be used,
	 * and whether special defense or normal defense of the defender should be used
	 */
	private static Set<String> physicalMove = new HashSet<String>(){
		private static final long serialVersionUID = -102889753243143607L;

		{
			this.add("Normal");
			this.add("Fighting");
			this.add("Flying");
			this.add("Groud");
			this.add("Rock");
			this.add("Bug");
			this.add("Ghost");
			this.add("Poison");
			this.add("Steel");
		}
	};
	
	/**
	 * according to the formula in https://bulbapedia.bulbagarden.net/wiki/Damage
	 * @param attacker - the attacking Pokemon
	 * @param defender - the defending Pokemon
	 * @param moveElemental - such as Fire, Water
	 * @param movePower - the power of Move
	 * @return
	 */
	public static int getDamage(Pokemon attacker, Pokemon defender, String moveElemental, int movePower){
		double damage = 0.0;
		double attack = physicalMove.contains(moveElemental)?attacker.getCurrentStat().getNormalAttack():attacker.getCurrentStat().getSpecialAttack();
		double defense = physicalMove.contains(moveElemental)?defender.getCurrentStat().getNormalDefense():defender.getCurrentStat().getSpecialDefense();
		damage = (((((2.0*attacker.getCurrentLevel())/5.0)+2)*movePower*(attack/defense))/50.0+2)*getModifier(attacker.getElemental(), defender.getElemental(), moveElemental);
		return (int)Math.round(damage);
	}
	
	/**
	 * calculate if there is any modifier of the damage,
	 * For example, if the attackerElemental is Fire, and the defenseElemental is Water, the modifier here will be 0.5 for example.
	 * If the attackerElemental is Water, and the defenseElemental is Fire, the modifier here will be 2 for example.
	 * return 1 for now for convenience
	 * @param attackElemental - the elemental of the attacking Pokemon
	 * @param defenseElemental - the elemental of the defending Pokemon
	 * @param moveElemental - the elemental of the move
	 * @return
	 */
	private static int getModifier(String attackElemental, String defenseElemental, String moveElemental){
		return 1;
	}
}
