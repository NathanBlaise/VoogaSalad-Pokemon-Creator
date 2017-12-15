package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/seed-bomb
 * @author cy122
 * @author Dan Sun
 */
public class MoveSeedBomb extends MoveDamage{

    /**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 80;
    	private final static int maxPP = 15;
    	private final static String name = "Seed Bomb";
    	private final static String elemental = "Grass";
	public MoveSeedBomb(){
		super(name, elemental, maxPP, power);
	}
}
