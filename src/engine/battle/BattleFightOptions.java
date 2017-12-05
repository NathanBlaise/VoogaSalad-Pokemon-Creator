package engine.battle;

import data.model.Pokemon;
import data.model.moves.Move;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Class to create the buttons and set actions for when fight button is chosen
 * @author nathanlewis
 *
 */
public class BattleFightOptions {
	
	private Pokemon activePokemon;
	private Pokemon enemyPokemon;
	protected BattleScene battleScene;
	private EnemyBattleFightOptions ebfo;
	
	private Button moveButton1;
	private Button moveButton2;
	private Button moveButton3;
	private Button moveButton4;
	protected Button[] buttonArr;
	
	
	protected Text message=new Text("");
	protected HBox hbox=new HBox(5);
	
	

	
	public BattleFightOptions(Pokemon ap, Pokemon ep,BattleScene bs) {
		activePokemon = ap;
		enemyPokemon = ep;
		battleScene = bs;
		
		setButtonText();
		
	}
	
	public Text getText() {
		message.setText("Select my attack");;
		message.setFont(new Font(30));
		return message;
		
	}
	
	public void setUpScene() {
		ebfo=battleScene.getEnemyBattleScene();
		if (!ebfo.getText().equals(null) && battleScene.getRootChildren().contains(ebfo.getText())) {
		    battleScene.rootRemove(ebfo.getText());
		}
		
		if (ebfo.getHBox()!=null && battleScene.getRootChildren().contains(ebfo.getHBox())) {
		    battleScene.rootRemove(ebfo.getHBox());
		}
		
		
		hbox=battleScene.fourButtonLayout(buttonArr);
		
		battleScene.rootAdd(hbox);
		battleScene.rootAdd(this.getText(),400,400);
		

		
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
			buttonArr[i].setText(move.getMoveName());
			buttonArr[i].setOnAction((event) -> {
				move.move(activePokemon, enemyPokemon);
				//Load hit animation, then change scene to enemy's move
				changeScene();
				activePokemon.printCurrentInfo();
				enemyPokemon.printCurrentInfo();
				int newActiveHP=activePokemon.getCurrentStat().getHP();
				int newEnemyHP=enemyPokemon.getCurrentStat().getHP();
				battleScene.getActivePokemonHP().setText("Current Hp: "+newActiveHP);
				battleScene.getEnemyPokemonHP().setText("Current Hp: "+newEnemyHP);
				
				if(activePokemon.isDead() || enemyPokemon.isDead()) {
				    battleScene.getGameScene().changeBackScene();
				}

				
				
				
			});
		}
	}

	public void changeScene() {
		
		ebfo.setUpScene();
	}
	
	protected Button[] getButtons() {
		return buttonArr;
	}
	
	
}
