package tests.authoring;

import java.io.File;
import data.PropertyReader;
import authoring.eventManage.EventEditor;
import authoring.eventManage.EventImage;
import javafx.application.Application;
import javafx.stage.Stage;

public class EventEditorTest extends Application{

	private String path = "../resources/PathMap.properties";
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage){
		//System.out.printf("%s",new PropertyReader(path).getString("DefaultImage"));
		new EventEditor(new EventImage(new File(new PropertyReader(path).getString("DefaultImage")).getAbsolutePath()));
	}

}
