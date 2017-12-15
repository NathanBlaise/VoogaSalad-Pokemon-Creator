package engine.pokemonScene;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import engine.battle.BattleScene;
import engine.battle.HealthBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class pokemonCol extends Pane{
	public final Image SELECT_BOX =new Image("file:images/selectBox.png",350,54,false,false);
	public final Image DESELECT_BOX =new Image("file:images/DeselectBox.png",350,54,false,false);
	public final Image OPEN_BALL =new Image("file:images/PokeballSelect.png",50,50,false,false);
	public final Image CLOSE_BALL = new Image("file:images/PokeballUnselect.png",50,50,false,false);
	public final int SPACE = 0;
	public final int HEALTH_BAR_LENGTH = 50 ;
	
	private ImageView myImage;
	private int level;
	private int fullHealth;
	private int curHealth;
	private String name = "";
	private BattleScene myBs;
	
	//GUI
	private VBox nameLevel;
	private ImageView pokeBall;
	private HealthBar healthBar;
	private ImageView bgBox;
	
	public pokemonCol(Image pokeImage,int le,String nam, int fullHea, int curHea) {
		myImage = new ImageView(pokeImage);
		level = le;
		fullHealth = fullHea;
		curHealth = curHea;
		name = nam;
		
		healthBar = new HealthBar(fullHea, 120,8);
		healthBar.setHealth(curHea, false);
		bgBox = new ImageView(DESELECT_BOX);
		
		// intialize pokeball status
		pokeBall = new ImageView(CLOSE_BALL);
		nameLevel = new VBox();
		setUpVerticalBox(nameLevel,SPACE);
		
		this.addEventFilter(MouseEvent.MOUSE_ENTERED, e->selectCol());
		this.addEventFilter(MouseEvent.MOUSE_EXITED, e->DeSelectCol());
		
		this.getChildren().add(bgBox);
		bgBox.setLayoutX(20);
		bgBox.setLayoutY(0);
		this.getChildren().add(nameLevel);
		nameLevel.setLayoutX(75);
		nameLevel.setLayoutY(10);
		this.getChildren().add(pokeBall);
		this.getChildren().add(myImage);
		myImage.setLayoutX(0);
		myImage.setLayoutY(0);
		this.getChildren().add(healthBar.getPane());
		healthBar.getPane().relocate(233, 19);
		
		
		
	
	}
	
	
	public pokemonCol(Image pokeImage,int le,String nam, int fullHea, int curHea, BattleScene bSce) {
		myImage = new ImageView(pokeImage);
		level = le;
		fullHealth = fullHea;
		curHealth = curHea;
		name = nam;
		myBs = bSce;
		
		healthBar = new HealthBar(fullHea, 120,8);
		healthBar.setHealth(curHea, false);
		bgBox = new ImageView(DESELECT_BOX);
		
		// intialize pokeball status
		pokeBall = new ImageView(CLOSE_BALL);
		nameLevel = new VBox();
		setUpVerticalBox(nameLevel,SPACE);
		
		this.addEventFilter(MouseEvent.MOUSE_ENTERED, e->selectCol());
		this.addEventFilter(MouseEvent.MOUSE_EXITED, e->DeSelectCol());
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, e->clickResult());
		
		this.getChildren().add(bgBox);
		bgBox.setLayoutX(20);
		bgBox.setLayoutY(0);
		this.getChildren().add(nameLevel);
		nameLevel.setLayoutX(75);
		nameLevel.setLayoutY(10);
		this.getChildren().add(pokeBall);
		this.getChildren().add(myImage);
		myImage.setLayoutX(0);
		myImage.setLayoutY(0);
		this.getChildren().add(healthBar.getPane());
		healthBar.getPane().relocate(233, 19);
		
		
		
	
	}
	
	
	
	private void clickResult() {
		myBs.changeActivePokemon(name);
		myBs.goBackToOriScene();
		
	}


	/**
	 * When selecting the colomn, change gui to notify the change
	 * @return
	 */
	private void selectCol() {
		bgBox.setImage(SELECT_BOX);
		pokeBall.setImage(OPEN_BALL);
	}

	
	/**
	 * When NOT selecting the colomn, change gui to normal
	 * @return
	 */
	private void DeSelectCol() {
		bgBox.setImage(DESELECT_BOX);
		pokeBall.setImage(CLOSE_BALL);
	}


	public void setUpVerticalBox(VBox verBox, int spacing) {
		verBox.getChildren().add(useFont(name, 15));
		verBox.getChildren().add(useFont("LV."+Integer.toString(level), 15));
		verBox.setSpacing(spacing);
		
	}
	
	
	
	
	

	/**
	 * @param target: target String
	 * @param Size: size of the text
	 * @return The Text with the certain target and certain size
	 */
	public Text useFont(String target, int Size) {
		Text ans = new Text(target);
		ans.setFont(getFont(Size));
		
		return ans;
		
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
	 * @return health Bar
	 */
	public HealthBar getHealthBar() {
		return healthBar;
	}
	
	
}
