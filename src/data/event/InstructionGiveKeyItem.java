package data.event;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import data.map.GameMap;
import data.player.Player;
import engine.UI.ScrollingText;
import engine.game.GameScene;
/**
 * give a key item to the player
 * @author cy122
 *
 */
public class InstructionGiveKeyItem extends Instruction{

	private static final long serialVersionUID = 2776886941870819048L;
	private String keyItemName = "";
	
	/**
	 * for serialization
	 */
	@Deprecated
	public InstructionGiveKeyItem(){
		
	}
	
	
	@Override
	public void execute(int SCREEN_WIDTH, int SCREEN_HEIGHT, Player mainPlayer, GameMap mainMap, Event event, GameScene gameScene) {
		if(mainPlayer!=null){
			Stage primaryStage = new Stage();
			StackPane stack = new StackPane();
			ScrollingText text = new ScrollingText("Congratulations!\nyou get "+keyItemName+"!", InstructionCheckKeyItem.getFont("src/resources/pkmnem.ttf", 31));
			text.setTextAlignment(TextAlignment.CENTER);
			stack.getChildren().add(text);
			primaryStage.setScene(new Scene(stack,500,200));
			primaryStage.show();
			// start animating the text
			text.animateText();
			mainPlayer.getKeyItems().add(keyItemName);
		}
		super.setGoNextInstruction(true);
		gameScene.changeBackScene();
	}


	public String getKeyItemName() {
		return keyItemName;
	}


	public void setKeyItemName(String keyItemName) {
		this.keyItemName = keyItemName;
	}

}
