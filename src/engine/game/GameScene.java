package engine.game;

import authoring.ScreenDisplay;
import data.event.Event;
import data.event.Instruction;
import data.map.DrawMap;
import data.map.GameMap;
import data.player.Player;
import engine.Engine;
//import engine.movement.Collisions;
import engine.movement.Direction;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Node;
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
import java.util.function.Function;

/**
 * The main scene for the game engine (where the player walks around)
 * This class EXTENDS ScreenDisplay
 * @author nathanlewis, cy122, tony
 *
 */

public class GameScene extends ScreenDisplay {

	private static final int PLAYER_WIDTH = 45;
	private static final int PLAYER_HEIGHT = 45;
	private static final int pixelSize = 48;
	private final int SCREEN_HEIGHT;
	private final int SCREEN_WIDTH;
	private final Image image = new Image("file:images/emerald_down_rest.png");
	
	private static final int offsetX = 10; //for the offset of the block of the player
	private static final int offsetY = 10; //for the offset of the block of the player
	private static final int sizeBlockX = PLAYER_WIDTH - 2*offsetX;
	private static final int sizeBlockY = PLAYER_HEIGHT - 2*offsetY;

//	AnimationTimer gameLoop;
	private GameMap mainMap;
	private GridPane mapPane;
	private Player mainPlayer;
//	private Direction collisionDir;
	private Stage myStage;

	//private Input input;
	private ArrayList<String> inputList;
	private Map<String, ArrayList<Function<String, Integer>>> inputHandler = new HashMap<String, ArrayList<Function<String, Integer>>>(){
		private static final long serialVersionUID = -7597339312567242941L;
		@Override
		public ArrayList<Function<String, Integer>> get(Object key){
			if(!this.keySet().contains(key)){
				this.put((String)key, new ArrayList<Function<String, Integer>>());
			}
			return super.get(key);
		}
		
	}; //this is for register a handler for different kind of component
	
	private ImageView playerImage;
	private Canvas tileCanvas;
	private GraphicsContext gc;
	// add a runSpeed here to allow players to run
	private int runSpeed = 1;
	private int frame = 0;
	private static final Map<String, Pair<Integer, Integer>> direction2grid = new HashMap<String, Pair<Integer, Integer>>();

