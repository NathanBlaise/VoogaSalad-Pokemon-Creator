package tests.battle;

import static org.junit.Assert.*;

import org.junit.Test;

import authoring.eventManage.Function;
import data.Database;
import engine.Engine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import start.DatabasePathConfig;

public class BattleSceneTest extends Application {
    private Engine engine;

//    @Test
//    public void test() {
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
	Stage gameStage = new Stage();

	BorderPane pathSetter = new DatabasePathConfig(gameStage, new Function<Database, String, Integer>() {
	    @Override
	    public Integer apply(Database one, String two) {
		engine = new Engine(one, two, gameStage);
		engine.toMainGameScene();
		return null;
	    }
	});
	gameStage.setScene(new Scene(pathSetter));
	gameStage.show();
	gameStage.centerOnScreen();

    }

    public static void main(String[] args) {
	launch(args);

    }

}
