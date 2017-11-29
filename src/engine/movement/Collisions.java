package engine.movement;


import data.event.Event;
import data.event.EventNPC;
import data.event.EventPokemon;
import data.event.Instruction;
import data.map.Cell;
import data.map.GameMap;
import data.player.Player;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import static engine.movement.Direction.DOWN;
import static engine.movement.Direction.UP;
import static engine.movement.Direction.LEFT;
import static engine.movement.Direction.RIGHT;

/**
 * Handles collisions between the player and the environment
 * @author nathanlewis
 *
 */

public class Collisions {
	
	private ImageView mainPlayerImage;
	private GameMap mainMap;
	private Player mainPlayer;
	private GridPane mapPane;
	private Direction collisionDir;
	
	public Collisions (Player player, ImageView playerImage, GameMap map, GridPane pane) {
		mainPlayerImage = playerImage;
		mainMap = map;
		mapPane = pane;
		mainPlayer = player;
		collisionDir = null;
	}
	
	public void checkCollisions(Direction dir) {
		//Cycle through cells on the map
		for (Node node: mapPane.getChildren()) {
			int i = GridPane.getRowIndex(node);
			int j = GridPane.getColumnIndex(node);
//			boolean eventCheck = false;
//			if(mainMap.getCells()[i][j].getEvent() == null) eventCheck = true;
//			System.out.println("Row = "+i+" Column = "+j+" Event = "+eventCheck + " Obstacle = "+mainMap.getCells()[i][j].isObstacle());
			if(mainPlayerImage.intersects(node.getBoundsInParent()) && collisionDir == null) {
				System.out.println("Row = "+i+" Column = "+j);
				Cell cell = mainMap.getCells()[i][j];
				if(cell.isObstacle()) {
					collisionDir = dir;
				}
				Event event = cell.getEvent();
				if(event != null) {
					if(event instanceof EventPokemon) {
						System.out.println("Pokemon encountered ");
						for(Instruction instruction: event.getInstructions()) {
							//execute instructions
						}
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
			if (mainPlayerImage.intersects(node.getBoundsInParent())) {
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
