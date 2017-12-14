package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/double-edge
 * @author cy122
 * @author Dan Sun
 */
public class MoveDoubleEdge extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 120;
    	private final static int maxPP = 15;
    	private final static String name = "Double-Edge";
    	private final static String elemental = "Normal";
    	private final static double selfDamageRatio = 1.0/3;
	public MoveDoubleEdge(){
		super(name, elemental, maxPP, power,selfDamageRatio);
	}
}
