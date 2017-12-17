package authoring.eventManage;

import java.io.File;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
/**
 * Choose the image for the event
 * @author cy122
 *
 */
public class EventImage extends BorderPane{
	private String imagePath;
	private ImageView imageHolder;
	
	/**
	 * 
	 * @param imagePath - must be a absolute path
	 * @param node
	 */
	public EventImage(String imagePath){
		super();
		Image image = new Image(new File(imagePath).toURI().toString());
		imageHolder = new ImageView(image);
		setCenter(imageHolder);
		setTop(new Label("↓Double Click to Change Image"));
		String style_outter="-fx-border-color: #F5FFFA;-fx-border-style: solid;-fx-border-width: 5;-fx-background-color: #F5FFFA;";
		super.setStyle(style_outter);
		imageHolder.setOnMouseEntered(e->{setCursor(Cursor.HAND);});
		imageHolder.setOnMouseExited(e->{setCursor(Cursor.DEFAULT);});
		this.imagePath = new String(imagePath);
	}
	
	public void addFileChooser(Node node){
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent click) {
		        if (click.getClickCount()==2) {
		           openFileChoose(node.getScene().getWindow());
		        }
		    }
		});
	}
	
	public void openFileChoose(Window window){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.setInitialDirectory(new File("images"));
		fileChooser.setInitialFileName("default.png");
		File file = fileChooser.showOpenDialog(window);
		if(file!=null){
			Image newImage = new Image(file.toURI().toString());
			if(newImage!=null){
				imageHolder.setImage(newImage);
				imagePath = file.getAbsolutePath();
			}
		}
	}
	
	public String getImagePath(){
		return new String(imagePath);
	}
	
	public Image getImage(){
		return imageHolder.getImage();
	}
}
