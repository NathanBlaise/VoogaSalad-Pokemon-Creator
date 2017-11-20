package data.map;

import java.io.Serializable;

import data.event.Event;

/**
 * The Cell is the unit that forms that forms a map.
 * It includes the event (Event), the tilePath (String), the openState (boolean), and the obstacle(boolean)
 * if the obstacle is true, the user cannot go through the tile
 * 
 * @author cy122
 *
 */
public class Cell implements Serializable{

	private static final long serialVersionUID = -2980246764307592878L;
	private String tilePath; /* the path of stile*/
	private boolean openState; 
	private boolean obstacle; /* if the obstacle is true, the user cannot go through the tile  */
	/** the event included in the cell. 
	 * @link data.event.Event
	 */
	private Event event;
	
	/**
	 * this is only used for serialization, it should not be used for any other intention
	 */
	public Cell(){
		
	}
	
	/**
	 * 
	 * @param tilePaths - in case that both flower and default tile are all tiles, but the flower tile overlaps the default tiles, tilePaths should be an array presents as: (default tile imagepath, flower tile imagepath).
	 * @param openState - 
	 * @param obstacle - if the obstacle is true, the user cannot go through the tile
	 * @param event - the event included in the cell.
	 */
	public Cell(String tilePath, boolean openState, boolean obstacle, Event event){
		this.tilePath = tilePath;
		this.openState = openState;
		this.obstacle = obstacle;
		this.event = event;
	}

	/**
	 * 
	 * @return - an array presents the bottom
	 */
	public String getTilePath() {
		return new String(tilePath);
	}

	/**
	 * 
	 * @param tilePaths
	 */
	public void setTilePaths(String tilePath) {
		this.tilePath = new String(tilePath);
	}

	public boolean isOpenState() {
		return openState;
	}

	public void setOpenState(boolean openState) {
		this.openState = openState;
	}

	/**
	 * 
	 * @return - if the return value is true, the user cannot go through the tile
	 */
	public boolean isObstacle() {
		return obstacle;
	}

	/**
	 * 
	 * @param obstacle - if the obstacle is true, the user cannot go through the tile
	 */
	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}

	/**
	 * 
	 * @return - the event included in the cell.
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * 
	 * @param event - the event included in the cell.
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
	
}
