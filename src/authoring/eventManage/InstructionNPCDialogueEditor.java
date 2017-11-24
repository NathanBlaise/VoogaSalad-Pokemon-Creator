package authoring.eventManage;

import java.util.ArrayList;

import data.event.InstructionNPCDialogue;
import data.model.NPC;
import engine.UI.UIComponentFactory.ListViewFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;

public class InstructionNPCDialogueEditor implements InstructionEditor{
	private ListView<String> dialogueList;
	private Callback<InstructionNPCDialogue, Integer> saver;
	private NPC npc;
	
	public InstructionNPCDialogueEditor(NPC npc, Callback<InstructionNPCDialogue, Integer> saver){
		this.npc = npc;
		this.saver = saver;
	}
	
	private ListView<String> createDialogueList(){
		ObservableList<String> names = FXCollections.observableArrayList();
		dialogueList = new ListView<>(names);

		dialogueList = ListViewFactory.createListView(new ContextMenu(), ComboBoxListCell.forListView(names),
				new ChangeListener<String>(){
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						// TODO save a list of instructions, open a user interface
						saver.call(new InstructionNPCDialogue(new ArrayList<String>(dialogueList.getItems()), npc));
					}
				});
		dialogueList.setEditable(true);
		
		dialogueList.setCellFactory(TextFieldListCell.forListView());		

		dialogueList.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
			@Override
			public void handle(ListView.EditEvent<String> t) {
				dialogueList.getItems().set(t.getIndex(), t.getNewValue());
				System.out.println("setOnEditCommit");
			}			
		});
		
		return dialogueList;
	}

	@Override
	public Node showEditor() {
		return createDialogueList();
	}
}
