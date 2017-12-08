package engine.battle;

import java.util.List;

import data.model.Pokemon;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BattleGUI {
	
	private Image grassBattleBackground = new Image("file:images/grass_battle_background.png");
	private Image grassBattleGrass1 = new Image("file:images/grass_battle_grass_1.png");
	private Image grassBattleGrass2 = new Image("file:images/grass_battle_grass_2.png");
	private Image itemList = new Image("file:images/item_list_background.jpg");
	private Image battleBox = new Image("file:images/battle_box.png");
	private String[] initialButtons = {"FIGHT","BAG","POKEMON","RUN"};
	
	private final int PLAYER_HOME_XPOS = 15;
	private final int PLAYER_HOME_YPOS = 303;
	private final int INFO_BOX_YPOS = 336;
	private final int ENEMY_HOME_XPOS = 330;
	private final int ENEMY_HOME_YPOS = 145;
	private final int ENEMY_POKEMON_XPOS = 400;
	private final int ENEMY_POKEMON_YPOS = 60;
	private final int PLAYER_POKEMON_XPOS = 100;
	private final int PLAYER_POKEMON_YPOS = 186;
	
	private GraphicsContext gc;
	private Pokemon playerPokemon;
	private Pokemon enemyPokemon;
	protected Button[] buttonArr;
	
	public BattleGUI(GraphicsContext gc, int width, int height, Pokemon pokemon1, Pokemon pokemon2) {
		this.gc = gc;
		playerPokemon = pokemon1;
		enemyPokemon = pokemon2;
		setUpScreen();
	}
	
	/*
	 * Used to display and animate all objects initially when battle loaded
	 */
	private void setUpScreen() {
		gc.drawImage(grassBattleBackground, 0, 0);
		gc.drawImage(grassBattleGrass1, PLAYER_HOME_XPOS, PLAYER_HOME_YPOS); //Needs to be animated
		gc.drawImage(grassBattleGrass2, ENEMY_HOME_XPOS, ENEMY_HOME_YPOS); //Needs to be animated
		gc.drawImage(battleBox,0,INFO_BOX_YPOS);
		gc.drawImage(new Image(gifSpriteURL(enemyPokemon)),ENEMY_POKEMON_XPOS, ENEMY_POKEMON_YPOS);
		gc.drawImage(new Image(backSpriteURL(playerPokemon)),PLAYER_POKEMON_XPOS ,PLAYER_POKEMON_YPOS);
	}
	
	/*
	 * Reset to 4 initial buttons - Fight, Bag, Pokemon and Run
	 */
	protected void resetButtons(){
		Button button1 = new Button(initialButtons[0]);
		Button button2 = new Button(initialButtons[1]);
		Button button3 = new Button(initialButtons[2]);
		Button button4 = new Button(initialButtons[3]);
		buttonArr = new Button[] {button1, button2, button3, button4};
	}
	
	/*
	 * Passed in the player's pokemon; return the pokemon pictures from the back
	 */
	private String backSpriteURL(Pokemon myPokemon) {
		return "file:images/pokemon_back_sprites/"+myPokemon.getSpecieIndex(myPokemon.getCurrentLevel())+".png";
	}
	
	/*
	 * Passed in the enemy pokemon; return the gif version of the picture
	 */
	private String gifSpriteURL(Pokemon enemyPokemon) {
		return "file:images/pokemon_sprites/"+enemyPokemon.getSpecieIndex(enemyPokemon.getCurrentLevel())+".gif_.gif";
	}

}
