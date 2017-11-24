package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;
/**
 * http://pokemon.wikia.com/wiki/Ember
 * @author cy122
 *
 */

public class MoveEmber extends MoveDamage{
	private static final long serialVersionUID = -4742071488456184768L;

	/**
	 * power is 40, maxPP is 25, name is "Ember", elemental is "Fire"
	 */
	public MoveEmber(){
		super("Ember", "Fire", 25, 40);
	}
}
