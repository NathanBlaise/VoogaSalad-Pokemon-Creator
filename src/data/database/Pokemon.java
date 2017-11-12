package data.database;

import java.io.Serializable;

public class Pokemon implements Serializable{
	private double HP, MP;
	private double currentLV;
	private double maxLV;
	private double maxHP,maxMP; // the HP and MP when it's in max LV
	
	//Skills of pokemon, no idea how it looks like for now
	private Skill skills;
}
