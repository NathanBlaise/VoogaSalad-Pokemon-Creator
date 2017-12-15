package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/aqua-tail
 * @author cy122
 * @author Dan Sun
 */
public class MoveAquaTail extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 90;
    	private final static int maxPP = 10;
    	private final static String name = "Aqua Tail";
    	private final static String elemental = "Water";
	public MoveAquaTail(){
		super(name, elemental, maxPP, power);
	}
}
