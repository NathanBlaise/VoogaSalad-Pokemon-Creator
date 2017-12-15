package data.event;

import java.util.ArrayList;

public class EventDIY extends Event{
	
	private static final long serialVersionUID = 7911921493930889760L;
	private static final ArrayList<String> availableInstructions = new ArrayList<String>();
	
	{
		availableInstructions.add("InstructionGoMap");
		availableInstructions.add("InstructionGoShop");
		availableInstructions.add("InstructionVictory");
		availableInstructions.add("InstructionPokemonFight");
		availableInstructions.add("InstructionCheckKeyItem");
		availableInstructions.add("InstructionGiveKeyItem");
		availableInstructions.add("InstructionNPCDialogue");
	}
	
	/**
	 * for serialization
	 */
	@Deprecated
	public EventDIY(){
		
	}
	
	public EventDIY(String pathName){
		super(pathName);
	}
	
	@Override
	public ArrayList<String> getAvailableInstructions() {
		return availableInstructions;
	}

	@Override
	public boolean instructionAddable() {
		return true;
	}

}
