package data.event;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import data.map.GameMap;
import data.player.Player;
import engine.game.GameScene;

public class InstructionVictory extends Instruction{

    private static final long serialVersionUID = 3425297418577947868L; //for serialization

    /**
     * for serialization
     */
    public InstructionVictory(){
    }

    @Override
    public void execute(int SCREEN_WIDTH, int SCREEN_HEIGHT, Player mainPlayer, GameMap mainMap, Event event, GameScene gameScene) {
	Text end=new Text("Congratulations! You Win!");
	final Stage dialog = new Stage();
	dialog.initModality(Modality.APPLICATION_MODAL);
	Stage myStage=gameScene.getStage();
	dialog.initOwner(myStage);
	VBox dialogVbox = new VBox(20);
	Button btn = new Button();
	btn.setText("Exit the game");
	dialogVbox.getChildren().add(end);
	dialogVbox.getChildren().add(btn);

	Scene dialogScene = new Scene(dialogVbox, 300, 200);	
	dialog.setScene(dialogScene);
	dialog.setOnCloseRequest(e->System.exit(0));
	btn.setOnAction(e->System.exit(0));
	dialog.show();
	
    }

}

