package engine.movement;

import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	    		nextPosY += input2direction.get(key).getKey()*runSpeed;
	    		nextPosX += input2direction.get(key).getValue()*runSpeed;
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
	public static void changePos(ImageView playerImage, double nextX, double nextY, double mapWidth, double mapHeight){
		if (nextX >= 0 && nextY >= 0 && (nextX + playerImage.getFitWidth()<= mapWidth)&&(nextY + playerImage.getFitHeight()<= mapHeight)) {
			playerImage.setX(nextX);
			playerImage.setY(nextY);
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
		return new Pair<Integer, Integer>(new Double(posY/gridHeight).intValue(), new Double(posX/gridWidth).intValue());
	}
	
	
}
