package tests.authoring;


import start.DatabasePathConfig;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import authoring.eventManage.Function;
import data.Database;

public class DatabasePathConfigTest extends Application implements Function<Database, String, Integer>{
			public static void main(String[] args) {
				launch(args);

			}

			@Override
			public void start(Stage primaryStage){
				primaryStage.setScene(new Scene(new DatabasePathConfig(primaryStage ,this)));
				primaryStage.show();
			}

			@Override
			public Integer apply(Database one, String two) {
				// TODO Auto-generated method stub
				return null;
			}
}
