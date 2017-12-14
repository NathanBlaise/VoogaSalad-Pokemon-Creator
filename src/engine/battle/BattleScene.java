package engine.battle;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

import authoring.ScreenDisplay;
import data.items.Item;
import data.items.PokemonBall;
import data.model.NPC;
import data.model.Pokemon;
import data.player.Player;
import engine.UI.PokemonLabel;
import engine.game.GameScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author nathanlewis, yiqinzhou
 * Controller of Scene for the Battle
 */


public class BattleScene extends ScreenDisplay{
	
	private static final int List_View_Y_POS = 200;
	private static final int List_View_X_POS = 500;
	private final int PLAYER_POKEMON_XPOS = 100;
	private final int PLAYER_POKEMON_YPOS = 186;
	private static final int LIST_OF_BAG_ITEMS_HEIGHT = 200;
	private static final int LIST_OF_BAG_ITEMS_WIDTH = 150;
	private final int BUTTONS_XPOS = 60;
	private final int BUTTONS_YPOS = 370;
	
	private String backgroundImage="file:images/item_list_background.jpg";
    private BattleScene bs;
	private Stage myStage;
	private Canvas canvas;
	private HBox	buttonBox;
	private BattleGUI gui;
	
	private BattleEnding be;
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
	public BattleScene(int width, int height, Paint background, Player player, NPC trainer, Pokemon pokemon, GameScene scene, Stage stage) {
		super(width, height, background);
		
		canvas = new Canvas(width,height);
		myStage = stage;
		mainPlayer = player;
		activePokemon = mainPlayer.getPokemons()[0];
		enemyTrainer = trainer;
		enemyPokemon = pokemon;
		gameScene = scene;
		myStage.setHeight(height);
		myStage.setWidth(width);
		gui = new BattleGUI(canvas.getGraphicsContext2D(),width,height,activePokemon,enemyPokemon);
		this.rootAdd(canvas);
		currentActivePokemon=new ImageView(new Image(gui.backSpriteURL(activePokemon)));
		this.rootAdd(currentActivePokemon,PLAYER_POKEMON_XPOS, PLAYER_POKEMON_YPOS);
		resetButtons();
		printHPInfo(activePokemon,390,240);
		printHPInfo(enemyPokemon,40,60);
		messageLabel = new PokemonLabel();
		messageLabel.setLayoutX(BUTTONS_XPOS);
		messageLabel.setLayoutY(BUTTONS_YPOS);
		this.rootAdd(messageLabel);
		this.bs=this;
		be=new BattleEnding(this.getGameScene());
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
			removeLists();
			this.rootRemove(buttonBox);
			
			bfo.setUpScene();				
		});	
	}


	public void removeLists() {
		rootRemove(listOfItems);
		rootRemove(listOfPokemons);
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
			removeLists();
			
			Map<String,Integer> bags= mainPlayer.getItems();
			ArrayList<String> itemNames=new ArrayList<>();
			for (String each:bags.keySet()) {

				for (int i=0;i<bags.get(each);i++) {
					itemNames.add(each);

				};
				
			}
			
			if (itemNames.size()==0) {
				be.showEnding("Nothing inside the bag",false);
				return;
			}
			
			listOfItems=addListView(itemNames,List_View_X_POS,List_View_Y_POS);
			
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

			removeLists();
			ArrayList<String> pokemonNames=new ArrayList<>();
			for (Pokemon each:mainPlayer.getPokemons()) {
			
				if((each!=null) &&each.getNickName()!=null){
					
					pokemonNames.add(each.getNickName());
				}
				
			}	
			listOfPokemons=addListView(pokemonNames,List_View_X_POS,List_View_Y_POS);
			pokemonListAction();			
		});
	}
	
	private void itemListAction() {
		listOfItems.setOnMouseClicked(new EventHandler<MouseEvent>(){
	          @Override
	          public void handle(MouseEvent arg0) {

	                 String item=listOfItems.getSelectionModel().getSelectedItems().get(0);
	            
	          
					try {
						Class<?> itemClass = Class.forName("data.items."+item);
						Constructor<?> constructor = itemClass.getConstructor();
		     			Item thisItem = (Item) constructor.newInstance();
		     			thisItem.useItem(mainPlayer, activePokemon, enemyPokemon);
		     			if (thisItem instanceof PokemonBall) {
		     				boolean caught=((PokemonBall) thisItem).getCaught();
		     				if (caught) {
		     					be.showEnding("The pokemon is caught!",true);
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
		printHPInfo(activePokemon,390,240);
		
		
	}
	
	/**
	 * Add Health Bar, name, level and health text for active player pokemon
	 */
	public void printHPInfo(Pokemon pokemon,int x,int y) {
		PokemonLabel PokemonName = new PokemonLabel(pokemon.getNickName());
		PokemonLabel PokemonLevel = new PokemonLabel("Lvl:" + pokemon.getCurrentLevel());
		HBox nameBox = new HBox(40);
		nameBox.getChildren().addAll(PokemonName,PokemonLevel);
		
		PokemonLabel PokemonHealth = new PokemonLabel(pokemon.getCurrentStat().getHP() + "/" +pokemon.getCurrentStat().getMaxHP());
		HealthBar healthBar = new HealthBar(pokemon.getCurrentStat().getHP(),150,15);
		HBox healthBox = new HBox(10);
		healthBox.getChildren().addAll(healthBar.getPane(),PokemonHealth);
		
		VBox PokemonInfo = new VBox(15);
		PokemonInfo.getChildren().addAll(nameBox,healthBox);
		
		
		PokemonInfo.setLayoutX(x);
		PokemonInfo.setLayoutY(y);
		this.rootAdd(PokemonInfo);
	
		
		if (pokemon.equals(activePokemon)){
			activePokemonHealth=PokemonHealth;
			activePokemonInfo=PokemonInfo;
			healthBarPlayer=healthBar;
			
		}
		else {
			enemyPokemonHealth=PokemonHealth;
			healthBarEnemy = healthBar;
		}
	
	    
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
