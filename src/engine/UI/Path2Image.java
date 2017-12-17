package engine.UI;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * The class provides the functionality of producing image of the 
 * desired width and height from a path
 * @author Dan Sun for commenting, cy122 as creator
 *
 */
public class Path2Image {
	/**
	 * Create an Image object given its path
	 * @param imgPath Path to the location of the file of the iamge
	 * @return The image object
	 */
	public static Image showImage(String imgPath){
		File file = new File(imgPath);
	        Image image = new Image(file.toURI().toString());
	        return image;
	}
	/**
	 * Scales an image to a specific width and height
	 * @param source The image to be scaled 
	 * @param targetWidth The desired width 
	 * @param targetHeight The desired height
	 * @param preserveRatio Whether to preserve image ratio
	 * @return The scaled version of the image provided
	 */
	public static ImageView scale(Image source, int targetWidth, int targetHeight, boolean preserveRatio) {
	    ImageView imageView = new ImageView(source);
	    imageView.setPreserveRatio(preserveRatio);
	    imageView.setFitWidth(targetWidth);
	    imageView.setFitHeight(targetHeight);
	    return imageView;
	}
}

