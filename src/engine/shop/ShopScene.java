package engine.shop;

import java.util.Map;
import java.util.stream.Collectors;

import authoring.ScreenDisplay;
import data.player.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

/**
 * 
 * @author nathanlewis
 *
 */

public class ShopScene extends ScreenDisplay{
	
	protected Canvas canvas;
	private GraphicsContext gc;
	
	private Image shopBackground = new Image("file:images/bag_game_background.png");
	private Image bagImage = new Image("file:images/shop_bag.png");
	
	private ObservableList<String> allItemsList = FXCollections.observableArrayList(
			"AttackBoost","Claw","DefenseBoost","HiPotion","HPDrain","HyperPotion","LevelBoost",
			"Potion","SpecialAttackBoost","SpecialDefenseBoost","SpeedBoost","SuperPotion","XPBoost");
	
	private Player mainPlayer;

	public ShopScene(int width, int height, Paint background, Player player) {
		super(width, height, background);
		mainPlayer = player;
		
		canvas = new Canvas(width,height);
		gc = canvas.getGraphicsContext2D();
		gc.drawImage(shopBackground, 0, 0);
		gc.drawImage(bagImage, 30, 200);
		this.rootAdd(canvas);
		this.rootAdd(setListView());
	}
	
	private ListView<String> setListView() {
		ListView<String> itemList = new ListView<>(allItemsList);
		Map<String, Image> imageCollection = allItemsList.stream().collect(
                Collectors.toMap(
                        item -> item,
                        item -> new Image("file:images/items/" + item.toLowerCase() + ".png")
                )
        );
		itemList.setCellFactory(param -> new ShopCell(imageCollection,mainPlayer));
        itemList.setPrefWidth(350);
        itemList.setPrefHeight(300);
        itemList.setLayoutX(340);
        itemList.setLayoutY(70);
        return itemList;
	}
	
	
}
