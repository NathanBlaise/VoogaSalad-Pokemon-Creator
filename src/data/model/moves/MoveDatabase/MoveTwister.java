package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/twister
 * @author cy122 
 * @author Dan Sun 
 *
 */
public class MoveTwister extends MoveDamage{
	private static final long serialVersionUID = -6817739927866468161L; //for serialization

	private final static int power = 40;
	private final static int maxPP = 20;
	private final static String name = "Twister";
	private final static String type = "Dragon";
	public MoveTwister(){
		super(name, type, maxPP, power);
	}
}
