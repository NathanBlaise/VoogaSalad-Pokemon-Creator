package data.model.moves;

import data.model.Pokemon;

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
	 * Constructor for moves that does not also damage the pokemon using it
	 * @param moveName - the name of move
	 * @param elemental - such as Fire, Water
	 * @param maxPP - the maximum PP number
	 * @param power - the power of move, related to the actual damage to the opponent, 
	 * where the damage is debuffed or buffed power according to the elemental
	 */
	public MoveDamage(String moveName, String elemental, int maxPP, int power){
		this(moveName,elemental,maxPP,power,0);
	}
	

	/**
	 * 
	 * @param moveName - the name of move
	 * @param elemental - such as Fire, Water
	 * @param maxPP - the maximum PP number
	 * @param power - the power of move, related to the actual damage to the opponent, 
	 * where the damage is debuffed or buffed power according to the elemental
	 * @param selfDamageRatio the fraction of damage that is dealt to self after using the move,
	 * 0 if the move does not damage self
	 */
	public MoveDamage(String moveName, String elemental, int maxPP, int power, double selfDamageRatio){
		super(moveName, elemental, maxPP, new Action<Pokemon, Pokemon>(){
			private static final long serialVersionUID = -1569968083239145204L;
			@Override
			public void move(Pokemon friend, Pokemon enemy) {
				damage(friend, enemy, elemental, power,selfDamageRatio);
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
 	 * @param selfDamageRatio the fraction of damage that is dealt to self after using the move,
	 * 0 if the move does not damage self
	 */
	private static void damage(Pokemon friend, Pokemon enemy, String moveElemental, int power, double selfDamageRatio) {
		int damage = DamageCalculator.getDamage(friend, enemy, moveElemental, power);
		int enemyCurrentHp = enemy.getCurrentStat().getHP();
		System.out.println("Damage is calculated to be " + damage);
		System.out.println("current enemy health is " + enemyCurrentHp);
		enemy.getCurrentStat().setHP(enemyCurrentHp-damage);
		System.out.println("After move, enemy health is " + enemy.getCurrentStat().getHP());
		int self_damage = (int)(selfDamageRatio * damage);
		if(self_damage!=0) {
		    int friendCurrentHp = friend.getCurrentStat().getHP();
		    System.out.println("current self health is " + friendCurrentHp);
		    friend.getCurrentStat().setHP(friendCurrentHp-self_damage);
		    System.out.println("After move, self health is " + friend.getCurrentStat().getHP());
		}

	}

	
}
