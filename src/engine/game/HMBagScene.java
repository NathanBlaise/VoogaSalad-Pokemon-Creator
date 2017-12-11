package engine.game;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;


public class HMBagScene extends BagScene{
	
	private final Image TM_HM =new Image("file:inventory/tm_hm.png",144,60,false,false);
	private final Image TM_HM_BAG=new Image("file:inventory/backpack_tm_hm.png",144,180,false,false);
	
	public HMBagScene(int width, int height, Paint background,UserPage userPage) {
		super(width, height, background,userPage);
		
		this.rootAdd(new ImageView(TM_HM_BAG),0,30);
		this.rootAdd(new ImageView(TM_HM),0,253);
		
		
	}

}
