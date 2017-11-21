package tests.authoring;

import java.util.HashMap;
import java.util.Map;

import authoring.databaseEditor.PokemonTab;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PokemonTabTest extends Application{
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage){
		Map<String, Integer> tempMap = new HashMap<String, Integer>();
		tempMap.put("speed", 1);
		tempMap.put("maxHP", 1);
		tempMap.put("specialAttack", 1);
		tempMap.put("specialDefense", 1);
		tempMap.put("normalAttack", 1);
		tempMap.put("normalDefense", 1);
		Scene scene = new Scene(new Group());
        ((Group) scene.getRoot()).getChildren().addAll(new PokemonTab(null, null).createStatList(tempMap));
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add("resources/sceneStyle.css");
	}
}
