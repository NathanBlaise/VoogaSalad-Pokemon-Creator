package engine.battle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;

import data.model.Pokemon;
import data.model.moves.Move;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Class to create the buttons and set actions for when fight button is chosen
 * @author nathanlewis
 *
 */
public class BattleFightOptions {
	
    private static final String DEFAULT_RESOURCE_PACKAGE = "util/English_Text";
	private ResourceBundle myResources;
	
	protected Pokemon activePokemon;
	protected Pokemon enemyPokemon;
	protected BattleScene battleScene;
	private EnemyBattleFightOptions ebfo;
	
	private Button moveButton1;
	private Button moveButton2;
	private Button moveButton3;
	private Button moveButton4;
	protected Button[] buttonArr;
	
	private Label ppLabel;
	private Label typeLabel;
	protected HBox hbox=new HBox(5);
	protected BattleEnding be;
	
	

	
	public BattleFightOptions(Pokemon ap, Pokemon ep,BattleScene bs, BattleEnding be) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		activePokemon = ap;
		enemyPokemon = ep;
		battleScene = bs;
		this.be = be;
		
		setButtonText();
		
	}
	
	
	public void setUpScene() {
		ebfo=battleScene.getEnemyBattleScene();
		
		if (ebfo.getHBox()!=null && battleScene.getRootChildren().contains(ebfo.getHBox())) {
		    battleScene.rootRemove(ebfo.getHBox());
		}
		
		Button back = new Button(myResources.getString("backButton"));
		setButtonStyle(back);
		
		hbox=battleScene.fourButtonLayout(buttonArr);
		hbox.getChildren().add(back);
		battleScene.rootAdd(hbox);
		
		back.setOnAction((e) -> {
			battleScene.rootRemove(hbox);
			battleScene.resetButtons();
		});
	
	}
	
	protected HBox getHBox() {
		return hbox;
		
	}
	
	protected void setButtonStyle(Button b) {
      
		b.setStyle(myResources.getString("buttonStyle"));
		Font font=getFont();
		b.setFont(font);
	}
	
	private Font getFont() {
		Font f = new Font(20) ;
		try {
			f = Font.loadFont(new FileInputStream(new File(myResources.getString("fontPath"))), 20);
		} catch (FileNotFoundException e) {
			e.printStackTrace();//handled by exiting the program
			System.exit(1);
			
		}
		return f;
	}
	
	private void setButtonText() {
		moveButton1 = new Button(myResources.getString("defaultButton"));
		moveButton2 = new Button(myResources.getString("defaultButton"));
		moveButton3 = new Button(myResources.getString("defaultButton"));
		moveButton4 = new Button(myResources.getString("defaultButton"));
	
		setButtonStyle(moveButton1);
		setButtonStyle(moveButton2);
		setButtonStyle(moveButton3);
		setButtonStyle(moveButton4);
		buttonArr = new Button[] {moveButton1,moveButton2,moveButton3,moveButton4};
		
		
		
		int i=0;
		
		for(Move move: activePokemon.getEquippedMoves()) {
			//Sets text and action for each button to be used for a move
		    	if (move==null) continue;
		    	String moveName = null;
		    	try {
		    	moveName = move.getMoveName();
		    	} catch(NullPointerException e) {
		    	    e.printStackTrace(); //handled by exiting
		    	    System.exit(1);
		    	}
			buttonArr[i].setText(moveName);
			buttonArr[i].setOnAction((event) -> {
				typeLabel = new Label("Type/"+ move.getElemental());
				ppLabel = new Label("PP: " + move.getPP()+"/"+move.getMaxPP());
				VBox moveInfo = new VBox(15);
				moveInfo.getChildren().addAll(typeLabel,ppLabel);
				Button confirm = new Button("Use "+move.getMoveName()+" ?");
				Button back = new Button(myResources.getString("backButton"));
				
				
				setButtonStyle(confirm);
				setButtonStyle(back);
				
				hbox.getChildren().clear();
				hbox.getChildren().addAll(confirm,back,moveInfo);
				confirm.setOnAction((e) -> {
					move.move(activePokemon, enemyPokemon);
//					System.out.println("Move " + move.getMoveName() + 
//						" has PP "+move.getPP());
					//Load hit animation, then change scene to enemy's move
					changeScene();
					activePokemon.printCurrentInfo();
					enemyPokemon.printCurrentInfo();
					//Update Health Bars
					battleScene.updateHealthBars(activePokemon.getCurrentStat().getHP(), enemyPokemon.getCurrentStat().getHP());
					
					
					if (enemyPokemon.isDead()) {
						be.showEnding(myResources.getString("win"), true, true);
					}
					
				
					
				});
				back.setOnAction((e) -> {
					hbox.getChildren().clear();
					setButtonText();
					setUpScene();
				});
			});
			
			i++;
		}
		
		
	}
	
	

	public void changeScene() {
		
		ebfo.setUpScene();
		
	}
	
	protected Button[] getButtons() {
		return buttonArr;
	}
	
	
}
