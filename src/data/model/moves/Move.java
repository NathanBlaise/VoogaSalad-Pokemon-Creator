package data.model.moves;

import java.io.Serializable;

import data.model.PokemonStat;

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
	private Action action;
	
	public Move(Move move) {
		PP = move.maxPP;
		maxPP = move.maxPP;
		moveName = move.getMoveName();
		elemental = move.elemental;
		action = move.action;
	}
	
	/**
	 * WARNING!
	 * This is only used for serialization.
	 */
	@Deprecated
	public Move() {
		
	}


	public Move(String moveName, String elemental, int maxPP, Action action) {
		super();
		System.out.println("in the move class");
		this.moveName = moveName;
		this.elemental = elemental;
		this.PP = maxPP;
		this.maxPP = maxPP;
		this.action = action;
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

	public Action getAction() {
		return action;
	}
	
	public void setAction(Action action) {
		this.action = action;
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

	public PokemonStat[] move(PokemonStat friend, PokemonStat enermy){
		PokemonStat[] result;
		if(available()){
			PP--;
			result = action.move(friend, enermy);
		}else{
			result = new PokemonStat[2];
			result[0]=friend;
			result[1]=enermy;
		}
		return result;
	}

	public void setPP(int PP){
		this.PP = (PP>=maxPP)?maxPP:PP;
		this.PP = (this.PP< 0)?0:this.PP;
	}
}
