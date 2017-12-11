package data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * passive data object for storing tile information
 * 
 * @author cy122
 *
 */
public class Tile implements Serializable{
	private static final long serialVersionUID = -5346283872507306973L;
	private String name;
	private boolean obstacle;
	private int height;
	private int width;
	private ArrayList<String> imagePaths; //will be used if height>1 or width>1
	private String wholePic;
	
	/**
	 * for serialization
	 */
	@Deprecated
	public Tile(){
		
	}
	
	public Tile(String name, boolean obstacle, int height, int width, String wholePic, List<String> imagePaths){
		this.name = name;
		this.obstacle = obstacle;
		this.height = height;
		this.width = width;
		this.wholePic = wholePic;
		this.imagePaths = new ArrayList<String>(imagePaths);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isObstacle() {
		return obstacle;
	}

	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public String getWholePic() {
		return wholePic;
	}

	public void setWholePic(String wholePic) {
		this.wholePic = wholePic;
	}

	public ArrayList<String> getImagePaths() {
		return imagePaths;
	}

	public void setImagePaths(ArrayList<String> imagePaths) {
		this.imagePaths = imagePaths;
	}
	
	
}
