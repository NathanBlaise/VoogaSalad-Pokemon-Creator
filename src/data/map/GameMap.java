package data.map;

import java.io.Serializable;

/**
 * Map is responsible for showing all the information needed to show a map on the screen,
 * and also include the info to let the Player interact with the map
 * @author cy122
 *
 */
public class GameMap implements Serializable{

	private static final long serialVersionUID = -2420508422654879735L;
	private int Xlength, Ylength; //Cell[0~(Xlength-1)][0~(Ylength-1)] is the legal Cell.
	private Cell[][] cells;
	
	// this constructor is only for serialization, it shouldn't be used for any intention else
	public GameMap(){
		
	}
	
	/**
	 * Cell[0~(Xlength-1)][0~(Ylength-1)] is the legal Cell.
	 * @param Xlength - the length in the first dimension
	 * @param Ylength - the length in the second dimension.
	 */
	public GameMap(int Xlength, int Ylength){
		this.Xlength = Xlength;
		this.Ylength = Ylength;
		cells = new Cell[Xlength][Ylength];
	}
	
	/**
	 * Cell[0~(Xlength-1)][0~(Ylength-1)] is the legal Cell.
	 * @param cells - the whole map
	 */
	public GameMap(Cell[][] cells){
		this.cells = cells;
		Xlength = cells.length;
		Ylength = cells[0].length;
	}
	
	/**
	 *  Cell[0~(Xlength-1)][0~(Ylength-1)] is the legal Cell.
	 * @return - the length in the first dimension.
	 */
	public int getXlength() {
		return Xlength;
	}
	
	/**
	 *  Cell[0~(Xlength-1)][0~(Ylength-1)] is the legal Cell.
	 * @param xlength - the length in the first dimension.
	 */
	public void setXlength(int xlength) {
		Xlength = xlength;
	}
	
	/**
	 * Cell[0~(Xlength-1)][0~(Ylength-1)] is the legal Cell.
	 * @return - the length in the second dimension.
	 */
	public int getYlength() {
		return Ylength;
	}
	
	/**
	 * Cell[0~(Xlength-1)][0~(Ylength-1)] is the legal Cell.
	 * @param ylength - the length in the second dimension.
	 */
	public void setYlength(int ylength) {
		Ylength = ylength;
	}
	
	/**
	 * Cell[0~(Xlength-1)][0~(Ylength-1)] is the legal Cell.
	 * @return - the whole map. 
	 * The length of the first dimension is getXLength(), the length of the second dimension is getYLength(), 
	 */
	public Cell[][] getCells() {
		return cells;
	}
	
	/**
	 * 
	 * @param the whole map
	 */
	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}
	
	/**
	 * set the cell in Cell[coordX][coordY]
	 * @param coordX - the index in the first dimension
	 * @param coordY - the index in the second dimension
	 * @param cell - the new cell
	 */
	public void setCell(int coordX, int coordY, Cell cell){
		try {
			cells[coordX][coordY] = cell;
		} catch (Exception e) {
			System.out.print("Dimension over range!");
			e.printStackTrace();
		}
	}
	
	/**
	 * set the cell in Cell[coordX][coordY]
	 * @param coordX - the index in the first dimension
	 * @param coordY - the index in the second dimension
	 * @param cell - the new cell
	 */
	public Cell getCell(int coordX, int coordY){
		try {
			return cells[coordX][coordY];
		} catch (Exception e) {
			System.out.print("Dimension over range!");
			e.printStackTrace();
			return null;
		}
	}
	
}
