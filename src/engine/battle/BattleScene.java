package engine.battle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoring.ScreenDisplay;
import data.items.Item;
import data.items.PokemonBall;
import data.model.NPC;
import data.model.Pokemon;
import data.model.moves.Move;
import data.player.Player;
import engine.Engine;
import engine.UI.PokemonLabel;
import engine.UI.ScrollingLabel;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * 
 * @author nathanlewis
 * Controller of Scene for the Battle
 */


public class BattleScene extends ScreenDisplay{
	
	private final int PLAYER_POKEMON_XPOS = 100;
	private final int PLAYER_POKEMON_YPOS = 186;
	private static final int LIST_OF_BAG_ITEMS_HEIGHT = 200;
	private static final int LIST_OF_BAG_ITEMS_WIDTH = 150;
	private final int BUTTONS_XPOS = 60;
	private final int BUTTONS_YPOS = 370;
	
	String backgroundImage="file:images/item_list_background.jpg";
    private BattleScene bs;
	private Stage myStage;
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
	private VBox activePokemonInfo;
	
	private HealthBar healthBarPlayer;
	private HealthBar healthBarEnemy;
	private PokemonLabel activePokemonHealth;
	private PokemonLabel enemyPokemonHealth;
	private ImageView currentActivePokemon;
	private PokemonLabel messageLabel;
	private Callback<Integer, Integer> winAction;
	private Callback<Integer, Integer> loseAction;
	
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
	public BattleScene(int width, int height, Paint background, Player player, NPC trainer, Pokemon pokemon, GameScene scene, Stage stage, Callback<Integer, Integer> winAction, Callback<Integer, Integer> loseAction) {
		super(width, height, background);
		canvas = new Canvas(width,height);
		myStage = stage;
		mainPlayer = player;
		activePokemon = mainPlayer.getPokemons()[0];
		enemyTrainer = trainer;
		enemyPokemon = pokemon;
		gameScene = scene;
		this.winAction = winAction;
		this.loseAction = loseAction;
		myStage.setHeight(height+20);
		myStage.setWidth(width);
		gui = new BattleGUI(canvas.getGraphicsContext2D(),width,height,activePokemon,enemyPokemon);
		this.rootAdd(canvas);
		currentActivePokemon=new ImageView(new Image(gui.backSpriteURL(activePokemon)));
		this.rootAdd(currentActivePokemon,PLAYER_POKEMON_XPOS, PLAYER_POKEMON_YPOS);
		resetButtons();
		printActiveHPInfo();
		printEnemyHPInfo();
		messageLabel = new PokemonLabel();
		messageLabel.setLayoutX(BUTTONS_XPOS);
		messageLabel.setLayoutY(BUTTONS_YPOS);
		this.rootAdd(messageLabel);
		this.bs=this;
		bfo = new BattleFightOptions(activePokemon,enemyPokemon,this);
		ebfo=new EnemyBattleFightOptions(enemyPokemon,activePokemon,this);
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
			this.rootRemove(buttonBox);
			
			bfo.setUpScene();				
		});	
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
			this.rootRemove(listOfPokemons);
			this.rootRemove(listOfItems);
			
			Map<String,Integer> bags= mainPlayer.getItems();
			ArrayList<String> itemNames=new ArrayList<>();
			for (String each:bags.keySet()) {

				for (int i=0;i<bags.get(each);i++) {
					itemNames.add(each);

				};
				
			}
			
			if (itemNames.size()==0) {
				showEnding("Nothing inside the bag", false, false);
				return;
			}
			
			listOfItems=addListView(itemNames,500,200);
			
			itemListAction();
		});
	}

	protected ListView<String> addListView(ArrayList<String> content, int x, int y) {
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
			//load list of pokemon
			this.rootRemove(listOfPokemons);
			this.rootRemove(listOfItems);
			ArrayList<String> pokemonNames=new ArrayList<>();
			for (Pokemon each:mainPlayer.getPokemons()) {
				//check if the pokemon has nick name, if they has nick name, then the pokemon exists
				if((each!=null) &&each.getNickName()!=null){
					
					pokemonNames.add(each.getNickName());
				}
				//System.out.println(each.getNickName());
			}	
			listOfPokemons=addListView(pokemonNames,500,200);
			pokemonListAction();			
		});
	}
	
	private void itemListAction() {
		listOfItems.setOnMouseClicked(new EventHandler<MouseEvent>(){
	          @Override
	          public void handle(MouseEvent arg0) {

	                 String item=listOfItems.getSelectionModel().getSelectedItems().get(0);
	                 System.out.println("this is what i want "+item);
	          
					try {
						Class<?> itemClass = Class.forName("data.items."+item);
						Constructor<?> constructor = itemClass.getConstructor();
		     			Item thisItem = (Item) constructor.newInstance();
		     			thisItem.useItem(mainPlayer, activePokemon, enemyPokemon);
		     			if (thisItem instanceof PokemonBall) {
		     				boolean caught=((PokemonBall) thisItem).getCaught();
		     				if (caught) {
		     					showEnding("The pokemon is caught!", true, true);
		     				} else {
		     					showEnding("The pokemon is not caught!", false, false);
		     				}
		     				
		     			} 
		     			updateHealthBars(activePokemon.getCurrentStat().getHP(), enemyPokemon.getCurrentStat().getHP());
		     			mainPlayer.deleteItem(item);
		     			rootRemove(listOfItems);
		     			
		     		
		     			rootRemove(buttonBox);
		     			ebfo.setUpScene();
		     			
					} catch (ClassNotFoundException | NoSuchMethodException
							| SecurityException | InstantiationException
							| IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						
						System.out.printf("type:%s, item not found!", item);
					}
	     			
	               
	          }
	      });	
		
	}
	
	
	//show the game end message
		protected void showEnding(String message, boolean whetherEnd, boolean whetherWin) {
			Text end=new Text(message);
			final Stage dialog = new Stage();
			dialog.initModality(Modality.APPLICATION_MODAL);
			Stage myStage=this.getGameScene().getStage();
			dialog.initOwner(myStage);
			VBox dialogVbox = new VBox(20);

			Button btn = new Button();
			btn.setText("Got it");
			dialogVbox.getChildren().add(end);
			dialogVbox.getChildren().add(btn);
			
			Scene dialogScene = new Scene(dialogVbox, 300, 200);
			dialog.setScene(dialogScene);
			dialog.show();
			btn.setOnAction((event) ->{
				dialog.close();
				if (whetherEnd) {
					if(whetherWin){
						winAction.call(0);
					}else{
						loseAction.call(0);
					}
				}
			});

		}
	
	private void pokemonListAction() {
		listOfPokemons.setOnMouseClicked(new EventHandler<MouseEvent>(){
	          @Override
	          public void handle(MouseEvent arg0) {

	                 String pokemon=listOfPokemons.getSelectionModel().getSelectedItems().get(0);
	                 for (Pokemon each: mainPlayer.getPokemons()) {
	                	     if (each.getNickName().equals(pokemon)) {
	                		     resetActivePokemon(each);
	                	    	     bfo = new BattleFightOptions(activePokemon,enemyPokemon,bs);
	                	    	     ebfo=new EnemyBattleFightOptions(enemyPokemon,activePokemon,bs);
	                	    	     changeActiveHPInfo();
	                	    	     
	                	    	     rootRemove(listOfPokemons);
	                	    	     break;
	                	     }
	                 }   
	          }
	      });	
	}
	
	
	private void resetActivePokemon(Pokemon newActivePokemon) {
	    	this.rootRemove(currentActivePokemon);
	    	activePokemon=newActivePokemon;
		currentActivePokemon.setImage(new Image(gui.backSpriteURL(activePokemon)));
		this.rootAdd(currentActivePokemon,PLAYER_POKEMON_XPOS, PLAYER_POKEMON_YPOS);
	}
	
	
	/*
	 * When run button pressed, exit the battle scene back to main game engine
	 */
	private void runButtonPressed(Button button) {
		button.setOnAction((event) -> {
			gameScene.changeBackScene();
		});
	}
	
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public Player getPlayer() {
		return mainPlayer;
	}

	public GameScene getGameScene() {
		return gameScene;
	}
	
	public HBox getButtonBox() {
		return buttonBox;
	}
	
	protected void setMessage(String string) {
		messageLabel.setText(string);
		messageLabel.animateText();
	}
	
	protected void clearMessage() {
		messageLabel.stopTimer();
		messageLabel.setText("");
	}
	
	
	private void changeActiveHPInfo() {
		this.rootRemove(activePokemonInfo);
		printActiveHPInfo();
		
		
	}
	
	/**
	 * Add Health Bar, name, level and health text for active player pokemon
	 */
	public void printActiveHPInfo() {
		PokemonLabel activePokemonName = new PokemonLabel(activePokemon.getNickName());
		PokemonLabel activePokemonLevel = new PokemonLabel("Lvl:" + activePokemon.getCurrentLevel());
		HBox nameBox = new HBox(40);
		nameBox.getChildren().addAll(activePokemonName,activePokemonLevel);
		healthBarPlayer = new HealthBar(activePokemon.getCurrentStat().getMaxHP(),150,15);
		healthBarPlayer.setHealth(activePokemon.getCurrentStat().getHP());
		activePokemonHealth = new PokemonLabel(activePokemon.getCurrentStat().getHP() + "/" + activePokemon.getCurrentStat().getMaxHP());
		HBox healthBox = new HBox(10);
		healthBox.getChildren().addAll(healthBarPlayer.getPane(),activePokemonHealth);
		
		activePokemonInfo = new VBox(15);
		activePokemonInfo.getChildren().addAll(nameBox,healthBox);
		
		
		activePokemonInfo.setLayoutX(390);
		activePokemonInfo.setLayoutY(240);
		this.rootAdd(activePokemonInfo);
	    
	}
	
	/**
	 * Add Health Bar, name, level and health text for enemy
	 */
	protected void printEnemyHPInfo() {
		PokemonLabel enemyPokemonName = new PokemonLabel(enemyPokemon.getNickName());
		PokemonLabel enemyPokemonLevel = new PokemonLabel("Lvl:" + enemyPokemon.getCurrentLevel());
		HBox enemyNameBox = new HBox(40);
		enemyNameBox.getChildren().addAll(enemyPokemonName,enemyPokemonLevel);
		healthBarEnemy = new HealthBar(enemyPokemon.getCurrentStat().getMaxHP(),150,15);
		healthBarEnemy.setHealth(enemyPokemon.getCurrentStat().getHP());
		enemyPokemonHealth = new PokemonLabel(enemyPokemon.getCurrentStat().getHP() + "/" + enemyPokemon.getCurrentStat().getMaxHP());
		HBox enemyHealthBox = new HBox(10);
		enemyHealthBox.getChildren().addAll(healthBarEnemy.getPane(),enemyPokemonHealth);
		VBox enemyPokemonInfo = new VBox(15);
		enemyPokemonInfo.getChildren().addAll(enemyNameBox,enemyHealthBox);
		
		enemyPokemonInfo.setLayoutX(40);
		enemyPokemonInfo.setLayoutY(60);
		this.rootAdd(enemyPokemonInfo);
	}
	
	/**
	 * Update Health Bars and Labels;
	 * @param playerHealth
	 * @param enemyHealth
	 */
	protected void updateHealthBars(int playerHealth, int enemyHealth) {
		healthBarPlayer.setHealth(playerHealth);
		healthBarEnemy.setHealth(enemyHealth);
		activePokemonHealth.setText(activePokemon.getCurrentStat().getHP() + "/" + activePokemon.getCurrentStat().getMaxHP());
		enemyPokemonHealth.setText(enemyPokemon.getCurrentStat().getHP() + "/" + enemyPokemon.getCurrentStat().getMaxHP());
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
