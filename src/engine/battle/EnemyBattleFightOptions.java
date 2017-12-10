package engine.battle;

import java.util.Random;

import data.model.Pokemon;
import data.model.moves.Move;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class EnemyBattleFightOptions extends BattleFightOptions {
	private BattleScene mainScene;
	private BattleFightOptions bfo;
	


	private Button cancel=new Button("Enemy Pokemon's move");

	
	public EnemyBattleFightOptions(Pokemon ap, Pokemon ep, BattleScene bs) {
		super(ap, ep,bs);
		mainScene = bs;
	}
	
	
	
	@Override
	public void changeScene() {
		mainScene.resetButtons();
		mainScene.rootRemove(super.hbox);
		
		
		//bfo.setUpScene();
	}
	
  @Override
   public void setUpScene() {
	  //perform move 
	  bfo=super.battleScene.getMyBattleScene();	
	  if (battleScene.getRootChildren().contains(bfo.getHBox())) {
		  battleScene.rootRemove(bfo.getHBox());
	  }
	  super.hbox=ButtonLayout(super.buttonArr);
	  battleScene.rootAdd(hbox);
	  cancel.setOnAction((event) -> {
		  performMove();
		  changeScene();
	  });
	}
  
  
  private void performMove() {
	    
	  int numberOfMoves=super.activePokemon.getAvailableMoves().size();
	  Random rand=new Random();
	  int thisMove=rand.nextInt(numberOfMoves);
	  Move move=super.activePokemon.getAvailableMoves().get(thisMove);
	  move.move(super.activePokemon, super.enemyPokemon);
	  super.battleScene.setMessage("Oh no! "+ enemyPokemon.getNickName() + " performed "+move.getMoveName()+"!");
	  
  }
  
  
  private HBox ButtonLayout(Button[] buttons) {
		VBox vbox1 = new VBox(15);
		vbox1.getChildren().addAll(cancel);
		hbox = new HBox(15);
		hbox.getChildren().addAll(vbox1);
		hbox.setLayoutX(30);
		hbox.setLayoutY(400);
		return hbox;
	}
  


}
