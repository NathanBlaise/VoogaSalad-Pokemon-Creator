package authoring.dragdrop;

import java.util.ArrayList;

import data.model.Tile;
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
	public EditMapScene(Paint background, StageDelegate stageHelper) {
		super(background, stageHelper);
		
		super.getScene().getStylesheets().add("resources/sceneStyle.css");
		super.getRoot().getStylesheets().add("resources/sceneStyle.css");
		
		// set up the t-menu
		tMenu = new TileMenu(stageHelper.getDatabase().getModel().getTiles());
		this.rootAdd(tMenu);
		this.rootAdd(new MapManager(stageHelper.getDatabase(), e->{
		    	//do nothing
			return null;
		}, new Tile("grass", false, 1, 1, "images/reg_tile_scaled.png", new ArrayList<String>())),200,0);
	}

}
