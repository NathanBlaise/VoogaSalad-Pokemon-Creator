package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/tackle
 * @author cy122
 * @author Dan Sun
 */
public class MoveTackle extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 40;
    	private final static int maxPP = 35;
    	private final static String name = "Tackle";
    	private final static String elemental = "Normal";
	public MoveTackle(){
		super(name, elemental, maxPP, power);
	}
}
