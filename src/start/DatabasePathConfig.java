package start;

import java.util.Map;

import data.Database;
import data.saving.DatabaseLoader;
import authoring.eventManage.Function;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;import javafx.util.Callback;

/**
 * let the user to choose which database
 * @author cy122
 *
 */
public class DatabasePathConfig extends BorderPane{
	public DatabasePathConfig(Stage stage, Function<Database, String, Integer> reaction){
		Label title = new Label("Choose the database please :)");
		title.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(title, 0.0);
		AnchorPane.setRightAnchor(title, 0.0);
		title.setAlignment(Pos.CENTER);
		TilePane databases = new TilePane();
		Map<String, String> availableDatabaseNames = DatabaseLoader.getAvailableDatabase();
		for(String name : availableDatabaseNames.keySet()){
			Button button = new Button();
			button.setText(name);
			button.setOnMouseClicked(e->{
				Database tempDatabase = DatabaseLoader.loadDatabase(availableDatabaseNames.get(name));
				reaction.apply(tempDatabase, availableDatabaseNames.get(name));
			});
			databases.getChildren().add(button);
		}
		Button button = new Button();
		button.setText(/*LanguageReader.convertLanguage("English", */"NEW Database"/*)*/);
		button.setOnMouseClicked(e->{
			 stage.setScene(new Scene(createDatabaseName(new Callback<String, Integer>(){
					@Override
					public Integer call(String param) {
						String path = DatabaseLoader.getDatabasePath(param);
						Database tempDatabase = DatabaseLoader.loadDatabase(path);
						reaction.apply(tempDatabase, path);
						return null;
					}		 
			 })));
			 stage.centerOnScreen();
		});
		databases.getChildren().add(button);
		databases.setAlignment(Pos.CENTER);
		setTop(title);
		setCenter(databases);
		setPrefSize(400,200);
	}
	
	private BorderPane createDatabaseName(Callback<String, Integer> saver){
		BorderPane result = new BorderPane();
		TextField databaseName = new TextField("input your database name please");
		Button saveButton = new Button("save");
		saveButton.setOnMouseClicked(e->{
			saver.call(databaseName.getText());
		});
		result.setTop(databaseName);
		result.setBottom(saveButton);
		result.setPrefSize(400, 200);
		return result;
	}	
}
