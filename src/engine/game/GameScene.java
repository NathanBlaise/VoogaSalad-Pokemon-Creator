package engine.game;

import authoring.ScreenDisplay;
import data.map.DrawMap;
import data.map.GameMap;
import data.model.Model;
import data.player.Player;
import engine.Engine;
import engine.movement.Input;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Paint;

/**
 * The main scene for the game engine (where the player walks around)
 * This class EXTENDS ScreenDisplay
 * @author nathanlewis
 *
 */

public class GameScene extends ScreenDisplay {
	
	private GameMap mainMap;
	private Player mainPlayer;
	private Model gameModel;
	private Input input;

	
	public GameScene(int width, int height, Paint background, Engine engine) {
		super(width, height, background);
		// Grabs map, player and all data in model from chosen database, saves in variables
		mainMap = engine.getDatabase().getMap();
		mainPlayer = engine.getDatabase().getPlayer();
		gameModel = engine.getDatabase().getModel();
		DrawMap drawMap = new DrawMap(mainMap);
		this.rootAdd(drawMap.getPane());
		this.rootAdd(mainPlayer.getPlayerImage());
		input = new Input(this.getScene());
		input.addListeners();
		
		/*
		 * Animation Timer to handle player movement
		 */
		AnimationTimer gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				mainPlayer.processInput(input);
				mainPlayer.move();
				mainPlayer.drawPlayer();
			}	
		};
		gameLoop.start();
	}
	
	

}
