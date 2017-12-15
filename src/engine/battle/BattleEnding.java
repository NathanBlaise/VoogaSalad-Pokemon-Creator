package engine.battle;

import java.util.ResourceBundle;

import engine.game.GameScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class BattleEnding {
	private GameScene gs;
	private Callback<Integer, Integer> winAction;
	private Callback<Integer, Integer> loseAction;
	
	private static final String DEFAULT_RESOURCE_PACKAGE = "util/English_Text";
	private ResourceBundle myResources;
	
	
	public BattleEnding(GameScene gs, 	Callback<Integer, Integer> winAction, Callback<Integer, Integer> loseAction) {
		this.gs=gs;
		this.winAction = winAction;
		this.loseAction = loseAction;
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
	}
	
	
	/**
	 * 
	 * @param message - the message shown in dialog
	 * @param whetherEnd - whether it is end of the battle
	 * @param whetherWin - whether the pokemon wins
	 */
	protected void showEnding(String message, boolean whetherEnd, boolean whetherWin) {
		Text end=new Text(message);
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		Stage myStage= gs.getStage();
		dialog.initOwner(myStage);
		VBox dialogVbox = new VBox(20);

		Button btn = new Button();
		btn.setText(myResources.getString("gotButton"));
		dialogVbox.getChildren().add(end);
		dialogVbox.getChildren().add(btn);
		
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		dialog.setScene(dialogScene);
		dialog.show();
		btn.setOnAction((event) ->{
			dialog.close();
			if (whetherEnd) {
				if(whetherWin){
					winAction.call(0);
				}else{
					loseAction.call(0);
				}
			}
		});
	}
		

}
