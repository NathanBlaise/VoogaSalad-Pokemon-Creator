package engine.battle;

import data.model.Pokemon;
import data.model.moves.Move;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Class to create the buttons and set actions for when fight button is chosen
 * @author nathanlewis
 *
 */
public class BattleFightOptions {
	
	private Pokemon activePokemon;
	private Pokemon enemyPokemon;
	
	private Button moveButton1;
	private Button moveButton2;
	private Button moveButton3;
	private Button moveButton4;
	private Button[] buttonArr;

	
	public BattleFightOptions(Pokemon ap, Pokemon ep) {
		activePokemon = ap;
		enemyPokemon = ep;
		setButtonText();
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
			});
		}
	}
	
	protected Button[] getButtons() {
		return buttonArr;
	}
}
