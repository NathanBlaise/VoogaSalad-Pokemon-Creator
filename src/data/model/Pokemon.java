package data.model;

import java.io.Serializable;

import data.model.moves.Move;

/**
 * responsible for all the data needed for individual Pokemon
 * @author cy122 ko72
 *
 */

public class Pokemon extends PokemonSpecie implements Serializable{
	private static final long serialVersionUID = -5135270377217672583L; //for serialization
	private static int moveNum = 4;
	private int currentLevel;
	private Move[] moves = new Move[moveNum];// the moves
	private String name = new String(""); //the name of Pokemon
	private double currentExperience; //the current experience got, it should greater or equal to 0, and smaller to the super.getUpNeededExp(currentLevel)
	private int currentHP;
	
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
		this.currentLevel = pokemon.currentLevel;
		this.currentHP = pokemon.currentHP;
		this.currentExperience = pokemon.currentExperience;
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
	
	/**
	 *  change the move in moves from originMove to newMove
	 */
	public void changeMove(Move originMove, Move newMove){
		outerloop:
		for(Move i: super.getAvailableMoves(currentLevel)){
			if(i.equals(newMove)){
				for(int j=0;j<moveNum;j++){
					if((moves[j]!=null) && moves[j].equals(originMove)){
						for(int k=0;k<moveNum;k++){
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
	
	/**
	 * fill up the HP to the max HP
	 */
	public void fillCurrentHP(){
		currentHP = super.getCurrentStat(currentLevel).getMaxHP();
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	public void setCurrentLevel(int level) {
		this.currentLevel = level;
		setField(level);
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
		PokemonStat result = new PokemonStat(super.getCurrentStat(currentLevel)){
			private static final long serialVersionUID = 1637221041100808975L;
			@Override
			public void setHP(int HP){
				this.setHP(HP);
				currentHP=this.getHP(); //let currentHP be the same as the HP of battle
			}
		};
		result.setHP(currentHP);
		return result;
	}
	
	/**
	 * 
	 * @return - all the moves that can be learned in or below the current level
	 */
	public Iterable<Move> getAvailableMoves(){
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
	public String getName(){
		return new String(name);
	}
	
	/**
	 * 
	 * @param name - the nick name of Pokemon
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * @return - the current HP
	 */
	public int getCurrentHP() {
		return currentHP;
	}
	
	/**
	 * WARNING! this method do not ensure that the current HP will be lower or equal to the maxHP after setting.
	 * @param hp - the current HP
	 */
	public void setCurrentHP(int hp) {
		this.currentHP = hp;
	}
	
	public static int getMoveNum(){
		return moveNum;
	}

}
