package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/bite
 * @author cy122
 * @author Dan Sun
 */
public class MoveBite extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 60;
    	private final static int maxPP = 25;
    	private final static String name = "Bite";
    	private final static String elemental = "Normal";
	public MoveBite(){
		super(name, elemental, maxPP, power);
	}
}
