package engine.battle;

import data.model.Pokemon;
import data.model.moves.Move;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Class to create the buttons and set actions for when fight button is chosen
 * @author nathanlewis
 *
 */
public class BattleFightOptions {
	
	private static final int experienceLevel = 50;
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
	
	

	
	public BattleFightOptions(Pokemon ap, Pokemon ep,BattleScene bs) {
		activePokemon = ap;
		enemyPokemon = ep;
		battleScene = bs;
		
		setButtonText();
		
	}
	
	
	public void setUpScene() {
		ebfo=battleScene.getEnemyBattleScene();
		
		if (ebfo.getHBox()!=null && battleScene.getRootChildren().contains(ebfo.getHBox())) {
		    battleScene.rootRemove(ebfo.getHBox());
		}
		
		Button back = new Button("Go Back");
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
	
	private void setButtonText() {
		moveButton1 = new Button("-");
		moveButton2 = new Button("-");
		moveButton3 = new Button("-");
		moveButton4 = new Button("-");
		
		buttonArr = new Button[] {moveButton1,moveButton2,moveButton3,moveButton4};
		int i=0;
		for(Move move: activePokemon.getAvailableMoves()) {
			//Sets text and action for each button to be used for a move
			buttonArr[i].setText(move.getMoveName());
			buttonArr[i].setOnAction((event) -> {
				typeLabel = new Label("Type/"+ move.getElemental());
				ppLabel = new Label("PP: " + move.getPP()+"/"+move.getMaxPP());
				VBox moveInfo = new VBox(15);
				moveInfo.getChildren().addAll(typeLabel,ppLabel);
				Button confirm = new Button("Use "+move.getMoveName()+" ?");
				Button back = new Button("Go Back");
				hbox.getChildren().clear();
				hbox.getChildren().addAll(confirm,back,moveInfo);
				confirm.setOnAction((e) -> {
					move.move(activePokemon, enemyPokemon);
					//Load hit animation, then change scene to enemy's move
					changeScene();
					activePokemon.printCurrentInfo();
					enemyPokemon.printCurrentInfo();
					//Update Health Bars
					battleScene.updateHealthBars(activePokemon.getCurrentStat().getHP(), enemyPokemon.getCurrentStat().getHP());
					
					
					if (enemyPokemon.isDead()) {
						battleScene.showEnding("The enemy pokemon is dead!",true);
						activePokemon.absorbExperience(experienceLevel);
						//enemyPokemon.resetCurrentStat();
						
						
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
