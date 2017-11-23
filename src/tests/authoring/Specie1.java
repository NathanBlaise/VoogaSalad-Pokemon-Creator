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
	private static final long serialVersionUID = -2300245113671553925L;
	private static String raceName = "Pikachu";
	private static String elemental = "Electric";
	private static int maxLevel = 3;
	private static Map<Integer, Move> levelMoves = new HashMap<Integer, Move>(){
		private static final long serialVersionUID = -4489971927210436840L;

		{
			this.put(1, new MoveGrowl());
			this.put(2, new MoveQuickAttack());
			this.put(3, new MoveTailWhip());
			this.put(2, new MoveThunderShock());
		}
	};
	private static Map<Integer, PokemonStat> levelStats = new HashMap<Integer, PokemonStat>(){
		private static final long serialVersionUID = 7186018425103111509L;

		{
			this.put(1, new PokemonStat(1,2,3,4,5,6));
			this.put(2, new PokemonStat(2,3,4,5,6,7));
			this.put(3, new PokemonStat(3,4,5,6,7,8));
		}
	};
	private static Map<Integer, Double> levelExp = new HashMap<Integer, Double>(){
		private static final long serialVersionUID = -5268879095105097566L;

		{
			this.put(1, 1.0);
			this.put(2, 2.0);
			this.put(3, 3.0);
		}
	};
	private static Map<Integer, String> levelEvolutionImagePath = new HashMap<Integer, String>(){
		private static final long serialVersionUID = -2273949704282056126L;

		{
			this.put(1, "images/pokemon_sprites/1.gif");
		}
	};
	
	public Specie1() {
		super(raceName, elemental, maxLevel, levelMoves, levelStats, levelExp, levelEvolutionImagePath);
	}
}
