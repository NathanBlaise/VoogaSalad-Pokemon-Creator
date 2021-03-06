package engine.battle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;

import data.model.Pokemon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

/**
 * 
 * @author Yiqin, nathan, tony, Dan Sun
 *
 */
public class BattleGUI {
	private static final String DEFAULT_RESOURCE_PACKAGE = "util/English_Text";
	private ResourceBundle myResources;
	private Image grassBattleBackground = new Image("file:images/grass_battle_background.png");
	private Image grassBattleGrass1 = new Image("file:images/grass_battle_grass_1.png");
	private Image grassBattleGrass2 = new Image("file:images/grass_battle_grass_2.png");
//	private Image itemList = new Image("file:images/item_list_background.jpg");
	private Image battleBox = new Image("file:images/battle_box.png");
//	private Image battle_button_box = new Image("file:images/battle_button_box.png");
	private Image hpBoxPlayer = new Image("file:images/hp_box_1.png");
	private Image hpBoxEnemy = new Image("file:images/hp_box_2.png");
	private String[] initialButtons = {"FIGHT","BAG","POKEMON","RUN"};
	
	private final int PLAYER_HOME_XPOS = 15;
	private final int PLAYER_HOME_YPOS = 303;
	private final int INFO_BOX_YPOS = 336;
	private final int ENEMY_HOME_XPOS = 330;
	private final int ENEMY_HOME_YPOS = 145;
	private final int ENEMY_POKEMON_XPOS = 400;
	private final int ENEMY_POKEMON_YPOS = 60;
	
	
	private GraphicsContext gc;
//	private Pokemon playerPokemon;
	private Pokemon enemyPokemon;
	protected Button[] buttonArr;
//	private ImageView currentActivePokemon;
	
	public BattleGUI(GraphicsContext gc, int width, int height, Pokemon pokemon1, Pokemon pokemon2) {
		this.gc = gc;
//		playerPokemon = pokemon1;
		enemyPokemon = pokemon2;
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
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
		//gc.drawImage(battle_button_box, 520, INFO_BOX_YPOS-4,200,150);
		gc.drawImage(new Image(gifSpriteURL(enemyPokemon)),ENEMY_POKEMON_XPOS, ENEMY_POKEMON_YPOS);
		//gc.drawImage(new Image(backSpriteURL(playerPokemon)),PLAYER_POKEMON_XPOS ,PLAYER_POKEMON_YPOS);
		gc.drawImage(hpBoxPlayer, 350, 225);
		gc.drawImage(hpBoxEnemy, 30, 50);
	}
	
	/*
	 * Reset to 4 initial buttons - Fight, Bag, Pokemon and Run
	 */
	protected void resetButtons(){
		Button button1 = new Button(initialButtons[0]);
		Button button2 = new Button(initialButtons[1]);
		Button button3 = new Button(initialButtons[2]);
		Button button4 = new Button(initialButtons[3]);
		setButtonStyle(button1);
		setButtonStyle(button2);
		setButtonStyle(button3);
		setButtonStyle(button4);
		buttonArr = new Button[] {button1, button2, button3, button4};
	}
	
	private void setButtonStyle(Button b) {
		b.setStyle(myResources.getString("buttonStyle"));
		Font font=getFont();
		b.setFont(font);
	}
	
	private Font getFont() {
		Font f = new Font(30) ;
		try {
			f = Font.loadFont(new FileInputStream(new File(myResources.getString("fontPath"))), 20);
		} catch (FileNotFoundException e) {
			e.printStackTrace();//handled by exiting the program
			System.exit(1);
		}
		return f;
	}

	
	/*
	 * Passed in the player's pokemon; return the pokemon pictures from the back
	 */
	String backSpriteURL(Pokemon myPokemon) {
		System.out.printf("%d\n", myPokemon.getSpecieIndex(myPokemon.getCurrentLevel()));
		return myResources.getString("backSpritePath")+myPokemon.getSpecieIndex(myPokemon.getCurrentLevel())+myResources.getString("suffix");
	}
	
	/*
	 * Passed in the enemy pokemon; return the gif version of the picture
	 */
	private String gifSpriteURL(Pokemon enemyPokemon) {
		return myResources.getString("spritePath")+enemyPokemon.getSpecieIndex(enemyPokemon.getCurrentLevel())+myResources.getString("gifSuffix");
	}

}
