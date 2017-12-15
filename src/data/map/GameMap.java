package data.map;

import java.io.Serializable;

import data.event.Event;
import data.event.EventPokemon;
import engine.UI.Path2Image;
import javafx.scene.image.ImageView;

/**
 * Map is responsible for showing all the information needed to show a map on the screen,
 * and also include the info to let the Player interact with the map
 * @author cy122
 * @author Dan Sun for additional commenting
 *
 */
public class GameMap implements Serializable{

	private static final long serialVersionUID = -2420508422654879735L;
	private int Xlength, Ylength; //Cell[0~(Xlength-1)][0~(Ylength-1)] is the legal Cell.
	private Cell[][] cells;
	private String name = ""; //the name of map
	
	// this constructor is only for serialization, it shouldn't be used for any intention else
	public GameMap(){
	    //do nothing
	}
	
	/**
	 * 
	 * @param name The name of the map.
	 * @param Xlength The length in the first dimension
	 * @param Ylength The length in the second dimension.
	 */
	public GameMap(String name, int Xlength, int Ylength){
	    	// Cell[0~(Xlength-1)][0~(Ylength-1)] is the legal Cell
		this.name = name;
		this.Xlength = Xlength;
		this.Ylength = Ylength;
		cells = new Cell[Xlength][Ylength];
	}
	
	/**
	 * Cell[0~(Xlength-1)][0~(Ylength-1)] is the legal Cell.
	 * @param cells - the whole map
	 */
	public GameMap(String name, Cell[][] cells){
		this.name = name;
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
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
			e.printStackTrace();//handled by exiting the program
			System.exit(1);
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
//			e.printStackTrace();//handled by exiting the program
			System.exit(1);
			return null;
		}
	}
	
	/**
	 * removes an exisiting event from the map
	 * @param event The event to be removed
	 */
	public void removeEvent(Event event) {
	    for (int i = 0; i < getXlength(); i++) {
		for (int j = 0; j < getYlength(); j++) {
			if(getCells()[i][j].getEvent()==event){
			    getCells()[i][j].setEvent(null);
			    return;
			}
		}
	}
	}
	
}
