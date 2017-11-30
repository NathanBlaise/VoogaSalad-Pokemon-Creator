package engine.game;

import authoring.ScreenDisplay;
import data.event.Event;
import data.event.EventNPC;
import data.event.EventPokemon;
import data.event.Instruction;
import data.map.Cell;
import data.map.DrawMap;
import data.map.GameMap;
import data.model.Model;
import data.player.Player;
import engine.Engine;
import engine.battle.BattleScene;
//import engine.movement.Collisions;
import engine.movement.Direction;
import engine.movement.Input;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

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
	
	AnimationTimer gameLoop;
	private GameMap mainMap;
	private GridPane mapPane;
	private Player mainPlayer;
	private Model gameModel;
	private Direction collisionDir;
	//private Input input;
	private ArrayList<String> inputList;
	private ImageView playerImage;
	private boolean detectCollisions;

	
	public GameScene(int width, int height, Paint background, Engine engine, Stage stage) {
		super(width, height, background);
		// Grabs map, player and all data in model from chosen database, saves in variables
		mainMap = engine.getDatabase().getMap();
		mainPlayer = engine.getDatabase().getPlayer();
		gameModel = engine.getDatabase().getModel();
		DrawMap drawMap = new DrawMap(mainMap);
		playerImage = new ImageView(image);
		playerImage.setFitHeight(PLAYER_HEIGHT);
		playerImage.setFitWidth(PLAYER_WIDTH);
		mapPane = drawMap.getPane();
		this.rootAdd(mapPane);
		this.rootAdd(playerImage);
		detectCollisions = true;
		inputList = new ArrayList<String>();
		this.getScene().setOnKeyPressed(mainPlayer -> {
			String code = mainPlayer.getCode().toString();
			if (!inputList.contains(code)) // only add once... prevent duplicates
				inputList.add(code);
		});
		
		this.getScene().setOnKeyReleased(mainPlayer -> {
			String code = mainPlayer.getCode().toString();
			inputList.remove(code);
		});
		
		collisionDir = null;

//		input = new Input(this.getScene());
//		input.addListeners();
		//Collisions collision = new Collisions(mainPlayer,playerImage,mainMap,drawMap.getPane(),stage,this.getScene());
		
		/*
		 * Animation Timer to handle player movement
		 */
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				of(Direction.cachedValues).filter(v -> inputList.contains(v.name())).findFirst().ifPresent(dir -> {
					mainPlayer.downspeed = 5;
					mainPlayer.upspeed = 5;
					mainPlayer.leftspeed = 5;
					mainPlayer.rightspeed = 5;
					
					t2 = System.nanoTime();
					diff = t2 - t1; //check time elapsed, reset t1 if gets too late
					if(detectCollisions) checkCollisions(dir);
					
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
		
		startGameLoop();
	}
	
	public void startGameLoop() {
		inputList.clear();
		gameLoop.start();
	}
	
	public void detectCollisions() {
		detectCollisions = true;
	}
	
	private void checkCollisions(Direction dir) {
		//Cycle through cells on the map
		for (Node node: mapPane.getChildren()) {
			int i = GridPane.getRowIndex(node);
			int j = GridPane.getColumnIndex(node);
			if(playerImage.intersects(node.getBoundsInParent()) && collisionDir == null) {
				Cell cell = mainMap.getCells()[i][j];
				if(cell.isObstacle()) {
					collisionDir = dir;
				}
				Event event = cell.getEvent();
				if(event != null) {
					if(event instanceof EventPokemon) {
						System.out.println("Pokemon encountered ");
						detectCollisions = false;
						BattleScene battle = new BattleScene(720,480,Color.WHITE,mainPlayer,null,((EventPokemon) event).getPokemon(),this);
						((Stage) this.getScene().getWindow()).setScene(battle.getScene());
					}
					else if(event instanceof EventNPC) {
						System.out.println("NPC encountered");
						for(Instruction instruction: event.getInstructions()) {
							//execute instructions
						}
					}
				}
			}	
		}
		if(collisionDir != null) {
			System.out.println(collisionDir);
			if (collisionDir == DOWN) mainPlayer.downspeed = 0; 
			if (collisionDir == UP ) mainPlayer.upspeed = 0;
			if (collisionDir == LEFT) mainPlayer.leftspeed = 0;
			if (collisionDir == RIGHT) mainPlayer.rightspeed = 0;
		}
		int count = 0;
		for (Node node: mapPane.getChildren()) {
			int i = GridPane.getRowIndex(node);
			int j = GridPane.getColumnIndex(node);
			if (playerImage.intersects(node.getBoundsInParent())) {
				Cell cell = mainMap.getCells()[i][j];
				if(cell.isObstacle()) {
					count = 1;
				}
			}
		}
		if (count == 0) {
			collisionDir = null;
		}
	}
	

}
