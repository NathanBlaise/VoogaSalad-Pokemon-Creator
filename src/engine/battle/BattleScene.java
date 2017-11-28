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
	private Button[] buttonArr; //Contains all starter buttons (Fight, Bag, Pokemon & Run)
	
	private Player mainPlayer;
	private Pokemon activePokemon;
	private NPC enemyTrainer;
	private Pokemon enemyPokemon;
	
	
	private final int PLAYER_HOME_XPOS = 15;
	private final int PLAYER_HOME_YPOS = 303;
	private final int INFO_BOX_YPOS = 336;
	private final int ENEMY_HOME_XPOS = 330;
	private final int ENEMY_HOME_YPOS = 145;
	
	private final int BUTTONS_XPOS = 60;
	private final int BUTTONS_YPOS = 370;
	
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
		//Create Fight,Bag, Pokemon and Run buttons, 
		Button button1 = new Button(initialButtons[0]);
		Button button2 = new Button(initialButtons[1]);
		Button button3 = new Button(initialButtons[2]);
		Button button4 = new Button(initialButtons[3]);
		buttonArr = new Button[] {button1, button2, button3, button4};
		this.rootAdd(fourButtonLayout(buttonArr));
		//Sets action for fight button
		fightButtonPressed(button1);
	}
	
	/*
	 * //When fight button pressed, remove current buttons and replace with current pokemon's moves
	 */
	private void fightButtonPressed(Button button) {
		button.setOnAction((event) -> { 
			BattleFightOptions bfo = new BattleFightOptions(activePokemon,enemyPokemon);
			for(Button b: buttonArr) {
				this.rootRemove(b);
			}
			this.rootAdd(fourButtonLayout(bfo.getButtons()));
		});
	}
	
	/*
	 * When bag button pressed remove current buttons and load new screen with items
	 */
	private void bagButtonPressed(Button button) {
		button.setOnAction((event) -> {
			//load list of pokemon
		});
	}
	
	/*
	 * When pokemon button pressed remove current buttons and load new screen with pokemon
	 */
	private void pokemonButtonPressed(Button button) {
		button.setOnAction((event) -> {
			//load list of pokemon
		});
	}
	
	/*
	 * When run button pressed, exit the battle scene back to main game engine
	 */
	private void runButtonPressed(Button button) {
		button.setOnAction((event) -> {
			//exit battle scene
		});
	}
	
	private HBox fourButtonLayout(Button[] buttons) {
		VBox vbox1 = new VBox(15);
		vbox1.getChildren().addAll(buttons[0],buttons[2]);
		VBox vbox2 = new VBox(15);
		vbox2.getChildren().addAll(buttons[1],buttons[3]);
		HBox hbox = new HBox(15);
		hbox.getChildren().addAll(vbox1,vbox2);
		hbox.setLayoutX(BUTTONS_XPOS);
		hbox.setLayoutY(BUTTONS_YPOS);
		return hbox;
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
