package authoring.eventManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import data.event.Instruction;
import engine.UI.UIComponentFactory.ListViewFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public final class EventInstructions{
	
	private ListView<String> listView;
	private Callback<List<Instruction>, Integer> saver;
	private Map<Integer, Instruction> instructionMap = new HashMap<Integer, Instruction>();
	
	
	@SuppressWarnings("unchecked")
	public EventInstructions(Map<String,String> instructions, Map<String, Function<Callback<Instruction, Integer>, Integer>> reactions, Callback<List<Instruction>, Integer> saver){
		ObservableList<String> names = FXCollections.observableArrayList();
		this.saver = saver;
		names.addAll(instructions.keySet());
		listView = ListViewFactory.createListView(new ContextMenu(), forListView(names, instructions, reactions),
				new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue) {
				// DO NOTHING
			}   
	    });		
	}
	
	private Callback<ListView<String>, ListCell<String>> forListView(ObservableList<String> items, Map<String,String> instructions, Map<String, Function<Callback<Instruction, Integer>, Integer>> reactions) {
		return list -> {
			ComboBoxListCell<String> result = new ComboBoxListCell<String>(null, items);
			result.itemProperty().addListener((obs, oldItem, newItem) -> {
		        if (newItem != null) {
					if((newItem!=null)&&(instructions.containsKey(newItem))){
						reactions.get(instructions.get(newItem)).apply(new InstructionCell(instructionMap, listView.getSelectionModel().getSelectedIndex()<0?0:listView.getSelectionModel().getSelectedIndex()));
					}
		        }
		    });
		      
			return result;
		};
    }
	
	public BorderPane getList(){
		BorderPane result = new BorderPane();
		result.setCenter(listView);
		result.setBottom(saveButton());
		return result;
	}
	
	private Button saveButton(){
		Button result = new Button("save");
		result.setOnMouseClicked(e->{
			List<Instruction> instructionArray = new ArrayList<Instruction>();
			for(int i=0;i<listView.getItems().size();i++){
				if(instructionMap.containsKey(i)){
					instructionArray.add(instructionMap.get(i));
				}
			}
			saver.call(instructionArray);
		});
		return result;
	}
	
	private class InstructionCell implements Callback<Instruction, Integer>{

		private int index;
		private Map<Integer, Instruction> instructionMap;
		
		public InstructionCell(Map<Integer, Instruction> instructionMap, int index){
			this.index = index;
			this.instructionMap = instructionMap;
		}
		
		@Override
		public Integer call(Instruction param) {
			instructionMap.put(index, param);
			return null;
		}
		
	}
}
