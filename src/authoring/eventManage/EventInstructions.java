package authoring.eventManage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import engine.UI.UIComponentFactory.ListViewFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxListCell;

public class EventInstructions {
	private Map<String,String> instructions;
	private ListView listView;
	
	public EventInstructions(Map<String,String> instructions){
		ObservableList names = FXCollections.observableArrayList();
		names.addAll(instructions.values());
		listView = ListViewFactory.createListView(new ContextMenu(), ComboBoxListCell.forListView(names),
				new ChangeListener<String>(){
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						// TODO save a list of instructions, open a user interface
						
					}});
		this.instructions=new HashMap<String,String>(instructions);
	}
	
	public ListView getListView(){
		return listView;
	}
	
}
