package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/gust
 * @author cy122 
 * @author Dan Sun 
 *
 */
public class MoveGust extends MoveDamage{
	private static final long serialVersionUID = -6817739927866468161L; //for serialization

	private final static int power = 40;
	private final static int maxPP = 35;
	private final static String name = "Gust";
	private final static String type = "Flying";
	public MoveGust(){
		super(name, type, maxPP, power);
	}
}
