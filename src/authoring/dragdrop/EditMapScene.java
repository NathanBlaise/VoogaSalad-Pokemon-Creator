package authoring.dragdrop;

import authoring.BasicAuthorScreen;
import authoring.StageDelegate;
import javafx.scene.paint.Paint;

/**
 * This class is to build a class extending basic author screen to make a map for the pokemon game
 * Allow users to drag tiles from a list and put them on the map
 * @author supertony cy122
 *
 */
public class EditMapScene extends BasicAuthorScreen {

	private TileMenu tMenu;
	private DBMap myMap;
	public EditMapScene(Paint background, StageDelegate stageHelper) {
		super(background, stageHelper);
		
		
		
		// set up the t-menu
		tMenu = new TileMenu(stageHelper.getDatabase().getModel().getTiles());
		this.rootAdd(tMenu);
		myMap = new DBMap(stageHelper.getDatabase());
		
	 
	    
		
		//testFont.setLayoutX(700);
		//testFont.setLayoutY(300);
		this.rootAdd(myMap.getGrid(),200,0);
		
	
	}

}
