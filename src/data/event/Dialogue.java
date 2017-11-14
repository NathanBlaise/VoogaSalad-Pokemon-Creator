package data.event;

import java.io.Serializable;
import java.util.Vector;

public class Dialogue extends Instruction implements Serializable{
	Vector<String> dialogues;

	@Override
	public void execute(Object... parameters) {
		// TODO call the API in Engine to begin a Dialogue
		
	}
}
