package engine.game;


import javafx.stage.Stage;

import java.util.ArrayList;

import data.Database;
import data.saving.DatabaseLoader;
import data.saving.DatabaseSaver;
import engine.pokemonScene.pokemonScene;
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;
import javafx.util.Duration;
import player.PokemonGameScene;
import start.DatabasePathConfig;



/**
 * for showing the info of player
 * 
 * @author tony cy122
 */
public class UserPage extends Application {

	/*final variable*/

	public static final int SIZE = 480;
	public static final Paint BACKGROUND = Color.WHITE;
	public static final int FRAMES_PER_SECOND = 8;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final int FONTSIZE = 15;
	public static final String ITEM_NAME_EXIT = "EXIT";
	public static final String ITEM_NAME_INVENTORY = "PACK";
	public static final String ITEM_NAME_SAVE = "SAVE";
	public static final String ITEM_NAME_POKEMON = "POKEMON";
	private final int PANE_XPOS = 220;
	private final int PANE_YPOS = 0;
	private final int ITEMBOX_XPOS = 250;
	private final int ITEMBOX_YPOS = 30;
	private final int POPOUT_WIDTH = 8;
	private final int POPOUT_HEIGHT = 8;
	
	/*instance variable*/
	
	private ArrayList <ItemColomn> colList = new ArrayList<ItemColomn>();
	private Scene scene;
	private Stage myStage;
	private boolean 	UserInterfaceEntered = false;
	private Pane root;
	private int tarRow = 0;
	private int currentSce = -1;
	private GridPane itemBox = new GridPane();
	private ArrayList<BagScene> bagSceList = new ArrayList<BagScene>();
	private UserPageArt uPageArt = new UserPageArt();
	private Database database;

	public UserPage(Database database, Stage stage, PokemonGameScene pGscene, Pane root){
		this.database = database;
		this.myStage = stage;
		this.scene =  pGscene.getScene();
		this.root = root;
		
		// SET UP BAGSCENE OVER HERE
		bagSceList.add(new PokemonBagScene(SIZE,SIZE,BACKGROUND, this));
		bagSceList.add(new HMBagScene(SIZE,SIZE,BACKGROUND,this));
		bagSceList.add(new KeyItemBagScene(SIZE,SIZE,BACKGROUND,this,pGscene));
		bagSceList.add(new potionBagScene(SIZE,SIZE,BACKGROUND,this));
		
		//SET UP COLOMN LIST
		colList.add(new ItemColomn(ITEM_NAME_INVENTORY));
		colList.add(new ItemColomn(ITEM_NAME_POKEMON));
		colList.add(new ItemColomn(ITEM_NAME_SAVE));
		colList.add(new ItemColomn(ITEM_NAME_EXIT));

		
		uPageArt.makeGrid(POPOUT_WIDTH, POPOUT_HEIGHT, root, PANE_XPOS,PANE_YPOS);
		uPageArt.setUpBasicItemGrid(root, colList, ITEMBOX_XPOS, ITEMBOX_YPOS);
		if (tarRow == -1) {
			tarRow = 0;
		}
	}
	
	
	
