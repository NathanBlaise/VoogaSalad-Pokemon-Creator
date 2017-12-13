package engine.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;


public class PokemonBagScene extends BagScene{
	/*final variable*/
	private final Image POKEBALL_BAG =new Image("file:inventory/backpack_pokeballs.png",144,180,false,false);
	private final Image POKEBALL =new Image("file:inventory/pokeballs.png",144,60,false,false);
	
	public PokemonBagScene(int width, int height, Paint background, UserPage userPage) {
		super(width, height, background, userPage);
	
		this.rootAdd(new ImageView(POKEBALL_BAG),0,30);
		this.rootAdd(new ImageView(POKEBALL),0,255);
	
		
	}
	
	
		
	
	

}
