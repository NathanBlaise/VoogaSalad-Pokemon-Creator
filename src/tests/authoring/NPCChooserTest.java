package tests.authoring;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import authoring.databaseEditor.NPCChooser;
import data.model.NPC;

public class NPCChooserTest extends Application implements Callback<NPC, Integer>{
			public static void main(String[] args) {
				launch(args);

			}

			@Override
			public void start(Stage primaryStage){
				primaryStage.setScene(new Scene(new NPCChooser(new NPC("images/CaptainMap.png","Jason") ,this).showNPC()));
				primaryStage.show();
			}

			@Override
			public Integer call(NPC param) {
				// TODO Auto-generated method stub
				return null;
			}
}
