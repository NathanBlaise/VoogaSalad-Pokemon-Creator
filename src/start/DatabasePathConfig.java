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
 * @author Dan Sun for commenting and refactoring
 *
 */
public class DatabasePathConfig{
	/**
	 * Constructor for the class, displays a panel that allows 
	 * user to choose the game type and the game name
	 * @param stage The stage to show the database chooser
	 * @param reaction The reaction of pressing the corresponding database 
	 */
	public DatabasePathConfig(Stage stage, Function3<String, Database, String, Integer> reaction){
		BorderPane gameTypeChooser = chooseGameType((type, databasesPath)->{
		    	//shows the chooser for databases of a specific game type
			BorderPane databasesChooser = chooseDatabase(stage, type, databasesPath, (database, path)-> reaction.apply(type, database, path));
			customizeAndShowStage(stage, databasesChooser);
			return null;
		});
		customizeAndShowStage(stage, gameTypeChooser);
	}

	private static void customizeAndShowStage(Stage stage, BorderPane gameTypeChooser) {
		stage.setScene(new Scene(gameTypeChooser));
		stage.show();
		stage.centerOnScreen();
	}
	
	/**
	 * choose the type of game first ---- like the Pacman, the Pokemon
	 * @param gameTypeChosenAction The action to perform when a game type is selected
	 * @return The chooser for game type
	 */
	private BorderPane chooseGameType(Function<String, String, Integer> gameTypeChosenAction){
		Label title = new Label("Choose the Game Type Please :)");
		Map<String, String> availableGameTypes = DatabaseLoader.getAvailableGameTypes();
		TilePane types = new TilePane();
		for(String name : availableGameTypes.keySet()){
			Button button = new Button();
			button.setText(name);
			button.setOnMouseClicked(e->{
			    	String gameTypeDatabaseFolder = availableGameTypes.get(name);
				gameTypeChosenAction.apply(name, gameTypeDatabaseFolder);
			});
			types.getChildren().add(button);
		}
		BorderPane result = new BorderPane();
		customizeBorderPane(title, types, result);
		return result;
	}
	/**
	 * Creates a selection pane for game within a particular game type
	 * @param stage The window to display the selection panel
	 * @param type The name of the type of the game
	 * @param databasesPath The path of the folder that contains available maps for this game type
	 * @param reaction The reaction of pressing the corresponding database 
	 * @return The BorderPane created to show the selection 
	 */
	private static BorderPane chooseDatabase(Stage stage, String type, String databasesPath, Function<Database, String, Integer> reaction) {
		BorderPane result = new BorderPane();
		new CreateDefaultDatabase(type);
		Label title = new Label("Choose the database please :)");
		configureTitle(title);
		TilePane databases = new TilePane();
		Map<String, String> availableDatabaseNames = DatabaseLoader.getAvailableDatabase(databasesPath);
		for(String name : availableDatabaseNames.keySet()){
			Button button = new Button();
			button.setText(name);
			button.setOnMouseClicked(e->{
			    	String xmlFilePath = availableDatabaseNames.get(name);
				Database tempDatabase = DatabaseLoader.loadDatabase(type, xmlFilePath);
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
			customizeAndShowStage(stage, databaseNameCreator);
		});
		databases.getChildren().add(button);
		databases.setAlignment(Pos.CENTER);
		customizeBorderPane(title, databases, result);
		return result;
	}

	private static void configureTitle(Label title) {
	    title.setMaxWidth(Double.MAX_VALUE);
	    AnchorPane.setLeftAnchor(title, 0.0);
	    AnchorPane.setRightAnchor(title, 0.0);
	    title.setAlignment(Pos.CENTER);
	}
	
	private static BorderPane createDatabaseName(Callback<String, Integer> reaction){
		BorderPane result = new BorderPane();
		TextField databaseName = new TextField("Input your database name please");
		Button saveButton = new Button("Save");
		saveButton.setOnMouseClicked(e->{
			reaction.call(databaseName.getText());
		});
		customizeBorderPane(databaseName, saveButton, result);
		return result;
	}
	
	private static BorderPane customizeBorderPane(Node top, Node bottom, BorderPane borderPane){
		borderPane.setPrefSize(400, 200);
		borderPane.setTop(top);
		borderPane.setCenter(bottom);
		return borderPane;
	}
}
