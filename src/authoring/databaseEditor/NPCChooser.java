package authoring.databaseEditor;

import java.io.File;

import data.model.NPC;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
/**
 * choose the NPC
 * @author cy122
 *
 */
public class NPCChooser {
	private NPC localNPC;
	private Callback<NPC, Integer> saver;
	BorderPane root = new BorderPane();
	
	public NPCChooser(NPC selectedNPC, Callback<NPC, Integer> saver){
		localNPC = new NPC(selectedNPC);
		this.saver = saver;
		showNPC();
	}
	
	public Button saveButton(){
		Button result = new Button("save");
		result.setOnMouseClicked(e->{showNPC();});
		result.setAlignment(Pos.BOTTOM_CENTER);
		return result;
	}
	
	public BorderPane showNPC(){
		root.getChildren().clear();
		//add components
		root.setBottom(saveButton());
        root.setCenter(showImage(localNPC.getImagePath()));
        root.setTop(showNickName(localNPC.getName()));
        root.setPrefSize(160, 230);
        root.getStylesheets().add("resources/sceneStyle.css");
        saver.call(localNPC);
        return root;
	}
	
	private ImageView showImage(String imgPath){
		ImageView imageView = new ImageView();
		File file = new File(imgPath);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        return imageView;
	}
	
	private VBox showNickName(String initialName){
		VBox result = new VBox();
		Label nickNameLabel = new Label("Nick Name:");
		result.getChildren().add(nickNameLabel);
		TextField nickNameField = new TextField(initialName);
		nickNameField.setOnKeyPressed(new EventHandler<KeyEvent>()
			    {
			        @Override
			        public void handle(KeyEvent ke)
			        {
			            if (ke.getCode().equals(KeyCode.ENTER))
			            {
			            	localNPC.setName(nickNameField.getText());
			    		    showNPC();
			            }
			        }
			   });
		result.getChildren().add(nickNameField);
		return result;
	}
}
