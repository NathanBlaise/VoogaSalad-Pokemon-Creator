package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/spark
 * @author cy122
 * @author Dan Sun
 */
public class MoveSpark extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 65;
    	private final static int maxPP = 20;
    	private final static String name = "Spark";
    	private final static String elemental = "Electric";
	public MoveSpark(){
		super(name, elemental, maxPP, power);
	}
}
