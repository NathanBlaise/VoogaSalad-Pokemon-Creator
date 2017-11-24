package tests.authoring;

import authoring.eventManage.InstructionNPCDialogueEditor;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import data.event.InstructionNPCDialogue;
import data.model.NPC;

public class DialogueInstructionTest extends Application implements Callback<InstructionNPCDialogue, Integer>{

	@Override
	public Integer call(InstructionNPCDialogue param) {
		System.out.printf("%s\n", param.getDialogues().toString());
		return null;
	}

	@Override
	public void start(Stage stage) throws Exception {
		NPC npc = new NPC("images/CaptainMap.png", "Walter White");
		InstructionNPCDialogueEditor instructionNPCDialogueEditor = new InstructionNPCDialogueEditor(npc, this);
		Group root = new Group();
		root.getChildren().add(instructionNPCDialogueEditor.showEditor());
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
