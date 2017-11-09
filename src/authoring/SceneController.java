package authoring;

import java.util.ArrayList;

import authoring.dragdrop.EditMapScene;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
	
	public SceneController(StageDelegate app){
		sceneList = new ArrayList<Scene>();
		
		for(int i = 0; i <5 ; i++) {
			// create a basic Author Screen
			BasicAuthorScreen bsc = new EditMapScene(Color.WHITE,app);
			sceneList.add(bsc.getScene());
		}
	}
	
	
	/**
	 * @return The Scene List which stores all the scenes
	 */
	public ArrayList <Scene> getScList() {
		return sceneList;
	}
}
