package tests.authoring;

import java.util.HashMap;
import java.util.Map;

import data.model.PokemonSpecie;
import data.model.PokemonStat;
import data.model.moves.Move;
import data.model.moves.MoveDatabase.MoveGrowl;
import data.model.moves.MoveDatabase.MoveQuickAttack;
import data.model.moves.MoveDatabase.MoveTailWhip;
import data.model.moves.MoveDatabase.MoveThunderShock;

public class Specie1 extends PokemonSpecie{
	private static final transient long serialVersionUID = -2300245113671553925L;
	private static final transient String raceName = "Pikachu";
	private static final transient String elemental = "Electric";
	private static final transient int maxLevel = 4;
	private static final transient Map<Integer, Move> levelMoves = new HashMap<Integer, Move>();
	private static final transient Map<Integer, PokemonStat> levelStats = new HashMap<Integer, PokemonStat>();
	private static final transient Map<Integer, Double> levelExp = new HashMap<Integer, Double>();
	private static final transient Map<Integer, String> levelEvolutionImagePath = new HashMap<Integer, String>();
	
	{
		levelMoves.put(1, new MoveGrowl());
		levelMoves.put(2, new MoveQuickAttack());
		levelMoves.put(3, new MoveTailWhip());
		levelMoves.put(4, new MoveThunderShock());
		levelExp.put(1, 1.0);
		levelEvolutionImagePath.put(1, "images/pokemon_sprites/1.gif");
		levelStats.put(1, new PokemonStat(1,2,3,4,5,6));
		levelStats.put(2, new PokemonStat(2,3,4,5,6,7));
		levelStats.put(3, new PokemonStat(3,4,5,6,7,8));
		levelStats.put(4, new PokemonStat(4,5,6,7,8,9));
	}
	
	public Specie1() {
		super(raceName, elemental, maxLevel, levelMoves, levelStats, levelExp, levelEvolutionImagePath);
	}
}
