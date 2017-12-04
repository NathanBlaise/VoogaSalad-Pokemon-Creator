package engine;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import data.Database;
import data.saving.DatabaseSaver;
import engine.game.GameScene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
	private String gameType;
	
	/**
	 * 
	 * @param database - the database the user has chosen for the game
	 * @param savePath - the string for the save path for the database
	 * @param primaryStage - the stage used to load the scene onto
	 * @param type - the type of game
	 */
	public Engine(Database database, String savePath, String gameType, Stage primaryStage){
		myStage = primaryStage;
		this.database = database;
		this.savePath = savePath;
		this.gameType = gameType;
	}
	
	/*
	 * Launches main gameScene
	 */
	public void toMainGameScene() {
		try {
			Class<?> gameSceneClass = Class.forName("player."+gameType+"GameScene");
			Constructor<?> constructor = gameSceneClass.getConstructor(new Class[]{int.class, int.class, Paint.class, Engine.class, Stage.class});
			GameScene gameScene = (GameScene) constructor.newInstance(GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT, Color.WHITE, this, myStage);
			myStage.setScene(gameScene.getScene());
			myStage.centerOnScreen();
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			System.out.printf("type:%s, Game Type not found!", gameType);
			e.printStackTrace();
		}

	}
	

	public Database getDatabase() {
		return database;
	}


	public void saveDatabase() {
		DatabaseSaver.save(database, savePath);
	}
	
}
