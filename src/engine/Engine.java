package engine;

import data.Database;
import data.saving.DatabaseSaver;
import engine.game.GameScene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Class used to link chosen database option to generating first scene, similar to Author
 * (needs to be refactored to remove duplicate code shared with Author)
 * @author nathanlewis
 *
 */

public class Engine {
	
	private final int GAME_SCREEN_HEIGHT = 480;
	private final int GAME_SCREEN_WIDTH = 720;
	
	private Stage myStage;
	private String savePath;
	private Database database;
	
	/**
	 * 
	 * @param database - the database the user has chosen for the game
	 * @param savePath - the string for the save path for the database
	 * @param primaryStage - the stage used to load the scene onto
	 */
	public Engine(Database database, String savePath, Stage primaryStage){
		myStage = primaryStage;
		this.database = database;
		this.savePath = savePath;
	}
	
	/*
	 * Launches main gameScene
	 */
	public void toMainGameScene() {
		GameScene gameScene = new GameScene(GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT, Color.WHITE, this, myStage);
		myStage.setScene(gameScene.getScene());
		myStage.centerOnScreen();

	}
	

	public Database getDatabase() {
		return database;
	}


	public void saveDatabase() {
		DatabaseSaver.save(database, savePath);
	}
	
}
