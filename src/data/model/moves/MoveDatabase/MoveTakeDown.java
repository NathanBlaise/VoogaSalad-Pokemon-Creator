package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/take-down
 * @author cy122
 * @author Dan Sun
 */
public class MoveTakeDown extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 90;
    	private final static int maxPP = 20;
    	private final static String name = "Take Down";
    	private final static String elemental = "Normal";
    	private final static double selfDamageRatio = 1.0/4;
	public MoveTakeDown(){
		super(name, elemental, maxPP, power,selfDamageRatio);
	}
}
