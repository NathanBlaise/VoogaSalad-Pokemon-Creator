package player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import data.event.Event;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Pair;
import engine.Engine;
import engine.game.GameScene;
import engine.game.UserPage;
import engine.movement.Collisions;
import engine.movement.Direction;
import engine.movement.PlayerMovement;
/**
 * 
 * @author nathan cy122 
 *
 */
public class PokemonGameScene extends GameScene{

	private static final int PLAYER_WIDTH = 40;
	private static final int PLAYER_HEIGHT = 45;
	private static final int sizeBlockX = PLAYER_WIDTH - 2*offsetX;
	private static final int sizeBlockY = PLAYER_HEIGHT - 2*offsetY;
	private final Image image = new Image("file:images/emerald_down_rest.png");
	private PlayerMovement playerMoves;
	
	{
		Image emerald_down_rest = new Image("file:images/emerald_down_rest.png");
		Image emerald_down_1 = new Image("file:images/emerald_down_1.png");
		Image emerald_down_2 = new Image("file:images/emerald_down_2.png");
		Image emerald_left_rest = new Image("file:images/emerald_left_rest.png");
		Image emerald_left_1 = new Image("file:images/emerald_left_1.png");
		Image emerald_left_2 = new Image("file:images/emerald_left_2.png");
		Image emerald_right_rest = new Image("file:images/emerald_right_rest.png");
		Image emerald_right_1 = new Image("file:images/emerald_right_1.png");
		Image emerald_right_2 = new Image("file:images/emerald_right_2.png");
		Image emerald_up_rest = new Image("file:images/emerald_up_rest.png");
		Image emerald_up_1 = new Image("file:images/emerald_up_1.png");
		Image emerald_up_2 = new Image("file:images/emerald_up_2.png");
		ArrayList<Image> up = new ArrayList<Image>(Arrays.asList(emerald_up_rest, emerald_up_1, emerald_up_2));
		ArrayList<Image> down = new ArrayList<Image>(Arrays.asList(emerald_down_rest, emerald_down_1, emerald_down_2));
		ArrayList<Image> left = new ArrayList<Image>(Arrays.asList(emerald_left_rest, emerald_left_1, emerald_left_2));
		ArrayList<Image> right = new ArrayList<Image>(Arrays.asList(emerald_right_rest, emerald_right_1, emerald_right_2));
		Direction direction = new Direction(up, down, left, right);
		playerMoves = new PlayerMovement(direction);
	}
	
	public PokemonGameScene(int width, int height, Paint background,
			Engine engine, Stage stage) {
		super(PLAYER_WIDTH, PLAYER_HEIGHT, 480, 480, background, engine, stage, true);
		playerImage.setImage(image);
		refreshMap(engine.getDatabase().searchMap(mainPlayer.getCurrentMapName()), mainPlayer.getPosX()*pixelSize, mainPlayer.getPosY()*pixelSize);
		changeBackScene();
		input.getInputList().clear();
	}

	@Override
	protected void step()  {
		if(input.getInputList().contains(KeyCode.ENTER.toString())) {
			super.pause();
			UserPage userPage = new UserPage(super.getDatabase(), super.getStage(), super.getScene(), super.getRoot());
			super.getScene().setOnKeyPressed(i->{
				userPage.handleKeyInput(super.getStage(), super.getRoot(), i.getCode(), h->{
					super.changeBackScene();
					return null;
				});
			});	
		}else {
			Pair<Double, Double> nextPos = new Pair<Double, Double>(playerImage.getX(), playerImage.getY());
			for(Node node: mapPane.getChildren()){
				int row = GridPane.getRowIndex(node);
				int column= GridPane.getColumnIndex(node);
				node.setLayoutX(mapPane.getLayoutX()+pixelSize*column);
				node.setLayoutY(mapPane.getLayoutY()+pixelSize*row);
			}
			nextPos = playerMoves.nextPosition(playerImage, input2direction, input, nextPos);
			double nextPosX = nextPos.getKey();
		    double nextPosY = nextPos.getValue();
		    if(!Collisions.checkCollision(nextPosX+offsetX, nextPosY+offsetY, sizeBlockX, sizeBlockY, mapPane)){
		    	PlayerMovement.changePos(pixelSize, playerImage, nextPosX, nextPosY, tileCanvas, (GameScene)this);
		    }else{
				Pair<Integer, Integer> playerIndex = PlayerMovement.playerIndexOnGrid(nextPosX+offsetX+sizeBlockX/2-tileCanvas.getLayoutX(), nextPosY+offsetY+sizeBlockY/2-tileCanvas.getLayoutY(), pixelSize, pixelSize);
				Map<Pair<Integer, Integer>, Event> collideEvents = Collisions.getCollideEvents(nextPosX+offsetX, nextPosY+offsetY, sizeBlockX, sizeBlockY, mapPane, mainMap);
				ArrayList<Pair<Integer, Integer>> directions = new ArrayList<Pair<Integer, Integer>>();
				for(String key: input2direction.keySet()){
					if(input.getInputList().contains(key)){
						directions.add(input2direction.get(key));
					}
				}
				Event encounterEvent = Collisions.searchEvent(playerIndex, directions, collideEvents);
			    if((encounterEvent!=null)&&(directions.size()!=0)){
			    	executeEvent(encounterEvent);
			    }
		    }
		}
	}

}
