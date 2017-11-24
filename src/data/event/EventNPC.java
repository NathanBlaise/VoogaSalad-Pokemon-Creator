package data.event;

import java.util.ArrayList;

import data.model.NPC;

/**
 * 
 * Event NPC holds an npc and its available instructions are InstructionNPCFight, InstructionNPCDialogue for now.
 * @see Event
 * @author cy122
 * 
 *
 */

public class EventNPC extends Event{
	private static final long serialVersionUID = -904353089393505949L;
	private static final ArrayList<String> availableInstructions = new ArrayList<String>() {
		private static final long serialVersionUID = 4770817977589641060L;

		{
			add("InstructionNPCFight");
			add("InstructionNPCDialogue");
	    }
	};
	private NPC npc;
	private static final boolean instructionAddable = true; //whether the length of instruction array list can be flexible
	
	/**
	 * set NPC in default empty value
	 * This is only used for serialization!
	 */
	@Deprecated
	public EventNPC(){
		npc = new NPC();
	}
	
	/**
	 * @param npc - the NPC that the npc included in that event will copy based on
	 */
	public EventNPC(NPC npc){
		super(npc.getImagePath());
		this.npc = new NPC(npc);
	}

	/**
	 * 
	 * @return - a copy of the npc included in that event
	 */
	public NPC getNpc() {
		return new NPC(npc);
	}

	/**
	 * 
	 * @param npc - the NPC that the npc included in that event will copy based on
	 */
	public void setNpc(NPC npc) {
		this.npc = new NPC(npc);
	}

	@Override
	/**
	 * @see Event#getAvailableInstructions()
	 */
	public ArrayList<String> getAvailableInstructions() {
		return new ArrayList<String>(availableInstructions);
	}

	@Override
	public boolean instructionAddable() {
		return instructionAddable;
	}
}
