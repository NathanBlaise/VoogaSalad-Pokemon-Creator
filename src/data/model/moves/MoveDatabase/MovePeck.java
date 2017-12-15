package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/peck
 * @author cy122
 * @author Dan Sun
 */
public class MovePeck extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 35;
    	private final static int maxPP = 35;
    	private final static String name = "Peck";
    	private final static String elemental = "Flying";
	public MovePeck(){
		super(name, elemental, maxPP, power);
	}
}
