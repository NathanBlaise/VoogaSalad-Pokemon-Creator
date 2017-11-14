package data.database;

import java.io.Serializable;

import data.database.moves.Move;

/**
 * 
 * @author cy122
 *
 */

public class Pokemon extends PokemonRace implements Serializable{
	private static final long serialVersionUID = -5135270377217672583L; //for serialization
	private int currentLevel;
	private Move[] moves = new Move[4];// the moves
	private double currentExperience; //the current experience got, it should greater or equal to 0, and smaller to the super.getUpNeededExp(currentLevel)
	private double currentHP;
	
	public Pokemon(PokemonRace origin, int targetLevel){
		super(origin);
		setField(targetLevel);
	}
	
	public Pokemon(PokemonRace origin){
		super(origin);
		setField(1);
	}
	
	private void setField(int targetLevel){
		currentLevel = (targetLevel <= super.getMaxLevel())?targetLevel:super.getMaxLevel();
		currentHP = super.getCurrentStat(currentLevel).getMaxHP();
		currentExperience = 0;
	}

	/**
	 * level up automatically
	 * @param experience - the experience got after a battle
	 * @return levelUp - whether Level is up. cannot make sure whether level up more than 1 if boolean is true.
	 */
	public boolean absorbExperience(double experience){
		int originLevel = currentLevel;
		double restExp = experience;
		while((currentLevel!=super.getMaxLevel())&&(restExp>0.0)){
			if((restExp - (super.getUpNeededExp(currentLevel)-currentExperience))>=0){
				restExp = restExp - (super.getUpNeededExp(currentLevel)-currentExperience);
				currentLevel++;
				currentExperience = 0.0;
			}else{
				currentExperience += restExp;
				restExp = 0.0;
			}
		}
		return (currentLevel > originLevel);
	}
	
	// change the move in moves from originMove to newMove
	public void changeMove(Move originMove, Move newMove){
		outerloop:
		for(Move i: super.getAvailableMoves(currentLevel)){
			if(i.equals(newMove)){
				for(int j=0;j<4;j++){
					if((moves[j]!=null) && moves[j].equals(originMove)){
						for(int k=0;k<4;k++){
							if((moves[k]!=null) && moves[k].equals(newMove)){
								break outerloop;
							}
						}
						moves[j] = newMove;
						break outerloop;
					}
				}
			}
		}
	}
	
	public void fillCurrentHP(){
		currentHP = super.getCurrentStat(currentLevel).getMaxHP();
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}

	public Move[] getMoves() {
		return moves;
	}

	public double getCurrentExperience() {
		return currentExperience;
	}
	
	public PokemonStat getCurrentStat(){
		PokemonStat result = new PokemonStat(super.getCurrentStat(currentLevel)){
			private static final long serialVersionUID = 1637221041100808975L;
			@Override
			public void setHP(double HP){
				this.setHP(HP);
				currentHP=this.getHP(); //let currentHP be the same as the HP of battle
			}
		};
		result.setHP(currentHP);
		return result;
	}
	
	public Iterable<Move> getAvailableMoves(){
		return super.getAvailableMoves(currentLevel);
	}
	
	public Double getUpNeededExp(){
		return super.getUpNeededExp(currentLevel);
	}
	
	public String levelEvolutionImagePath(int currentLevel){
		return super.levelEvolutionImagePath(currentLevel);
	}
}
