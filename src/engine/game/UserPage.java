package engine.game;


import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;


import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

import javafx.animation.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;



/**
 * A basic example JavaFX program for the first lab.
 * 
 * @author Robert C. Duvall
 */
public class UserPage extends Application {

	public static final String ITEM_NAME_EXIT = "EXIT";
	public static final String ITEM_NAME_INVENTORY = "PACK";
	public static final String ITEM_NAME_SAVE = "SAVE";
	private final int PANE_XPOS = 180;
	private final int PANE_YPOS = 0;
	private final int ITEMBOX_XPOS = 200;
	private final int ITEMBOX_YPOS = 30;
	private final Image PANE_POINT = new Image("file:dialog/dialogue_pointer.png",24,24,false,false);
	private final Image TOP_LEFT = new Image("file:dialog/dialogue_top_left.png",32,32,false,false);
	private final Image LEFT = new Image("file:dialog/dialogue_left.png",32,32,false,false);
	private final Image RIGHT = new Image("file:dialog/dialogue_right.png",32,32,false,false);
	private final Image TOP = new Image("file:dialog/dialogue_top.png",32,32,false,false);
	private final Image BOTTOM_LEFT = new Image("file:dialog/dialogue_bottom_left.png",32,32,false,false);
	private final Image TOP_RIGHT = new Image("file:dialog/dialogue_top_right.png",32,32,false,false);
	private final Image BOTTOM_RIGHT = new Image("file:dialog/dialogue_bottom_right.png",32,32,false,false);
	private final Image CONTENT = new Image("file:dialog/dialogue_bg.png",32,32,false,false);
	private final Image BOTTOM = new Image("file:dialog/dialogue_bottom.png",32,32,false,false);
	private final int POPOUT_WIDTH = 7;
	private final int POPOUT_HEIGHT = 6;

	private ImageView arrow1 = new ImageView();
	private ImageView arrow2 = new ImageView();
	private ImageView arrow3 = new ImageView();
	private boolean UserInterfaceEntered = true;
	//private final Image  = new Image("file:dialog/dialogue_top_left.png");
	
	
	
	public static final String BALL2_IMAGE = "newNathanBall.gif";
	public static final int SIZE = 400;
	public static final Paint BACKGROUND = Color.WHITE;
	public static final int FRAMES_PER_SECOND = 8;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final int KEY_INPUT_SPEED = 5;
	public static final double GROWTH_RATE = 1.1;
	public static final int BOUNCER_SPEED = 40;
	public static final int FONTSIZE = 15;
	
	
	private Group root = new Group();
	private String TargetString;
	private Text DisplayText = new Text();
	private String DisplayStr = new String();
	private Scene myScene;
	private Rectangle myTopBlock;
	private int count = 0;
	private int tarRow = 0;
	private GridPane itemBox = new GridPane();
	private ArrayList<ImageView> arrowList = new ArrayList<ImageView>();
	

	
	
	
	
	/**
	 * Initialize what will be displayed and how it will be updated.
	 */
	@Override
	public void start (Stage s) {

		Scene scene = setupGame(SIZE, SIZE, BACKGROUND);
		s.setScene(scene);
		s.show();
		
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	
	

	// Create the game's "scene": what shapes will be in the game and their starting properties
	private Scene setupGame (int width, int height, Paint background) {
		
		myScene = new Scene(root, width, height, background);
		myTopBlock = new Rectangle(width / 2 - 25, height / 2 - 100, 50, 50);
	
		
		
		
		//set up arrowList
		
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
	
		return myScene;
	}

	
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
	/**
	 * @param target: target String
	 * @param Size: size of the text
	 * @return The Text with the certain target and certain size
	 */
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

	
	
	
	
	private void step (double elapsedTime) {
		
		
		
		

		
		
		
	

	}


	// What to do each time a key is pressed
	private void handleKeyInput (KeyCode code) {
		if  (code == KeyCode.ENTER) {
			if(UserInterfaceEntered) {
			makeGrid(root);
			setUpBasicItemGrid();
		 	UserInterfaceEntered = false;
	}
			
			
		}
		if (code == KeyCode.RIGHT) {
			
		}
		else if (code == KeyCode.LEFT) {
		
		}
		else if (code == KeyCode.UP) {
			
		}
		else if (code == KeyCode.X) {
		if (UserInterfaceEntered) {
			
		}
		}
		else if (code == KeyCode.DOWN) {
			updateItemGrid(root,tarRow);
			tarRow = (tarRow+1)%3;
			
		}
		
	}
	

	
	/**
	 * @return the specific Pokemon font
	 */
	private Font getFont(int fontSize) {
		Font f = new Font(30) ;
		try {
			f = Font.loadFont(new FileInputStream(new File("./font/font.ttf")), fontSize);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}
	
	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		launch(args);
	}
	
	
	
	
}
