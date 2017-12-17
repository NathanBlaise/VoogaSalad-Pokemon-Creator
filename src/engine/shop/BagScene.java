package engine.shop;

import java.util.List;

import data.items.Item;
import data.player.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

public class BagScene extends ItemsScene{
	
	private Canvas canvas;
	private GraphicsContext gc;
	private Image shopBackground = new Image("file:images/bag_game_background.png");
	private Image bagImage = new Image("file:images/shop_bag.png");
	
	public BagScene(int width, int height, List<Item> availableItems, Paint background, Player player) {
		super(width, height, availableItems, background, player);
		canvas = new Canvas(width,height);
		gc = canvas.getGraphicsContext2D();
		gc.drawImage(shopBackground, 0, 0);
		gc.drawImage(bagImage, 50, 200);
	}

	@Override
	protected ListView<String> setListView(List<Item> items) {
		
		return null;
	}

}
