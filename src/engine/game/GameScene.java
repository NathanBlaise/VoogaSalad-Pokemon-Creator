package engine.game;

import authoring.ScreenDisplay;
import data.map.DrawMap;
import data.map.GameMap;
import data.model.Model;
import data.player.Player;
import engine.Engine;
import engine.movement.Collisions;
import engine.movement.Direction;
import engine.movement.Input;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

import static engine.movement.Direction.DOWN;
import static engine.movement.Direction.UP;
import static engine.movement.Direction.LEFT;
import static engine.movement.Direction.RIGHT;
import static java.util.stream.Stream.of;

import java.util.ArrayList;

/**
 * The main scene for the game engine (where the player walks around)
 * This class EXTENDS ScreenDisplay
 * @author nathanlewis
 *
 */

public class GameScene extends ScreenDisplay {
	
	private final int PLAYER_WIDTH = 43;
	private final int PLAYER_HEIGHT = 65;
	private final Image image = new Image("file:images/emerald_down_rest.png");
	
	private long t1 = System.nanoTime();
	private long t2, diff;
	private long interval = 200000000;
	
	private GameMap mainMap;
	private Player mainPlayer;
	private Model gameModel;
	private Input input;
	private ArrayList inputList;
	private ImageView playerImage;

	
	public GameScene(int width, int height, Paint background, Engine engine) {
		super(width, height, background);
		// Grabs map, player and all data in model from chosen database, saves in variables
		mainMap = engine.getDatabase().getMap();
		mainPlayer = engine.getDatabase().getPlayer();
		gameModel = engine.getDatabase().getModel();
		DrawMap drawMap = new DrawMap(mainMap);
		playerImage = new ImageView(image);
		playerImage.setFitHeight(PLAYER_HEIGHT);
		playerImage.setFitWidth(PLAYER_WIDTH);
		this.rootAdd(drawMap.getPane());
		this.rootAdd(playerImage);
		inputList = new ArrayList();
		this.getScene().setOnKeyPressed(mainPlayer -> {
			String code = mainPlayer.getCode().toString();
			if (!inputList.contains(code)) // only add once... prevent duplicates
				inputList.add(code);
		});

		this.getScene().setOnKeyReleased(mainPlayer -> {
			String code = mainPlayer.getCode().toString();
			inputList.remove(code);
		});

//		input = new Input(this.getScene());
//		input.addListeners();
		Collisions collision = new Collisions(mainPlayer,playerImage,mainMap,drawMap.getPane());
		
		/*
		 * Animation Timer to handle player movement
		 */
		AnimationTimer gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				of(Direction.cachedValues).filter(v -> inputList.contains(v.name())).findFirst().ifPresent(dir -> {
					mainPlayer.downspeed = 5;
					mainPlayer.upspeed = 5;
					mainPlayer.leftspeed = 5;
					mainPlayer.rightspeed = 5;
					
					t2 = System.nanoTime();
					diff = t2 - t1; //check time elapsed, reset t1 if gets too late
					
					collision.checkCollisions(dir);
					
					if (diff < interval) {
						playerImage.setImage(dir.image1.apply(mainPlayer));
						playerImage.setX(mainPlayer.getPosX() - PLAYER_WIDTH);
						playerImage.setY(mainPlayer.getPosY() - PLAYER_HEIGHT);
					}

					if (diff > interval && diff < interval * 2) {
						playerImage.setImage(dir.image.apply(mainPlayer));
						playerImage.setX(mainPlayer.getPosX() - PLAYER_WIDTH);
						playerImage.setY(mainPlayer.getPosY() - PLAYER_HEIGHT);
					}
					if (diff > interval * 2 && diff < interval * 3) {
						playerImage.setImage(dir.image2.apply(mainPlayer));
						playerImage.setX(mainPlayer.getPosX() - PLAYER_WIDTH);
						playerImage.setY(mainPlayer.getPosY() - PLAYER_HEIGHT);
					}
					if (diff > interval * 3) {
						playerImage.setImage(dir.image.apply(mainPlayer));
						playerImage.setX(mainPlayer.getPosX() - PLAYER_WIDTH);
						playerImage.setY(mainPlayer.getPosY() - PLAYER_HEIGHT);
					}
					if (diff > interval * 4) t1 = System.nanoTime();
					dir.move.accept(mainPlayer);
					mainPlayer.direction = dir;

				});
				
				//mainPlayer.processInput(input);
				//mainPlayer.move();
				//drawPlayer();
			}	
		};
		gameLoop.start();
		
	}
	
	private void drawPlayer() {
		playerImage.setX(mainPlayer.getPosX());
		playerImage.setY(mainPlayer.getPosY());
	}
	

}
