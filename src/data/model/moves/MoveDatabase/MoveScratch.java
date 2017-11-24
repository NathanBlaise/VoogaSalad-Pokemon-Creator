package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;
/**
 * http://pokemon.wikia.com/wiki/Scratch
 * @author cy122
 *
 */

public class MoveScratch extends MoveDamage{
	private static final long serialVersionUID = -8037135689240796961L;

	/**
	 * power is 40, maxPP is 35, name is "Scratch", elemental is "Normal"
	 */
	public MoveScratch(){
		super("Scratch", "Normal", 35, 40);
	}
}
