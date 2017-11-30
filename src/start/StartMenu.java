package start;

import data.Database;

import data.model.NPC;
import data.player.Player;
import engine.Engine;

import engine.UI.Fade;
import engine.UI.Path2Image;
import engine.battle.BattleScene;
import authoring.Author;
import authoring.StageDelegate;
import authoring.eventManage.Function;
import javafx.animation.FadeTransition;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * @author cy122
 * 
 * Showing the menu to user
 *
 */

public class StartMenu {
	private Scene scene;
	private Pane root = new Pane();
	
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
		
		
		FadeTransition fade = Fade.fadeIn(root, null);
		
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
		
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
		fade.play();
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
		BorderPane pathSetter = new DatabasePathConfig(editorStage, new Function<Database, String, Integer>(){
			@Override
			public Integer apply(Database one, String two) {
				StageDelegate editor = new Author(one, two, editorStage);
				editor.toFirstAuthorScene();
				return null;
			}
		});
		editorStage.setScene(new Scene(pathSetter));
		editorStage.show();
		editorStage.centerOnScreen();
	}
	
	// Used currently to test battle screen
	private void goPlay() {
		Stage gameStage = new Stage();

		BorderPane pathSetter = new DatabasePathConfig(gameStage, new Function<Database, String, Integer>() {
			@Override
			public Integer apply(Database one, String two) {
				ImageView background = new ImageView(Path2Image.showImage("images/BattleBegin.gif"));
				background.setFitWidth(720);
				background.setFitHeight(480);
				Group root = new Group();
				root.getChildren().add(background);
				Scene splashGame = new Scene(root);
				gameStage.setScene(splashGame);
				gameStage.centerOnScreen();
				splashGame.setOnKeyPressed(e->{
					if(e.getCode()==KeyCode.SPACE) {
						Engine engine = new Engine(one, two, gameStage);
						engine.toMainGameScene();
					}
				});
				return null;
			}
		});
		gameStage.setScene(new Scene(pathSetter));
		gameStage.show();
		gameStage.centerOnScreen();
	}
}
