package start;

import authoring.StageDelegate;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashScreen {
	private Scene scene;
	private VBox root = new VBox();
	private StageDelegate sH;
	
	public SplashScreen(Stage stage, StageDelegate stageHelper) {
		root.getChildren().addAll(createLogo());
		
		scene = new Scene(root,320,180);
		scene.getStylesheets().add("resources/sceneStyle.css");
		
		stage.setScene(scene);
		stage.show();
		
		//set key event
		sH = stageHelper;
		scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		
	}
	private ImageView createLogo() {
		ImageView imageView = new ImageView(new Image("resources/splashImage.png"));
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(300);
		RotateTransition rt = new RotateTransition(Duration.millis(600), imageView);
		rt.setByAngle(360);
		rt.setCycleCount(2);
		rt.setAutoReverse(true);
		new Timeline(new KeyFrame(Duration.seconds(1), e->rt.play())).play();
		return imageView;
	}
	
	
	private void handleKeyInput (KeyCode code) {
		if (code == KeyCode.SPACE) {
			sH.toFirstAuthorScene();
		}
		
	}
	
}
