package data.map;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class DrawPane {
	
	final protected int PANE_CELL_SIZE = 48;
	
	protected GridPane myPane;
	protected GameMap myMap;
	private int  PANE_WIDTH_NUMBER;
	private int  PANE_HEIGHT_NUMBER;
	
	/**
	 * Class used to draw map (used in both DBMap and GameScene), refactoring of original code in DBMap
	 * @author nathanlewis
	 *
	 */
	
	public DrawPane(GameMap map) {
		myMap = map;
		PANE_WIDTH_NUMBER = map.getYlength();
		PANE_HEIGHT_NUMBER = map.getXlength();
		myPane = drawGridPane();
	}
	
	public GridPane getPane() {
		return myPane;
	}
	
	/*
	 * Draws the grid pane to the corrrect size
	 */
	private GridPane drawGridPane() {
		//initialize the grid pane
		GridPane pane = new GridPane();
		
		for(int i = 0; i < PANE_WIDTH_NUMBER ; i++) {
			ColumnConstraints column = new ColumnConstraints(PANE_CELL_SIZE);
			pane.getColumnConstraints().add(column);
		}

	    for(int i = 0; i < PANE_HEIGHT_NUMBER ; i++) {
	        RowConstraints row = new RowConstraints(PANE_CELL_SIZE);
	        pane.getRowConstraints().add(row);
	    }
	    
	    return pane;
	}

}
