package data.event;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import data.map.GameMap;
import data.player.Player;
import engine.game.GameScene;
import engine.shop.ShopScene;

public class InstructionGoShop extends Instruction{
	
	private static final long serialVersionUID = 3425297418577947868L; //for serialization
	
	/**
	 * for serialization
	 */
	public InstructionGoShop(){
	}

	@Override
	public void execute(int SCREEN_WIDTH, int SCREEN_HEIGHT, Player mainPlayer, GameMap mainMap, Event event, GameScene gameScene) {
		ShopScene shopScene = new ShopScene(720,480, gameScene, gameScene.getDatabase().getModel().getItems(), Color.WHITE,mainPlayer);
		Stage stage = gameScene.getStage();
		stage.setScene(shopScene.getScene());
		stage.setHeight(480+20);
		stage.setWidth(720);
		stage.show();
	}
	
	

}

