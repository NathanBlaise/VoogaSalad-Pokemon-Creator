package engine.game;

import java.util.ArrayList;

import data.Database;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;

public class KeyItemBagScene extends BagScene {
	
	private final Image KEYITEM =new Image("file:inventory/keyitems.png",144,60,false,false);
	private final Image KEYITEM_BAG =new Image("file:inventory/backpack_keyitems.png",144,180,false,false);
	private ArrayList <ItemColomn> colList = new ArrayList <ItemColomn>();
	private Database dataBase;
	private int tarRow = 0;
	private int listLength = 0;
	private keyItemInterface app;
	
	
	public KeyItemBagScene(int width, int height, Paint background, UserPage userPage, keyItemInterface app) {
		super(width, height, background,userPage );
		dataBase = userPage.getDataBase();
		this.app = app;
		this.rootAdd(new ImageView(KEYITEM_BAG),0,30);
		this.rootAdd(new ImageView(KEYITEM),0,253);
		
		//TO-DO: connected to the database
		colList.add(new ItemColomn("BICYCLE","BICYCLE HELPS YOU \nRIDE FASTER",1));
		colList.add(new ItemColomn("HOT TEA","HOT TEA HELPS YOU \nGO TO NEXT LEVEL",5));
		colList.add(new ItemColomn("RETURN", "RETURN TO THE MENU"));
		
		listLength = colList.size();
		
		//Make the grid show up on the screen
		uPageArt.setUpBasicItemGrid(this.getRoot(), colList, 160, 60);
		this.rootAdd(useFont(colList.get(0).getExplanation(),20), 15, 400);
		

	}
	
	
	@Override
	public void handleKeyInput (KeyCode code) {
	
	if (code == KeyCode.UP) {
		tarRow = (tarRow+1)%listLength;
		System.out.println(tarRow);
		uPageArt.updateItemGrid(this.getRoot(), tarRow, colList);
		
		
	}

	else if (code == KeyCode.Z) {
		if(tarRow == 0) {
			if (app.isBiking() == false) {
			app.changeToBike();
			uPage.goBackToOriScene();
			}
			else{
				app.changeToWalk();
				uPage.goBackToOriScene();
			}
			
	}
		if (tarRow == listLength -1 ) {
			uPage.goBackToOriScene();
		}
	
	}
	
	else if (code == KeyCode.DOWN) {
		tarRow = (tarRow+1)%listLength;
		uPageArt.updateItemGrid(this.getRoot(), tarRow, colList);
		
		
	}
	
	else if (code == KeyCode.RIGHT) uPage.switchBagSceneRight();
	else if (code == KeyCode.LEFT)  uPage.swithBagSceneLeft();
	}
}
