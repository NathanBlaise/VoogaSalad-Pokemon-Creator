package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/slash
 * @author cy122
 * @author Dan Sun
 */
public class MoveSlash extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 70;
    	private final static int maxPP = 20;
    	private final static String name = "Slash";
    	private final static String elemental = "Normal";
	public MoveSlash(){
		super(name, elemental, maxPP, power);
	}
}
