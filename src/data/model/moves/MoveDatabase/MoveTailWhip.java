package data.model.moves.MoveDatabase;

import java.util.ArrayList;
import data.model.moves.MoveStatChange;

/**
 * tail whip: let the oppent's defense reduce 1 level, no damage
 * @author cy122
 *
 */
public class MoveTailWhip extends MoveStatChange{
	private static final long serialVersionUID = 4811448840696562950L;//for serialization
	
	/**
	 * see @data.model.moves.MoveStatChange#MoveStatChange()
	 */
	public MoveTailWhip(){
		super("Tail Whip", "Electric", 30, false, -1, new ArrayList<String>(){
			private static final long serialVersionUID = 1676726288407767303L;
			{
				this.add("SpecialDefense");
				this.add("NormalDefense");
			}
		});
	}
	
	public MoveTailWhip(String temp){
		this();
	}
}
