package tests.authoring;

import authoring.eventManage.DIYEventEditor;
import data.Database;
import data.event.Event;
import data.event.EventDIY;
import data.event.Instruction;
import data.saving.DatabaseLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Callback;

public class EventDIYTest extends Application implements Callback<Event, Integer>{
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage){
		String path = DatabaseLoader.getDatabasePath("Pokemon", "fancyMap");
		Database tempDatabase = DatabaseLoader.loadDatabase("Pokemon", path);
		new DIYEventEditor(new EventDIY("images/reg_tile_scaled.png"), tempDatabase, this);
	}

	@Override
	public Integer call(Event param) {
		System.out.printf("%s ", "I'm called!");
		System.out.printf("%s ", param.getImagePath());
		System.out.printf("%s ", ((EventDIY)param).getAvailableInstructions());
		for(Instruction temp: param.getInstructions()){
			System.out.printf("%s ", temp.getClass().getName());
		}
		System.out.printf("\n");
		return null;
	}
}
