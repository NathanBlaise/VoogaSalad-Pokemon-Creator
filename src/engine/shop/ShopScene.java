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

public class ShopScene extends ScreenDisplay{
	
	private final int TABLE_LIST_WIDTH = 350;
	private final int ITEM_LIST_HEIGHT = 480;
	private final int ITEM_LIST_XPOS = 360;
	private final int TABLE_HEIGHT = 300;
	private final int TABLE_YPOS= 100;
	private final int QUIT_BUTTON_XPOS = 120;
	private final int QUIT_BUTTON_YPOS = 420;
	private final int CURRENCY_DISPLAY_SIZEX = 350;
	private final int CURRENCY_DISPLAY_SIZEY = 100;
	
	private Player mainPlayer;
	private StackPane currencyDisplay = new StackPane();
	
	/**
	 * Scene used to buy items, contains a listview to purchase items, a table view to show players items and currency display
	 * @param width
	 * @param height
	 * @param gameScene
	 * @param availableItems
	 * @param background
	 * @param player
	 */
	public ShopScene(int width, int height, GameScene gameScene, List<Item> availableItems, Paint background, Player player) {
		super(width, height, background);
		mainPlayer = player;
		currencyDisplay.getChildren().addAll(createCurrencyDisplay());
		currencyDisplay.setPrefSize(CURRENCY_DISPLAY_SIZEX, CURRENCY_DISPLAY_SIZEY);
		currencyDisplay.setId("stack-pane");
		this.rootAdd(currencyDisplay, 0, 0);
		this.rootAdd(setListView(availableItems));
		this.getScene().getStylesheets().add("resources/shopScene.css");
		
		Map<String,Integer> items = null;
		try{
		    items = mainPlayer.getItems();
		} catch(NullPointerException e) {
		    e.printStackTrace();//handled by exiting 
		    System.exit(1);
		}
		this.rootAdd(showItemList(items));
		this.getScene().getStylesheets().add("resources/shopScene.css");
		Button quitButton = new Button("quit");
		quitButton.setOnMouseClicked(e->{
			gameScene.changeBackScene();
		});
		this.rootAdd(quitButton,QUIT_BUTTON_XPOS, QUIT_BUTTON_YPOS);
	}
	
	private ListView<String> setListView(List<Item> items) {
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
        itemList.setPrefWidth(TABLE_LIST_WIDTH);
        itemList.setPrefHeight(ITEM_LIST_HEIGHT);
        itemList.setLayoutX(ITEM_LIST_XPOS);
        itemList.setLayoutY(0);
        return itemList;
	}
	
	//https://stackoverflow.com/questions/37541279/javafx-centered-text-in-scene
	private Text createCurrencyDisplay(){
		Text currency =  new Text("Money: " + new Double(mainPlayer.getCurrency()).intValue());
		currency.setTextAlignment(TextAlignment.CENTER);
		StackPane.setAlignment(currency, Pos.CENTER);
		return currency;
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
        table.setPrefWidth(TABLE_LIST_WIDTH);
        table.setPrefHeight(TABLE_HEIGHT);
        table.setLayoutX(0);
        table.setLayoutY(TABLE_YPOS);
        return table;
	}
	
	
	
}
