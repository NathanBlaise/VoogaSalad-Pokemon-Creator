package engine.movement;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.image.Image;

public class Bicycle {
	
	private boolean isBiking = false;

	public Direction  MakeBicycleSprite() {
		Image emerald_down_rest = new Image("file:images/emerald_down_rest.png");
		Image emerald_down_1 = new Image("file:images/emerald_down_1.png");
		Image emerald_down_2 = new Image("file:images/emerald_down_2.png");
		Image emerald_left_rest = new Image("file:images/Bike_left_1.gif");
		Image emerald_left_1 = new Image("file:images/Bike_left_2.gif");
		Image emerald_left_2 = new Image("file:images/Bike_left_3.gif");
		Image emerald_left_3 = new Image("file:images/Bike_left_4.gif");
		Image emerald_right_rest = new Image("file:images/Bike_right_1.gif");
		Image emerald_right_1 = new Image("file:images/Bike_right_2.gif");
		Image emerald_right_2 = new Image("file:images/Bike_right_3.gif");
		Image emerald_right_3 = new Image("file:images/Bike_right_4.gif");
		Image emerald_up_rest = new Image("file:images/emerald_up_rest.png");
		Image emerald_up_1 = new Image("file:images/emerald_up_1.png");
		Image emerald_up_2 = new Image("file:images/emerald_up_2.png");
		ArrayList<Image> up = new ArrayList<Image>(Arrays.asList(emerald_up_rest, emerald_up_1, emerald_up_2));
		ArrayList<Image> down = new ArrayList<Image>(Arrays.asList(emerald_down_rest, emerald_down_1, emerald_down_2));
		ArrayList<Image> left = new ArrayList<Image>(Arrays.asList(emerald_left_rest, emerald_left_1, emerald_left_2,emerald_left_3));
		ArrayList<Image> right = new ArrayList<Image>(Arrays.asList(emerald_right_rest, emerald_right_1, emerald_right_2,emerald_right_3));
		Direction direction = new Direction(up, down, left, right);
		
		return direction;
	}
	
	public boolean getBikingStatus() {
		return isBiking;
	}
	
	public void setBikingStatus(boolean bool) {
		isBiking = bool;
	}
	
}
