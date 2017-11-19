package data.map;

import java.io.Serializable;
import java.util.ArrayList;

import data.event.Event;

public class Cell implements Serializable{

	private static final long serialVersionUID = -2980246764307592878L;
	ArrayList<String> tileImage; // in case that both flower and default tile are all tiles, but the flower tile overlaps the default tiles,
								 // tileImage should be (default tile path, flower tile path).
	boolean openState;
	Event event;
}
