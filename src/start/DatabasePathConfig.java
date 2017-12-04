package start;

import java.util.Map;

import data.Database;
import data.saving.CreateDefaultDatabase;
import data.saving.DatabaseLoader;
import authoring.eventManage.Function;
import authoring.eventManage.Function3;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Node;

/**
 * let the user to choose which database
 * @author cy122
 *
 */
public class DatabasePathConfig{
	public DatabasePathConfig(Stage stage, Function3<String, Database, String, Integer> reaction){
		BorderPane gameTypeChooser = chooseGameType((type, databasesPath)->{
			BorderPane databasesChooser = chooseDatabase(stage, type, databasesPath, (database, path)-> reaction.apply(type, database, path));
			customizeStage(stage, databasesChooser);
			return null;
		});
		customizeStage(stage, gameTypeChooser);
	}

	private void customizeStage(Stage stage, BorderPane gameTypeChooser) {
		stage.setScene(new Scene(gameTypeChooser));
		stage.show();
		stage.centerOnScreen();
	}
	
	/**
	 * choose the type of game first ---- like the Pacman, the Pokemon
	 * @param saver
	 * @return
	 */
	private BorderPane chooseGameType(Function<String, String, Integer> saver){
		Label title = new Label("Choose the Game Type Please :>)");
		Map<String, String> availableGameTypes = DatabaseLoader.getAvailableGameTypes();
		TilePane types = new TilePane();
		for(String name : availableGameTypes.keySet()){
			Button button = new Button();
			button.setText(name);
			button.setOnMouseClicked(e->{
				saver.apply(name, availableGameTypes.get(name));
			});
			types.getChildren().add(button);
		}
		BorderPane result = new BorderPane();
		customizeBorderPane(title, types, result);
		return result;
	}

	private BorderPane chooseDatabase(Stage stage, String type, String databasesPath, Function<Database, String, Integer> reaction) {
		BorderPane result = new BorderPane();
		new CreateDefaultDatabase();
		Label title = new Label("Choose the database please :)");
		title.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(title, 0.0);
		AnchorPane.setRightAnchor(title, 0.0);
		title.setAlignment(Pos.CENTER);
		TilePane databases = new TilePane();
		Map<String, String> availableDatabaseNames = DatabaseLoader.getAvailableDatabase(databasesPath);
		for(String name : availableDatabaseNames.keySet()){
			Button button = new Button();
			button.setText(name);
			button.setOnMouseClicked(e->{
				Database tempDatabase = DatabaseLoader.loadDatabase(type, availableDatabaseNames.get(name));
				reaction.apply(tempDatabase, availableDatabaseNames.get(name));
			});
			databases.getChildren().add(button);
		}
		Button button = new Button();
		button.setText("NEW Database");
		button.setOnMouseClicked(e->{
			BorderPane databaseNameCreator = createDatabaseName(new Callback<String, Integer>(){
				@Override
				public Integer call(String param) {
					String path = DatabaseLoader.getDatabasePath(type, param);
					Database tempDatabase = DatabaseLoader.loadDatabase(type, path);
					reaction.apply(tempDatabase, path);
					return null;
				}		 
			});
			this.customizeStage(stage, databaseNameCreator);
		});
		databases.getChildren().add(button);
		databases.setAlignment(Pos.CENTER);
		this.customizeBorderPane(title, databases, result);
		return result;
	}
	
	private BorderPane createDatabaseName(Callback<String, Integer> saver){
		BorderPane result = new BorderPane();
		TextField databaseName = new TextField("input your database name please");
		Button saveButton = new Button("save");
		saveButton.setOnMouseClicked(e->{
			saver.call(databaseName.getText());
		});
		this.customizeBorderPane(databaseName, saveButton, result);
		return result;
	}
	
	private BorderPane customizeBorderPane(Node top, Node bottom, BorderPane borderPane){
		borderPane.setPrefSize(400, 200);
		borderPane.setTop(top);
		borderPane.setCenter(bottom);
		return borderPane;
	}
}
