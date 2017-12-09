package engine.movement;

import java.util.List;
import java.util.Map;

import data.player.Player;
import engine.game.GameScene;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class PlayerMovement {
	
	// add a runSpeed here to allow players to run
	private int runSpeed = 1;
	private int frame = 0;
	private Direction direction;
	
	public PlayerMovement(Direction direction){
		this.direction = direction;
	}
	
	
	public Pair<Double, Double> nextPosition(ImageView playerImage, Map<String, Pair<Integer, Integer>> input2direction, Input input, Pair<Double, Double> nextPos) {
		int nextPic = (++frame/30);
	    for(String key: input2direction.keySet()){
		    double nextPosX = nextPos.getKey();
		    double nextPosY = nextPos.getValue();
	    	if(input.getInputList().contains(key)){
	    		nextPosX += input2direction.get(key).getKey()*runSpeed;
	    		nextPosY += input2direction.get(key).getValue()*runSpeed;
	    		List<Image> imageSets = direction.getPictures(key).getImages();
	    		playerImage.setImage(imageSets.get(nextPic%imageSets.size()));
	    		nextPos = new Pair<Double, Double>(nextPosX, nextPosY);
	    	}
	    }
		return nextPos;
	}
	
	/**
	 * change the position if the player hasn't collided with the border of map
	 * @param playerImage - the imageview of player
	 * @param nextX - the x coordinate of the left-up point of future player's image
	 * @param nextY - the y coordinate of the left-up point of future player's image
	 * @param mapWidth - the width of map
	 * @param mapHeight - the height of map
	 */
	public static void changePos(Player player, double pixelSize, ImageView playerImage, double nextX, double nextY, Canvas tileCanvas, GameScene gameScene){
		if (nextX >= tileCanvas.getLayoutX() && nextY >= tileCanvas.getLayoutY() && (nextX + playerImage.getFitWidth()<= tileCanvas.getLayoutX()+tileCanvas.getWidth())&&(nextY + playerImage.getFitHeight()<= tileCanvas.getLayoutY()+tileCanvas.getHeight())) {
			gameScene.changePlayerImagePosition(nextX, nextY);
//			player.setPosX(new Double(nextX/pixelSize).intValue());
//			player.setPosY(new Double(nextY/pixelSize).intValue());
		}
	}
	
	
	/**
	 * 
	 * @param posX - the posX of player
	 * @param posY - the posY of player
	 * @param gridWidth - the width of a grid
	 * @param gridHeight - the height of a grid
	 * @return the left element of pair is row index, the right element of pair is column index
	 */
	public static Pair<Integer, Integer> playerIndexOnGrid(double posX, double posY, double gridWidth, double gridHeight){
		return new Pair<Integer, Integer>(new Double(posX/gridWidth).intValue(), new Double(posY/gridHeight).intValue());
	}
	
	
}
