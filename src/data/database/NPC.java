package data.database;

import java.io.Serializable;

// the same meaning to trainer in Pokemon, the NPC don't need to stay in the Map:
// they are just a part of database, 
// and waiting for the Event to put NPC in the Instructions such as beginning a dialogue, or a battle

public class NPC implements Serializable{
	private Pokemon[] pokemons;
}
