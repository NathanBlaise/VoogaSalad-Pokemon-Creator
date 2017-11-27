package authoring;

import javafx.scene.paint.Color;


/**
 * This class to initialize all the scenes over here and pass it to the main class
 * @author supertony
 *
 */
public class SceneController {
	/*final variables*/
	
	/*instance variables*/
	private SceneList sceneList;
	public int currentIndex = 0;
	
	public SceneController(StageDelegate app){
		sceneList = new SceneList(Color.WHITE,app);	
		
			// create an Editing Map Screen
			sceneList.add("authoring.dragdrop.EditMapScene");
			
			// create a Event Picture Screen
			sceneList.add("authoring.editEventImage.EditEventImageScene");
			
			//create Database Screen
			sceneList.add("authoring.databaseEditor.DatabaseScene");
			
	}
	
	
	/**
	 * @return The Scene List which stores all the scenes
	 */
	public SceneList getScList() {
		return sceneList;
	}
	
	
	/**
	 * Pass the map from the first scene to the second one
	 */
	public void passMapForward() {
		//eeis.setMyMap(bsc.passMyMap());
		//do nothing for now
	}
	
	public void passMapBackward() {
		//bsc.setMyMap(eeis.passMyMap());
		//do nothing for now
	}
	
}
	
	
