package engine.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import authoring.ScreenDisplay;
import data.items.Item;
import data.player.Player;
import engine.game.GameScene;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;
 
/**
 * 
 * @author nathanlewis
 *
 */

public class ShopScene extends ItemsScene{
	
//	private Image shopBackground = new Image("file:images/bag_game_background.png");
//	private Image bagImage = new Image("file:images/shop_bag.png");
	
	private Player mainPlayer;
	
	/**
	 * Scene used to buy items
	 * @param width
	 * @param height
	 * @param gameScene
	 * @param availableItems
	 * @param background
	 * @param player
	 */
	public ShopScene(int width, int height, GameScene gameScene, List<Item> availableItems, Paint background, Player player) {
		super(width, height, availableItems, background, player);
		this.rootAdd(showItemList(mainPlayer.getItems()));
		this.getScene().getStylesheets().add("resources/shopScene.css");
		Button quitButton = new Button("quit");
		quitButton.setOnMouseClicked(e->{
			gameScene.changeBackScene();
		});
		this.rootAdd(quitButton,120, 420);
	}
	
	protected ListView<String> setListView(List<Item> items) {
		ObservableList<String> allItemsList = FXCollections.observableArrayList(
			items.stream()
				 .map(e->e.getItemName())
				 .collect(Collectors.toList())
		);

		ListView<String> itemList = new ListView<>(allItemsList);
		Map<String, Item> itemCollection = items.stream().
				collect(
                Collectors.toMap(
                        Item::getItemName,
                        item -> item
                )
        );
		itemList.setCellFactory(param -> new ShopCell(itemCollection,mainPlayer,new Callback<Integer, Integer>(){
			@Override
			public Integer call(Integer param) {
				rootAdd(showItemList(mainPlayer.getItems()));
				currencyDisplay.getChildren().clear();
				currencyDisplay.getChildren().addAll(createCurrencyDisplay());
				return null;
			}
		}));
        itemList.setPrefWidth(350);
        itemList.setPrefHeight(480);
        itemList.setLayoutX(360);
        itemList.setLayoutY(0);
        return itemList;
	}
	
	@SuppressWarnings("unchecked")
	public TableView<List<String>> showItemList(Map<String, Integer> items){
		TableView<List<String>> table = new TableView<List<String>>();
		table.setEditable(false); 
        TableColumn<List<String>, String> abilityCol = new TableColumn<List<String>, String>("Item");
        abilityCol.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> p) {
                StringProperty result = new SimpleStringProperty();
                result.set(p.getValue().get(0));
                return result;
            }
         });
        TableColumn<List<String>, String> valueCol = new TableColumn<List<String>, String>("Number");
        valueCol.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> p) {
                StringProperty result = new SimpleStringProperty();
                result.set(p.getValue().get(1));
                return result;
            }
         });
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        for(String key: items.keySet()){
        	List<String> tempList = new ArrayList<String>();
        	tempList.add(key);
        	tempList.add(String.valueOf(items.get(key)));
        	data.add(tempList);
        }
        table.setItems(data); 
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(abilityCol, valueCol);
        table.setPrefWidth(350);
        table.setPrefHeight(300);
        table.setLayoutX(0);
        table.setLayoutY(100);
        return table;
	}
	
	
	
}
