package data.model.moves;

import java.io.Serializable;

import data.model.Pokemon;

/**
 * 
 * @author cy122
 * this encapsulates a turn of battle by having the name of move and PP number of move
 *
 */

public class Move implements Serializable{
	
	private static final long serialVersionUID = -9188380778013480995L; //used for serialization
	private String moveName = "";
	private String elemental = ""; //such as Water, Fire
	private int PP; //the times that the move can be used
	private int maxPP;
	
	/*
	Things with Action are commented out for now, not sure how to serialize them or if it is necessary for Move
	*/
	/**
	 * @see Action
	 * action is used for the actual attack/defense action of move, please check this.move() for further information.
	 */
	private Action<Pokemon, Pokemon> action;
	
	public Move(Move move) {
		PP = move.maxPP;
		maxPP = move.maxPP;
		moveName = move.getMoveName();
		elemental = move.elemental;
		action = (Serializable & Action<Pokemon, Pokemon>) move.action;
	}
	
	/**
	 * WARNING!
	 * This is only used for serialization.
	 */
	@Deprecated
	public Move() {
		
	}

	/**
	 * 
	 * @param moveName - the name of move
	 * @param elemental - like Fire, Water
	 * @param maxPP - the maximum PP of Move, like 40
	 * @param action - @see Action
	 */
	public Move(String moveName, String elemental, int maxPP, Action<Pokemon, Pokemon> action) {
		this.moveName = moveName;
		this.elemental = elemental;
		this.PP = maxPP;
		this.maxPP = maxPP;
		this.action = (Serializable & Action<Pokemon, Pokemon>) action;
	}
	
	@Override
	public boolean equals(Object move){
		if((move instanceof Move)&&(((Move)move).getMoveName().equals(getMoveName()))){
			return true;
		}
		return false;
	}
	
	public String getMoveName(){
		return moveName;
	}
	
	public void setMoveName(String moveName) {
		this.moveName = moveName;
	}
	
	public int getMaxPP(){
		return maxPP;
	}
	
	public void setMaxPP(int maxPP) {
		this.maxPP = maxPP;
	}
	
	public int getPP(){
		return PP;
	}

	public Action<Pokemon, Pokemon> getAction() {
		return (Serializable & Action<Pokemon, Pokemon>) action;
	}
	
	public void setAction(Action<Pokemon, Pokemon> action) {
		this.action = (Serializable & Action<Pokemon, Pokemon>) action;
	}
	
	public String getElemental() {
		return elemental;
	}

	public void setElemental(String elemental) {
		this.elemental = elemental;
	}

	public boolean available(){
		return PP>0;
	}

	/**
	 * this is the most important part of move
	 * It should be used repetitively during the battle
	 * @param friend - the Pokemon who is going to use the current move
	 * @param enemy - the Pokemon who is NOT going to use the current move
	 * @return an array. The first element is friend, the second element is enemy.
	 */
	public Pokemon[] move(Pokemon friend, Pokemon enemy){
		Pokemon[] result = new Pokemon[2];
		if(available()){
			PP--;
			System.out.println("PP Down: " + this.getPP());
			action.move(friend, enemy);
		}
		result[0]=friend;
		result[1]=enemy;
	
		return result;
	}

	public void setPP(int PP){
		this.PP = (PP>=maxPP)?maxPP:PP;
		this.PP = (this.PP< 0)?0:this.PP;
	}
}
