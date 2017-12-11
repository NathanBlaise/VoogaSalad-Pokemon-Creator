package engine.game;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;


public class UserPageArt {
	
	/*final variable*/
	private final Image TOP_LEFT = new Image("file:dialog/dialogue_top_left.png",32,32,false,false);
	private final Image LEFT = new Image("file:dialog/dialogue_left.png",32,32,false,false);
	private final Image RIGHT = new Image("file:dialog/dialogue_right.png",32,32,false,false);
	private final Image TOP = new Image("file:dialog/dialogue_top.png",32,32,false,false);
	private final Image BOTTOM_LEFT = new Image("file:dialog/dialogue_bottom_left.png",32,32,false,false);
	private final Image TOP_RIGHT = new Image("file:dialog/dialogue_top_right.png",32,32,false,false);
	private final Image BOTTOM_RIGHT = new Image("file:dialog/dialogue_bottom_right.png",32,32,false,false);
	private final Image CONTENT = new Image("file:dialog/dialogue_bg.png",32,32,false,false);
	private final Image BOTTOM = new Image("file:dialog/dialogue_bottom.png",32,32,false,false);
	

	/*instance variable*/
	
	
	
	
	public UserPageArt() {
		
	}
	
	
	
	public void setUpBasicItemGrid(Pane root, ArrayList <ItemColomn> colList, int xPos, int yPos) {
		VBox itemGrid = new VBox();
		
		for (ItemColomn itemCol: colList ) {
			itemGrid.getChildren().add(itemCol);
		}
		// initialize the grid
		updateItemGrid(root,0, colList);
		
		root.getChildren().add(itemGrid);
		itemGrid.setLayoutX(xPos);
		itemGrid.setLayoutY(yPos);
		 
	}
	
	

	public void updateItemGrid(Pane root, int targetRow, ArrayList<ItemColomn>colList) {
		for (int i = 0; i < colList.size(); i++) {
			if (targetRow == i) colList.get(i).selectItemColomn();
			else  colList.get(i).deselectItemColomn();
		}
	}
	
	
	
	
	public void makeGrid(int width, int height, Pane root, int xPos, int yPos) {
		//build a grid pane
		GridPane userBox = new GridPane();
		
		for(int i = 0; i < width ; i++) {
			ColumnConstraints column = new ColumnConstraints(32);
			userBox.getColumnConstraints().add(column);
		}

	    for(int i = 0; i < height  ; i++) {
	        RowConstraints row = new RowConstraints(32);
	        userBox.getRowConstraints().add(row);
	    }
	    
	    userBox.setStyle("-fx-grid-lines-visible: false");
	    
	    for(int i = 0; i < width ; i++) 
	    	 for(int j = 0; j < height ; j++)
	    		 if (i == 0 && j == 0)  userBox.add(new ImageView(TOP_LEFT), 0, 0);
	    		 else if (i == 0 && j == height -1 ) userBox.add(new ImageView(BOTTOM_LEFT), i, j);
	    		 else if (i == width -1 && j == height -1 ) userBox.add(new ImageView(BOTTOM_RIGHT), i, j);
	    		 else if (i == width -1  && j == 0 ) userBox.add(new ImageView(TOP_RIGHT), i, j);
	    		 else if (i == 0)  userBox.add(new ImageView(LEFT), i, j);
	    		 else if (i == width -1 ) userBox.add(new ImageView(RIGHT), i, j);
	    		 else if (j == 0)  userBox.add(new ImageView(TOP), i, j);
	    		 else if (j == height -1 ) userBox.add(new ImageView(BOTTOM), i, j);
	    		 else userBox.add(new ImageView(CONTENT), i, j);
	    			
	    
		root.getChildren().add(userBox);
		
		userBox.setLayoutX(xPos);
		userBox.setLayoutY(yPos);
	}
	
	
	
}
