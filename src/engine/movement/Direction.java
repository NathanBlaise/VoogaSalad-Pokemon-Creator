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
	
	private final pictures up, down, left, right;

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


	public Direction(ArrayList<Image> up, ArrayList<Image> down, ArrayList<Image> left, ArrayList<Image> right){
		this.up = new pictures(up);
		this.down = new pictures(down);
		this.left = new pictures(left);
		this.right = new pictures(right);
	}
	
	public final pictures getPictures(String key){
		if(key.equals(KeyCode.LEFT.toString())){
			return left;
		}else if(key.equals(KeyCode.RIGHT.toString())){
			return right;
		}else if(key.equals(KeyCode.UP.toString())){
			return up;
		}else{
			return down;
		}
	}
	
	public class pictures{
		
		private ArrayList<Image> images = new ArrayList<Image>();
		
		pictures(ArrayList<Image> images) {
			this.images = new ArrayList<Image>(images);
		}
		
		public ArrayList<Image> getImages() {
			return images;
		}
	}

}
