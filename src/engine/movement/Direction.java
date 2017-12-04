package engine.movement;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
/**
 * 
 * @author cy122, nathan, tony
 *
 */
public class Direction {
	public enum pictures{
		UP(emerald_up_rest, emerald_up_1, emerald_up_2),
		DOWN(emerald_down_rest, emerald_down_1, emerald_down_2),
		LEFT(emerald_left_rest, emerald_left_1, emerald_left_2),
		RIGHT(emerald_right_rest, emerald_right_1, emerald_right_2);
		
		private ArrayList<Image> images = new ArrayList<Image>();
		
		pictures(Image image1, Image image2, Image image3) {
			images.add(image1);
			images.add(image2);
			images.add(image3);
		}
		
		public ArrayList<Image> getImages() {
			return images;
		}
	}

	static {
		emerald_down_rest = new Image("file:images/emerald_down_rest.png");
		emerald_down_1 = new Image("file:images/emerald_down_1.png");
		emerald_down_2 = new Image("file:images/emerald_down_2.png");
		emerald_left_rest = new Image("file:images/emerald_left_rest.png");
		emerald_left_1 = new Image("file:images/emerald_left_1.png");
		emerald_left_2 = new Image("file:images/emerald_left_2.png");
		emerald_right_rest = new Image("file:images/emerald_right_rest.png");
		emerald_right_1 = new Image("file:images/emerald_right_1.png");
		emerald_right_2 = new Image("file:images/emerald_right_2.png");
		emerald_up_rest = new Image("file:images/emerald_up_rest.png");
		emerald_up_1 = new Image("file:images/emerald_up_1.png");
		emerald_up_2 = new Image("file:images/emerald_up_2.png");
	}
	
	
	/*
	 * NEEDS REFACTORING!
	 */
	public static final transient Image emerald_down_rest; //Don't need all of these
	public static final transient Image emerald_down_1;
	public static final transient Image emerald_down_2;
	public static final transient Image emerald_left_rest;
	public static final transient Image emerald_left_1;
	public static final transient Image emerald_left_2;
	public static final transient Image emerald_right_rest;
	public static final transient Image emerald_right_1;
	public static final transient Image emerald_right_2;
	public static final transient Image emerald_up_rest;
	public static final transient Image emerald_up_1;
	public static final transient Image emerald_up_2;


//	public static final Direction[] cachedValues = values();
	
	public static pictures getPictures(String key){
		if(key.equals(KeyCode.LEFT.toString())){
			return pictures.LEFT;
		}else if(key.equals(KeyCode.RIGHT.toString())){
			return pictures.RIGHT;
		}else if(key.equals(KeyCode.UP.toString())){
			return pictures.UP;
		}else{
			return pictures.DOWN;
		}
	}

}
