package engine.battle;

import engine.game.GameScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BattleEnding {
	private GameScene gs;
	
	public BattleEnding(GameScene gs) {
		
	}
	
	
	
	//show the game end message
			protected void showEnding(String message, boolean whetherEnd) {
				Text end=new Text(message);
				final Stage dialog = new Stage();
				dialog.initModality(Modality.APPLICATION_MODAL);
				Stage myStage=gs.getStage();
				dialog.initOwner(myStage);
				VBox dialogVbox = new VBox(20);

				Button btn = new Button();
				btn.setText("Got it");
				dialogVbox.getChildren().add(end);
				dialogVbox.getChildren().add(btn);
				
				Scene dialogScene = new Scene(dialogVbox, 300, 200);
				dialog.setScene(dialogScene);
				dialog.show();
				btn.setOnAction((event) ->{
					dialog.close();
					if (whetherEnd) {
					gs.changeBackScene();
					}
				});

			}
		

}
