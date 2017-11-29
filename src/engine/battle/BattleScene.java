package engine.battle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import authoring.ScreenDisplay;
import data.event.InstructionNPCFight;
import data.items.Item;
import data.model.NPC;
import data.model.Pokemon;
import data.player.Player;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 
 * @author nathanlewis
 * Controller of Scene for the Battle
 */


public class BattleScene extends ScreenDisplay{
	
	
	private static final int LIST_OF_BAG_ITEMS_HEIGHT = 200;
	private static final int LIST_OF_BAG_ITEMS_WIDTH = 150;
	Image grassBattle = new Image("file:images/grass_battle.png");
	Image grassBattleBackground = new Image("file:images/grass_battle_background.png");
	Image grassBattleGrass1 = new Image("file:images/grass_battle_grass_1.png");
	Image grassBattleGrass2 = new Image("file:images/grass_battle_grass_2.png");
	Image itemList = new Image("file:images/item_list_background.jpg");
	String backgroundImage="file:images/item_list_background.jpg";

	Image battleBox = new Image("file:images/battle_box.png");

	Image enemyImage;
	Image player;
	Image emeraldBattle2 = new Image("file:images/emerald_battle_2.png");
//	Image emeraldBattle3 = new Image("file:images/emerald_battle_3.png");
//	Image emeraldBattle4 = new Image("file:images/emerald_battle_4.png");
	
	String[] initialButtons = {"FIGHT","BAG","POKEMON","RUN"};
	
	private VBox vbox1;
	private VBox vbox2;
	private HBox hbox;
	
	private GraphicsContext gc;
	private Canvas canvas;
	private Font f;
	private Button[] buttonArr; //Contains all starter buttons (Fight, Bag, Pokemon & Run)
	
	private Player mainPlayer;
	private Pokemon activePokemon;
	private InstructionNPCFight enemyTrainer;
	private Pokemon enemyPokemon;
	private ListView<String> listOfItems;
	private ListView<String> listOfPokemons;
	//an instance variable to show whose turn it is, 0 means player's turn to attack, 1 means NPC's turn.
	private int current=0;
	
	
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
	 * @param testNPC - the encountered trainer from the game (null if pokemon is encountered)
	 * @param enemyPokemon - the encountered enemy pokemon (null if trainer is encountered)
	 */
	public BattleScene(int width, int height, Color background, Player player, InstructionNPCFight testNPC, Pokemon pokemon) {
		super(width, height, background);
		mainPlayer = player;
		activePokemon = mainPlayer.getPokemons()[0];
		enemyTrainer = testNPC;
		enemyPokemon = pokemon;
		canvas = new Canvas(width,height);
		gc = canvas.getGraphicsContext2D();
		this.rootAdd(canvas);
		loadFont();
		buttonInitialSetUp();
		characterSetUp();
		setUpScreen();
	}
	
	/*
	 * Set up images for player and NPC
	 */
	
	private void characterSetUp() {
		String imagePathForNPC=enemyTrainer.getNpc().getImagePath();
		enemyImage=new Image(imagePathForNPC);
		//hard code for now
		player=emeraldBattle2;
		
		
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
		bagButtonPressed(button2);
		pokemonButtonPressed(button3);
		runButtonPressed(button4);
	}
	
	

	
	/*
	 * //When fight button pressed, remove current buttons and replace with current pokemon's moves
	 */
	private void fightButtonPressed(Button button) {
		button.setOnAction((event) -> { 
		
	
			BattleFightOptions bfo = new BattleFightOptions(activePokemon,enemyPokemon);
			this.rootRemove(hbox);
			this.rootAdd(fourButtonLayout(bfo.getButtons()));
			this.rootAdd(bfo.getText(),400,400);

			
		});
		
		
		
	}
	
	/*
	 * When bag button pressed remove current buttons and load new screen with items
	 */
	private void bagButtonPressed(Button button) {
		button.setOnAction((event) -> {
			
			//gc.drawImage(itemList, PLAYER_HOME_XPOS, PLAYER_HOME_YPOS,100,200);
			
			//load list of pokemon
			
			//Pokemon[] bags= mainPlayer.getPokemons();
			ArrayList<String> itemNames=new ArrayList<>();
//			for (Pokemon each:bags) {
//				itemNames.add(each.getName());
//			}
			
	
			
			//put itemNames in real code, but will hard code for now
			itemNames.add("bag1");
			itemNames.add("bag2");
			itemNames.add("bag3");
			itemNames.add("bag4");
			addListView(listOfItems,itemNames);
	
			
		
			
			
		});
	}

	public void addListView(ListView<String> list,ArrayList<String> content) {
		ObservableList<String> items =FXCollections.observableArrayList (content
		    );
		
		list=new ListView<String>(); 
		list.setItems(items);
		list.setTranslateX(500);
		list.setTranslateY(200);
		list.setPrefWidth(LIST_OF_BAG_ITEMS_WIDTH);
		list.setPrefHeight(LIST_OF_BAG_ITEMS_HEIGHT);
		list.setStyle("-fx-control-inner-background: #61a2b1;");
		


		this.rootAdd(list);
	}
	
	/*
	 * When pokemon button pressed remove current buttons and load new screen with pokemon
	 */
	private void pokemonButtonPressed(Button button) {
		button.setOnAction((event) -> {
			//load list of pokemon
			if (listOfItems!=null) {
			    this.rootRemove(listOfItems);
			}
			
			
			

			ArrayList<String> itemNames=new ArrayList<>();
//			ArrayList<Item> bags= mainPlayer.getItems();
//			for (Item each:bags) {
//				itemNames.add(each.getItemName());
//		}
//			
			//put itemNames in real code, but will hard code for now
			itemNames.add("item1");
			itemNames.add("item2");
			itemNames.add("item3");
			
			addListView(listOfItems,itemNames);
			
			
		
			
			
			
		});
	}
	
	/*
	 * When run button pressed, exit the battle scene back to main game engine
	 */
	private void runButtonPressed(Button button) {
		button.setOnAction((event) -> {
			//exit battle scene
			Stage stage = (Stage) button.getScene().getWindow();
		    stage.hide();
		});
	}
	
	private HBox fourButtonLayout(Button[] buttons) {
		vbox1 = new VBox(15);
		vbox1.getChildren().addAll(buttons[0],buttons[2]);
		vbox2 = new VBox(15);
		vbox2.getChildren().addAll(buttons[1],buttons[3]);
		hbox = new HBox(15);
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
		gc.drawImage(enemyImage, 400, 50);
		gc.drawImage(player,190 ,250);
		
		
	}
	
	
	
}
