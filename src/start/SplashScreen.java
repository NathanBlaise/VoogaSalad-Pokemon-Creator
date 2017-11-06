package start;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SplashScreen {
	private Scene scene;
	private VBox root = new VBox();
	
	
	public SplashScreen(Stage stage) {
		Label lb_text = new Label("Here are some texts");
		root.getChildren().addAll(lb_text);
		
		scene = new Scene(root,320,180);
		scene.getStylesheets().add("resources/sceneStyle.css");
		
		stage.setScene(scene);
		stage.show();
	}

}
