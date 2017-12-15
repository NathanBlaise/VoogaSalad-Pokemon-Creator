package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/air-slash
 * @author cy122 
 * @author Dan Sun 
 *
 */
public class MoveAirSlash extends MoveDamage{
	private static final long serialVersionUID = -6817739927866468161L; //for serialization

	private final static int power = 75;
	private final static int maxPP = 20;
	private final static String name = "Air Slash";
	private final static String type = "Flying";
	public MoveAirSlash(){
		super(name, type, maxPP, power);
	}
}
