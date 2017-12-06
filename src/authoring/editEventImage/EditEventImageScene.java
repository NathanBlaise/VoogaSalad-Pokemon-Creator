package authoring.editEventImage;

import java.util.ArrayList;

import data.model.Tile;
import authoring.BasicAuthorScreen;
import authoring.StageDelegate;
import authoring.dragdrop.DBMap;
import authoring.dragdrop.MapManager;
import javafx.scene.paint.Paint;

/**
 * The class extends basic author screen
 * The second scene of authoring page
 * Aim to specify the location of events
 * @author supertony
 *
 */
public class EditEventImageScene extends BasicAuthorScreen {
	/*final variable*/
	
	/*instance variable*/
	private DBMap myMap;

	public EditEventImageScene(Paint background, StageDelegate stageHelper) {
		super(background, stageHelper);
		// TODO Auto-generated constructor stub
		this.rootAdd(new EventImageMenu(stageHelper.getDatabase().getModel().getPokemonSpecies(), stageHelper.getDatabase().getModel().getNPCs()));
		this.rootAdd(new MapManager(stageHelper.getDatabase(), e->{
			return null;
		}, new Tile("grass", false, 1, 1, "images/reg_tile_scaled.png", new ArrayList<String>())),200,0);
//		myMap = new DBMap(stageHelper.getDatabase());
//		this.rootAdd(myMap.getGrid(),200,0);
		
	}
	
	

}
