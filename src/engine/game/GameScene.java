package engine.game;

import authoring.ScreenDisplay;
import data.map.DrawMap;
import data.map.GameMap;
import data.model.Model;
import data.player.Player;
import engine.Engine;
import javafx.scene.paint.Paint;


public class GameScene extends ScreenDisplay {
	
	private GameMap mainMap;
	private Player mainPlayer;
	private Model gameModel;

	public GameScene(int width, int height, Paint background, Engine engine) {
		super(width, height, background);
		mainMap = engine.getDatabase().getMap();
		mainPlayer = engine.getDatabase().getPlayer();
		gameModel = engine.getDatabase().getModel();
		DrawMap drawMap = new DrawMap(mainMap);
		this.rootAdd(drawMap.getPane());
	}
	

}
