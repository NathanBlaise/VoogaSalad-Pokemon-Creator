package data.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This class holds a set of stat that the race of pokemon holds
 * The formula of damage should be similar to the formula in https://bulbapedia.bulbagarden.net/wiki/Damage
 * @author cy122 ko72
 *
 */

public class PokemonStat implements Serializable{
	private static final long serialVersionUID = -7738935652284851234L; //needed for serialization
	private int speed;
	private int specialAttack;
	private int specialDefense;
	private int normalDefense;
	private int normalAttack;
	private int maxHP;
	private int HP; // the HP the pokemon has during battle

	/**
	 * The formula of damage should be similar to the formula in https://bulbapedia.bulbagarden.net/wiki/Damage
	 * @param maxHP
	 * @param normalAttack
	 * @param normalDefense
	 * @param specialAttack
	 * @param specialDefense
	 * @param speed
	 */
	public PokemonStat(int maxHP, int normalAttack, int normalDefense, int specialAttack, int specialDefense, int speed){
		this.maxHP=maxHP;
		this.normalAttack=normalAttack;
		this.normalDefense=normalDefense;
		this.specialAttack=specialAttack;
		this.specialDefense=specialDefense;
		this.speed=speed;
		this.HP = maxHP;
	}
	
	/**
	 * WARNING!
	 * This constructor is only for serialization, it shouldn't be used for any intention else.
	 */
	@Deprecated
	public PokemonStat() {
		
	}
	
	public PokemonStat(PokemonStat origin){
		this.maxHP=origin.maxHP;
		this.normalAttack=origin.normalAttack;
		this.normalDefense=origin.normalDefense;
		this.specialAttack=origin.specialAttack;
		this.specialDefense=origin.specialDefense;
		this.speed=origin.speed;
		this.HP = origin.maxHP;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setSpecialAttack(int specialAttack) {
		this.specialAttack = specialAttack;
	}

	public void setSpecialDefense(int specialDefense) {
		this.specialDefense = specialDefense;
	}

	public void setNormalDefense(int normalDefense) {
		this.normalDefense = normalDefense;
	}

	public void setNormalAttack(int normalAttack) {
		this.normalAttack = normalAttack;
	}
	
	public int getSpeed() {
		return speed;
	}

	public int getSpecialAttack() {
		return specialAttack;
	}

	public int getSpecialDefense() {
		return specialDefense;
	}

	public int getNormalDefense() {
		return normalDefense;
	}

	public int getNormalAttack() {
		return normalAttack;
	}

	public int getMaxHP() {
		return maxHP;
	}
	
	public int getHP(){
		return HP;
	}
	
	public void setHP(int HP){
		this.HP=(HP>maxHP)?maxHP:HP;
		this.HP=(HP<0)?0:HP;
	}

	/**
	 * please use constructor to create a new PokemonStat instead
	 * @param maxHP
	 */
	@Deprecated
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	
	public Map<String, Integer> getStatMap(){
		Map<String, Integer> result = new HashMap<String, Integer>();
		result.put("maxHP", maxHP);
		result.put("normalAttack", normalAttack);
		result.put("normalDefense", normalDefense);
		result.put("specialAttack", specialAttack);
		result.put("specialDefense", specialDefense);
		result.put("speed", speed);
		return result;
	}
	
}
