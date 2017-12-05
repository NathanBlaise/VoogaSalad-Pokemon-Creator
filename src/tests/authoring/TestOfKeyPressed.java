package tests.authoring;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TestOfKeyPressed  extends Application {

	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Button m = new Button("Tony");
		m.setOnKeyPressed(e->{
			System.out.println("I'm called");
		});
		root.getChildren().add(m);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

}
