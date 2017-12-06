package data.event;

import java.util.Collection;

import data.map.GameMap;
import data.player.Player;
import engine.game.GameScene;

public class InstructionGoMap extends Instruction{
	
	private static final long serialVersionUID = 3425297418577947868L; //for serialization
	private String mapName = "";
	
	/**
	 * just for serialization
	 */
	@Deprecated
	public InstructionGoMap(){
		mapName = new String("");
	}
	
	public InstructionGoMap(String mapName){
		this.mapName = mapName;
	}

	@Override
	public void execute(int SCREEN_WIDTH, int SCREEN_HEIGHT, Player mainPlayer, GameMap mainMap, Event event, GameScene gameScene) {
		Collection<GameMap> gameMaps = gameScene.getDatabase().getMaps();
		for(GameMap map: gameMaps){
			if(map.getName().equals(mapName)){
				gameScene.refreshMap(SCREEN_WIDTH, SCREEN_HEIGHT, mainMap);
				break;
			}
		}
		
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

}
