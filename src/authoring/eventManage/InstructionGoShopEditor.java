package authoring.eventManage;

import data.Database;
import data.event.Instruction;
import data.event.InstructionGoShop;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class InstructionGoShopEditor implements InstructionEditor {
	
	public InstructionGoShopEditor(InstructionGoShop instructionGoShop, Database database, Callback<Instruction, Integer> saver){
		saver.call(new InstructionGoShop());
	}
	
	@Override
	public Node showEditor() {
		return new BorderPane();
	}

}
