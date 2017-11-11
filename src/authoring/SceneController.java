package authoring;

import java.util.ArrayList;

import authoring.dragdrop.DBMap;
import authoring.dragdrop.EditMapScene;
import authoring.editEventImage.EditEventImageScene;
import javafx.scene.Scene;
import javafx.scene.paint.Color;


/**
 * This class to initialize all the scenes over here and pass it to the main class
 * @author supertony
 *
 */
public class SceneController {
	/*final variables*/
	
	/*instance variables*/
	private ArrayList <Scene> sceneList;
	public int currentIndex = 0;
	private EditMapScene bsc;
	private EditEventImageScene  eeis;
	
	public SceneController(StageDelegate app){
		sceneList = new ArrayList<Scene>();
		
		
			// create an Editing Map Screen
			bsc = new EditMapScene(Color.WHITE,app);
			sceneList.add(bsc.getScene());
			
			// create a Event Picture Screen
			eeis = new EditEventImageScene(Color.WHITE,app);
			sceneList.add(eeis.getScene());
			
	}
	
	
	/**
	 * @return The Scene List which stores all the scenes
	 */
	public ArrayList <Scene> getScList() {
		return sceneList;
	}
	
	
	/**
	 * Pass the map from the first scene to the second one
	 */
	public void passMap() {
		eeis.setMyMap(bsc.passMyMap());
	}
}
