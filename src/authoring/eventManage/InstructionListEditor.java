package authoring.eventManage;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Callback;
import data.event.Event;
import data.event.Instruction;
/**
 * a glue code between event editor and instruction editor
 * @author cy122
 *
 */
public class InstructionListEditor implements Callback<List<Instruction>, Integer>{

	private Event event;
	private Callback<Event, Integer> saver;
	
	public InstructionListEditor(Event event, Callback<Event, Integer> saver){
		this.event = event;
		this.saver = saver;
	}
	
	@Override
	public Integer call(List<Instruction> param) {
		event.setInstructions(new ArrayList<Instruction>(param));
		saver.call(event);
		return null;
	}	
}
