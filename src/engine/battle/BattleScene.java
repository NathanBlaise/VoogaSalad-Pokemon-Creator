package engine.battle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import authoring.ScreenDisplay;
import data.model.NPC;
import data.model.Pokemon;
import data.player.Player;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * 
 * @author nathanlewis
 * Controller of Scene for the Battle
 */


public class BattleScene extends ScreenDisplay{
	
	
	Image grassBattle = new Image("file:images/grass_battle.png");
	Image grassBattleBackground = new Image("file:images/grass_battle_background.png");
	Image grassBattleGrass1 = new Image("file:images/grass_battle_grass_1.png");
	Image grassBattleGrass2 = new Image("file:images/grass_battle_grass_2.png");

	Image battleBox = new Image("file:images/battle_box.png");

//	Image emeraldBattle1 = new Image("file:images/emerald_battle_1.png");
//	Image emeraldBattle2 = new Image("file:images/emerald_battle_2.png");
//	Image emeraldBattle3 = new Image("file:images/emerald_battle_3.png");
//	Image emeraldBattle4 = new Image("file:images/emerald_battle_4.png");
	
	String[] initialButtons = {"FIGHT","BAG","POKEMON","RUN"};
	
	private GraphicsContext gc;
	private Canvas canvas;
	private Font f;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button[] buttonArr; //Contains all buttons
	
	private Player mainPlayer;
	private Pokemon activePokemon;
	private NPC enemyTrainer;
	private Pokemon enemyPokemon;
	
	
	private final int PLAYER_HOME_XPOS = 15;
	private final int PLAYER_HOME_YPOS = 303;
	private final int INFO_BOX_YPOS = 336;
	private final int ENEMY_HOME_XPOS = 330;
	private final int ENEMY_HOME_YPOS = 145;
	
	/**
	 * 
	 * @param width - width of scene
	 * @param height - height of scene
	 * @param background - background color
	 * @param player - the main player object from the game
	 * @param trainer - the encountered trainer from the game (null if pokemon is encountered)
	 * @param enemyPokemon - the encountered enemy pokemon (null if trainer is encountered)
	 */
	public BattleScene(int width, int height, Paint background, Player player, NPC trainer, Pokemon pokemon) {
		super(width, height, background);
		mainPlayer = player;
		activePokemon = mainPlayer.getPokemons()[0];
		enemyTrainer = trainer;
		enemyPokemon = pokemon;
		canvas = new Canvas(width,height);
		gc = canvas.getGraphicsContext2D();
		this.rootAdd(canvas);
		loadFont();
		setUpScreen();
		buttonInitialSetUp();
	}
	
	/*
	 * May be used to utilize pokemon specific font
	 */
	private void loadFont() {
		try {
			f = Font.loadFont(new FileInputStream(new File("./src/resources/pkmnem.ttf")), 30);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Set up four initial buttons to be used in battle and sets them to default.
	 */
	private void buttonInitialSetUp(){
		button1 = new Button(initialButtons[0]);
		button2 = new Button(initialButtons[1]);
		button3 = new Button(initialButtons[2]);
		button4 = new Button(initialButtons[3]);
		buttonArr = new Button[] {button1, button2, button3, button4};
		VBox vbox1 = new VBox(15);
		vbox1.getChildren().addAll(button1,button3);
		VBox vbox2 = new VBox(15);
		vbox2.getChildren().addAll(button2,button4);
		HBox hbox = new HBox(15);
		hbox.getChildren().addAll(vbox1,vbox2);
		hbox.setLayoutX(60);
		hbox.setLayoutY(INFO_BOX_YPOS+30);
		fightButtonPressed();
		bagButtonPressed();
		this.rootAdd(hbox);
	}
	
	/*
	 * What to do when fight button is pressed
	 */
	private void fightButtonPressed() {
		button1.setOnAction((event) -> {
			int i = 0;
			for(Button button: buttonArr) {
				if(i < activePokemon.getMoveNum()) {
					button.setText(activePokemon.getMoves()[i].getMoveName());
//					button.setOnAction((e) -> {
//						activePokemon.getMoves()[i].move(activePokemon, enemyPokemon);
//						//Move to next scene, enemies move
//					});
				} else {
					button.setText("");
				}
				i++;
			}
		});
	}
	
	private void bagButtonPressed() {
		button2.setOnAction((event) -> {
			//Load list of items
		}); 
	}
	
	private void pokemonButtonPressed() {
		button3.setOnAction((event) -> {
			//load list of pokemon
		});
	}
	
	private void runButtonPressed() {
		button4.setOnAction((event) -> {
			//exit battle scene
		});
	}
	
	/*
	 * Used to display and animate all objects initially when battle loaded
	 */
	private void setUpScreen() {
		gc.drawImage(grassBattleBackground, 0, 0);
		gc.drawImage(grassBattleGrass1, PLAYER_HOME_XPOS, PLAYER_HOME_YPOS); //Needs to be animated
		gc.drawImage(grassBattleGrass2, ENEMY_HOME_XPOS, ENEMY_HOME_YPOS); //Needs to be animated
		gc.drawImage(battleBox,0,INFO_BOX_YPOS);
	}
	
}
