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
	private static final ArrayList<String> availableInstructions = new ArrayList<String>();
	private NPC npc;
	private static final boolean instructionAddable = true; //whether the length of instruction array list can be flexible
	
	{
		availableInstructions.add("InstructionNPCFight");
		availableInstructions.add("InstructionNPCDialogue");
	}
	
	/**
	 * set NPC in default empty value
	 * This is only used for serialization!
	 */
	@Deprecated
	public EventNPC(){
	}
	
	/**
	 * @param npc - the NPC that the npc included in that event will copy based on
	 */
	public EventNPC(NPC npc){
		super(npc.getImagePath());
		this.npc = npc;
	}

	/**
	 * 
	 * @return - a copy of the npc included in that event
	 */
	public NPC getNpc() {
		return npc;
	}

	/**
	 * 
	 * @param npc - the NPC that the npc included in that event will copy based on
	 */
	public void setNpc(NPC npc) {
		this.npc = npc;
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
