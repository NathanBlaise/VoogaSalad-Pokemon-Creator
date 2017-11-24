package authoring.eventManage;

import java.util.List;

import data.event.Instruction;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public abstract class EventEditor {
	EventEditor(){
		
	}
	
	protected abstract void editObject();
	
	private void editInstructions(Callback<List<Instruction>, Integer> saver){
		Stage stage = new Stage();
		BorderPane borderPane = new BorderPane();
		stage.setScene(new Scene(borderPane));
		stage.show();
	}
}
