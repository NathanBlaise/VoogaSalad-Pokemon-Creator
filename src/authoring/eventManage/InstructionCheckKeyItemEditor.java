package authoring.eventManage;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import data.Database;
import data.event.Instruction;
import data.event.InstructionCheckKeyItem;
import engine.UI.UIComponentFactory.UIComponentFactory;
/**
 * edit the InstructionCheckKeyItem
 * @author cy122
 *
 */
public class InstructionCheckKeyItemEditor implements InstructionEditor {
	
	private InstructionCheckKeyItem instruction;
	private Callback<Instruction, Integer> saver;
	
	public InstructionCheckKeyItemEditor(InstructionCheckKeyItem instructionCheckKeyItem, Database database, Callback<Instruction, Integer> saver){
		this.instruction = instructionCheckKeyItem;
		this.saver = saver;
		this.saver.call(this.instruction);
	}
	
	@Override
	public Node showEditor() {
		BorderPane result = new BorderPane();
		result.setCenter(UIComponentFactory.nameEditor("The name of key item to check:", instruction.getKeyItemName(), e->{instruction.setKeyItemName(e);return null;}));
		return result;
	}

}