package authoring.eventManage;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

import data.PropertyReader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * 
 * @author cy122
 * 
 * This provides a UI to let user edit the event
 *
 */

public class EventEditor {
	private Stage stage =  new Stage();
	private EventRegister eventRegister = new EventRegister();
	private BorderPane root = new BorderPane();
	private EventImage eventImage;
	//TODO: load the actual data into this
	private EventInstructions eventInstructions = new EventInstructions(new PropertyReader("../resources/English.properties").getMap("Instructions"));
	private Scene scene = new Scene(root);
	
	public EventEditor(EventImage eventImage){
		this.eventImage = eventImage;
		this.eventImage.addFileChooser(root);
		//TODO: add the whole components to event editor
		root.setLeft(eventImage);
		root.setCenter(eventInstructions.getListView());
		stage.setTitle("Event Editor");
		stage.setScene(scene);
		stage.show();
	}
	
}
