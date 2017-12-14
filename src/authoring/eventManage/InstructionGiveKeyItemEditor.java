package authoring.eventManage;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import data.Database;
import data.event.Instruction;
import data.event.InstructionGiveKeyItem;
import engine.UI.UIComponentFactory.UIComponentFactory;

public class InstructionGiveKeyItemEditor implements InstructionEditor {
	
	private InstructionGiveKeyItem instruction;
	private Callback<Instruction, Integer> saver;
	
	public InstructionGiveKeyItemEditor(InstructionGiveKeyItem instructionGiveKeyItem, Database database, Callback<Instruction, Integer> saver){
		this.instruction = instructionGiveKeyItem;
		this.saver = saver;
		this.saver.call(this.instruction);
	}
	
	@Override
	public Node showEditor() {
		BorderPane result = new BorderPane();
		result.setCenter(UIComponentFactory.nameEditor("The name of key item to give:", instruction.getKeyItemName(), e->{instruction.setKeyItemName(e);return null;}));
		return result;
	}

}