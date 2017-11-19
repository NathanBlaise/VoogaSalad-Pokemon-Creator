package data.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Vector;

import data.database.moves.Move;

/**
 * 
 * @author cy122
 *
 */

public class PokemonSpecie implements Serializable{
	private static final long serialVersionUID = -7134194712571483657L; //needed for serialization
	private String specieName = ""; //the name of race
	private String elemental = ""; //such as Fire, Water
	private int maxLevel; //the max level of the pokemon race, when the pokemon goes to this level, 
							//it cannot get more experience or go higher level. Example: maxLevel = 100
	private Map<Integer,Move> levelMoves; //the key here is the lowest level for the pokemon to learn the related move
	private Map<Integer,PokemonStat> levelStats; //the value here is the PokemonStat for the related level, 
												//any int between 1 to maxLevel (include 1 and maxLevel) should stay in the key set.
	private Map<Integer,Double> levelExp; // the value here is the experience needed to go to next level for the current level key
	private Map<Integer,String> levelEvolutionImagePath; // the value here is the path of Image of pokemon for the related level.
													// if there is no image for the current level, 
													//then choose the image of the biggest level which is lower than or equal to the current level
	public PokemonSpecie(String raceName, String ability, int maxLevel,
			Map<Integer, Move> levelMoves,
			Map<Integer, PokemonStat> levelStats,
			Map<Integer, Double> levelExp,
			Map<Integer, String> levelEvolutionImagePath) {
		this.specieName = raceName;
		this.elemental = ability;
		this.maxLevel = maxLevel;
		this.levelMoves = levelMoves;
		this.levelStats = levelStats;
		this.levelExp = levelExp;
		this.levelEvolutionImagePath = levelEvolutionImagePath;
	}
	
	public PokemonSpecie() {
		
	}
	
	public PokemonSpecie(PokemonSpecie origin){
		this.specieName = origin.specieName;
		this.elemental = origin.elemental;
		this.maxLevel = origin.maxLevel;
		this.levelMoves = origin.levelMoves;
		this.levelStats = origin.levelStats;
		this.levelExp = origin.levelExp;
		this.levelEvolutionImagePath = origin.levelEvolutionImagePath;	
	}
	
	public String getSpecieName(){
		return new String(specieName);
	}
	
	public void setSpecieName(String raceName) {
		this.specieName = raceName;
	}
	
	public String getElemental(){
		return new String(elemental);
	}
	
	public void setElemental(String ability) {
		this.elemental = ability;
	}
	
	public int getMaxLevel(){
		return maxLevel;
	}
	
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
	
	public Map<Integer, Move> getLevelMoves(){
		return levelMoves;
	}
	
	public void setLevelMoves(Map<Integer, Move> moveMap) {
		this.levelMoves = moveMap;
	}
	
	public Map<Integer, PokemonStat> getLevelStats(){
		return levelStats;
	}
	
	public void setLevelStats(Map<Integer, PokemonStat> statMap) {
		this.levelStats = statMap;
	}
	
	public Map<Integer, Double> getLevelExp(){
		return levelExp;
	}
	
	public void setLevelExp(Map<Integer, Double> expMap) {
		this.levelExp = expMap;
	}
	
	public Map<Integer, String> getLevelEvolutionImagePath(){
		return levelEvolutionImagePath;
	}
	
	public void setLevelEvolutionImagePath(Map<Integer, String> stringMap) {
		this.levelEvolutionImagePath = stringMap;
	}
	
	protected Iterable<Move> getAvailableMoves(int currentLevel){
		Vector<Move> availableMoves = new Vector<Move>();
		for(int i:levelMoves.keySet()){
			if(i<=currentLevel){
				availableMoves.add(new Move(levelMoves.get(i)));
			}
		}
		return availableMoves;
	}
	
	protected PokemonStat getCurrentStat(int currentLevel){
		return new PokemonStat(levelStats.get(currentLevel));
	}
	
	protected Double getUpNeededExp(int currentLevel){
		return new Double(levelExp.get(currentLevel));
	}
	
	protected String levelEvolutionImagePath(int currentLevel){
		String currentImagePath = "";
		for(int i:levelMoves.keySet()){
			if(i<=currentLevel){
				currentImagePath = new String(levelEvolutionImagePath.get(i));
			}
		}
		return currentImagePath;
	}
}
