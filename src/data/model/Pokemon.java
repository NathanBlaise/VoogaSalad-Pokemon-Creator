package data.model;

import java.io.Serializable;
import java.util.List;

import data.model.moves.Move;

/**
 * responsible for all the data needed for individual Pokemon
 * @author cy122 ko72 nathanlewis
 *
 */

public class Pokemon extends PokemonSpecie implements Serializable{
	private static final long serialVersionUID = -5135270377217672583L; //for serialization
	private static int moveNum = 4;
	private int currentLevel;
	private Move[] moves = new Move[moveNum];// the moves
	private String name = new String(""); //the name of Pokemon
	private double currentExperience; //the current experience got, it should greater or equal to 0, and smaller to the super.getUpNeededExp(currentLevel)
	private PokemonStat currentStat; // the stat of current pokemon
	
	public Pokemon(PokemonSpecie origin, String name, int targetLevel){
		super(origin);
		//something needs to be added to add a name to the pokemon
		this.name = new String(name);
		//currently, there is nothing setting the moves of the pokemon(needed for serialization)
		setField(targetLevel);
	}
	
	/**
	 * WARNING!
	 * This is only used for serialization, please use other constructor for any other intention
	 */
	@Deprecated
	public Pokemon(){
		
	}
	
	public Pokemon(PokemonSpecie origin, String name){
		super(origin);
		this.name = new String(name);
		setField(1);
	}
	
	public Pokemon(Pokemon pokemon){
		super(pokemon);
		this.name = pokemon.name;
		this.moves = new Move[moveNum];
		for(int i=0;i<moveNum;i++){
			if(pokemon.moves[i]!=null){
				this.moves[i] = new Move(pokemon.moves[i]);
			}
		}
		setField(pokemon.currentLevel);
	}
	
	private void setField(int targetLevel){
		currentLevel = (targetLevel <= super.getMaxLevel())?targetLevel:super.getMaxLevel();
		currentExperience = 0;
		for(int i=0;i<moveNum;i++){
			if(!super.getAvailableMoves(currentLevel).contains(moves[i])){
				moves[i]=null;
			}
		}
		currentStat = new PokemonStat(super.getCurrentStat(currentLevel));
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
		resetCurrentStat();
		return (currentLevel > originLevel);
	}
	
	/**
	 * this should be called after every battle to reset the currentStat
	 */
	public void resetCurrentStat(){
		int currentHP = currentStat.getHP();
		currentStat = new PokemonStat(super.getCurrentStat(currentLevel));
		currentStat.setHP(currentHP);
	}
	
	/**
	 *  change the move in moves from originMove to newMove
	 *  @return - whether the change succeeds
	 */
	public boolean changeMove(Move originMove, Move newMove){
		for(int k=0;k<moveNum;k++){
			if((moves[k]!=null) && moves[k].equals(newMove)){
				return true;
			}
		}
		for(Move i: super.getAvailableMoves(currentLevel)){
			if(i.equals(newMove)){
				for(int j=0;j<moveNum;j++){
					if((moves[j]!=null) && moves[j].equals(originMove)){
						moves[j] = new Move(newMove);
						return true;
					}
				}
				for(int j=0;j<moveNum;j++){
					if(moves[j]==null){
						moves[j] = new Move(newMove);
						return true;
					}
				}
				return false;
			}
		}
		return false;
	}
	
	/**
	 * fill up the HP to the max HP
	 */
	public void fillCurrentHP(){
		currentStat.setHP(currentStat.getMaxHP());
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	/**
	 * use changeCurrentLevel instead, because this function is used for serialization already
	 * @param level
	 */
	@Deprecated
	public void setCurrentLevel(int level) {
		this.currentLevel = level;
	}

	/**
	 * the substitute of setCurrentLevel
	 */
	public void changeCurrentLevel(int level) {
		currentLevel = level>=super.getMaxLevel()?super.getMaxLevel():level;
		currentLevel = currentLevel>=1?currentLevel:1;
		setField(currentLevel);
	}
	
	/**
	 * 
	 * @return - an array of current Moves
	 */
	public Move[] getMoves() {
		return moves;
	}
	
	/**
	 * WARNING! This is only used for serialization, please use changeMove(Move originMove, Move newMove) instead
	 * @param moves
	 */
	public void setMoves(Move[] moves) {
		this.moves = moves;
	}

	public double getCurrentExperience() {
		return currentExperience;
	}
	
	/**
	 * @param exp - the exp of currentExperienceS
	 */
	public void setCurrentExperience(double exp) {
		this.currentExperience = exp;
	}
	
	/**
	 * 
	 * @return - the stats of current level
	 */
	public PokemonStat getCurrentStat(){
		return currentStat;
	}
	
	/**
	 * set the current status of Pokemon
	 * @param currentStat - the current status of Pokemon
	 */
	public void setCurrentStat(PokemonStat currentStat) {
		this.currentStat = new PokemonStat(currentStat);
	}

	/**
	 * 
	 * @return - all the moves that can be learned in or below the current level
	 */
	public List<Move> getAvailableMoves(){
		return super.getAvailableMoves(currentLevel);
	}
	
	/**
	 * 
	 * @return - the exp needed for going to the next level
	 */
	public Double getUpNeededExp(){
		return super.getUpNeededExp(currentLevel);
	}
	
	/**
	 * get the current image path
	 */
	public String getCurrentImagePath(){
		return super.getImagePath(currentLevel);
	}
	
	/**
	 * 
	 * @return - the nick name of Pokemon
	 */
	public String getNickName(){
		return new String(name);
	}
	
	/**
	 * 
	 * @param name - the nick name of Pokemon
	 */
	public void setName(String name){
		this.name = name;
	}
	

	public static int getMoveNum(){
		return moveNum;

	}

}
