package engine.movement;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import data.event.Event;
import data.map.GameMap;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 * Handles collisions between the player and the environment
 * @author nathanlewis cy122
 *
 */

public class Collisions {
	
	/**
	 * check whether the player has collided with other obstacles and events
	 * @param posX - the x coordinate of the left-up point of player's detection block
	 * @param posY - the y coordinate of the left-up point of player's detection block
	 * @param blockWidth - the width of detection block of player
	 * @param blockHeight - the height of detection block of player
	 * @return true if the player has collided with other nodes
	 */
	public static boolean checkCollision(double posX, double posY, double blockWidth, double blockHeight, GridPane mapPane) {
		for (Node node: mapPane.getChildren() ) {
			if (GridPane.getRowIndex(node)!= null && GridPane.getColumnIndex(node)!= null){
				if(node.getBoundsInParent().intersects(posX, posY, blockWidth, blockHeight)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * search the event that the player collides with
	 * @param posX - the x coordinate of the left-up point of player's detection block
	 * @param posY - the y coordinate of the left-up point of player's detection block
	 * @param blockWidth - the width of detection block of player
	 * @param blockHeight - the height of detection block of player
	 * @param direction - an array of the offset of future position
	 * @return
	 */
	public static Event searchEvent(Pair<Integer, Integer> playerFuturePos, ArrayList<Pair<Integer, Integer>> direction, Map<Pair<Integer, Integer>, Event> collideEvents) {
		for(Pair<Integer, Integer> offset : direction){
				Pair<Integer, Integer> tempIndex = new Pair<Integer, Integer>(playerFuturePos.getKey()+offset.getKey(), playerFuturePos.getValue()+offset.getValue());
				if(collideEvents.keySet().contains(tempIndex)){
					Event tempEvent = collideEvents.get(tempIndex);
					return tempEvent;
				}
		}
		return null;
	}
	
	/**
	 * 
	 * @param posX - the x coordinate of the left-up point of player's detection block
	 * @param posY - the y coordinate of the left-up point of player's detection block
	 * @param blockWidth - the width of detection block of player
	 * @param blockHeight - the height of detection block of player
	 * @return a map from the colliding event's coordinates to the event
	 */
	public static Map<Pair<Integer, Integer>, Event> getCollideEvents(double posX, double posY, double blockWidth, double blockHeight, GridPane mapPane, GameMap mainMap){
		Map<Pair<Integer, Integer>, Event> result = new HashMap<Pair<Integer, Integer>, Event>();
		for (Node node: mapPane.getChildren() ) {
			if (GridPane.getRowIndex(node)!= null && GridPane.getColumnIndex(node)!= null){
				int i = GridPane.getRowIndex(node);
				int j = GridPane.getColumnIndex(node);
				if(node.getBoundsInParent().intersects(posX, posY, blockWidth, blockHeight) && mainMap.getCell(i, j).getEvent()!=null) {
					result.put(new Pair<Integer, Integer>(i, j), mainMap.getCell(i, j).getEvent());
				}
			}
		}
		return result;
	}
	
	
}
