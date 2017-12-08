package data.event;

import java.util.Collection;

import data.map.GameMap;
import data.player.Player;
import engine.game.GameScene;

public class InstructionGoMap extends Instruction{
	
	private static final long serialVersionUID = 3425297418577947868L; //for serialization
	private String mapName = "";
	private int futureX, futureY;
	
	/**
	 * just for serialization
	 */
	@Deprecated
	public InstructionGoMap(){
		mapName = new String("");
		futureX = 0;
		futureY = 0;
	}
	
	public InstructionGoMap(String mapName, int futureX, int futureY){
		this.mapName = mapName;
		this.futureX = futureX;
		this.futureY = futureY;
	}

	@Override
	public void execute(int SCREEN_WIDTH, int SCREEN_HEIGHT, Player mainPlayer, GameMap mainMap, Event event, GameScene gameScene) {
		Collection<GameMap> gameMaps = gameScene.getDatabase().getMaps();
		for(GameMap map: gameMaps){
			if(map.getName().equals(mapName)){
				mainPlayer.setPosX(futureX);
				mainPlayer.setPosY(futureY);
				gameScene.refreshMap(futureX, futureY, map);
				break;
			}
		}
		super.setGoNextInstruction(true);
		gameScene.changeBackScene();
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public int getFutureX() {
		return futureX;
	}

	public void setFutureX(int futureX) {
		this.futureX = futureX;
	}

	public int getFutureY() {
		return futureY;
	}

	public void setFutureY(int futureY) {
		this.futureY = futureY;
	}
	
	

}
