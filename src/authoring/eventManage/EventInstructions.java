package authoring.eventManage;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EventInstructions extends TableView<String>{
	private Map<String,String> instructions;
	
	EventInstructions(Map<String,String> instructions){
		this.instructions=new HashMap<String,String>(instructions);
		TableColumn<String,String> firstCol = new TableColumn<>("Instructions");
		firstCol.setMinWidth(100);
		super.getColumns().addAll(firstCol);
		setEditable(true);
	}
	
	
}
