package data.event;

import java.util.ArrayList;

import data.model.NPC;

public class EventNPC extends Event{
	private static final long serialVersionUID = -904353089393505949L;

	NPC npc;
	
	public EventNPC(){
		setAvailableInstructions();
		npc = new NPC();
	}
	
	public EventNPC(NPC npc){
		setAvailableInstructions();
		this.npc = new NPC(npc);
	}
	
	private void setAvailableInstructions(){
		ArrayList<String> result = new ArrayList<String>();
		result.add("BeginDialogueInstruction");
		result.add("BeginNPCBattleInstruction");
		super.setAvailableInstructions(result);
	}

	public NPC getNpc() {
		return new NPC(npc);
	}

	public void setNpc(NPC npc) {
		this.npc = new NPC(npc);
	}
}
