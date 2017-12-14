package player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import data.event.Event;
import engine.Engine;
import engine.game.GameScene;
import engine.movement.Collisions;
import engine.movement.Direction;
import engine.movement.PlayerMovement;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * Game scene for pacman game version
 * @author nathanlewis
 *
 */

public class PacmanGameScene extends GameScene {
	
	private final static int PLAYER_WIDTH = 36;
	private final static int PLAYER_HEIGHT = 36;
	private static final int sizeBlockX = PLAYER_WIDTH - 2*offsetX;
	private static final int sizeBlockY = PLAYER_HEIGHT - 2*offsetY;
	private final Image image = new Image("file:images/pacman_movement/pacman_down_1.png");
	private PlayerMovement playerMoves;
	
	{
		Image pacman_down_1 = new Image("file:images/pacman_movement/pacman_down_1.png");
		Image pacman_down_2 = new Image("file:images/pacman_movement/pacman_down_2.png");
		Image pacman_left_1 = new Image("file:images/pacman_movement/pacman_left_1.png");
		Image pacman_left_2 = new Image("file:images/pacman_movement/pacman_left_2.png");
		Image pacman_right_1 = new Image("file:images/pacman_movement/pacman_right_1.png");
		Image pacman_right_2 = new Image("file:images/pacman_movement/pacman_right_2.png");
		Image pacman_up_1 = new Image("file:images/pacman_movement/pacman_up_1.png");
		Image pacman_up_2 = new Image("file:images/pacman_movement/pacman_up_2.png");
		ArrayList<Image> up = new ArrayList<Image>(Arrays.asList(pacman_up_1, pacman_up_2));
		ArrayList<Image> down = new ArrayList<Image>(Arrays.asList(pacman_down_1, pacman_down_2));
		ArrayList<Image> left = new ArrayList<Image>(Arrays.asList(pacman_left_1, pacman_left_2));
		ArrayList<Image> right = new ArrayList<Image>(Arrays.asList(pacman_right_1, pacman_right_2));
		Direction direction = new Direction(up, down, left, right);
		playerMoves = new PlayerMovement(direction);
	}

	public PacmanGameScene( int width, int height, Paint background, Engine engine,
			Stage stage) {
		super(PLAYER_WIDTH, PLAYER_HEIGHT, 720, 480, background, engine, stage, false);
		playerImage.setImage(image);
		refreshMap(mainMap);
		changeBackScene();
		
	}

	@Override
	protected void step() {
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
	    } else {
	    	Pair<Integer, Integer> playerIndex = PlayerMovement.playerIndexOnGrid(nextPosX+offsetX+sizeBlockX/2-tileCanvas.getLayoutX(), nextPosY+offsetY+sizeBlockY/2-tileCanvas.getLayoutY(), pixelSize, pixelSize);
			Map<Pair<Integer, Integer>, Event> collideEvents = Collisions.getCollideEvents(nextPosX+offsetX, nextPosY+offsetY, sizeBlockX, sizeBlockY, mapPane, mainMap);
			ArrayList<Pair<Integer, Integer>> directions = new ArrayList<Pair<Integer, Integer>>();
			for(String key: input2direction.keySet()){
				if(input.getInputList().contains(key)){
					directions.add(input2direction.get(key));
				}
				Event encounterEvent = Collisions.searchEvent(playerIndex, directions, collideEvents);
			}
	    }
	}
	
}
