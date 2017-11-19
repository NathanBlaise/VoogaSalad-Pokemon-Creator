package data.map;

import java.io.Serializable;

public class Map implements Serializable{

	private static final long serialVersionUID = -2420508422654879735L;
	private int Xlength, Ylength;
	private Cell[][] cells;
	
	public Map(){
		
	}
	
	public int getXlength() {
		return Xlength;
	}
	public void setXlength(int xlength) {
		Xlength = xlength;
	}
	public int getYlength() {
		return Ylength;
	}
	public void setYlength(int ylength) {
		Ylength = ylength;
	}
	public Cell[][] getCells() {
		return cells;
	}
	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}
	
	
}
