package data.model.moves;

public class MoveTailWhip extends Move{
	private static final long serialVersionUID = 4811448840696562950L;//for serialization
	MoveTailWhip(){
		super("Tail Whip", "Electric", 30, (friend,enermy)->{});
	}

}
