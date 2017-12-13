package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import authoring.Author;
import authoring.StageDelegate;
import authoring.eventManage.Function3;
import data.Database;
import data.saving.DatabaseLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import start.DatabasePathConfig;
/**
 * Directly goes to the authoring environment for testing
 * @author DanSun
 *
 */
public class PokemonAuthoring extends Application{

    public static void main(String[] args) {
	launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
	Stage editorStage = new Stage();
	String type = "Pokemon";
	String dataBaseName = "RunFromAuthoringTestOnly!";
	String path = DatabaseLoader.getDatabasePath(type, dataBaseName);
	Database tempDatabase = DatabaseLoader.loadDatabase(type, path);
	StageDelegate editor = new Author(tempDatabase, path, editorStage,type);
	editor.toFirstAuthorScene();
	editorStage.show();
    }

}
