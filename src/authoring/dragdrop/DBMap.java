package authoring.dragdrop;

import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * Using drag board to drag and drop the each tile to the map
 * Contains two levels
 * The first level below: Pane
 * The second level above: Events
 * @author supertony
 *
 */
public class DBMap {
	/*final variable*/
	final private static int   PANE_WIDTH_NUMBER = 10;
	final private static int  PANE_HEIGHT_NUMBER = 15;
	final private static int PANE_CELL_SIZE = 48;
	/*instance variable*/
	private GridPane myPane;
	private DBCell [][] myCell;
	
	public DBMap() {
		// deal with Grid Pane
		drawGridPane();
		
		// deal with myCell
		myCell = new DBCell[PANE_HEIGHT_NUMBER][PANE_WIDTH_NUMBER];
		for (int i = 0; i < PANE_HEIGHT_NUMBER; i++) {
			for (int j = 0; j < PANE_WIDTH_NUMBER; j++) {
				myCell[i][j] = new DBCell(i,j,myPane);
				myCell[i][j].UpdateDBCell(myPane);
			}
		}
		
		// deal with my Event
		
		
	}

	
	
	
	/*the method to draw the grid pane*/
	private void drawGridPane() {
		//initialize the grid pane
		myPane = new GridPane();
		
		for(int i = 0; i < PANE_HEIGHT_NUMBER ; i++) {
			ColumnConstraints column = new ColumnConstraints(PANE_CELL_SIZE);
			myPane.getColumnConstraints().add(column);
		}

	    for(int i = 0; i < PANE_WIDTH_NUMBER ; i++) {
	        RowConstraints row = new RowConstraints(PANE_CELL_SIZE);
	        myPane.getRowConstraints().add(row);
	    }
	    
	    myPane.setStyle("-fx-grid-lines-visible: true");
	}
	
	/*the method to access the grid pane*/
	public GridPane getGrid() {
		return myPane;
	}


}
