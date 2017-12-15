package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/assurance
 * @author cy122 
 * @author Dan Sun 
 *
 */
public class MoveAssurance extends MoveDamage{
	private static final long serialVersionUID = -6817739927866468161L; //for serialization

	private final static int power = 60;
	private final static int maxPP = 10;
	private final static String name = "Assurance";
	private final static String type = "Dark";
	public MoveAssurance(){
		super(name, type, maxPP, power);
	}
}
