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
public class ScrollingDialogue extends Application {

	public static final int SIZE = 400;
	public static final Paint BACKGROUND = Color.WHITE;
	public static final int FRAMES_PER_SECOND = 8;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final int KEY_INPUT_SPEED = 5;
	public static final double GROWTH_RATE = 1.1;
	public static final int BOUNCER_SPEED = 40;
	public static final int FONTSIZE = 15;
	
	

	private String TargetString;
	private Text DisplayText;
	private String DisplayStr = new String();
	private Scene myScene;
	private Rectangle myTopBlock;
	private double barLength = 200;
	private int count = 0;
	private Rectangle r = new Rectangle();

	
	
	
	
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
		
		Group root = new Group();
		myScene = new Scene(root, width, height, background);
	
		myTopBlock = new Rectangle(width / 2 - 25, height / 2 - 100, 50, 50);
	
		TargetString = new String ("I love Tony!\nWhat about you?");
		
		DisplayText = new Text(DisplayStr);
		
		DisplayText.setLayoutX(100);
		DisplayText.setLayoutY(barLength);
		root.getChildren().add(DisplayText);
		Font f = getFont();
		DisplayText.setFont(f);

		
		r.setX(50);
		r.setY(50);
		r.setWidth(200);
		r.setHeight(15);
		r.setArcWidth(10);
		r.setArcHeight(10);
		r.setFill(Color.LIGHTGREEN);
		
		root.getChildren().add(r);
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
	
		return myScene;
	}

	
	
	
	
	private void step (double elapsedTime) {
		
		
		
		
		if (count < TargetString.length()) {
			DisplayStr = DisplayStr + TargetString.substring(count,count+1) ;
			DisplayText.setText(DisplayStr);
		
		
		}
		
		
		
		
		count +=1;
		
		
		

		
		updateHealthBar();
		
		
	

	}


	private void updateHealthBar() {
		if (barLength > 20) {
		barLength = barLength - 5;
		r.setWidth(barLength);
		
		if (barLength < 50)  r.setFill(Color.RED);
		else if (barLength < 100) r.setFill(Color.ORANGE);
		}
	}

	// What to do each time a key is pressed
	private void handleKeyInput (KeyCode code) {
		if (code == KeyCode.RIGHT) {
			myTopBlock.setX(myTopBlock.getX() + KEY_INPUT_SPEED);
		}
		else if (code == KeyCode.LEFT) {
			myTopBlock.setX(myTopBlock.getX() - KEY_INPUT_SPEED);
		}
		else if (code == KeyCode.UP) {
			myTopBlock.setY(myTopBlock.getY() - KEY_INPUT_SPEED);
		}
		else if (code == KeyCode.DOWN) {
			myTopBlock.setY(myTopBlock.getY() + KEY_INPUT_SPEED);
		}
	}
	

	
	/**
	 * @return the specific Pokemon font
	 */
	private Font getFont() {
		Font f = new Font(30) ;
		try {
			f = Font.loadFont(new FileInputStream(new File("./font/font.ttf")), FONTSIZE);
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
