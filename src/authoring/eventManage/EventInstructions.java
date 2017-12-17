package authoring.eventManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.event.Event;
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

/**
 * For editing the event
 * @author cy122
 *
 */
public final class EventInstructions{
	
	private ListView<String> listView;
	private Callback<List<Instruction>, Integer> saver;
	private Map<Integer, Instruction> instructionMap = new HashMap<Integer, Instruction>();
	
	@SuppressWarnings("unchecked")
	public EventInstructions(Event event, Map<String, String> instructions, Map<String, Function<Instruction, Callback<Instruction, Integer>, Integer>> reactions,Callback<List<Instruction>, Integer> saver) {
		ObservableList<String> names = FXCollections.observableArrayList();
		this.saver = saver;
		names.addAll(instructions.keySet());
		for(int i=0; i<event.getInstructions().size();i++){
			Instruction temp = event.getInstructions().get(i);
			instructionMap.put(i, temp);
		}
		
		listView = ListViewFactory.createListView(new ContextMenu(), forListView(names, instructions, reactions),
				new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue) {
				// DO NOTHING
			}   
	    }, "add new instruction", "remove the last instruction");
		
		for(int i=0; i<event.getInstructions().size();i++){
			Instruction temp = event.getInstructions().get(i);
			String name = temp.getClass().getSimpleName();
			for(String key: instructions.keySet()){
				if(instructions.get(key).equals(name)){
					listView.getItems().add(key);
					break;
				}
			}
		}
		
		
		ContextMenu contextMenu = ListViewFactory.createClickMenu(listView, new ContextMenu(), "add new instruction", "remove the last instruction", h->{showSelectedInstruction(instructions, reactions);}, h->{instructionMap.remove(listView.getItems().size());}); 
		
		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.SECONDARY){
					contextMenu.show(listView, event.getScreenX(), event.getScreenY());
				}else{
					showSelectedInstruction(instructions, reactions);
				}
	        }
	    });
		
	}
	
	private void showSelectedInstruction(
			Map<String, String> instructions,
			Map<String, Function<Instruction, Callback<Instruction, Integer>, Integer>> reactions) {
		int index = listView.getSelectionModel().getSelectedIndex()<0?0:listView.getSelectionModel().getSelectedIndex();
		if((index<listView.getItems().size())&&(instructionMap.keySet().contains(index))&&(instructionMap.get(index)!=null)&&(!instructionMap.get(index).equals(""))){
			String newItem = listView.getSelectionModel().getSelectedItem()==null?"":listView.getSelectionModel().getSelectedItem();
			if(instructions.containsKey(newItem))
				reactions.get(instructions.get(newItem)).apply(instructionMap.get(index), new InstructionCell(instructionMap, index));
		}
	}

	private Callback<ListView<String>, ListCell<String>> forListView(ObservableList<String> items, Map<String,String> instructions, Map<String, Function<Instruction, Callback<Instruction, Integer>, Integer>> reactions) {
		return list -> {
			ComboBoxListCell<String> result = new ComboBoxListCell<String>(null, items);
//			if(!instructionMap.containsKey(listView.getItems().size())){
//				System.out.printf("put null object! %d\n", listView.getItems().size());
//				instructionMap.put(listView.getItems().size(), null);
//			}
			result.itemProperty().addListener((obs, oldItem, newItem) -> {
				if((newItem!=null)&&(!((String)newItem).equals(oldItem))){
				dealWithNewType(instructions, reactions);
//				if ((newItem != null)&&(!newItem.equals(""))&&((listView.getSelectionModel().getSelectedIndex()>=0)||((listView.getSelectionModel().getSelectedIndex()<0))&&(listView.getItems().size()>1))) {
//						int index = listView.getSelectionModel().getSelectedIndex()<0?0:listView.getSelectionModel().getSelectedIndex();
//						if((instructions.containsKey(newItem))&&(index<instructionMap.size())){
//							reactions.get(instructions.get(newItem)).apply(instructionMap.get(index), new InstructionCell(instructionMap, index));
//						}
//		        }
				}
		    });
			
			result.setOnMouseClicked(new EventHandler<MouseEvent>() {
		        @Override
		        public void handle(MouseEvent event) {
						dealWithNewType(instructions, reactions);
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
			save();
		});
		return result;
	}

	private void save() {
		List<Instruction> instructionArray = new ArrayList<Instruction>();
		for(int i=0;i<listView.getItems().size();i++){
			if(instructionMap.containsKey(i)){
				instructionArray.add(instructionMap.get(i));
			}
		}
		saver.call(instructionArray);
	}
	
	private void dealWithNewType(
			Map<String, String> instructions,
			Map<String, Function<Instruction, Callback<Instruction, Integer>, Integer>> reactions) {
		int index = listView.getSelectionModel().getSelectedIndex()<0?0:listView.getSelectionModel().getSelectedIndex();
		if(index<listView.getItems().size()){
			Instruction instruction;
			if(instructionMap.keySet().contains(index)){
				instruction = instructionMap.get(index);
			}else{
				instruction = null;
			}
//							System.out.printf("getItems() size: %d\n", listView.getItems().size());
			String newItem = listView.getSelectionModel().getSelectedItem()==null?"":listView.getSelectionModel().getSelectedItem();
			if(instructions.containsKey(newItem)){
				reactions.get(instructions.get(newItem)).apply(instruction, new InstructionCell(instructionMap, index));
			}
		}
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
