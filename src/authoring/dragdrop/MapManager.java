package authoring.dragdrop;

import java.util.ArrayList;
import java.util.List;

import authoring.eventManage.Function3;
import data.Database;
import data.map.Cell;
import data.map.GameMap;
import data.model.Tile;
import engine.UI.UIComponentFactory.ListViewFactory;
import engine.UI.UIComponentFactory.UIComponentFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * For editing the event
 * @author cy122
 *
 */
public final class MapManager extends BorderPane{
	
	private ListView<GameMap> listView;
	private Callback<List<GameMap>, Integer> saver;
	private Database database;
	private Tile defaultTile;
	ScrollPane sp = new ScrollPane();
	
	@SuppressWarnings("unchecked")
	public MapManager(Database database, Callback<List<GameMap>, Integer> saver, Tile defaultTile) {
		this.defaultTile = defaultTile;
		this.saver = saver;
		this.database = database;
		listView = ListViewFactory.createListView(new ContextMenu(), forListView(),
				new ChangeListener<GameMap>() {
			@Override
			public void changed(ObservableValue<? extends GameMap> observable,GameMap oldValue, GameMap newValue) {
				// DO NOTHING
			}   
	    }, "add new instruction", "remove the last instruction");
		for(int i=0; i<database.getMaps().size();i++){
			GameMap temp = database.getMaps().get(i);
			listView.getItems().add(temp);
		}
		
		ContextMenu contextMenu = ListViewFactory.createClickMenu(null, new ContextMenu(), "add new map", "remove currrent selected map", h->{addNewMap();}, h->{deleteSelectedMap();}); 
		
		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.SECONDARY){
					contextMenu.show(listView, event.getScreenX(), event.getScreenY());
				}else{
					showSelectedMap();
				}
	        }
	    });
		listView.getItems().addListener(new ListChangeListener<GameMap>(){
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends GameMap> c) {
				save();
			}
		});
		
		sp.setPrefSize(48*15, 48*10);
		setCenter(sp);
		showSelectedMap();
		setRight(listView);
	}
	
	private void showSelectedMap() {
		int index = listView.getSelectionModel().getSelectedIndex()<0?0:listView.getSelectionModel().getSelectedIndex();
		if(index<listView.getItems().size()){
			GameMap selectedItem = listView.getItems().get(index);
			GridPane map = new DBMap(database, selectedItem).getGrid();
			sp.setContent(map);
		}
	}
	
	private void addNewMap(){
		new sizeChooser((Xlength, Ylenghth, MapName)->{
			GameMap newMap = new GameMap(MapName, Xlength, Ylenghth);
			for(int i=0; i<Xlength; i++){
				for(int j=0; j<Ylenghth; j++){
					newMap.setCell(i,j,new Cell(defaultTile.getWholePic(), true, defaultTile.isObstacle(), null));
				}
			}
			listView.getItems().add(newMap);
			GridPane map = new DBMap(database, newMap).getGrid();
			sp.setContent(map);
			return null;
		});
	}
	
	private void deleteSelectedMap(){
		int index = listView.getSelectionModel().getSelectedIndex()<0?0:listView.getSelectionModel().getSelectedIndex();
		if((index<listView.getItems().size())&&(listView.getItems().size()>1)){
			listView.getItems().remove(index);
		}
	}

	private Callback<ListView<GameMap>, ListCell<GameMap>> forListView() {
		return list -> {
			MapCell result = new MapCell();
			result.textProperty().addListener((obs, oldItem, newItem) -> {
				if ((newItem != null)&&(!newItem.equals(""))) {
					result.getItem().setName(newItem);
		        }
		    });
			result.setOnMouseClicked(new EventHandler<MouseEvent>() {
		        @Override
		        public void handle(MouseEvent event) {
					showSelectedMap();
		        }
		    });
			return result;
		};
    }

	private void save() {
		ArrayList<GameMap> gameMapArray = new ArrayList<GameMap>(listView.getItems());
		database.setMaps(gameMapArray);
		saver.call(gameMapArray);
	}
	
    static class MapCell extends ListCell<GameMap> {
    	@Override
        public void updateItem(GameMap item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.getName());
            }
        }
    }
    
    private class sizeChooser extends BorderPane{
    	int lengthX = 10;
    	int lengthY = 15;
    	Label instruction = new Label("choose the size of your map please :-)");
    	
    	public sizeChooser(Function3<Integer, Integer, String, Integer> saver){ 
        	Stage stage = new Stage();
          	TextField nameMap = new TextField("input your map name please");
       		HBox xChooser =  UIComponentFactory.intSlider(lengthX, 1, 20, x -> {lengthX = x; return null;}, "height");
       		HBox yChooser =  UIComponentFactory.intSlider(lengthY, 1, 20, y -> {lengthY = y; return null;}, "width");
       		Button button = new Button("go to new map");
          	button.setOnMouseClicked(e->{
          		saver.apply(lengthX, lengthY, nameMap.getText());
          		stage.close();
          	});
          	VBox vBox = new VBox();
          	vBox.getChildren().addAll(xChooser, yChooser, nameMap);
          	setTop(instruction);
          	setCenter(vBox);
          	setBottom(button);
          	stage.setScene(new Scene(this));
          	stage.show();
          	stage.centerOnScreen();
    	}
    }
}

