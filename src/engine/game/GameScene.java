package engine.game;

import authoring.ScreenDisplay;
import data.event.Event;
import data.event.EventNPC;
import data.event.EventPokemon;
import data.event.Instruction;
import data.event.InstructionNPCDialogue;
import data.map.Cell;
import data.map.DrawMap;
import data.map.GameMap;
import data.model.Model;
import data.player.Player;
import engine.Engine;
import engine.battle.BattleScene;
import engine.battle.NPCBattleHelper;
//import engine.movement.Collisions;
import engine.movement.Direction;
import engine.movement.Input;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import static java.util.stream.Stream.of;

import java.util.ArrayList;

/**
 * The main scene for the game engine (where the player walks around)
 * This class EXTENDS ScreenDisplay
 * @author nathanlewis, cy122, tony
 *
 */

public class GameScene extends ScreenDisplay {

	private static final int PLAYER_WIDTH = 45;
	private static final int PLAYER_HEIGHT = 45;
	private final Image image = new Image("file:images/emerald_down_rest.png");

	private long t1 = System.nanoTime();
	private long t2, diff;
	private long interval = 200000000;
	
	private static final int offsetX = 10; //for the offset of the block of the player
	private static final int offsetY = 10; //for the offset of the block of the player
	private static final int sizeBlockX = PLAYER_WIDTH - 2*offsetX;
	private static final int sizeBlockY = PLAYER_HEIGHT - 2*offsetY;

	AnimationTimer gameLoop;
	private GameMap mainMap;
	private GridPane mapPane;
	private Player mainPlayer;
	private Model gameModel;
	private Direction collisionDir;
	private Stage myStage;
	private NPCBattleHelper npcHelper;

	//private Input input;
	private ArrayList<String> inputList;
	private ImageView playerImage;
	private Canvas tileCanvas;
	private boolean detectCollisions;
	private GraphicsContext gc;
	private Dialogue NPCDialogue;
	private Boolean beginDialogue = false;
	// add a runSpeed here to allow players to run
	private int runSpeed = 1;
	private ScreenDisplay myself;
	private int frame = 0;
	private boolean upkey = false;
	private boolean rightkey = false;
	private boolean downkey = false;
	private boolean leftkey = false;


	public GameScene(int width, int height, Paint background, Engine engine, Stage stage) {
		super(width, height, background);
		// Grabs map, player and all data in model from chosen database, saves in variables
		mainMap = engine.getDatabase().getMap();
		mainPlayer = engine.getDatabase().getPlayer();
		gameModel = engine.getDatabase().getModel();


		//Initialize variables; Deal with map
		tileCanvas = new Canvas (720,480);
		gc = tileCanvas.getGraphicsContext2D();
		DrawMap drawMap = new DrawMap(mainMap,gc);

		//Deal with player
		playerImage = new ImageView(image);
		playerImage.setFitHeight(PLAYER_HEIGHT);
		playerImage.setFitWidth(PLAYER_WIDTH);
		this.getScene().setOnKeyPressed(e->{
		    if (e.getCode() == KeyCode.DOWN) {
		          downkey = true;
		    } else if (e.getCode() == KeyCode.UP) {
		             upkey = true;
		    }else if (e.getCode() == KeyCode.LEFT) {
		             leftkey = true;
		    }else if (e.getCode() == KeyCode.RIGHT) {
		             rightkey = true;
		    }
		});
		this.getScene().setOnKeyReleased(e->{
		    if (e.getCode() == KeyCode.DOWN) {
		    		downkey = false;
		    } else if (e.getCode() == KeyCode.UP) {
		    		upkey = false;
		    }else if (e.getCode() == KeyCode.LEFT) {
		    		leftkey = false;
		    }else if (e.getCode() == KeyCode.RIGHT) {
		    		rightkey = false;
		    }
		});
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY*0.0025),
				e -> step(SECOND_DELAY*0.01));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();

		//Add tile pics into root
		this.rootAdd(tileCanvas);
		mapPane = drawMap.getPane();
		this.rootAdd(mapPane);
		this.rootAdd(playerImage);
		detectCollisions = true;
		
		inputList = new ArrayList<String>();
		NPCDialogue = new Dialogue();
		myStage = stage;
		myself = this;
		npcHelper = new NPCBattleHelper(720, 480, Color.WHITE);
	}

	private void step(double sECOND_DELAY) 		{
		double nextPosX = playerImage.getX();
		double nextPosY = playerImage.getY();
		int nextPic = (++frame/30)%3;
	    if (downkey) {
	        nextPosY += runSpeed;
	        playerImage.setImage(Direction.pictures.DOWN.getImages().get(nextPic));
	    } else if (upkey) {
	        nextPosY -= runSpeed;
	        playerImage.setImage(Direction.pictures.UP.getImages().get(nextPic));
	    }else if (leftkey) {
	    		nextPosX -= runSpeed;
	    		playerImage.setImage(Direction.pictures.LEFT.getImages().get(nextPic));
	    }else if (rightkey) {
	    		nextPosX += runSpeed;
	    		playerImage.setImage(Direction.pictures.RIGHT.getImages().get(nextPic));
	    }
	    checkCollision(nextPosX, nextPosY);
	}

