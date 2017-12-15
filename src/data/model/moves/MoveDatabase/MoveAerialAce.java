package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/aerial-ace
 * @author cy122 
 * @author Dan Sun 
 *
 */
public class MoveAerialAce extends MoveDamage{
	private static final long serialVersionUID = -6817739927866468161L; //for serialization

	private final static int power = 60;
	private final static int maxPP = 20;
	private final static String name = "Aerial Ace";
	private final static String type = "Flying";
	public MoveAerialAce(){
		super(name, type, maxPP, power);
	}
}
