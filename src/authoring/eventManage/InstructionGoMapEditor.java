package authoring.eventManage;

import java.util.ArrayList;
import java.util.List;

import data.Database;
import data.event.Instruction;
import data.event.InstructionGoMap;
import data.map.GameMap;
import engine.UI.UIComponentFactory.UIComponentFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Pair;
/**
 * the editor to customized which grid the player will be in the new map and which new map to go
 * @author cy122
 *
 */
public class InstructionGoMapEditor implements InstructionEditor {

    private InstructionGoMap instructionGoMap;
    private Database database;
    private Callback<Instruction, Integer> saver;

    public InstructionGoMapEditor(InstructionGoMap instructionGoMap, Database database, Callback<Instruction, Integer> saver){
	this.instructionGoMap = instructionGoMap;
	this.database = database;
	this.saver = saver;
    }

    private BorderPane createMapList(){
	BorderPane borderPane = new BorderPane();
	ListView<String> mapLists;
	List<String> mapNames = new ArrayList<String>();
	for(GameMap map: database.getMaps()){
	    mapNames.add(map.getName());
	}
	ObservableList<String> names = FXCollections.observableArrayList(mapNames);
	mapLists = new ListView<>(names);
	mapLists.setEditable(false);
	mapLists.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	    public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
		instructionGoMap.setMapName(new_val);
		Pair<Integer, Integer> size = searchLength(new_val, database.getMaps());
		borderPane.setRight(new sizeChooser(size.getKey(), size.getValue(), 0, 0, (xcoor, ycoor)->{
		    instructionGoMap.setFutureX(xcoor);
		    instructionGoMap.setFutureY(ycoor);
		    return null;
		}));
		saver.call(instructionGoMap);
	    }
	});
	mapLists.setCellFactory(TextFieldListCell.forListView());
	int index = names.indexOf(instructionGoMap.getMapName());
	if(index!=-1){
	    mapLists.getSelectionModel().select(index);
	    Pair<Integer, Integer> size = searchLength(instructionGoMap.getMapName(), database.getMaps());
	    borderPane.setRight(new sizeChooser(size.getKey(), size.getValue(), instructionGoMap.getFutureX(), instructionGoMap.getFutureY(), (xcoor, ycoor)->{
		instructionGoMap.setFutureX(xcoor);
		instructionGoMap.setFutureY(ycoor);
		return null;
	    }));
	}
	borderPane.setCenter(mapLists);
	return borderPane;
    }
    /**
     * Returns the <Y Lenght, X Length> of the map with name mapName
     * @param mapName
     * @param maps
     * @return
     */
    private Pair<Integer, Integer> searchLength(String mapName, List<GameMap> maps){
	for(GameMap map: maps){
	    if(map.getName().equals(mapName)){
		return new Pair<Integer, Integer>(map.getYlength(), map.getXlength());
	    }
	}
	return new Pair<Integer, Integer>(0, 0);
    }

    private class sizeChooser extends BorderPane{
	int lengthX = 0;
	int lengthY = 0;
	Label instruction = new Label("change the position of the player please");
	/**
	 * 
	 * @param XMax maximum  x dimension of the map
	 * @param YMax maximum  y dimension of the map
	 * @param initialX default X position of player in the next map
	 * @param initialY default Y position of player in the next map
	 * @param saver function for saving the selection
	 */
	public sizeChooser(int XMax, int YMax, int initialX, int initialY, Function<Integer, Integer, Integer> saver){ 
	    lengthX = initialX;
	    lengthY = initialY;
	    HBox xChooser =  UIComponentFactory.intSlider(lengthX, 0, XMax-1, x -> {lengthX = x; saver.apply(lengthX, lengthY); return null;}, "horizontal coordinate");
	    HBox yChooser =  UIComponentFactory.intSlider(lengthY, 0, YMax-1, y -> {lengthY = y; saver.apply(lengthX, lengthY); return null;}, "vertical coordinate");
	    VBox vBox = new VBox();
	    vBox.getChildren().addAll(xChooser, yChooser);
	    setTop(instruction);
	    setCenter(vBox);
	}
    }

    @Override
    public Node showEditor() {
	return createMapList();
    }

}
