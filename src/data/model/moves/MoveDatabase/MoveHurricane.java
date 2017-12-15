package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/hurricane
 * @author cy122 
 * @author Dan Sun 
 *
 */
public class MoveHurricane extends MoveDamage{
	private static final long serialVersionUID = -6817739927866468161L; //for serialization

	private final static int power = 110;
	private final static int maxPP = 10;
	private final static String name = "Hurricane";
	private final static String type = "Flying";
	public MoveHurricane(){
		super(name, type, maxPP, power);
	}
}
