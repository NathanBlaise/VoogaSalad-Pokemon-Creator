package data.database;

import java.io.Serializable;

public class Pokemon implements Serializable{
	private double HP, MP;
	private double currentLV;
	private double maxLV;
	private double levelExperience; //the experience for each level
	private double currentExperience; //the current experience got
	private double maxHP,maxMP; // the HP and MP when the Pokemon's in max LV
//	private String ability;
	
	//Moves of pokemon, no idea how it looks like for now
	private Move[] moves;
	private Move[] currentMoves;
}
