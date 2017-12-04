package engine.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.function.Function;

import data.event.InstructionNPCDialogue;
import authoring.ScreenDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 * Pass in an arrayList of String, scene to put the chatBox on the screen
 * Class would be used in game scene class
 * @author supertony
 *
 */
public class Dialogue {
	/*final variable*/
	private final static int XPOS = 0;
	private final static int YPOS = 336;
	private ImageView CHATBOX = new ImageView(new Image("file:images/battle_box.png"));
	private final int TEXT_XPOS = 30;
	private final int TEXT_YPOS = 380;
	private final int FONTSIZE = 36;
	
	
	/*instance variable*/
	private GameScene oriDisplay;
	private ArrayList<String> sentenceList;
	private InstructionNPCDialogue instruction;
	private Label textDisplay;
	
	private Function<String, Integer> handleF = new Function<String, Integer>(){
		@Override
		public Integer apply(String t) {
			DialogueExecution(); 
			return null;
		}
	};
	private Map<String, ArrayList<Function<String, Integer>>> inputHandler;

	//default Dialogue constructor
//	public Dialogue () {
//		
//	}
	
	
	public Dialogue (InstructionNPCDialogue instruction, GameScene gameScene, Map<String, ArrayList<Function<String, Integer>>> inputHandler) {
		sentenceList = instruction.getDialogues();
		this.instruction = instruction;
		oriDisplay = gameScene;
		this.inputHandler = inputHandler;
		inputHandler.get("F").add(handleF);

		//deal with specific pokemon font
		textDisplay = new Label("");
		Font f = getFont();
		
		if (sentenceList != null) {
		textDisplay.setText(sentenceList.get(0));
		}
		
		textDisplay.setFont(f); 
		
		//Put textDisplay and ChatBox on the screen
		putOnScreen();
	}
	
	
	
	/**
	 * Execute the dialogue
	 */
	public void DialogueExecution() {

			int currentIndex = sentenceList.indexOf(textDisplay.getText());
			if (currentIndex < sentenceList.size() -1 ) {
				textDisplay.setText(sentenceList.get(currentIndex + 1)); 
			}
			else {
				
				System.out.println(1);
				oriDisplay.rootRemove(CHATBOX); 
				oriDisplay.rootRemove(textDisplay);
				Iterator<Function<String, Integer>> iter = inputHandler.get("F").iterator();
				while(iter.hasNext()){
				    Function<String, Integer> func = iter.next();
				      if( func.equals(handleF) )
				      {
				        iter.remove();
				      }
				}
				instruction.setGoNextInstruction(true);
				oriDisplay.changeBackScene();
			}
	}
	
	
	
	/**
	 * Put the textBox and the chatBox on the screen
	 */
	private void putOnScreen() {
		//set up the position of chatBox
		if (!oriDisplay.getRootChildren().contains(CHATBOX)) {
			oriDisplay.rootAdd(CHATBOX, XPOS, YPOS);
		}
		//set up the position of textDisplay
		if (!oriDisplay.getRootChildren().contains(textDisplay)) {
			oriDisplay.rootAdd(textDisplay,TEXT_XPOS,TEXT_YPOS);
		}
	
	}
	


	
	
	
	
	/**
	 * @return the specific Pokemon font
	 */
	private Font getFont() {
		Font f = new Font(30) ;
		try {
			f = Font.loadFont(new FileInputStream(new File("./src/resources/pkmnem.ttf")), FONTSIZE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}
//	
//	/**
//	 * Getter of the boolean value beginBattle
//	 * Help notify the NPC battle begins
//	 * @return the boolean value
//	 */
//	
//	public boolean getBattleFlag() {
//		return beginBattle;
//	}
//	
//	/**
//	 * Setter of the boolean value beginBattle
//	 * Set the BeginBattle a new Boolean value
//	 */
//	public void setBattleFlag(boolean BattleFlag) {
//		beginBattle = BattleFlag;
//	}
	

}
