package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/wild-charge
 * @author cy122
 * @author Dan Sun
 */
public class MoveWildCharge extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 90;
    	private final static int maxPP = 15;
    	private final static String name = "Wild Charge";
    	private final static String elemental = "Electric";
    	private final static double selfDamageRatio = 1.0/4;
	public MoveWildCharge(){
		super(name, elemental, maxPP, power,selfDamageRatio);
	}
}
