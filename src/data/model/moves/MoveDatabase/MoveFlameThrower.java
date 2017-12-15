package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/flamethrower
 * @author cy122
 * @author Dan Sun
 */
public class MoveFlameThrower extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 90;
    	private final static int maxPP = 15;
    	private final static String name = "Flame Thrower";
    	private final static String elemental = "Fire";
	public MoveFlameThrower(){
		super(name, elemental, maxPP, power);
	}
}
