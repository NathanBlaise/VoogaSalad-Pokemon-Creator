package data.event;

import java.io.Serializable;

import data.database.Pokemon;

public class BattleVSPokemon extends Instruction implements Serializable{

	private Pokemon pokemon;


	@Override
	public void execute(Object... parameters) {
		// TODO call the API in Engine to begin a battle with pokemon
		
	}

}
