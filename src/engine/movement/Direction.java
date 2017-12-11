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