//		this.getScene().setOnKeyPressed(mainPlayer -> {
//			String code = mainPlayer.getCode().toString();
//			if (!inputList.contains(code)) // only add once... prevent duplicates
//				inputList.add(code);
//		});
//
//		this.getScene().setOnKeyReleased(mainPlayer -> {
//			String code = mainPlayer.getCode().toString();
//			inputList.remove(code);
//		});
//
//		collisionDir = null;
//
//		//		input = new Input(this.getScene());
//		//		input.addListeners();
//		//Collisions collision = new Collisions(mainPlayer,playerImage,mainMap,drawMap.getPane(),stage,this.getScene());
//
//		/*
//		 * Animation Timer to handle player movement
//		 */
//
//
//
//	
//
//		gameLoop = new AnimationTimer() {
//			@Override
//			public void handle(long now) {
//				
//				if (beginDialogue)  NPCDialogue.DialogueExecution();
//				
//				if (NPCDialogue.getBattleFlag() == true) 	{
//					
//					if (!npcHelper.isBattleStart) {
//					
//					myStage.setScene(npcHelper.getScene());
//					npcHelper.startTimer();
//					npcHelper.setIsBattleStart(true);
//					}
//					
//					else {
//						NPCDialogue.setBattleFlag(false);
//					}
//				}
//
//				npcHelper.updateBattle((GameScene)myself,inputList);
//				
//				of(Direction.cachedValues).filter(v -> inputList.contains(v.name())).findFirst().ifPresent(dir -> {
//
//					//change speed when you press X
//					if (inputList.contains("X")) runSpeed = 5; 
//					if (!inputList.contains("X")) runSpeed = 3; 
//
//					mainPlayer.downspeed = runSpeed;
//					mainPlayer.upspeed = runSpeed;
//					mainPlayer.leftspeed = runSpeed;
//					mainPlayer.rightspeed = runSpeed;
//
//					t2 = System.nanoTime();
//					diff = t2 - t1; //check time elapsed, reset t1 if gets too late
////					if(detectCollisions) checkCollisions(dir);
//
//					if (diff < interval) {
//						playerImage.setImage(dir.image1.apply(mainPlayer));
////						playerImage.setX(mainPlayer.getPosX() - PLAYER_WIDTH);
////						playerImage.setY(mainPlayer.getPosY() - PLAYER_HEIGHT);
//					}
//
//					if (diff > interval && diff < interval * 2) {
//						playerImage.setImage(dir.image.apply(mainPlayer));
////						playerImage.setX(mainPlayer.getPosX() - PLAYER_WIDTH);
////						playerImage.setY(mainPlayer.getPosY() - PLAYER_HEIGHT);
//					}
//					if (diff > interval * 2 && diff < interval * 3) {
//						playerImage.setImage(dir.image2.apply(mainPlayer));
////						playerImage.setX(mainPlayer.getPosX() - PLAYER_WIDTH);
////						playerImage.setY(mainPlayer.getPosY() - PLAYER_HEIGHT);
//					}
//					if (diff > interval * 3) {
//						playerImage.setImage(dir.image.apply(mainPlayer));
////						playerImage.setX(mainPlayer.getPosX() - PLAYER_WIDTH);
////						playerImage.setY(mainPlayer.getPosY() - PLAYER_HEIGHT);
//					}
//					if (diff > interval * 4) t1 = System.nanoTime();
//					dir.move.accept(mainPlayer);
//					mainPlayer.direction = dir;
//
//				});
//
//				//mainPlayer.processInput(input);
//				//mainPlayer.move();
//				//drawPlayer();
//				
//
//			}	
//			};
//		
//		startGameLoop();
//	}

	public void startGameLoop() {
		inputList.clear();
		gameLoop.start();
	}

	public void detectCollisions() {
		detectCollisions = true;
	}
	
	private int checkCollision(double nextX, double nextY) {
		for (Node node: mapPane.getChildren() ) {
			if (GridPane.getRowIndex(node)!= null && GridPane.getColumnIndex(node)!= null){
				int i = GridPane.getRowIndex(node);
				int j = GridPane.getColumnIndex(node);
				if(node.getBoundsInParent().intersects(nextX + offsetX, nextY + offsetY, sizeBlockX, sizeBlockY) && collisionDir == null) {
					return -1;
				}
			}
		}
		if (nextX >= 0 && nextY >= 0 && (nextX + playerImage.getFitWidth()<= mapPane.getWidth())&&(nextY + playerImage.getFitHeight()<= mapPane.getHeight())) {
			playerImage.setX(nextX);
			playerImage.setY(nextY);
			return 1;
		}
		return -1;
	}

