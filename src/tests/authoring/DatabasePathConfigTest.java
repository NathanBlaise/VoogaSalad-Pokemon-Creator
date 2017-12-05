package tests.authoring;


import start.DatabasePathConfig;
import javafx.application.Application;
import javafx.stage.Stage;
import authoring.eventManage.Function3;
import data.Database;

public class DatabasePathConfigTest extends Application implements Function3<String, Database, String, Integer>{
			public static void main(String[] args) {
				launch(args);

			}

			@Override
			public void start(Stage primaryStage){
				new DatabasePathConfig(primaryStage ,this);
			}

			@Override
			public Integer apply(String gameType, Database one, String two) {
				// TODO Auto-generated method stub
				return null;
			}
}
