package authoring.dragdrop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import authoring.BasicAuthorScreen;
import authoring.StageDelegate;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This class is to build a class extending basic author screen to make a map for the pokemon game
 * Allow users to drag tiles from a list and put them on the map
 * @author supertony
 *
 */
public class EditMapScene extends BasicAuthorScreen {

	private TileMenu tMenu;
	private DBMap myMap;
	public EditMapScene(Paint background, StageDelegate stageHelper) {
		super(background, stageHelper);
		
		
		
		// set up the t-menu
		tMenu = new TileMenu();
		this.rootAdd(tMenu);
		myMap = new DBMap();
		
	 
	    
		
		//testFont.setLayoutX(700);
		//testFont.setLayoutY(300);
		this.rootAdd(myMap.getGrid(),200,0);
		
		
		/*For The Pokemon Font*/
		/* Font f = new Font(30) ;
		try {
			f = Font.loadFont(new FileInputStream(new File("./src/resources/pkmnem.ttf")), 30);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	/*	Label testFont = new Label("POKEMON");
		//testFont.setFont(f); // use this font with our label
		this.rootAdd(testFont);
	*/
	
	}
	
	
	/**
	 * The public method to pass the map to next scene
	 * @return myMap
	 */
	public DBMap passMyMap() {
		DBMap myMapCopy = myMap;
		return myMapCopy;
	}
	
	
	/**
	 * @param map: pass in the map from the scene before
	 * 
	 */
	public void setMyMap(DBMap map) {
		myMap = map;
		this.rootRemove(myMap.getGrid());
		this.rootAdd(myMap.getGrid(),200,0);
	}
	

}
