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

public class Specie2 extends PokemonSpecie{
	private static final long serialVersionUID = -2300245113671553925L;
	private static String raceName = "Golumn";
	private static String elemental = "Ghost";
	private static int maxLevel = 3;
	private static Map<Integer, Move> levelMoves = new HashMap<Integer, Move>();
	private static Map<Integer, PokemonStat> levelStats = new HashMap<Integer, PokemonStat>();
	private static Map<Integer, Double> levelExp = new HashMap<Integer, Double>();
	private static Map<Integer, String> levelEvolutionImagePath = new HashMap<Integer, String>();
	
	{
		levelMoves.put(1, new MoveGrowl());
		levelMoves.put(2, new MoveQuickAttack());
		levelMoves.put(3, new MoveTailWhip());
		levelExp.put(1, 1.0);
		levelExp.put(2, 2.0);
		levelExp.put(3, 3.0);
		levelEvolutionImagePath.put(1, "images/pokemon_sprites/2.gif");
		levelStats.put(1, new PokemonStat(1,2,3,4,5,6));
		levelStats.put(2, new PokemonStat(2,3,4,5,6,7));
		levelStats.put(3, new PokemonStat(3,4,5,6,7,8));
	}
	
	public Specie2() {
		super(raceName, elemental, maxLevel, levelMoves, levelStats, levelExp, levelEvolutionImagePath);
	}
}
