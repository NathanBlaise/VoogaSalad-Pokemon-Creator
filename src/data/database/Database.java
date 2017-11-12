package data.database;

import java.io.Serializable;
import java.util.ArrayList;

// database holds the data which is unrelated with the actual movement/action of screen.

public class Database implements Serializable{
	private ArrayList<NPC> NPCs= new ArrayList<NPC>();
	private ArrayList<Pokemon> Pokemons= new ArrayList<Pokemon>();
}
