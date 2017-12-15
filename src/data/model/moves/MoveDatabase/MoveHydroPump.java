package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/hydro-pump
 * @author cy122
 * @author Dan Sun
 */
public class MoveHydroPump extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 110;
    	private final static int maxPP = 5;
    	private final static String name = "Hydro Pump";
    	private final static String elemental = "Water";
	public MoveHydroPump(){
		super(name, elemental, maxPP, power);
	}
}
