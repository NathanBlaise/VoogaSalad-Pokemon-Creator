package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/peck
 * @author cy122
 * @author Dan Sun
 */
public class MoveDrillPeck extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 80;
    	private final static int maxPP = 20;
    	private final static String name = "Drill Peck";
    	private final static String elemental = "Flying";
	public MoveDrillPeck(){
		super(name, elemental, maxPP, power);
	}
}
