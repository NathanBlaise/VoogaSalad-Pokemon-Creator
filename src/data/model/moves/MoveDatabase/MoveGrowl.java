package data.model.moves.MoveDatabase;

import java.util.Arrays;

import data.model.moves.MoveStatChange;

/**
 * http://pokemon.wikia.com/wiki/Growl
 * @author cy122
 *
 */

public class MoveGrowl extends MoveStatChange{
	private static final long serialVersionUID = 7741406280695095173L; // for serialization

	/**
	 * decrease the opponent's attack to one level lower
	 * see @data.model.moves.MoveStatChange#MoveStatChange()
	 */
	public MoveGrowl(){
		super("Growl", "Normal", 40, false, -1, Arrays.asList("NormalAttack", "SpecialAttack"));
	}
}
