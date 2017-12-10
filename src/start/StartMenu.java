package start;

import data.Database;
import engine.Engine;
import engine.UI.Fade;
import engine.UI.Path2Image;
import authoring.Author;
import authoring.StageDelegate;
import authoring.eventManage.Function3;
import javafx.animation.FadeTransition;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author cy122
 * @author Dan Sun for refactoring
 * Showing the menu to user
 *
 */

public class StartMenu {
	private Scene scene;
	private Pane root = new Pane();
	//coordinates for different buttons
	private final static double NewX = 593.0;
	private final static double EditX = 831.0;
	private final static double PlayX = 1063.0;
	private final static double InitialY = 575.0;
	private final static double AreaWidth = 110.0;
	private final static double AreaHeight = 50.0;
	
	
	public StartMenu(Stage stage) {
//		root.getChildren().add(createMenuBar());
		root.getChildren().add(createSplashMenu());
		root.getChildren().add(createLogo());
		
		scene = new Scene(root);
		scene.getStylesheets().add("resources/sceneStyle.css");
		addCursorPatternOnScene();	
		FadeTransition fade = Fade.fadeIn(root, null);
		addMouseEventOnScene();	
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
		fade.play();
	}

	private void addMouseEventOnScene() {
	    scene.setOnMouseClicked(e -> {
	    	double x = e.getSceneX();
	    	double y = e.getSceneY();
	    	if((y<=InitialY+AreaHeight)&&(y>=InitialY)){
	    		if((x>=NewX)&&(x<=NewX+AreaWidth)){
	    			//TODO create a new file in default configuration and let user edit this file
	    		}else if((x>=EditX)&&(x<=EditX+AreaWidth)){
	    			goEdit();
	    		}else if((x>=PlayX)&&(x<=PlayX+AreaWidth)){
	    			//TODO go to player
	    			goPlay();
	    		}
	    	}
	    });
	}

	private void addCursorPatternOnScene() {
	    scene.setOnMouseMoved(e -> {
	    	double x = e.getSceneX();
	    	double y = e.getSceneY();
	    	if((y<=InitialY+AreaHeight)&&(y>=InitialY)){
	    		if(((x>=NewX)&&(x<=NewX+AreaWidth))||((x>=EditX)&&(x<=EditX+AreaWidth))||((x>=PlayX)&&(x<=PlayX+AreaWidth))){
	    			scene.setCursor(Cursor.HAND);
	    		}else{
	    			scene.setCursor(Cursor.DEFAULT);
	    		}
	    	}else{
	    		scene.setCursor(Cursor.DEFAULT);
	    	}
	    });
	}

	private ImageView createLogo() {
		ImageView imageView = new ImageView(new Image("resources/splashImage.png"));
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(400);
		imageView.setX(470);
		imageView.setY(100);
//		RotateTransition rt = new RotateTransition(Duration.millis(600), imageView);
//		rt.setByAngle(360);
//		rt.setCycleCount(2);
//		rt.setAutoReverse(true);
//		new Timeline(new KeyFrame(Duration.seconds(1), e->rt.play())).play();
		return imageView;
	}
	
//	private MenuBar createMenuBar(){
//		MenuItem createMenu = UIComponentFactory.createMenuItem("New", e->{});
//		MenuItem authorMenu = UIComponentFactory.createMenuItem("Edit", e->{});
//		MenuItem playMenu = UIComponentFactory.createMenuItem("Play", e->{});
//		return new MenuBar(new Menu("File",null,createMenu,authorMenu,playMenu));
//	}
	
	private ImageView createSplashMenu(){
		ImageView imageView = new ImageView(new Image("resources/splashImage2.png"));
		return imageView;
	}
	
	private void goEdit(){
		Stage editorStage = new Stage();
		new DatabasePathConfig(editorStage, new Function3<String, Database, String, Integer>(){
			@Override
			public Integer apply(String gameType, Database database, String savePath) {
			    	//function to create and  go to authoring environment 
				StageDelegate editor = new Author(database, savePath, editorStage);
				editor.toFirstAuthorScene();
				return null;
			}
		});
	}
	
	private void goPlay() {
		Stage gameStage = new Stage();
		new DatabasePathConfig(gameStage, new Function3<String, Database, String, Integer>() {
			@Override
			public Integer apply(String gameType, Database database, String savePath) {
			    	//shows the splash game screen, and creates the Engine
				ImageView background = new ImageView(Path2Image.showImage("images/BattleBegin.gif"));
				background.setFitWidth(720);
				background.setFitHeight(480);
				Group root = new Group();
				root.getChildren().add(background);
				Scene splashGame = new Scene(root);
				gameStage.setScene(splashGame);
				gameStage.centerOnScreen();
				splashGame.setOnKeyPressed(e->{
					Engine engine = new Engine(database, savePath, gameType, gameStage);
					engine.toMainGameScene();
				});
				return null;
			}
		});
	}
}
