package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/inferno
 * @author cy122
 * @author Dan Sun
 */
public class MoveInferno extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 100;
    	private final static int maxPP = 5;
    	private final static String name = "Inferno";
    	private final static String elemental = "Fire";
	public MoveInferno(){
		super(name, elemental, maxPP, power);
	}
}
