package data.database.moves;

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
	private String moveName;
	private int PP; //the times that the move can be used
	private int maxPP;
	private Action action;
	
	public Move(Move move) {
		PP = move.maxPP;
		maxPP = move.maxPP;
		moveName = move.getMoveName();
		action = move.action;
	}


	public Move(String moveName, int maxPP, Action action) {
		super();
		this.moveName = moveName;
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
		return new String(moveName);
	}
	
	public int getMaxPP(){
		return maxPP;
	}
	
	public int getPP(){
		return PP;
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
