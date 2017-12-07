package authoring.eventManage;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import data.Database;
import data.event.Event;
import data.event.EventDIY;
import data.event.Instruction;

/**
 * the editor to edit the EventDIY
 * @author cy122
 *
 */
public class DIYEventEditor {
	
	private EventDIY eventDIY;
	private Database database;
	private Callback<Event, Integer> saver;
	
	public DIYEventEditor(EventDIY eventDIY, Database database, Callback<Event, Integer> saver){
		Stage stage = new Stage();
		this.eventDIY = eventDIY;
		this.database = database;
		this.saver = saver;
		editImage(stage, eventDIY);
		stage.show();
	}
	
	private void editImage(Stage stage, EventDIY eventDIY){
		EventImage imagePane = new EventImage(eventDIY.getImagePath());
		imagePane.addFileChooser(imagePane);
		Button next = new Button(">");
		next.setOnMouseClicked(e->{
			eventDIY.setImagePath(imagePane.getImagePath());
			stage.setScene(new Scene(showEvent(stage)));
		});
		imagePane.setBottom(next);		
		stage.setScene(new Scene(imagePane));
	}
	
	private BorderPane showEvent(Stage stage){
		BorderPane result = new BorderPane();
		Map<String, String> name2instruction = NPCEventEditor.createName2Instruction(eventDIY);
		Map<String, Function<Instruction, Callback<Instruction, Integer>, Integer>> reactions = createReaction(stage, result);
		result.setLeft(new EventInstructions(eventDIY, name2instruction, reactions, new InstructionListEditor(eventDIY, saver)).getList());
		return result;
	}
	
	private Map<String, Function<Instruction, Callback<Instruction, Integer>, Integer>> createReaction(Stage stage, BorderPane instructionPane){
		Map<String, Function<Instruction, Callback<Instruction, Integer>, Integer>> result = new HashMap<String, Function<Instruction, Callback<Instruction, Integer>, Integer>>();
		for(String instructionName: eventDIY.getAvailableInstructions()){
			result.put(instructionName, (instruction, e)->{
				try {
					Class<?> instructionClass = Class.forName("data.event."+instructionName);
					if(!(instructionClass.isInstance(instruction))){
						Constructor<?> constructor = instructionClass.getConstructor(new Class[]{});
						instruction = (Instruction) constructor.newInstance();
					}
					Class<?> instructionEditorClass = Class.forName("authoring.eventManage."+instructionName+"Editor");
					Constructor<?> editorConstructor = instructionEditorClass.getConstructor(new Class[]{instructionClass, Database.class, Callback.class});
					InstructionEditor instructionEditor = (InstructionEditor) editorConstructor.newInstance(instruction, database, e);
					instructionPane.setCenter(instructionEditor.showEditor());
					instructionPane.autosize();
					stage.sizeToScene();
					return null;
				} catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e1) {
					e1.printStackTrace();
					return null;
				}
			});
		}
		return result;
	}
}
