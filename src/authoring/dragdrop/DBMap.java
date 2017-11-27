package authoring.dragdrop;

import data.map.GameMap;
import javafx.scene.image.ImageView;
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
public class DBMap implements cellDelegate {
	/*final variable*/
	private int   PANE_WIDTH_NUMBER;
	private int  PANE_HEIGHT_NUMBER;
	final private static int PANE_CELL_SIZE = 48;
	/*instance variable*/
	private GridPane myPane;
	private DBCell [][] myCell;	
	private GameMap gameMap;
	
	
	public DBMap(GameMap map) {
		this.gameMap = map;
		PANE_HEIGHT_NUMBER = map.getXlength();
		PANE_WIDTH_NUMBER = map.getYlength();
		drawGridPane();
		
		// deal with myCell
		myCell = new DBCell[PANE_HEIGHT_NUMBER][PANE_WIDTH_NUMBER];
		for (int i = 0; i < PANE_HEIGHT_NUMBER; i++) {
			for (int j = 0; j < PANE_WIDTH_NUMBER; j++) {
				myCell[i][j] = new DBCell(i,j, map.getCells()[i][j], myPane, this);
				
			}
		}
	}




	/*the method to draw the grid pane*/
	private void drawGridPane() {
		//initialize the grid pane
		myPane = new GridPane();
		
		for(int i = 0; i < PANE_WIDTH_NUMBER ; i++) {
			ColumnConstraints column = new ColumnConstraints(PANE_CELL_SIZE);
			myPane.getColumnConstraints().add(column);
		}

	    for(int i = 0; i < PANE_HEIGHT_NUMBER ; i++) {
	        RowConstraints row = new RowConstraints(PANE_CELL_SIZE);
	        myPane.getRowConstraints().add(row);
	    }
	    
	    myPane.setStyle("-fx-grid-lines-visible: true");
	}
	
	/*the method to access the grid pane*/
	public GridPane getGrid() {
		return myPane;
	}




	
	@Override
	public void updateCellList(DBCell dbCell) {
		//update my Cell
		myCell[dbCell.getCellRow()][dbCell.getCellCol()] = dbCell;
		gameMap.setCell(dbCell.getCellRow(), dbCell.getCellCol(), dbCell.getCell());
	}




	@Override
	public boolean checkSurroundingCells(int col, int row) {
		
			/*myGrid.add(new ImageView(db.getImage()), row-1,col);
	    	myGrid.add(new ImageView(), row-1, col+1);
	    	myGrid.add(new ImageView(), row-1, col-1);
	    	myGrid.add(new ImageView(), row, col-1);
	    	myGrid.add(new ImageView(), row, col+1);
	    	myGrid.add(new ImageView(), row, col);
	    	myGrid.add(new ImageView(), row-2, col+1);
	    	myGrid.add(new ImageView(), row-2, col-1);
	    	myGrid.add(new ImageView(), row-2, col);*/
		
		for (int i = col-1; i <= col+1; i++) {
			for (int j = row-1; j <= row+1; j++) {
				if (i>=0 && j>= 0 && i < PANE_WIDTH_NUMBER && j < PANE_HEIGHT_NUMBER ) {
					System.out.println("col: " + col +" row: " + row);
					System.out.println("x cor: " + i +" y cor: " + j);
					if (myCell[j][i].getState() == false) {
						System.out.println("x cor: " + i +" y cor: " + j + " openState: "+ myCell[j][i].getState());
						return false;
					}
					
				}
				else {
					return false;
				}
			}
		}
		
		return true;
		
	}




	@Override
	public void UpdateSurroundingCells(int col,int row) {
		
		for (int i = col-1; i <= col+1; i++) {
			for (int j = row-1; j <= row+1; j++) {
				if (i>=0 && j>= 0 && !(i == col && j == row) && i < PANE_HEIGHT_NUMBER && j < PANE_WIDTH_NUMBER) {
					myCell[j][i].setState(false);
					myPane.add(new ImageView(), j, i);
					
				}
			}
		}
		
	}

	
}
