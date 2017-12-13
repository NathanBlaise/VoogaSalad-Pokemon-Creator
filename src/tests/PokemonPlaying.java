package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import authoring.Author;
import authoring.StageDelegate;
import authoring.eventManage.Function3;
import data.Database;
import data.saving.DatabaseLoader;
import engine.Engine;
import engine.UI.Path2Image;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import start.DatabasePathConfig;
/**
 * Directly goes to the authoring environment for testing
 * @author DanSun
 *
 */
public class PokemonPlaying extends Application{

    public static void main(String[] args) {
	launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
	Stage gameStage = new Stage();
	String type = "Pokemon";
	String dataBaseName = "RunFromAuthoringTestOnly!";
	String path = DatabaseLoader.getDatabasePath(type, dataBaseName);
	Database tempDatabase = DatabaseLoader.loadDatabase(type, path);
	
	
    	//shows the splash game screen, and creates the Engine
	ImageView background = new ImageView(Path2Image.showImage("images/BattleBegin.gif"));
	background.setFitWidth(720);
	background.setFitHeight(480);
	Group root = new Group();
	root.getChildren().add(background);
	Scene splashGame = new Scene(root);
	
	gameStage.setScene(splashGame);
	gameStage.centerOnScreen();
	gameStage.show();
	splashGame.setOnKeyPressed(e->{
		Engine engine = new Engine(tempDatabase, path, type, gameStage);
		engine.toMainGameScene();
	});
    }

}
