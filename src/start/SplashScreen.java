package start;

import authoring.UIComponentFactory;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashScreen {
	private Scene scene;
	private BorderPane root = new BorderPane();
	
	
	public SplashScreen(Stage stage) {
		root.setTop(createMenuBar());
		root.setCenter(createLogo());
		
		scene = new Scene(root,380,250);
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
		new Timeline(new KeyFrame(Duration.seconds(1), e->rt.play())).play();
		return imageView;
	}
	
	private MenuBar createMenuBar(){
		MenuItem createMenu = UIComponentFactory.createMenuItem("New", e->{});
		MenuItem authorMenu = UIComponentFactory.createMenuItem("Edit", e->{});
		MenuItem playMenu = UIComponentFactory.createMenuItem("Play", e->{});
		return new MenuBar(new Menu("File",null,createMenu,authorMenu,playMenu));
	}
	
}
