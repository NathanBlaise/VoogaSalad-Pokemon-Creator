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

	
	public EnemyBattleFightOptions(
			Pokemon ap, Pokemon ep, BattleScene bs) {
		super(ap, ep,bs);
		mainScene = bs;
		// TODO Auto-generated constructor stub
		
		
	}
	
	
	
	@Override
	public void changeScene() {
		mainScene.buttonInitialSetUp();
		mainScene.rootRemove(super.hbox);
		mainScene.rootRemove(this.getText());
		
		
		//bfo.setUpScene();
	}
	
  @Override
   public void setUpScene() {
	  //perform move 
	  
	  
	  bfo=super.battleScene.getMyBattleScene();
	  
	  if (battleScene.getRootChildren().contains(bfo.getText())) {
		  battleScene.rootRemove(bfo.getText());
		  battleScene.getRootChildren().remove(bfo.getText());
		
		    
		   
		}
		
		if (battleScene.getRootChildren().contains(bfo.getHBox())) {
		    battleScene.rootRemove(bfo.getHBox());
		  
		}
		
		
		
		
		super.hbox=ButtonLayout(super.buttonArr);
		
		battleScene.rootAdd(hbox);

		battleScene.rootAdd(this.getText(),400,420);
		//battleScene.gc.drawImage(battleScene.battleBox,0,0);

		cancel.setOnAction((event) -> {
			//move.move(activePokemon, enemyPokemon);
			//Load hit animation, then change scene to enemy's move
			performMove();
			int newActiveHP=activePokemon.getCurrentStat().getHP();
			int newEnemyHP=enemyPokemon.getCurrentStat().getHP();
			//reverse because now it is reversed
			battleScene.getActivePokemonHP().setText("Hp: "+newEnemyHP);
			battleScene.getEnemyPokemonHP().setText("Hp: "+newActiveHP);
			changeScene();
			
			
			
			
		});
		
		
		//battleScene.setTextEffects(actionMessage, 20, 20);
		
		

		
	}
  
  private void styleText(Text t) {
	  t.setTranslateX(20);
	  t.setTranslateY(40);
	  InnerShadow is = new InnerShadow();
		is.setOffsetX(4.0f);
		is.setOffsetY(4.0f);
		t.setEffect(is);
		t.setFill(Color.BLACK);
		
		t.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
	  
  }
  
  private void performMove() {
	  
	
	  
	  int numberOfMoves=super.activePokemon.getAvailableMoves().size();
	  Random rand=new Random();
	  int thisMove=rand.nextInt(numberOfMoves);
	  
	  Move move=super.activePokemon.getAvailableMoves().get(thisMove);
	  move.move(super.activePokemon, super.enemyPokemon);
	  System.out.println(move.getMoveName());
	  String currentMessage="Oh no! It performs "+move.getMoveName()+" to you!";
	  super.battleScene.getActionMessage().setText(currentMessage);
		 
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
  
  @Override
  public Text getText() {
		super.message.setText("Watch out!");
		super.message.setFont(new Font(30));
		return super.message;
		
	}


}
