package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/pursuit
 * @author cy122
 * @author Dan Sun
 */
public class MovePursuit extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 40;
    	private final static int maxPP = 20;
    	private final static String name = "Pursuit";
    	private final static String elemental = "Dark";
	public MovePursuit(){
		super(name, elemental, maxPP, power);
	}
}