	{
		direction2grid.put(KeyCode.DOWN.toString(), new Pair<Integer, Integer>(1, 0));
		direction2grid.put(KeyCode.UP.toString(), new Pair<Integer, Integer>(-1, 0));
		direction2grid.put(KeyCode.LEFT.toString(), new Pair<Integer, Integer>(0, -1));
		direction2grid.put(KeyCode.RIGHT.toString(), new Pair<Integer, Integer>(0, 1));
		inputList = new ArrayList<String>(){
			private static final long serialVersionUID = -7808020477777447026L;
			@Override
			public boolean add(String element){
				boolean flag = false;
				if(!inputList.contains(element)){
					flag = super.add(element);
				}

				if(inputHandler.keySet().contains(element)){
					for(int i=0;;i++){
						if(i>=inputHandler.get(element).size()){
							break;
						}else{
							inputHandler.get(element).get(i).apply(element);
						}
					}
				}
				return flag;
			}
		};
	}
	
	
	public GameScene(int width, int height, Paint background, Engine engine, Stage stage) {
		super(width, height, background);
		// Grabs map, player and all data in model from chosen database, saves in variables
		mainMap = engine.getDatabase().getMap();
		mainPlayer = engine.getDatabase().getPlayer();

		SCREEN_HEIGHT = height;
		SCREEN_WIDTH = width;

		//Initialize variables; Deal with map
		tileCanvas = new Canvas (SCREEN_WIDTH,SCREEN_HEIGHT);
		gc = tileCanvas.getGraphicsContext2D();
		DrawMap drawMap = new DrawMap(mainMap,gc);

		//Deal with player
		playerImage = new ImageView(image);
		playerImage.setFitHeight(PLAYER_HEIGHT);
		playerImage.setFitWidth(PLAYER_WIDTH);
		
		this.getScene().setOnKeyPressed(e->{
			String code = e.getCode().toString();
			inputList.add(code); //add method is overrided by inputList
		});
		this.getScene().setOnKeyReleased(e->{
			String code = e.getCode().toString();
				inputList.remove(code);
		});

		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY*0.0025),
				e -> step());
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();

		//Add tile pics into root
		this.rootAdd(tileCanvas);
		mapPane = drawMap.getPane();
		this.rootAdd(mapPane);
		this.rootAdd(playerImage);

		myStage = stage;
	}

	/**
	 * 
	 * called recursively during the runtime of game
	 */
	private void step() 		{
		double nextPosX = playerImage.getX();
		double nextPosY = playerImage.getY();
		int nextPic = (++frame/30)%3;
	    for(String key: direction2grid.keySet()){
	    	if(inputList.contains(key)){
	    		nextPosY += direction2grid.get(key).getKey()*runSpeed;
	    		nextPosX += direction2grid.get(key).getValue()*runSpeed;
	    		playerImage.setImage(Direction.getPictures(key).getImages().get(nextPic));
	    	}
	    }
	    if(!checkCollision(nextPosX+offsetX, nextPosY+offsetY, sizeBlockX, sizeBlockY)){
	    	changePos(playerImage, nextPosX, nextPosY);
	    }else{
	    	Event encounterEvent = searchEvent(nextPosX+offsetX, nextPosY+offsetY, sizeBlockX, sizeBlockY);
	    	if(encounterEvent!=null){
	    		executeEvent(encounterEvent);
	    	}
	    }
	}
	
	/**
	 * search the event that the player collides with
	 * @param posX - the x coordinate of the left-up point of player's detection block
	 * @param posY - the y coordinate of the left-up point of player's detection block
	 * @param blockWidth - the width of detection block of player
	 * @param blockHeight - the height of detection block of player
	 * @return
	 */
	private Event searchEvent(double posX, double posY, double blockWidth, double blockHeight) {
		Map<Pair<Integer, Integer>, Event> collideIndexes = getCollideEvents(posX, posY, blockWidth, blockHeight);
		Pair<Integer, Integer> playerIndex = playerIndexOnGrid(playerImage.getX()+playerImage.getFitWidth()/2, playerImage.getY()+playerImage.getFitHeight()/2, pixelSize);
		for(String key:direction2grid.keySet()){
			if(inputList.contains(key)){
				Pair<Integer, Integer> tempIndex = new Pair<Integer, Integer>(playerIndex.getKey()+direction2grid.get(key).getKey(), playerIndex.getValue()+direction2grid.get(key).getValue());
				if(collideIndexes.keySet().contains(tempIndex)){
					Event tempEvent = collideIndexes.get(tempIndex);
					return tempEvent;
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param event - the event to be executed
	 */
	private void executeEvent(Event event){
		releaseAllKeys();
		for(int i=0;;) {
			//execute instructions
			if(i >= event.getInstructions().size()){
				break;
			}
			Instruction instruction = event.getInstructions().get(i);
			if (instruction.isGoNextInstruction()==false) {
				instruction.execute(SCREEN_WIDTH,SCREEN_HEIGHT,mainPlayer,mainMap,event,this);
				break;
			} else {
				i++;
			}
		}	
	}

	/**
	 * change the position if the player hasn't collided with the border of map
	 * @param playerImage - the imageview of player
	 * @param nextX - the x coordinate of the left-up point of future player's image
	 * @param nextY - the y coordinate of the left-up point of future player's image
	 */
	private void changePos(ImageView playerImage, double nextX, double nextY){
		if (nextX >= 0 && nextY >= 0 && (nextX + playerImage.getFitWidth()<= mapPane.getWidth())&&(nextY + playerImage.getFitHeight()<= mapPane.getHeight())) {
			playerImage.setX(nextX);
			playerImage.setY(nextY);
		}
	}
	
	/**
	 * check whether the player has collided with other obstacles and events
	 * @param posX - the x coordinate of the left-up point of player's detection block
	 * @param posY - the y coordinate of the left-up point of player's detection block
	 * @param blockWidth - the width of detection block of player
	 * @param blockHeight - the height of detection block of player
	 * @return true if the player has collided with other nodes
	 */
	private boolean checkCollision(double posX, double posY, double blockWidth, double blockHeight) {
		for (Node node: mapPane.getChildren() ) {
			if (GridPane.getRowIndex(node)!= null && GridPane.getColumnIndex(node)!= null){
				if(node.getBoundsInParent().intersects(posX, posY, blockWidth, blockHeight)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param posX - the x coordinate of the left-up point of player's detection block
	 * @param posY - the y coordinate of the left-up point of player's detection block
	 * @param blockWidth - the width of detection block of player
	 * @param blockHeight - the height of detection block of player
	 * @return a map from the colliding event's coordinates to the event
	 */
	private Map<Pair<Integer, Integer>, Event> getCollideEvents(double posX, double posY, double blockWidth, double blockHeight){
		Map<Pair<Integer, Integer>, Event> result = new HashMap<Pair<Integer, Integer>, Event>();
		for (Node node: mapPane.getChildren() ) {
			if (GridPane.getRowIndex(node)!= null && GridPane.getColumnIndex(node)!= null){
				int i = GridPane.getRowIndex(node);
				int j = GridPane.getColumnIndex(node);
				if(node.getBoundsInParent().intersects(posX, posY, blockWidth, blockHeight) && mainMap.getCell(i, j).getEvent()!=null) {
					result.put(new Pair<Integer, Integer>(i, j), mainMap.getCell(i, j).getEvent());
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param posX - the posX of player
	 * @param posY - the posY of player
	 * @param pixelSize - the size of pixel
	 * @return the left element of pair is row index, the right element of pair is column index
	 */
	private Pair<Integer, Integer> playerIndexOnGrid(double posX, double posY, double pixelSize){
		return new Pair<Integer, Integer>(new Double(posY/pixelSize).intValue(), new Double(posX/pixelSize).intValue());
	}

	
	/**
	 * release all the pressed keys
	 */
	public void releaseAllKeys(){
		inputList.clear();
	}

	
	/**
	 * The method called by NPC Battle Helper
	 * Come back from the NPC Battle Scene to Game Scene
	 */
	public void changeBackScene() {
		myStage.setScene(this.getScene());
	}

	public ArrayList<String> getInputList() {
		return inputList;
	}

	public Map<String, ArrayList<Function<String, Integer>>> getInputHandler() {
		return inputHandler;
	}

}
