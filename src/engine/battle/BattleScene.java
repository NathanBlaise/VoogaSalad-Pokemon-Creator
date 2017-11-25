package engine.battle;

import java.time.Duration;

import authoring.ScreenDisplay;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

/**
 * 
 * @author nathanlewis
 * Class to hold GUI components of the Battle, to be used in BattleController to be displayed
 * on screen
 */


public class BattleScene extends ScreenDisplay{
	
	
	Image grassBattle = new Image("file:images/grass_battle.png");
	Image grassBattleBackground = new Image("file:images/grass_battle_background.png");
	Image grassBattleGrass1 = new Image("file:images/grass_battle_grass_1.png");
	Image grassBattleGrass2 = new Image("file:images/grass_battle_grass_2.png");

	Image battleBox = new Image("file:images/battle_box.png");

	Image emeraldBattle1 = new Image("file:images/emerald_battle_1.png");
	Image emeraldBattle2 = new Image("file:images/emerald_battle_2.png");
	Image emeraldBattle3 = new Image("file:images/emerald_battle_3.png");
	Image emeraldBattle4 = new Image("file:images/emerald_battle_4.png");
	
	private GraphicsContext gc;
	private Canvas canvas;
	
	public BattleScene(int width, int height, Paint background) {
		super(width, height, background);
		canvas = new Canvas(width,height);
		gc = canvas.getGraphicsContext2D();
	}
	
	/*
	 * Used to display and animate all objects initially when battle loaded
	 */
	private void setUpScreen() {
		gc.drawImage(grassBattleBackground, 0, 0);
		gc.drawImage(grassBattleGrass1, 0, 400);
	}
	
}
