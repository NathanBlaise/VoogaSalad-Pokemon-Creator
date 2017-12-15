package player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import data.event.Event;
import data.event.EventPacmanEnemy;
import data.map.Cell;
import engine.Engine;
import engine.game.GameScene;
import engine.movement.Collisions;
import engine.movement.Direction;
import engine.movement.PlayerMovement;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	private Random rand = new Random();
	private PlayerMovement playerMoves;
	private Map<Event,ImageView> pacmanEnemies;
	
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
		restartGame();
	}

	@Override
	protected void step() {
		Pair<Double, Double> nextPos = new Pair<Double, Double>(playerImage.getX(), playerImage.getY());
		nextPos = playerMoves.nextPosition(playerImage, input2direction, input, nextPos);
		double nextPosX = nextPos.getKey();
	    double nextPosY = nextPos.getValue();
	    	if(!Collisions.checkCollision(nextPosX+offsetX, nextPosY+offsetY, sizeBlockX, sizeBlockY, mapPane)){
	    		PlayerMovement.changePos(pixelSize, playerImage, nextPosX, nextPosY, tileCanvas, (GameScene)this);
	    	} else {
	    		executeFoundEvent(nextPosX, nextPosY,sizeBlockX,sizeBlockY);
	    	}
	    	for (Map.Entry<Event, ImageView> entry : pacmanEnemies.entrySet()) {
	    		ImageView enemyImage = entry.getValue();
	    		if(playerImage.intersects(enemyImage.getBoundsInParent())) {
	    			this.rootRemove(playerImage);
	    			restartGame();
	    		}
	    		if(!Collisions.checkCollision(enemyImage.getX(), enemyImage.getY(), sizeBlockX, sizeBlockY, mapPane)){
	    			int newDist = (int) (rand.nextInt(3) -1) * ((EventPacmanEnemy) entry.getKey()).getPacmanEnemy().getSpeed();
	    			double newXPos = enemyImage.getX() + newDist;
	    			enemyImage.setX(newXPos);
	    		} else {
	    			enemyImage.setX(enemyImage.getX() - 5);
	    		}
	    	}
	    		
	    		
	}

	
	
	
	private void restartGame() {
		pacmanEnemies = refreshMap(mainMap,2*pixelSize,3*pixelSize);
		for (Map.Entry<Event, ImageView> entry : pacmanEnemies.entrySet()) {
			entry.getValue().setX(((EventPacmanEnemy) entry.getKey()).getXPos() * pixelSize);
			entry.getValue().setY(((EventPacmanEnemy) entry.getKey()).getYPos() * pixelSize);
			this.rootAdd(entry.getValue());
		}
		changeBackScene();
	}
	
}
