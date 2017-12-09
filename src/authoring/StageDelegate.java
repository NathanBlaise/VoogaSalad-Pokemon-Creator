package authoring;
/**
 * Interface that defines the functionality shared 
 * among game environments such as authoring and playing
 * @author Dan Sun for commenting
 */
import javafx.stage.Stage;
import data.Database;


public interface StageDelegate {

	/**
	 * Click the Button, let the stage set the next scene;
	 */
	void GoButtonPressed();
	/**
	 * Click the Button, let the stage set the back scene;
	 */
	void BackButtonPressed();
	/**
	 * Type the Space to go to first authoring scene;
	 */
	void toFirstAuthorScene();
	
	/**
	 * get the database
	 */
	Database getDatabase();
	
	/**
	 * save the database to the related XML file
	 */
	void saveDatabase();
	
	/**
	 * 
	 * @return - the stage
	 */
	Stage getStage();
	
}
