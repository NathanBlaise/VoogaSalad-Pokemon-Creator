package data.saving;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import data.model.Pokemon;
import data.model.PokemonSpecie;
import data.model.PokemonStat;
import data.model.moves.Action;
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

		Pokemon pok = new Pokemon(ps, "Jake", 1);
		//System.out.println(pok.getMoves()[3]);
		
		
		/*
	    FileOutputStream fos = new FileOutputStream("xmlSerializeTest1.xml");
	    XMLEncoder encoder = new XMLEncoder(fos);
	    encoder.setExceptionListener(new ExceptionListener() {
	            public void exceptionThrown(Exception e) {
	                System.out.println("Exception! :"+e.toString());
	            }
	    });
	    encoder.writeObject(doubleMap);
	    encoder.close();
	    fos.close();
	    */
		
		
		
	    
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("xmlSerializeTest4.xml")));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File");
		}
		encoder.writeObject(pok);
		encoder.close();
		
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("xmlSerializeTest4.xml")));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File " + "xmlSerializeTest4.xml" + " not found");
		}
		Pokemon pokemon = (Pokemon) decoder.readObject();
		System.out.println(pokemon);
		System.out.println(pokemon.getElemental());
	}
}
