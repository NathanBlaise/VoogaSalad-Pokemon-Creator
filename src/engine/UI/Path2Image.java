package engine.UI;

import java.io.File;

import javafx.scene.image.Image;

public class Path2Image {
	
	public static Image showImage(String imgPath){
			File file = new File(imgPath);
	        Image image = new Image(file.toURI().toString());
	        return image;
	}
}
