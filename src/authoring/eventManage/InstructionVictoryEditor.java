package authoring.eventManage;

import data.Database;
import data.event.Instruction;
import data.event.InstructionVictory;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
/**
 * 
 * @author Dan Sun
 *
 */
public class InstructionVictoryEditor implements InstructionEditor {
	
	public InstructionVictoryEditor(InstructionVictory instructionVictory, Database database, Callback<Instruction, Integer> saver){
		saver.call(new InstructionVictory());
	}
	
	@Override
	public Node showEditor() {
		return new BorderPane();
	}

}
