package data.model;

import java.io.Serializable;

/**
 * This class holds a set of stat that the race of pokemon holds
 * The formula of damage should be similar to the formula in https://bulbapedia.bulbagarden.net/wiki/Damage
 * @author cy122 ko72
 *
 */

public class PokemonStat implements Serializable{
	private static final long serialVersionUID = -7738935652284851234L; //needed for serialization
	private double speed;
	private double specialAttack;
	private double specialDefense;
	private double normalDefense;
	private double normalAttack;
	private double maxHP;
	private double HP; // the HP the pokemon has during battle

	/**
	 * The formula of damage should be similar to the formula in https://bulbapedia.bulbagarden.net/wiki/Damage
	 * @param maxHP
	 * @param normalAttack
	 * @param normalDefense
	 * @param specialAttack
	 * @param specialDefense
	 * @param speed
	 */
	public PokemonStat(double maxHP, double normalAttack, double normalDefense, double specialAttack, double specialDefense, double speed){
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
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setSpecialAttack(double specialAttack) {
		this.specialAttack = specialAttack;
	}

	public void setSpecialDefense(double specialDefense) {
		this.specialDefense = specialDefense;
	}

	public void setNormalDefense(double normalDefense) {
		this.normalDefense = normalDefense;
	}

	public void setNormalAttack(double normalAttack) {
		this.normalAttack = normalAttack;
	}
	
	public double getSpeed() {
		return speed;
	}

	public double getSpecialAttack() {
		return specialAttack;
	}

	public double getSpecialDefense() {
		return specialDefense;
	}

	public double getNormalDefense() {
		return normalDefense;
	}

	public double getNormalAttack() {
		return normalAttack;
	}

	public double getMaxHP() {
		return maxHP;
	}
	
	public double getHP(){
		return HP;
	}
	
	public void setHP(double HP){
		this.HP=(HP>maxHP)?maxHP:HP;
		this.HP=(HP<0)?0:HP;
	}

	public void setMaxHP(double maxHP) {
		this.maxHP = maxHP;
	}
	
}
