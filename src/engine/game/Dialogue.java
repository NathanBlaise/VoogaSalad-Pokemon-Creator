package engine.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

import data.event.InstructionNPCDialogue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 * Pass in an arrayList of String, scene to put the chatBox on the screen
 * Class would be used in game scene class
 * @author supertony cy122
 *
 */
public class Dialogue {
	/*final variable*/

	private ImageView CHATBOX = new ImageView(new Image("file:images/battle_box.png"));
	private final static int FONTSIZE = 36;
	
	
	/*instance variable*/
	private GameScene oriDisplay;
	private ArrayList<String> sentenceList;
	private ScrollingText textDisplay = new ScrollingText();
	
	private Function<String, Integer> handleF = new Function<String, Integer>(){
		@Override
		public Integer apply(String t) {
			DialogueExecution(); 
			return null;
		}
	};
	private Map<String, ArrayList<Function<String, Integer>>> inputHandler;
	
	
	public Dialogue (InstructionNPCDialogue instruction, GameScene gameScene, Map<String, ArrayList<Function<String, Integer>>> inputHandler) {
		sentenceList = instruction.getDialogues();
		oriDisplay = gameScene;
		this.inputHandler = inputHandler;
		inputHandler.get("F").add(handleF);
		//Put textDisplay and ChatBox on the screen
		putOnScreen();

		//deal with specific pokemon font
		Font f = getFont();
		
		if (sentenceList != null) {
			textDisplay.setText(sentenceList.get(0));
			textDisplay.animateText();
		}
		
		textDisplay.setFont(f); 
	}
	
	
	
	/**
	 * Execute the dialogue
	 */
	public void DialogueExecution() {

			int currentIndex = sentenceList.indexOf(textDisplay.getText());
			if (currentIndex < sentenceList.size() -1 ) {
				textDisplay.setText(sentenceList.get(currentIndex + 1)); 
				textDisplay.animateText();
			}
			else {
				oriDisplay.rootRemove(CHATBOX); 
				oriDisplay.rootRemove(textDisplay);
				ArrayList<Function<String, Integer>> handlers = inputHandler.get("F");
				handlers.remove(handleF);
				oriDisplay.changeBackScene();
			}
	}
	
	 
	
	/**
	 * Put the textBox and the chatBox on the screen
	 */
	private void putOnScreen() {
		oriDisplay.getScene().getStylesheets().add("resources/sceneStyle.css");
		//set up the position of chatBox
		if (!oriDisplay.getRootChildren().contains(CHATBOX)) {
			CHATBOX.setPreserveRatio(true);
			CHATBOX.setFitWidth(oriDisplay.getStage().getWidth());
			oriDisplay.rootAdd(CHATBOX, 0, new Double(oriDisplay.getStage().getHeight()).intValue()-new Double(20+0.7*CHATBOX.getImage().getHeight()).intValue());
		}
		//set up the position of textDisplay
		if (!oriDisplay.getRootChildren().contains(textDisplay)) {
			oriDisplay.rootAdd(textDisplay, 40, 50 + new Double(oriDisplay.getStage().getHeight()).intValue()-new Double(20+0.7*CHATBOX.getImage().getHeight()).intValue());
		}
	
	}
	


	
	
	
	
	/**
	 * @return the specific Pokemon font
	 */
	public static Font getFont() {
		Font f = new Font(10) ;
		try {
			f = Font.loadFont(new FileInputStream(new File("./src/resources/pkmnem.ttf")), FONTSIZE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//handled by exiting the program
			System.exit(1);
		}
		return f;
	}
	

}
