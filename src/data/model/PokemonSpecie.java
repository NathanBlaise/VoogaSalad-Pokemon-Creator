package data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import data.model.moves.Move;

/**
 * This holds all the data needed for create a specie of Pokemon
 * @author cy122 ko72
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
	
	public PokemonSpecie(String raceName, String elemental, int maxLevel,
			Map<Integer, Move> levelMoves,
			Map<Integer, PokemonStat> levelStats,
			Map<Integer, Double> levelExp,
			Map<Integer, String> levelEvolutionImagePath) {
		this.specieName = raceName;
		this.elemental = elemental;
		this.maxLevel = maxLevel;
		this.levelMoves = levelMoves;
		this.levelStats = levelStats;
		this.levelExp = levelExp;
		this.levelEvolutionImagePath = levelEvolutionImagePath;
	}
	
	/**
	 * WARNING!
	 * This constructor is only used for serialization, it shouldn't be used for any intention else.
	 */
	@Deprecated
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
	
	/**
	 * 
	 * @return - such as Pikachu
	 */
	public String getSpecieName(){
		return new String(specieName);
	}
	
	/**
	 * 
	 * @param raceName - such as Pikachu
	 */
	public void setSpecieName(String raceName) {
		this.specieName = raceName;
	}
	
	/**
	 * 
	 * @return- such as Fire, Water
	 */
	public String getElemental(){
		return new String(elemental);
	}
	
	/**
	 * 
	 * @param elemental - such as Fire, Water
	 */
	public void setElemental(String elemental) {
		this.elemental = elemental;
	}
	
	/**
	 * 
	 * @return - the highest level that Pokemon can be
	 */
	public int getMaxLevel(){
		return maxLevel;
	}
	
	/**
	 * 
	 * @param maxLevel - the highest level that Pokemon can be
	 */
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
	
	public Map<Integer, Move> getLevelMoves(){
		return levelMoves;
	}
	
	/**
	 * only for serialization,
	 * please use constructor instead
	 */
	@Deprecated
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
	
	protected List<Move> getAvailableMoves(int currentLevel){
		List<Move> availableMoves = new ArrayList<Move>();
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
	
	protected String getImagePath(int currentLevel){
		String currentImagePath = "";
		for(int i:levelEvolutionImagePath.keySet()){
			if(i<=currentLevel){
				currentImagePath = new String(levelEvolutionImagePath.get(i));
			}
		}
		return currentImagePath;
	}

	/**
	 * https://stackoverflow.com/questions/2367381/how-to-extract-numbers-from-a-string-and-get-an-array-of-ints
	 * @return
	 */
	public int getSpecieIndex(int currentLevel) {
		String indexImagePath = getImagePath(currentLevel);   
		indexImagePath = indexImagePath.replaceAll("[^-?0-9]+", " "); 
	    int index = 1;
		try {
			index = Integer.parseInt(Arrays.asList(indexImagePath.trim().split(" ")).get(0));
		} catch (NumberFormatException e) {
			index = 1;
			e.printStackTrace();
		}
		return index;
	}
	
}
