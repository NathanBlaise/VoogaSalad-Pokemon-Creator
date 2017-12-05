package engine.battle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import authoring.ScreenDisplay;
import data.items.Item;
import data.model.NPC;
import data.model.Pokemon;
import data.model.moves.Move;
import data.player.Player;
import engine.game.GameScene;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.InnerShadow;
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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author nathanlewis
 * Controller of Scene for the Battle
 */


public class BattleScene extends ScreenDisplay{
	
	
	private static final int LIST_OF_BAG_ITEMS_HEIGHT = 200;
	private static final int LIST_OF_BAG_ITEMS_WIDTH = 150;
	private Text actionMessage=new Text("");
	
	Image grassBattle = new Image("file:images/grass_battle.png");
	Image grassBattleBackground = new Image("file:images/grass_battle_background.png");
	Image grassBattleGrass1 = new Image("file:images/grass_battle_grass_1.png");
	Image grassBattleGrass2 = new Image("file:images/grass_battle_grass_2.png");
	Image itemList = new Image("file:images/item_list_background.jpg");
	String backgroundImage="file:images/item_list_background.jpg";

	Image battleBox = new Image("file:images/battle_box.png");
    
	Image pokemonImage;
	Image enemyImage;
	
	Image emeraldBattle2 = new Image("file:images/emerald_battle_2.png");
//	Image emeraldBattle3 = new Image("file:images/emerald_battle_3.png");
//	Image emeraldBattle4 = new Image("file:images/emerald_battle_4.png");
	
	String[] initialButtons = {"FIGHT","BAG","POKEMON","RUN"};
	
	private VBox vbox1;
	private VBox vbox2;
	private HBox hbox;
	
	GraphicsContext gc;
	private Canvas canvas;
	private Font f;
	private Button[] buttonArr; //Contains all starter buttons (Fight, Bag, Pokemon & Run)
	
	private BattleFightOptions bfo;
	private EnemyBattleFightOptions ebfo;
	private Player mainPlayer;
	private Pokemon activePokemon;
	private NPC enemyTrainer;
	private Pokemon enemyPokemon;
	private GameScene gameScene;
	private ListView<String> listOfItems;
	private ListView<String> listOfPokemons;
	//an instance variable to show whose turn it is, 0 means player's turn to attack, 1 means NPC's turn.
	
	private Text activePokemonHP;
	private Text enemyPokemonHP;

	
	
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
	 * @param inputList 
	 * @param enemyPokemon - the encountered enemy pokemon (null if trainer is encountered)
	 */
	public BattleScene(int width, int height, Paint background, Player player, NPC trainer, Pokemon pokemon, GameScene scene) {
		super(width, height, background);
		mainPlayer = player;
		activePokemon = mainPlayer.getPokemons()[0];
		
		enemyTrainer = trainer;
		enemyPokemon = pokemon;
		canvas = new Canvas(width,height);
		gc = canvas.getGraphicsContext2D();
		gameScene = scene;
		this.rootAdd(canvas);
		loadFont();
		buttonInitialSetUp();
		characterSetUp();
		setUpScreen();
		printHPInfo();
		rootAdd(actionMessage);
		setTextEffects(actionMessage,20,40,Color.BLACK);
	
		

	}
	
	/*
	 * Set up images for player and NPC
	 */
	