//	private void checkCollisions(Direction dir) {
//		//Cycle through cells on the map
//		for (Node node: mapPane.getChildren() ) {
//			if (GridPane.getRowIndex(node)!= null && GridPane.getColumnIndex(node)!= null){
//			int i = GridPane.getRowIndex(node);
//			int j = GridPane.getColumnIndex(node);
//			if(playerImage.getBoundsInParent().intersects(node.getBoundsInParent()) && collisionDir == null) {
//				Cell cell = mainMap.getCells()[i][j];
//				if(cell.isObstacle() || cell.getEvent()!=null) {
//					collisionDir = dir;
//					System.out.printf("collide! %d %d: ", i, j);
//					System.out.print(collisionDir);
//					System.out.printf("\n");
//				}
//				Event event = cell.getEvent();
//				
//				// Deal With Event
//				
//				if(event != null) {
//					Scene resultScene = null;
//					
//					if(event instanceof EventPokemon) {
//						System.out.println("Pokemon encountered ");
//						detectCollisions = false;
//						BattleScene battle = new BattleScene(720,480,Color.WHITE,mainPlayer,null,((EventPokemon) event).getPokemon(), this);
//
//						// Change the battle scene here
//						resultScene = battle.getScene();
//						((Stage) this.getScene().getWindow()).setScene(resultScene);
//						cell.setEvent(null);
//
//						//Not Let the player Move Away
//						resultScene.setOnKeyReleased(mainPlayer -> {
//							String code = mainPlayer.getCode().toString();
//							inputList.remove(code);
//						});
//
//
//					} else if(event instanceof EventNPC) {
//						System.out.println("NPC encountered");
//
//						for(Instruction instruction: event.getInstructions()) {
//							//execute instructions
//
////							if (instruction instanceof InstructionNPCDialogue) {
////								ArrayList<String> DialogList = ((InstructionNPCDialogue) instruction).getDialogues();
////								NPCDialogue = new Dialogue(DialogList, this, inputList);
////								beginDialogue = true;
////							}
//							
//							
//						}
//						}
//
//					}
//				}
//
//
//
//
//
//
//
//				//					resultScene.setOnKeyPressed(mainPlayer -> {
//				//						String code = mainPlayer.getCode().toString();
//				//						if (!inputList.contains(code)) // only add once... prevent duplicates
//				//							inputList.add(code);
//				//					});
//				//
//
//				//remove the event
//
//			}
//		}	
//
////		if(collisionDir != null) {
////			//System.out.println(collisionDir);
////			if (collisionDir == DOWN) mainPlayer.downspeed = 0; 
////			if (collisionDir == UP ) mainPlayer.upspeed = 0;
////			if (collisionDir == LEFT) mainPlayer.leftspeed = 0;
////			if (collisionDir == RIGHT) mainPlayer.rightspeed = 0;
////		}
////		
////		
////		int count = 0;
////		for (Node node: mapPane.getChildren()) {
////			if (GridPane.getRowIndex(node)!= null && GridPane.getColumnIndex(node)!= null) {
////			int i = GridPane.getRowIndex(node);
////			int j = GridPane.getColumnIndex(node);
////			if (playerImage.intersects(node.getBoundsInParent())) {
////				Cell cell = mainMap.getCells()[i][j];
////				if(cell.isObstacle()) {
////					count = 1;
////				}
////			}
////		}
////		}
////		if (count == 0) {
////			collisionDir = null;
////		}
//	}



	
	/**
	 * The method called by NPC Battle Helper
	 * Come back from the NPC Battle Scene to Game Scene
	 */
	public void changeBackScene() {
		myStage.setScene(this.getScene());
	}




}
