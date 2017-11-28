package authoring.eventManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.event.EventNPC;
import data.event.Instruction;
import engine.UI.UIComponentFactory.ListViewFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public final class EventInstructions{
	
	private ListView<String> listView;
	private Callback<List<Instruction>, Integer> saver;
	private Map<Integer, Instruction> instructionMap = new HashMap<Integer, Instruction>();
	
	@SuppressWarnings("unchecked")
	public EventInstructions(EventNPC eventNPC, Map<String, String> instructions, Map<String, authoring.eventManage.Function<Instruction, Callback<Instruction, Integer>, Integer>> reactions,Callback<List<Instruction>, Integer> saver) {
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
		for(int i=0; i<eventNPC.getInstructions().size();i++){
			Instruction temp = eventNPC.getInstructions().get(i);
			String name = temp.getClass().getSimpleName();
			for(String key: instructions.keySet()){
				if(instructions.get(key).equals(name)){
					listView.getItems().add(key);
					break;
				}
			}
			instructionMap.put(i, temp);
		}
		ContextMenu contextMenu = ListViewFactory.createClickMenu(listView, new ContextMenu()); 
		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.SECONDARY){
					contextMenu.show(listView, event.getScreenX(), event.getScreenY());
				}else{
					int index = listView.getSelectionModel().getSelectedIndex()<0?0:listView.getSelectionModel().getSelectedIndex();
					if((index<listView.getItems().size())&&(instructionMap.keySet().contains(index))&&(instructionMap.get(index)!=null)){
						String newItem = new String(listView.getItems().get(index));
						if(instructions.containsKey(newItem))
							reactions.get(instructions.get(newItem)).apply(instructionMap.get(index), new InstructionCell(instructionMap, index));
					}
				}
	        }
	    });
	}

	private Callback<ListView<String>, ListCell<String>> forListView(ObservableList<String> items, Map<String,String> instructions, Map<String, Function<Instruction, Callback<Instruction, Integer>, Integer>> reactions) {
		return list -> {
			ComboBoxListCell<String> result = new ComboBoxListCell<String>(null, items);
			instructionMap.put(listView.getItems().size(), null);
			result.itemProperty().addListener((obs, oldItem, newItem) -> {
				if (newItem != null) {
					if((newItem!=null)&&(instructions.containsKey(newItem))){
						int index = listView.getSelectionModel().getSelectedIndex()<0?0:listView.getSelectionModel().getSelectedIndex();
							reactions.get(instructions.get(newItem)).apply(instructionMap.get(index), new InstructionCell(instructionMap, index));
					}
		        }
		    });
			result.setOnMouseClicked(new EventHandler<MouseEvent>() {
		        @Override
		        public void handle(MouseEvent event) {
					int index = listView.getSelectionModel().getSelectedIndex()<0?0:listView.getSelectionModel().getSelectedIndex();
					if((index<listView.getItems().size())&&(instructionMap.keySet().contains(index))&&(instructionMap.get(index)!=null)){
						String newItem = new String(listView.getItems().get(index));
						if(instructions.containsKey(newItem)){
							reactions.get(instructions.get(newItem)).apply(instructionMap.get(index), new InstructionCell(instructionMap, index));
						}
					}
		        }
		    }	
					);
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