	private void characterSetUp() {
//		String imagePathForEP=enemyPokemon.getCurrentImagePath();
	enemyImage=new Image("file:images/pokemon_back_sprites/1.png");
	pokemonImage=new Image("file:images/pokemon_back_sprites/10.png");
		
		
		//hard code for now
//		System.out.println("Current Path: "+activePokemon.getCurrentImagePath());
//		System.out.println("Enemy path: " + enemyPokemon.getCurrentImagePath());
//		System.out.println(backSpriteURL(activePokemon));
//		System.out.println(gifSpriteURL(enemyPokemon));
		
		//enemyImage=new Image(backSpriteURL(enemyPokemon));
		//pokemonImage=new Image(gifSpriteURL(activePokemon));
		
		//System.out.println(activePokemon.getCurrentImagePath().substring(0, 14) + "_back" + activePokemon.getCurrentImagePath().substring(14, 25) + "png");
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
	protected void buttonInitialSetUp(){
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
	
			
				rootRemove(listOfItems);
				rootRemove(listOfPokemons);
		
			
			
		    actionMessage.setText("");
			this.rootRemove(hbox);
			bfo = new BattleFightOptions(activePokemon,enemyPokemon,this);
			ebfo=new EnemyBattleFightOptions(enemyPokemon,activePokemon,this);
	
			bfo.setUpScene();
			
			
			
			
			
		});
		
		
		
	}
	

	
	public Text getActionMessage() {
		return actionMessage;
	}
	
	public BattleFightOptions getMyBattleScene() {
		return bfo;
	}
	
	public EnemyBattleFightOptions getEnemyBattleScene() {
		return ebfo;
	}
	
	/*
	 * When bag button pressed remove current buttons and load new screen with items
	 */
	private void bagButtonPressed(Button button) {
		button.setOnAction((event) -> {
			actionMessage.setText("");
			this.rootRemove(listOfPokemons);
			this.rootRemove(listOfItems);
			
			//gc.drawImage(itemList, PLAYER_HOME_XPOS, PLAYER_HOME_YPOS,100,200);
			
			//load list of pokemon
		
			
//			ArrayList<Item> bags= mainPlayer.getItems();
			ArrayList<String> itemNames=new ArrayList<>();
//			for (Item each:bags) {
//				itemNames.add(each.getItemName());
//			}
			
	
			
			//put itemNames in real code, but will hard code for now
			itemNames.add("item1");
			itemNames.add("item2");
			itemNames.add("item3");
			
			listOfItems=addListView(itemNames,500,200);
			System.out.println(listOfItems==null);
			
		});
	}

	public ListView<String> addListView(ArrayList<String> content, int x, int y) {
		ObservableList<String> items =FXCollections.observableArrayList (content
		    );
		
		ListView<String> list=new ListView<String>(); 
		list.setItems(items);
		list.setTranslateX(x);
		list.setTranslateY(y);
		list.setPrefWidth(LIST_OF_BAG_ITEMS_WIDTH);
		list.setPrefHeight(LIST_OF_BAG_ITEMS_HEIGHT);
		list.setStyle("-fx-control-inner-background: #61a2b1;");
		
     

		this.rootAdd(list);
		return list;
	}
	
	/*
	 * When pokemon button pressed remove current buttons and load new screen with pokemon
	 */
	private void pokemonButtonPressed(Button button) {
		button.setOnAction((event) -> {
			actionMessage.setText("");
			//load list of pokemon
	
			    this.rootRemove(listOfItems);
			    this.rootRemove(listOfPokemons);
	

			ArrayList<String> pokemonNames=new ArrayList<>();
			for (Pokemon each:mainPlayer.getPokemons()) {
				//check if the pokemon has nick name, if they has nick name, then the pokemon exists
				if((each!=null) &&each.getNickName()!=null){
					System.out.println(each.getNickName());
					pokemonNames.add(each.getNickName());
				}
				//System.out.println(each.getNickName());
			}
			
			listOfPokemons=addListView(pokemonNames,500,200);
		
			
			
			
		});
	}
	
	/*
	 * When run button pressed, exit the battle scene back to main game engine
	 */
	private void runButtonPressed(Button button) {
		button.setOnAction((event) -> {
			gameScene.changeBackScene();
		});
	}
	
	public HBox fourButtonLayout(Button[] buttons) {
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
		gc.drawImage(enemyImage,400, 60);
		gc.drawImage(pokemonImage,100 ,193);
	}
	
	
	/*
	 * Passed in the pictures of the front; return the pokemon pictures from the back
	 */
	private String backSpriteURL(Pokemon myPokemon) {
		String path=myPokemon.getCurrentImagePath();
		return "file:"+path.substring(0, 14) + "_back_sprites" + path.substring(15, path.length());
	}
	
	/*
	 * Passed in the pictures of the enemy; return the gif version of the picture
	 */
	private String gifSpriteURL(Pokemon enemyPokemon) {
		String path=enemyPokemon.getCurrentImagePath();
		return "file:"+path.substring(0, 14) +"_sprites"+ path.substring(15,path.length());
	}
	

	public GameScene getGameScene() {
		return gameScene;
	}
	public Text getActivePokemonHP() {
		return activePokemonHP;
	}
	
	public Text getEnemyPokemonHP() {
		return enemyPokemonHP;
	}
	
	public void printHPInfo() {
		activePokemonHP=new Text( "Hp: " + activePokemon.getCurrentStat().getHP());
		setTextEffects(activePokemonHP,300,300,Color.RED);
		enemyPokemonHP=new Text( "Hp: " + enemyPokemon.getCurrentStat().getHP());
	
		
		setTextEffects(enemyPokemonHP,200,150,Color.RED);
		this.rootAdd(activePokemonHP);
		this.rootAdd(enemyPokemonHP);
	    
	}
	
	
	public void setTextEffects(Text t,int x, int y, Color c) {
		InnerShadow is = new InnerShadow();
		is.setOffsetX(4.0f);
		is.setOffsetY(4.0f);
		t.setEffect(is);
		t.setFill(c);
		t.setTranslateX(x);
		t.setTranslateY(y);
		t.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
		 
	
		
	}
	


	
	
}
