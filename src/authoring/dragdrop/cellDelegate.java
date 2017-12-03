package authoring.dragdrop;

import data.Database;


/**
 * The interface used to pass the GridPane to its each individual cell
 * Update the cell 
 * @author supertony
 *
 */
public interface cellDelegate {

	/**
	 * @param dbCell
	 * The method updates the cell list inside the DBMap class once editing the cell's image
	 */
	public void updateCellList(DBCell dbCell);
	
	/**
	 * @param row: x coordinate
	 * @param col: y coordinate 
	 * The method checks if the other cells are open to insert a new Image View
	 */
	
	public boolean checkSurroundingCells(int col, int row, int width, int height);
	
	
	
	/**
	 * @param row: x coordinate
	 * @param col: y coordinate
	 * The method updates the status of the surround cells (change from open to close)
	 */
	
	public void UpdateSurroundingCells(int col, int row);
	
	/**
	 * 
	 * @return - the database
	 */
	public Database getDatabase();

	/**
	 * Get the cell list from the DBMap
	 */
	public DBCell[][] getCellList();
}
