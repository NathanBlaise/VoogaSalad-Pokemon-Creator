package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/flame-burst
 * @author cy122
 * @author Dan Sun
 */
public class MoveFlameBurst extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 70;
    	private final static int maxPP = 15;
    	private final static String name = "Flame Burst";
    	private final static String elemental = "Fire";
	public MoveFlameBurst(){
		super(name, elemental, maxPP, power);
	}
}
