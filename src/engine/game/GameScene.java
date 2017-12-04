package engine.game;

import authoring.ScreenDisplay;
import data.event.Event;
import data.event.Instruction;
import data.map.DrawMap;
import data.map.GameMap;
import data.player.Player;
import engine.Engine;
import engine.movement.Input;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * The main scene for the game engine (where the player walks around)
 * This class EXTENDS ScreenDisplay
 * @author nathanlewis, cy122, tony
 *
 */

public abstract class GameScene extends ScreenDisplay {

	protected static final int pixelSize = 48;
	private final int SCREEN_HEIGHT;
	private final int SCREEN_WIDTH;
	private final Image image = new Image("file:images/emerald_down_rest.png");

	protected GameMap mainMap;
	private Player mainPlayer;
	private Stage myStage;
	protected GridPane mapPane;

	protected Input input;
	
	protected ImageView playerImage;
	private Canvas tileCanvas;
	private Timeline animation;

	protected static final Map<String, Pair<Integer, Integer>> input2direction = new HashMap<String, Pair<Integer, Integer>>();

	{
		input2direction.put(KeyCode.DOWN.toString(), new Pair<Integer, Integer>(1, 0));
		input2direction.put(KeyCode.UP.toString(), new Pair<Integer, Integer>(-1, 0));
		input2direction.put(KeyCode.LEFT.toString(), new Pair<Integer, Integer>(0, -1));
		input2direction.put(KeyCode.RIGHT.toString(), new Pair<Integer, Integer>(0, 1));
	}
	
	
	public GameScene(int PLAYER_WIDTH, int PLAYER_HEIGHT, int width, int height, Paint background, Engine engine, Stage stage) {
		super(width, height, background);
		
		SCREEN_HEIGHT = height;
		SCREEN_WIDTH = width;
		
		// Grabs map, player and all data in model from chosen database, saves in variables
		mainMap = engine.getDatabase().getMap();
		mainPlayer = engine.getDatabase().getPlayer();
		myStage = stage;
		
		//Deal with player
		playerImage = new ImageView(image);
		playerImage.setFitHeight(PLAYER_HEIGHT);
		playerImage.setFitWidth(PLAYER_WIDTH);
		//Initialize variables; Deal with map
		tileCanvas = new Canvas (SCREEN_WIDTH,SCREEN_HEIGHT);
		GraphicsContext gc = tileCanvas.getGraphicsContext2D();
		DrawMap drawMap = new DrawMap(mainMap,gc);
		//Add tile pics into root
		this.rootAdd(tileCanvas);
		mapPane = drawMap.getPane();
		this.rootAdd(mapPane);
		this.rootAdd(playerImage);

		input = new Input(this.getScene());

		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY*0.0025), e -> step());
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
	}

	/**
	 * 
	 * called recursively during the runtime of game
	 */
	protected abstract void step();
	
	/**
	 * 
	 * @param event - the event to be executed
	 */
	protected void executeEvent(Event event){
		for(int i=0;;) {
			//execute instructions
			if(i >= event.getInstructions().size()){
				break;
			}
			Instruction instruction = event.getInstructions().get(i);
			if (instruction.isGoNextInstruction()==false) {
				pause();
				instruction.execute(SCREEN_WIDTH,SCREEN_HEIGHT,mainPlayer,mainMap,event,this);
				break;
			} else {
				i++;
			}
		}	
	}

	protected void pause() {
		input.releaseAllKeys();
		animation.pause();
		input.removeListeners();
	}
	
	/**
	 * The method called by NPC Battle Helper
	 * Come back from the NPC Battle Scene to Game Scene
	 */
	public void changeBackScene() {
		input.addListeners();
		animation.play();
		myStage.setScene(this.getScene());
	}

	public Set<String> getInputList() {
		return input.getInputList();
	}

	public Map<String, ArrayList<Function<String, Integer>>> getInputHandler() {
		return input.getInputHandler();
	}
	
	public void refreshMap(int SCREEN_WIDTH, int SCREEN_HEIGHT, GameMap mainMap){
		this.rootClear();
		Canvas tileCanvas = new Canvas (SCREEN_WIDTH,SCREEN_HEIGHT);
		GraphicsContext gc = tileCanvas.getGraphicsContext2D();
		DrawMap drawMap = new DrawMap(mainMap,gc);
		//Add tile pics into root
		this.rootAdd(tileCanvas);
		GridPane mapPane = drawMap.getPane();
		this.rootAdd(mapPane);
		playerImage.setX(0);
		playerImage.setY(0);
		this.rootAdd(playerImage);
	}

}
