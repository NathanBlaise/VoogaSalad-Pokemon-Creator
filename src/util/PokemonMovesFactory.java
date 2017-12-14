package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import data.model.moves.Move;

/**
 * This class gets the move class for pokemon
 * when a String corresponding to the move of the name 
 * is given
 * @author Dan Sun
 *
 */
public class PokemonMovesFactory {

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
	String className = "";
	try {
	    className = getMoveClassName(moveName);
	} catch (IOException e) {
	    System.out.println("Cannot get the class name of the move " +
		    moveName);
	    e.printStackTrace(); //handled by exiting the program
	    System.exit(1);
	}
	Class<?> c = null; 
	try {
	    c = Class.forName(className);
	} catch(ClassNotFoundException e) {
	    System.out.println("Cannot find class for move " + moveName);
	    System.out.println("Attempted " + className);
	    e.printStackTrace(); //handled by exiting the program
	    System.exit(1);
	}
	Constructor<?> cons = c.getConstructor();
	Object object = cons.newInstance(new Object[] {});
	return (Move)object;
    }

    //adopted from https://www.mkyong.com/java/java-properties-file-examples/
    /**
     * Looks into the file to find the class name that matches the move name given
     * @param moveName the name of the move specified
     * @return the class name of that name used by Java
     * @throws IOException 
     */
    private String getMoveClassName(String moveName) throws IOException {
	//read properties file
	Properties moveName2ClassName = new Properties();
	InputStream input = null;
	input = new FileInputStream(pokemonMoveLookupPath);
	// load a properties file
	moveName2ClassName.load(input);
	return moveName2ClassName.getProperty(moveName);

    }

}
