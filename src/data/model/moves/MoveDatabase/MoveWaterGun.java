package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/water-gun
 * @author cy122
 * @author Dan Sun
 */
public class MoveWaterGun extends MoveDamage{

	/**
     * 
     */
    private static final long serialVersionUID = 272776333857782615L;

    	private final static int power = 40;
    	private final static int maxPP = 25;
    	private final static String name = "Water Gun";
    	private final static String elemental = "Water";
	public MoveWaterGun(){
		super(name, elemental, maxPP, power);
	}
}
