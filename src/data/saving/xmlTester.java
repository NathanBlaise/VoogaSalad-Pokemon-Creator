package data.saving;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import data.model.Pokemon;
import data.model.PokemonSpecie;
import data.model.PokemonStat;
import data.model.moves.ActionExample;
import data.model.moves.Move;

public class xmlTester {

	public xmlTester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) throws IOException{

		PokemonStat pStat = new PokemonStat(1, 2, 3, 4, 5, 6);
		
		Map<Integer, PokemonStat> pStatMap = new HashMap<>();
		 
		pStatMap.put(1, pStat);
		Move move = new Move("name", "Water", 1, new ActionExample());
		Map<Integer, Move> moveMap = new HashMap<>();
		moveMap.put(1, move);
		moveMap.put(2, move);
		moveMap.put(3, move);
		moveMap.put(4, move);
		Map<Integer, Double> doubleMap = new HashMap<>();
		doubleMap.put(1, 1.0);
		Map<Integer, String> stringMap = new HashMap<>();
		stringMap.put(1, "string in map");
		
		PokemonSpecie ps = new PokemonSpecie("race", "abil", 2, moveMap, pStatMap, doubleMap, stringMap);

		
	    xmlWriter xw = new xmlWriter();
	    
	    xmlReader<Pokemon> xr = new xmlReader<Pokemon>();
		
		Pokemon pok = new Pokemon(ps, "Jake", 1);

	    xw.writeXML(pok, "xmlSerializeTest5.xml");
	    xr.readXML("xmlSerializeTest5.xml");

	}
}
