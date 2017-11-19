package data.event;

import java.util.ArrayList;

import data.model.NPC;

/**
 * 
 * @see Instruction
 * 
 * @author cy122
 * 
 * this is a instruction for talking with NPC, 
 * it holds a private ArrayList of String for dialogue,
 * and a private NPC for the npc who is talking with.
 *
 */
public class InstructionNPCDialogue implements Instruction{
	private static final long serialVersionUID = 3057407796442711712L;
	/**
	 * the 0 element of dialogues is the sentence of the NPC
	 * the 1 element of dialogues is the sentence of the Player
	 * and so on
	 * 
	 * Generally, the even element is said by NPC, the odd element is said by Player
	 */
	private ArrayList<String> dialogues;
	/**
	 * the NPC who is talking
	 */
	private NPC npc;

	
	/**
	 * WARNING!
	 * 
	 * this is only for serialization, it shouldn't be called for any intention else.
	 */
	public InstructionNPCDialogue(){
	}
	
	/**
	 * 
	 * @param dialogues - the sentence of even order is said by NPC, the odd sentence is said by Player. Start with the 0th element.
	 * @param npc - the npc who is talking
	 */
	public InstructionNPCDialogue(ArrayList<String> dialogues, NPC npc){
		this.dialogues = new ArrayList<String>(dialogues);
		this.npc = new NPC(npc);
	}
	
	/**
	 * 
	 * @return - the dialogue. the sentence of even order is said by NPC, the odd sentence is said by Player. Start with the 0th element.
	 */
	public ArrayList<String> getDialogues() {
		return new ArrayList<String>(dialogues);
	}

	/**
	 * 
	 * @param dialogues - the sentence of even order is said by NPC, the odd sentence is said by Player. Start with the 0th element.
	 */
	public void setDialogues(ArrayList<String> dialogues) {
		this.dialogues = new ArrayList<String>(dialogues);
	}

	/**
	 * 
	 * @return - a copy of the npc who is tallking
	 */
	public NPC getNpc() {
		return new NPC(npc);
	}

	/**
	 * 
	 * @param npc - the npc who is talking. The private NPC will copy based on it.
	 */
	public void setNpc(NPC npc) {
		this.npc = new NPC(npc);
	}

	@Override
	/**
	 * @see Instruction#execute
	 */
	public void execute(Object... parameters) {
		// TODO call the API in Engine to begin a Dialogue
		
	}
}
