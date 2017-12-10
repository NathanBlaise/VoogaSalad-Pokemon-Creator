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

public abstract class ItemsScene extends ScreenDisplay {

	protected Player mainPlayer;
	protected StackPane currencyDisplay = new StackPane();
	
	/**
	 * Scene used to buy items
	 * @param width
	 * @param height
	 * @param gameScene
	 * @param availableItems
	 * @param background
	 * @param player
	 */
	public ItemsScene(int width, int height, List<Item> availableItems, Paint background, Player player) {
		super(width, height, background);
		mainPlayer = player;
		currencyDisplay.getChildren().addAll(createCurrencyDisplay());
		currencyDisplay.setPrefSize(350, 100);
		currencyDisplay.setId("stack-pane");
		this.rootAdd(currencyDisplay, 0, 0);
		this.rootAdd(setListView(availableItems));
		this.getScene().getStylesheets().add("resources/shopScene.css");
	}
	
	protected abstract ListView<String> setListView(List<Item> items);
	
	
	//https://stackoverflow.com/questions/37541279/javafx-centered-text-in-scene
	protected Text createCurrencyDisplay(){
		Text currency =  new Text("Money: " + new Double(mainPlayer.getCurrency()).intValue());
		currency.setTextAlignment(TextAlignment.CENTER);
        StackPane.setAlignment(currency, Pos.CENTER);
        return currency;
	}
	
	
	
}
