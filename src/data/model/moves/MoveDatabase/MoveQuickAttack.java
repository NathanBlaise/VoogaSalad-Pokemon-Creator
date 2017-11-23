package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://bulbapedia.bulbagarden.net/wiki/Quick_Attack_(move)
 * @author cy122
 *
 */
public class MoveQuickAttack extends MoveDamage{
	private static final long serialVersionUID = -6817739927866468161L; //for serialization

	/**
	 * power is 40, maxPP is 30, name is "Quick Attack", elemental is "Normal"
	 */
	public MoveQuickAttack(){
		super("Quick Attack", "Normal", 30, 40);
	}
}
