package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/wing-attack
 * @author cy122 
 * @author Dan Sun 
 *
 */
public class MoveWingAttack extends MoveDamage{
	private static final long serialVersionUID = -6817739927866468161L; //for serialization

	private final static int power = 60;
	private final static int maxPP = 35;
	private final static String name = "Wing Attack";
	private final static String type = "Flying";
	public MoveWingAttack(){
		super(name, type, maxPP, power);
	}
}
