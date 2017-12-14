package authoring;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class to initialize all the scenes over here and pass it to the main class
 * @author supertony
 * @author Dan Sun
 *
 */
public class SceneController {
	/*final variables*/
	
	/*instance variables*/
	private SceneList sceneList;
	public int currentIndex = 0;
	
	public SceneController(StageDelegate stageDelegate, String gameType){
		sceneList = new SceneList(Color.WHITE,stageDelegate);	
		// create an Editing Map Screen
		sceneList.add("authoring.dragdrop.EditMapScene");			
		// create a Event Picture Screen
		sceneList.add("authoring.editEventImage.EditEventImageScene");
		//create Database Screen
		if(gameType.equals("Pokemon")) {
			sceneList.add("authoring.databaseEditor.DatabaseScene");
		}
	}
	
	/**
	 * Sets the stage to display the first editing scene
	 * @param stage The stage that is going to display editing scenes
	 */
	public void goToFirstEditingScene(Stage stage) {
	    stage.setScene(sceneList.get(0));
	}
	
	/**
	 * Set the stage to be the next editing window
	 * @param stage The stage that displays editing windows
	 */
	public void goToNextEditingScene(Stage stage) {
	    if (currentIndex < sceneList.size() - 1) {
		currentIndex +=1;
		stage.setScene(sceneList.get(currentIndex));
	    }
	}
	/**
	 * Set the stage to be the previous editing window
	 * @param stage The stage that displays editing windows
	 */
	public void goToPreviousEditingScene(Stage stage) {
	    if (currentIndex > 0){
		currentIndex -=1;
		stage.setScene(sceneList.get(currentIndex));
	    }
	}
}
	
	
