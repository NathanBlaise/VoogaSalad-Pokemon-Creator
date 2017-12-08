package engine.game;

import authoring.ScreenDisplay;
import data.Database;
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
	private int screen_height;
	private int screen_width;
	private int instructionIndex = 0;
	private final Image image = new Image("file:images/emerald_down_rest.png");

	protected GameMap mainMap;
	protected Player mainPlayer;
	protected Stage myStage;
	protected GridPane mapPane;
	private Engine engine;

	protected Input input;
	
	protected ImageView playerImage;
	private Canvas tileCanvas;
	protected Timeline animation;
	protected Event currentEvent;

	protected static final Map<String, Pair<Integer, Integer>> input2direction = new HashMap<String, Pair<Integer, Integer>>();

	{
		input2direction.put(KeyCode.DOWN.toString(), new Pair<Integer, Integer>(1, 0));
		input2direction.put(KeyCode.UP.toString(), new Pair<Integer, Integer>(-1, 0));
		input2direction.put(KeyCode.LEFT.toString(), new Pair<Integer, Integer>(0, -1));
		input2direction.put(KeyCode.RIGHT.toString(), new Pair<Integer, Integer>(0, 1));
	}
	
	
	public GameScene(int PLAYER_WIDTH, int PLAYER_HEIGHT, int width, int height, Paint background, Engine engine, Stage stage) {
		super(width, height, background);
		
		screen_height = height;
		screen_width = width;
		
		// Grabs map, player and all data in model from chosen database, saves in variables
		mainMap = engine.getDatabase().getMap();
		mainPlayer = engine.getDatabase().getPlayer();
		myStage = stage;
		this.engine = engine;
		
		//Deal with player
		playerImage = new ImageView(image);
		playerImage.setFitHeight(PLAYER_HEIGHT);
		playerImage.setFitWidth(PLAYER_WIDTH);
		//Initialize variables; Deal with map
		tileCanvas = new Canvas (screen_width,screen_height);
		GraphicsContext gc = tileCanvas.getGraphicsContext2D();
		DrawMap drawMap = new DrawMap(mainMap,gc);
		//Add tile pics into root
		this.rootAdd(tileCanvas);
		mapPane = drawMap.getPane();
		this.rootAdd(mapPane);
		this.rootAdd(playerImage);

		input = new Input(this.getScene());

		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY*0.0025), e -> {
			if(hasNextInstruction()&&(currentEvent!=null)) {
				executeEvent(currentEvent, instructionIndex);
			}else{
				step();
			}
		});
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
	protected int executeEvent(Event event){
		currentEvent = event;
		instructionIndex = 0;
		executeEvent(event, instructionIndex);
		return 1;
	}
	
	private int executeEvent(Event event, int index){
		if(index >= event.getInstructions().size()){
			instructionIndex = 0;
			event = null;
			return 0;
		}
		Instruction instruction = event.getInstructions().get(index);
//		if (instruction.isGoNextInstruction()==false) {
			pause();
			instruction.execute(screen_width,screen_height,mainPlayer,mainMap,event,this);
//		} else {
//			index++;
//		}	
		return -1;
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
		if((currentEvent!=null)&&(currentEvent.getInstructions().get(instructionIndex).isGoNextInstruction())){
			instructionIndex++;
		}else{
			currentEvent = null;
		}
		myStage.setScene(this.getScene());
		myStage.sizeToScene();
		myStage.setHeight(mapPane.getHeight()+20);
		screen_height = new Double(mapPane.getHeight()).intValue();
		myStage.setWidth(mapPane.getWidth());
		screen_width = new Double(mapPane.getWidth()).intValue();
		input.releaseAllKeys();
		refreshMap(mainPlayer.getPosX(), mainPlayer.getPosY(), mainMap);
		input.addListeners();
		animation.play();
	}
	
	public boolean hasNextInstruction() {
		return instructionIndex!=0;
	}

	public Set<String> getInputList() {
		return input.getInputList();
	}

	public Map<String, ArrayList<Function<String, Integer>>> getInputHandler() {
		return input.getInputHandler();
	}
	
	public Stage getStage() {
		return myStage;
	}
	
	public void refreshMap(int futureX, int futureY, GameMap mainMap){
		currentEvent = null;
		this.mainMap = mainMap;
		this.rootClear();
		Canvas tileCanvas = new Canvas (mainMap.getYlength()*pixelSize, mainMap.getXlength()*pixelSize);
		GraphicsContext gc = tileCanvas.getGraphicsContext2D();
		DrawMap drawMap = new DrawMap(mainMap,gc);
		//Add tile pics into root
		this.rootAdd(tileCanvas);
		mapPane = drawMap.getPane();
		this.rootAdd(mapPane);
		playerImage.setX(pixelSize*futureX);
		playerImage.setY(pixelSize*futureY);
		this.rootAdd(playerImage);
	}
	
	/**
	 * 
	 * @return - the database
	 */
	public Database getDatabase(){
		return engine.getDatabase();
	}

}