	/**
	 * Initialize what will be displayed and how it will be updated.
	 */
	@Override
	public void start (Stage s) {
		
		myStage = s;
		

		
		//SET UP MAIN SCENE
		scene = setupGame(SIZE, SIZE, BACKGROUND);
		myStage.setScene(scene);
		myStage.show();
		
		
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	
	

	
	private Scene setupGame (int width, int height, Paint background) {
		
		scene = new Scene(root, width, height, background);
		scene.setOnKeyPressed(e -> handleKeyInput(myStage, root, e.getCode(), i->{return null;}));
	
		return scene;
		
	}
	


	/*
	private void setUpBasicItemGrid() {
		for(int i = 0; i < 2 ; i++) {
			ColumnConstraints column = new ColumnConstraints(40);
			itemBox.getColumnConstraints().add(column);
		}

	    for(int i = 0; i < 3  ; i++) {
	        RowConstraints row = new RowConstraints(40);
	        itemBox.getRowConstraints().add(row);
	    }
		updateItemGrid(root,0);
		
		for(int i = 0; i < 2 ; i++) {
	    	 for(int j = 0; j < 3 ; j++) {
		 if (i == 1) {
			 if (j == 0) itemBox.add(useFont(ITEM_NAME_INVENTORY,25), i, j);
			 if (j == 1) itemBox.add(useFont(ITEM_NAME_SAVE,25), i, j);
			 if (j == 2) itemBox.add(useFont(ITEM_NAME_EXIT,25), i, j);
		
		 }
		 
		 if (i == 0) {
			 if (j == 0) itemBox.add(arrow1, i, j);
			 if (j == 1) itemBox.add(arrow2, i, j);
			 if (j == 2) itemBox.add(arrow3, i, j);
			 
			 
		
		 }
	    	 }
		}

		itemBox.setLayoutX(ITEMBOX_XPOS);
		itemBox.setLayoutY(ITEMBOX_YPOS);
		 root.getChildren().add(itemBox);
		 
		 
	}
	
	
	
	
	private void updateItemGrid(Group root, int targetRow) {
		
		
		
		if (targetRow == 0) {
			arrow1.setImage(PANE_POINT);
			arrow2.setImage(null);
			arrow3.setImage(null);
		}
		if (targetRow == 1) {
			arrow2.setImage(PANE_POINT);
			arrow1.setImage(null);
			arrow3.setImage(null);
		}
		if (targetRow == 2) {
			arrow3.setImage(PANE_POINT);
			arrow1.setImage(null);
			arrow2.setImage(null);
		}
	}
	*//**
	 * @param target: target String
	 * @param Size: size of the text
	 * @return The Text with the certain target and certain size
	 *//*
	private Text useFont(String target, int Size) {
		Text ans = new Text(target);
		ans.setFont(getFont(Size));
		
		return ans;
		
	}


	private void makeGrid(Group root) {
		//build a grid pane
		GridPane userBox = new GridPane();
		
		for(int i = 0; i < POPOUT_WIDTH ; i++) {
			ColumnConstraints column = new ColumnConstraints(32);
			userBox.getColumnConstraints().add(column);
		}

	    for(int i = 0; i < POPOUT_HEIGHT  ; i++) {
	        RowConstraints row = new RowConstraints(32);
	        userBox.getRowConstraints().add(row);
	    }
	    
	    userBox.setStyle("-fx-grid-lines-visible: false");
	    
	    for(int i = 0; i < POPOUT_WIDTH ; i++) 
	    	 for(int j = 0; j < POPOUT_HEIGHT ; j++)
	    		 if (i == 0 && j == 0)  userBox.add(new ImageView(TOP_LEFT), 0, 0);
	    		 else if (i == 0 && j == POPOUT_HEIGHT -1 ) userBox.add(new ImageView(BOTTOM_LEFT), i, j);
	    		 else if (i == POPOUT_WIDTH -1 && j == POPOUT_HEIGHT -1 ) userBox.add(new ImageView(BOTTOM_RIGHT), i, j);
	    		 else if (i == POPOUT_WIDTH -1  && j == 0 ) userBox.add(new ImageView(TOP_RIGHT), i, j);
	    		 else if (i == 0)  userBox.add(new ImageView(LEFT), i, j);
	    		 else if (i == POPOUT_WIDTH -1 ) userBox.add(new ImageView(RIGHT), i, j);
	    		 else if (j == 0)  userBox.add(new ImageView(TOP), i, j);
	    		 else if (j == POPOUT_HEIGHT -1 ) userBox.add(new ImageView(BOTTOM), i, j);
	    		 else userBox.add(new ImageView(CONTENT), i, j);
	    			
	    
		root.getChildren().add(userBox);
		
		userBox.setLayoutX(PANE_XPOS);
		userBox.setLayoutY(PANE_YPOS);
	}

	
	
	
	*/
	private void step (double elapsedTime) {
		
		
		
		

		
		
		
	

	}


	// What to do each time a key is pressed
	public void handleKeyInput (Stage myStage, Pane root, KeyCode code, Callback<Integer, Integer> end) {
//		if  (code == KeyCode.ENTER) {
//			
//			// load the game scene
//			if(UserInterfaceEntered) {
//			
////			uPageArt.makeGrid(POPOUT_WIDTH, POPOUT_HEIGHT, root, PANE_XPOS,PANE_YPOS);
////			uPageArt.setUpBasicItemGrid(root, colList, ITEMBOX_XPOS, ITEMBOX_YPOS);
//			
////			//change the tarRow
////			if (tarRow == -1) {
////				tarRow = 0;
////			}
//			
//		 	UserInterfaceEntered = false;
//	}
//			
//			
//		}
	
		
		
//		else 
			if (code == KeyCode.UP) {
			tarRow = (tarRow+2)%colList.size();
			uPageArt.updateItemGrid(root, tarRow, colList);
		}
		
		
		else if (code == KeyCode.Z) {
		if (!UserInterfaceEntered) {
			if(tarRow == 0) {
				myStage.setScene(bagSceList.get(0).getScene());
				currentSce = 0;
			}
			else if(tarRow == 1) {
				myStage.setScene(new pokemonScene(723,SIZE,BACKGROUND, this, database.getPlayer()).getScene());
				myStage.sizeToScene();
			}
			else if(tarRow == 2) {
				Stage stage = new Stage();
				BorderPane databaseChooser = DatabasePathConfig.chooseDatabase(stage, "Pokemon", DatabaseLoader.getAvailableGameTypes().get("Pokemon"), (database, path)-> {
					DatabaseSaver.save(this.database, path);
					ScrollingText text = new ScrollingText("Database successfully saved.", Dialogue.getFont());
					text.setTextAlignment(TextAlignment.CENTER);
					StackPane stack = new StackPane();
					stack.getChildren().add(text);
					stage.setScene(new Scene(stack,500,200));
					text.animateText();
					return null;
				});
				stage.setScene(new Scene(databaseChooser));
				stage.show();
			}
			else if(tarRow == 3) {
				UserInterfaceEntered = true; 
				tarRow = -1;
				itemBox.getChildren().clear();
				uPageArt.removeAll(root);
				end.call(0);
			}
			
		}
		}
		
		else if (code == KeyCode.DOWN) {
			tarRow = (tarRow+1)%colList.size();
			uPageArt.updateItemGrid(root, tarRow, colList);
			
			
		}
		
	}
	
	
	public void switchBagSceneRight() {
		if (currentSce != -1) {
			currentSce = (currentSce + 1)%colList.size();
			myStage.setScene(bagSceList.get(currentSce).getScene());
		}
	}
	
	public void swithBagSceneLeft() {
		if (currentSce != -1) {
			currentSce = (currentSce + 2)%colList.size();
			myStage.setScene(bagSceList.get(currentSce).getScene());
		}
	}
	
	

	/**
	 * The getter of database
	 */
	
	public Database getDataBase() {
		return database;
	}


	
	/**
	 * Let each scene able to go back to original scene
	 */

	public void goBackToOriScene() {
		myStage.setScene(scene);
		currentSce = -1;
		tarRow = 0;
	}

	
	
	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		launch(args);
	}
	
	
	
	
}
