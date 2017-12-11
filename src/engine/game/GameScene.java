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
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
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
	
	private boolean changeMapPosition;
	
	protected ImageView playerImage;
	protected Canvas tileCanvas;
	protected Timeline animation;
	protected Event currentEvent;

	protected static final Map<String, Pair<Integer, Integer>> input2direction = new HashMap<String, Pair<Integer, Integer>>();

	{
		input2direction.put(KeyCode.DOWN.toString(), new Pair<Integer, Integer>(0, 1));
		input2direction.put(KeyCode.UP.toString(), new Pair<Integer, Integer>(0, -1));
		input2direction.put(KeyCode.LEFT.toString(), new Pair<Integer, Integer>(-1, 0));
		input2direction.put(KeyCode.RIGHT.toString(), new Pair<Integer, Integer>(1, 0));
	}
	
	
	public GameScene(int PLAYER_WIDTH, int PLAYER_HEIGHT, int width, int height, Paint background, Engine engine, Stage stage, boolean changeMapPosition) {
		super(width, height, Color.BLACK);
		this.getRoot().setStyle("-fx-background-color: #000000");
		screen_height = height;
		screen_width = width;
		
		// Grabs map, player and all data in model from chosen database, saves in variables
		mainMap = engine.getDatabase().getMap();
		mainPlayer = engine.getDatabase().getPlayer();
		
		myStage = stage;
		this.engine = engine;
		this.changeMapPosition = changeMapPosition;
		
		//Deal with player
		playerImage = new ImageView(image);
		playerImage.setFitHeight(PLAYER_HEIGHT);
		playerImage.setFitWidth(PLAYER_WIDTH);
		playerImage.setY((height-PLAYER_HEIGHT)/2);
		playerImage.setX((width-PLAYER_WIDTH)/2);
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
			pause();
			assert(mainPlayer!=null);
			instruction.execute(screen_width,screen_height,mainPlayer,mainMap,event,this);	
		return -1;
	}
	
	/**
	 * pause the game scene
	 */
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
		refreshMap(mainMap);
		myStage.setWidth(screen_width);
		myStage.setHeight(screen_height);
		input.releaseAllKeys();
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
	
	public void refreshMap(GameMap mainMap){
		currentEvent = null;
		this.mainMap = mainMap;
		this.rootClear();
		tileCanvas = new Canvas (mainMap.getYlength()*pixelSize, mainMap.getXlength()*pixelSize);
		GraphicsContext gc = tileCanvas.getGraphicsContext2D();
		DrawMap drawMap = new DrawMap(mainMap,gc);
		//Add tile pics into root
		this.rootAdd(tileCanvas);
		mapPane = drawMap.getPane();
		this.rootAdd(mapPane);
		System.out.printf("x: %d, y: %d\n", mainPlayer.getPosX(), mainPlayer.getPosY());
		changePlayerImagePosition(mainPlayer.getPosX()*pixelSize, mainPlayer.getPosY()*pixelSize);
		this.rootAdd(playerImage);
	}
	
	/**
	 * 
	 * @return - the database
	 */
	public Database getDatabase(){
		return engine.getDatabase();
	}

	public void changePlayerImagePosition(double futureX, double futureY){
		if(changeMapPosition == true){
			tileCanvas.setLayoutX(tileCanvas.getLayoutX()+(playerImage.getX()-futureX));
			tileCanvas.setLayoutY(tileCanvas.getLayoutY()+(playerImage.getY()-futureY));
			mapPane.setLayoutX(mapPane.getLayoutX()+(playerImage.getX()-futureX));
			mapPane.setLayoutY(mapPane.getLayoutY()+(playerImage.getY()-futureY));
			for(Node node: mapPane.getChildren()){
				int row = GridPane.getRowIndex(node);
				int column= GridPane.getColumnIndex(node);
				node.setLayoutX(mapPane.getLayoutX()+pixelSize*column);
				node.setLayoutY(mapPane.getLayoutY()+pixelSize*row);
			}
		}else{
			playerImage.setX(playerImage.getX()-mainPlayer.getPosX()*pixelSize);
			playerImage.setY(playerImage.getY()-mainPlayer.getPosY()*pixelSize);
		}
		mainPlayer.setPosX(new Double((playerImage.getX()-mapPane.getLayoutX())/pixelSize).intValue());
		mainPlayer.setPosY(new Double((playerImage.getY()-mapPane.getLayoutY())/pixelSize).intValue());
	}
	
	protected Canvas getTileCanvas() {
		return tileCanvas;
	}
}
