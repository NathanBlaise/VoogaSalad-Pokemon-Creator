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
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author nathanlewis
 * Controller of Scene for the Battle
 */


public class BattleScene extends ScreenDisplay{
	
	
	private static final int LIST_OF_BAG_ITEMS_HEIGHT = 200;
	private static final int LIST_OF_BAG_ITEMS_WIDTH = 150;
	private final int BUTTONS_XPOS = 60;
	private final int BUTTONS_YPOS = 370;
	private Text actionMessage=new Text("");
	
	String backgroundImage="file:images/item_list_background.jpg";

	private Canvas canvas;
	private HBox	buttonBox;
	private BattleGUI gui;
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
		canvas = new Canvas(width,height);
		mainPlayer = player;
		activePokemon = mainPlayer.getPokemons()[0];
		enemyTrainer = trainer;
		enemyPokemon = pokemon;
		gameScene = scene;
		gui = new BattleGUI(canvas.getGraphicsContext2D(),width,height,activePokemon,enemyPokemon);
		this.rootAdd(canvas);
		resetButtons();
		printHPInfo();
		rootAdd(actionMessage);
		setTextEffects(actionMessage,20,40,Color.BLACK);
	}
	
	
	/*
	 * Set up four initial buttons to be used in battle and sets them to default.
	 */
	
	
	protected void resetButtons() {
		gui.resetButtons();
		fightButtonPressed(gui.buttonArr[0]);
		bagButtonPressed(gui.buttonArr[1]);
		pokemonButtonPressed(gui.buttonArr[2]);
		runButtonPressed(gui.buttonArr[3]);
		buttonBox = fourButtonLayout(gui.buttonArr);
		this.rootAdd(buttonBox);
	}
	
	/*
	 * //When fight button pressed, remove current buttons and replace with current pokemon's moves
	 */
	private void fightButtonPressed(Button button) {
		button.setOnAction((event) -> { 
			rootRemove(listOfItems);
			rootRemove(listOfPokemons);
		    actionMessage.setText("");
			this.rootRemove(buttonBox);
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
		ObservableList<String> items =FXCollections.observableArrayList (content);
		
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
			pokemonListAction();			
		});
	}
	
	
	
	private void pokemonListAction() {
		listOfPokemons.setOnMouseClicked(new EventHandler<MouseEvent>(){
	          @Override
	          public void handle(MouseEvent arg0) {

	                 String item=listOfPokemons.getSelectionModel().getSelectedItems().get(0);
	                 for (Pokemon each: mainPlayer.getPokemons()) {
	                	     if (each.getNickName().equals(item)) {
	                	    	     activePokemon=each;
	                	    	     break;
	                	     }
	                 }
	                 activePokemonHP.setText(activePokemon.getNickName()+System.getProperty("line.separator")+"Hp: "+activePokemon.getCurrentStat().getHP());     
	          }
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
		activePokemonHP=new Text( activePokemon.getNickName()+System.getProperty("line.separator")+"Hp: " + activePokemon.getCurrentStat().getHP());
		setTextEffects(activePokemonHP,300,260,Color.RED);
		enemyPokemonHP=new Text( enemyPokemon.getNickName()+System.getProperty("line.separator")+"Hp: " + enemyPokemon.getCurrentStat().getHP());
	
		
		setTextEffects(enemyPokemonHP,200,150,Color.RED);
		this.rootAdd(activePokemonHP);
		this.rootAdd(enemyPokemonHP);
	    
	}
	
	
	private void setTextEffects(Text t,int x, int y, Color c) {
		InnerShadow is = new InnerShadow();
		is.setOffsetX(4.0f);
		is.setOffsetY(4.0f);
		t.setEffect(is);
		t.setFill(c);
		t.setTranslateX(x);
		t.setTranslateY(y);
		t.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
		
	}
	
	/*
	 * Draws buttons in 4 place format correctly on screen
	 */
	protected HBox fourButtonLayout(Button[] buttons) {
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
	
}
