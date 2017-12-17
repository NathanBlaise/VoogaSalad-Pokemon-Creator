package data.event;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import engine.UI.ScrollingText;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import data.map.GameMap;
import data.player.Player;
import engine.game.GameScene;
/**
 * 
 * @author cy122
 * check whether the key item exists
 *
 */
public class InstructionCheckKeyItem extends Instruction{

	private static final long serialVersionUID = -1593186227282410325L;
	private String keyItemName = "";

	/**
	 * for serialization
	 */
	@Deprecated
	public InstructionCheckKeyItem(){
		
	}
	
	@Override
	public void execute(int SCREEN_WIDTH, int SCREEN_HEIGHT, Player mainPlayer,
			GameMap mainMap, Event event, GameScene gameScene) {
		if(mainPlayer.getKeyItems().contains(keyItemName)){
			super.setGoNextInstruction(true);
			gameScene.changeBackScene();
			Stage primaryStage = new Stage();
			StackPane stack = new StackPane();
			ScrollingText text = new ScrollingText("Congratulations! you can pass here now,\n because you have "+keyItemName+".", getFont("src/resources/pkmnem.ttf", 31));
			text.setTextAlignment(TextAlignment.CENTER);
			stack.getChildren().add(text);
			primaryStage.setScene(new Scene(stack,500,200));
			primaryStage.show();
			// start animating the text
			text.animateText();
		}else{
			gameScene.changeBackScene();
			Stage primaryStage = new Stage();
			StackPane stack = new StackPane();
			ScrollingText text = new ScrollingText("Sorry! you can not pass here,\n because you don't have "+keyItemName+".", getFont("src/resources/pkmnem.ttf", 31));
			text.setTextAlignment(TextAlignment.CENTER);
			stack.getChildren().add(text);
			primaryStage.setScene(new Scene(stack,500,200));
			primaryStage.show();
			// start animating the text
			text.animateText();
		}
	}
	
	
	/**
	 * 
	 * @param fontPath - the path of font
	 * @param fontSize - the size of font
	 * @return the specific Pokemon font
	 */
	public static Font getFont(String fontPath, int fontSize) {
		Font f = new Font(30) ;
		try {
			f = Font.loadFont(new FileInputStream(new File(fontPath)), fontSize);
		} catch (FileNotFoundException e) {
			e.printStackTrace();//handled by exiting the program
			System.out.printf("oops! no such fonts!");
		}
		return f;
	}

	public String getKeyItemName() {
		return keyItemName;
	}

	public void setKeyItemName(String keyItemName) {
		this.keyItemName = keyItemName;
	}
	
	

}
