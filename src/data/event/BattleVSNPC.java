package data.event;

import java.io.Serializable;

import data.model.NPC;

public class BattleVSNPC  extends Instruction implements Serializable{

	private NPC npc;

	@Override
	public void execute(Object... parameters) {
		// TODO call the API in Engine to begin a battle with npc
		
	}

}
