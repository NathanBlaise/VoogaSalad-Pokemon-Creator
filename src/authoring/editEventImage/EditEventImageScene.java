package authoring.editEventImage;

import authoring.BasicAuthorScreen;
import authoring.StageDelegate;
import authoring.dragdrop.DBMap;
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
		myMap = new DBMap(stageHelper.getDatabase());
		this.rootAdd(myMap.getGrid(),200,0);
		
	}
	
	/**
	 * The public method to pass the map to next scene
	 * @return myMap
	 */
	public DBMap passMyMap() {
		DBMap myMapCopy = myMap;
		return myMapCopy;
	}
	
	
	
	/**
	 * @param map: pass in the map from the scene before
	 * 
	 */
	public void setMyMap(DBMap map) {
		myMap = map;
		this.rootRemove(myMap.getGrid());
		this.rootAdd(myMap.getGrid(),200,0);
	}
	
	
	

}
