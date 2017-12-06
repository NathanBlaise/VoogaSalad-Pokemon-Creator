package data;

import java.io.Serializable;
import java.util.ArrayList;

import data.map.GameMap;
import data.model.Model;
import data.player.Player;

// workspace is the exact class which will be stored into XML file using serialization
// every time a new game is created to be edited, the Authoring should create a new Workspace class,
// and set all the necessary values in it to be default values, when user wants to save, 
// then the related Workspace Object should be serialized and stored.

public class Database implements Serializable{
	private static final long serialVersionUID = -7248010162820532176L;
	// because image in JavaFX cannot be serialized, so it's preferred to use String Array to store maps, 
	// where the String presents as the absolute path of the image
	private ArrayList<GameMap> maps = new ArrayList<GameMap>();
	private Model model;
	private Player player;
	
	/**
	 * only used for serialization
	 */
	@Deprecated
	public Database(){
		
	}
	
	public Database(ArrayList<GameMap> maps, Model model, Player player) {
		this.maps = maps;
		this.model = model;
		this.player = player;
	}
	
	public ArrayList<GameMap> getMaps() {
		return maps;
	}

	public void setMaps(ArrayList<GameMap> maps) {
		this.maps = maps;
	}

	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public GameMap getMap(){
		return maps.get(0);
	}
	
}
