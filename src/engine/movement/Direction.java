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
		UP(sprite_up_rest, sprite_up_1, sprite_up_2),
		DOWN(sprite_down_rest, sprite_down_1, sprite_down_2),
		LEFT(sprite_left_rest, sprite_left_1, sprite_left_2),
		RIGHT(sprite_right_rest, sprite_right_1, sprite_right_2);
		
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

	private static Image sprite_down_rest = new Image("file:images/emerald_down_rest.png"); 
	private static Image sprite_down_1 = new Image("file:images/emerald_down_1.png");
	private static Image sprite_down_2 = new Image("file:images/emerald_down_2.png");
	private static Image sprite_left_rest = new Image("file:images/emerald_left_rest.png");
	private static Image sprite_left_1 = new Image("file:images/emerald_left_1.png");
	private static Image sprite_left_2 = new Image("file:images/emerald_left_2.png");
	private static Image sprite_right_rest = new Image("file:images/emerald_right_rest.png");
	private static Image sprite_right_1 = new Image("file:images/emerald_right_1.png");
	private static Image sprite_right_2 = new Image("file:images/emerald_right_2.png");
	private static Image sprite_up_rest = new Image("file:images/emerald_up_rest.png");;
	private static Image sprite_up_1 = new Image("file:images/emerald_up_1.png");
	private static Image sprite_up_2 = new Image("file:images/emerald_up_2.png");

	
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
