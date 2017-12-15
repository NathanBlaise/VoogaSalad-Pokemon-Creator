package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/hyper-fang
 * @author cy122
 * @author Dan Sun
 */
public class MoveHyperFang extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 80;
    	private final static int maxPP = 15;
    	private final static String name = "Hyper Fang";
    	private final static String elemental = "Normal";
	public MoveHyperFang(){
		super(name, elemental, maxPP, power);
	}
}
