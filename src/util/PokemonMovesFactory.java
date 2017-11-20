package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import data.model.moves.Move;


/**
 * This class gets the move class for pokemon
 * when a String corresponding to the move of the name 
 * is given
 * @author Dan Sun
 *
 */
public class PokemonMovesFactory {
    //TODO: complete a file that matches the name of each file 
    // to the complete class name of the move, to be used 
    // for reflection
    
    private String pokemonMoveLookupPath;
    
    public PokemonMovesFactory(String path) {
	pokemonMoveLookupPath = path;
    }
    /**
     * Gets the move specified by its name
     * @param moveName the name of the move
     * @return The move object corresponding to the name
     */
    public Move getMove(String moveName) throws ClassNotFoundException, 
    NoSuchMethodException, SecurityException, 
    InstantiationException, IllegalAccessException, 
    IllegalArgumentException, InvocationTargetException {
	String className = getMoveClassName(moveName);
	Class<?> c = Class.forName(className);
	Constructor<?> cons = c.getConstructor();
	Object object = cons.newInstance(new Object[] {}); //TODO:add list of paramters
	return (Move)object;
    }

    private String getMoveClassName(String moveName) {
	// TODO: get class name from a file
	return "";
    }
    
}
