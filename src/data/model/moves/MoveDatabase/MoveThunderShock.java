package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * http://pokemon.wikia.com/wiki/Thunder_Shock
 * @author cy122
 *
 */

public class MoveThunderShock extends MoveDamage{
	private static final long serialVersionUID = 1735261228334333867L;

	public MoveThunderShock(){
		super("Thunder Shock", "Electric", 30, 40);
	}
}
