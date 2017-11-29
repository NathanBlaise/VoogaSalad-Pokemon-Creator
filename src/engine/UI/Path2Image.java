package engine.UI;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Path2Image {
	
	public static Image showImage(String imgPath){
			File file = new File(imgPath);
	        Image image = new Image(file.toURI().toString());
	        return image;
	}
	
	public static ImageView scale(Image source, int targetWidth, int targetHeight, boolean preserveRatio) {
	    ImageView imageView = new ImageView(source);
	    imageView.setPreserveRatio(preserveRatio);
	    imageView.setFitWidth(targetWidth);
	    imageView.setFitHeight(targetHeight);
	    return imageView;
	}
}

