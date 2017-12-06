package authoring.eventManage;

import java.util.ArrayList;
import java.util.List;

import data.Database;
import data.event.Instruction;
import data.event.InstructionGoMap;
import data.map.GameMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;

public class InstructionGoMapEditor implements InstructionEditor {

	private InstructionGoMap instructionGoMap;
	private Database database;
	private Callback<Instruction, Integer> saver;
	
	public InstructionGoMapEditor(InstructionGoMap instructionGoMap, Database database, Callback<Instruction, Integer> saver){
		this.instructionGoMap = instructionGoMap;
		this.database = database;
		this.saver = saver;
	}
	
	private ListView<String> createMapList(){
		ListView<String> result;
		List<String> mapNames = new ArrayList<String>();
		for(GameMap map: database.getMaps()){
			mapNames.add(map.getName());
		}
		ObservableList<String> names = FXCollections.observableArrayList(mapNames);
		result = new ListView<>(names);
		result.setEditable(false);
	    result.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
            instructionGoMap.setMapName(new_val);
            saver.call(instructionGoMap);
          }
        });
		result.setCellFactory(TextFieldListCell.forListView());
		result.getItems().addAll(names);
		int index = names.indexOf(instructionGoMap.getMapName());
		if(index!=-1){
			result.getSelectionModel().select(index);
		}
		return result;
	}
	
	@Override
	public Node showEditor() {
		return createMapList();
	}

}
