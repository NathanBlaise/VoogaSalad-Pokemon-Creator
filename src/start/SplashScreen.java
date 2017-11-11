package start;

import engine.UI.Fade;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author cy122
 *
 * splash screen: showing to user about loading the program and showing the Logo
 *
 */

public class SplashScreen {
	private Stage mainStage;
	private Scene scene;
	private VBox root = new VBox();
	
	public SplashScreen(Stage stage) {
		mainStage=stage;
		
		root.getChildren().addAll(createLogo());
		
		scene = new Scene(root,320,180);
		scene.getStylesheets().add("resources/sceneStyle.css");
		
		stage.setScene(scene);
		stage.show();
	}
	
	
	private ImageView createLogo() {
		ImageView imageView = new ImageView(new Image("resources/splashImage.png"));
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(300);
		RotateTransition rt = new RotateTransition(Duration.millis(600), imageView);
		rt.setByAngle(360);
		rt.setCycleCount(2);
		rt.setAutoReverse(true);
		rt.setOnFinished(e -> {Fade.fadeOut(root, (a)->{new StartMenu(mainStage);}).play();});
		new Timeline(new KeyFrame(Duration.seconds(1), e->rt.play())).play();
		return imageView;
	}
	
}
