package authoring.dragdrop;

import data.Database;
import data.map.DrawPane;
import data.map.GameMap;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Using drag board to drag and drop the each tile to the map
 * Contains two levels
 * The first level below: Pane
 * The second level above: Events
 * @author supertony cy122
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
	private Database database;
	
	
	public DBMap(Database database) {
		this.database = database;
		this.gameMap = database.getMap();
		PANE_HEIGHT_NUMBER = gameMap.getXlength();
		PANE_WIDTH_NUMBER = gameMap.getYlength();
		DrawPane drawPane = new DrawPane(database.getMap()); //Class used to draw pane (refactoring)
		myPane = drawPane.getPane();
		myPane.setStyle("-fx-grid-lines-visible: true");
		// deal with myCell
		myCell = new DBCell[PANE_HEIGHT_NUMBER][PANE_WIDTH_NUMBER];
		for (int i = 0; i < PANE_HEIGHT_NUMBER; i++) {
			for (int j = 0; j < PANE_WIDTH_NUMBER; j++) {
				myCell[i][j] = new DBCell(i,j, gameMap.getCells()[i][j], myPane, this);
				
			}
		}
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
	public boolean checkSurroundingCells(int col, int row, int width, int height) {
		int left = col - width/2;
		int up = row - height/2;
		for (int i = left; i < left + width; i++) {
			for (int j = up; j < up + height; j++) {
				if (i>=0 && j>= 0 && i < PANE_WIDTH_NUMBER && j < PANE_HEIGHT_NUMBER ) {
				
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




	@Override
	public Database getDatabase() {
		return database;
	}



	@Override
	public DBCell[][] getCellList() {
		return myCell;
		
	}

	
}
