package engine.battle;

import java.util.Random;
import java.util.ResourceBundle;

import data.model.Pokemon;
import data.model.moves.Move;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class EnemyBattleFightOptions extends BattleFightOptions {
	private BattleScene mainScene;
	private BattleFightOptions bfo;
	private ResourceBundle myResources;
	private static final String DEFAULT_RESOURCE_PACKAGE = "util/English_Text";
	


	private Button cancel;

	
	public EnemyBattleFightOptions(Pokemon ap, Pokemon ep, BattleScene bs, BattleEnding be) {
		super(ap, ep,bs,be);
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		
		mainScene = bs;
		cancel=new Button(myResources.getString("enemyButton"));
		super.setButtonStyle(cancel);
		
		
	}
	
	
	private void handleKeyInput(KeyCode code) {

		// start the game
		if (code == KeyCode.ENTER) {
			
			    	mainScene.clearMessage();
                 mainScene.resetButtons();
                 mainScene.getCanvas().setFocusTraversable(false);
		}
	}
	
	
	
	@Override
	public void changeScene() {
		mainScene.getCanvas().setFocusTraversable(true);
		mainScene.getCanvas().setOnKeyPressed(e -> handleKeyInput(e.getCode()));
	
		
		 
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
	  battleScene.updateHealthBars(enemyPokemon.getCurrentStat().getHP(), activePokemon.getCurrentStat().getHP());
	  super.battleScene.setMessage("Oh no! "+ activePokemon.getNickName() + " performed "+move.getMoveName()+"!");
	  if(enemyPokemon.isDead()) {
			be.showEnding(myResources.getString("lose"),true,false);
//			battleScene.getPlayer().deletePokemon(enemyPokemon);
		}  
	  
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
