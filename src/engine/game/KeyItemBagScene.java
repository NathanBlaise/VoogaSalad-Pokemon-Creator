package engine.game;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class KeyItemBagScene extends BagScene {
	
	private final Image KEYITEM =new Image("file:inventory/keyitems.png",144,60,false,false);
	private final Image KEYITEM_BAG =new Image("file:inventory/backpack_keyitems.png",144,180,false,false);
	
	
	public KeyItemBagScene(int width, int height, Paint background, UserPage userPage) {
		super(width, height, background,userPage );
		
		this.rootAdd(new ImageView(KEYITEM_BAG),0,30);
		this.rootAdd(new ImageView(KEYITEM),0,253);
	}

}
