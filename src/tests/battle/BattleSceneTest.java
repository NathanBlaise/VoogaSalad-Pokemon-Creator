package tests.battle;

import java.awt.Color;

import authoring.eventManage.Function3;
import data.Database;
import engine.Engine;
import engine.battle.BattleScene;
import javafx.application.Application;
import javafx.scene.paint.Paint;
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

	new DatabasePathConfig(gameStage, new Function3<String, Database, String, Integer>() {
	    @Override
	    public Integer apply(String gameType, Database one, String two) {
		engine = new Engine(one, two, gameType, gameStage);
		engine.toMainGameScene();
		return null;
	    }
	});

    }

    public static void main(String[] args) {
	launch(args);

    }

}
