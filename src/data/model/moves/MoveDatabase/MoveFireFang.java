package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/fire-fang
 * @author cy122
 * @author Dan Sun
 */
public class MoveFireFang extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 65;
    	private final static int maxPP = 15;
    	private final static String name = "Fire Fang";
    	private final static String elemental = "Fire";
	public MoveFireFang(){
		super(name, elemental, maxPP, power);
	}
}
