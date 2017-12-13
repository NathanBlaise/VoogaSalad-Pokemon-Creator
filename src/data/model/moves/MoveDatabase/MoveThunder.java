package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/thunder
 * @author cy122
 * @author Dan Sun
 */
public class MoveThunder extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 110;
    	private final static int maxPP = 10;
    	private final static String name = "Thunder";
    	private final static String elemental = "Electric";
	public MoveThunder(){
		super(name, elemental, maxPP, power);
	}
}
