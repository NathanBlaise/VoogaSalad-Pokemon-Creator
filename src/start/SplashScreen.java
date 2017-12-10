package start;

import engine.UI.Fade;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author cy122
 * @author Dan Sun： for refactor
 * splash screen: showing to user about loading the program and showing the Logo
 * animates the spalsh screen, and spawns the StartMenu after it finishes
 * passes the current stage to the startMenu
 */

public class SplashScreen {
    
	private Stage mainStage;
	private Scene scene;
	private VBox root = new VBox();
	private RotateTransition rt; //for animation
	/**
	 * Displays the splash screen on stage
	 * @param stage The stage to display the splash screen
	 */
	public SplashScreen(Stage stage) {	
	    	mainStage=stage;
		root.getChildren().addAll(createLogo());
		setupScene();
		stage.setScene(scene);
		stage.show();
	}


	private void setupScene() {
	    scene = new Scene(root,320,180);
	    scene.getStylesheets().add("resources/sceneStyle.css");
	    scene.setOnKeyPressed(e->{
	    	if(e.getCode() == KeyCode.SPACE){
	    		rt.setOnFinished((a)->{});
	    		goToStartMenu();
	    	}
	    });
	}
	
	
	private ImageView createLogo() {
		ImageView imageView = new ImageView(new Image("resources/splashImage.png"));
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(300);
		addAnimationToLogo(imageView);
		return imageView;
	}


	private void addAnimationToLogo(ImageView imageView) {
	    rt = new RotateTransition(Duration.millis(600), imageView);
	    rt.setByAngle(360);
	    rt.setCycleCount(2);
	    rt.setAutoReverse(true);
	    rt.setOnFinished(e -> {Fade.fadeOut(root, (a)->{goToStartMenu();}).play();});
	    new Timeline(new KeyFrame(Duration.seconds(1), e->rt.play())).play();
	}
	
	private void goToStartMenu() {
	    new StartMenu(mainStage);
	}
}
