package engine.game;

import java.util.ArrayList;

import authoring.ScreenDisplay;
import data.event.Instruction;
import data.event.InstructionNPCFight;
import data.map.DrawMap;
import data.map.GameMap;
import data.model.Model;
import data.model.NPC;
import data.model.Pokemon;
import data.player.Player;
import engine.Engine;
import engine.battle.BattleScene;
import javafx.scene.paint.Color;
import engine.movement.Input;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * The main scene for the game engine (where the player walks around)
 * This class EXTENDS ScreenDisplay
 * @author nathanlewis
 *
 */

public class GameScene extends ScreenDisplay {
	
	private GameMap mainMap;
	private Player mainPlayer;
	private Model gameModel;
	private Input input;


	public GameScene(int width, int height, Color background, Engine engine, Stage gameStage) {

		super(width, height, background);
		// Grabs map, player and all data in model from chosen database, saves in variables
		mainMap = engine.getDatabase().getMap();
		mainPlayer = engine.getDatabase().getPlayer();
		gameModel = engine.getDatabase().getModel();
		DrawMap drawMap = new DrawMap(mainMap);

		
		NPC npc=new NPC("file:images/emerald_battle_1.png","testC");
		Pokemon[] list=new Pokemon[2];
		Player testPlayer=engine.getDatabase().getPlayer();
		InstructionNPCFight testNPC=new InstructionNPCFight(npc,list);
		ArrayList<Instruction> testIns=engine.getDatabase().getMap().getCells()[0][0].getEvent().getInstructions();
		System.out.println(testIns.get(0).toString());
		//BattleScene test=new BattleScene(1000,1000,Color.AQUA,new Player(),new InstructionNPCFight(),null);
		BattleScene test=new BattleScene(width,height,Color.AQUA,testPlayer,testNPC,null);
		
		//gameStage.setScene(test.getScene());
		//gameStage.show();
		//gameStage.centerOnScreen();
		super.myScene=test.getScene();
		
		//this.rootAdd(drawMap.getPane());
		this.rootAdd(mainPlayer.getPlayerImage());
		input = new Input(this.getScene());
		input.addListeners();

		
		/*
		 * Animation Timer to handle player movement
		 */
		AnimationTimer gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				mainPlayer.processInput(input);
				mainPlayer.move();
				mainPlayer.drawPlayer();
			}	
		};
		gameLoop.start();

	}
	
	

}
