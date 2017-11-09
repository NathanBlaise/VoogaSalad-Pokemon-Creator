package authoring;


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
	 * Type the Space to go to first author scene;
	 */
	void toFirstAuthorScene();
	
}
