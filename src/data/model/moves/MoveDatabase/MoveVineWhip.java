package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * http://pokemon.wikia.com/wiki/Vine_Whip
 * @author cy122
 *
 */
public class MoveVineWhip extends MoveDamage{
	private static final long serialVersionUID = -4369541544870446516L;

	/**
	 * power is 35, maxPP is 10, name is "Wine Whip", elemental is "Grassz"
	 */
	public MoveVineWhip(){
		super("Wine Whip", "Grass", 10, 35);
	}
}