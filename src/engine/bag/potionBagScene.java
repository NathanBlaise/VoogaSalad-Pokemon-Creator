package engine.bag;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;


public class potionBagScene extends BagScene{
	/*final variable*/
	private final Image POTION =new Image("file:inventory/potions.png",144,55,false,false);
	private final Image POTION_BAG =new Image("file:inventory/backpack_potions.png",144,155,false,false);
	
	
	public potionBagScene(int width, int height, Paint background,UserPage userPage) {
		super(width, height, background,userPage);
		this.rootAdd(new ImageView(POTION_BAG),0,30);
		this.rootAdd(new ImageView(POTION),0,225);
	}

	
}
