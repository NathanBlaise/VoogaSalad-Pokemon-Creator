import java.io.File;
import java.util.Map;

/**
 * This API represents the basic methods needed for parsing xml files in our program. 
 * The parser will be called upon to determine initial conditions of a game based
 * upon either previously saved or created games. The parser will take in the grid
 * on which the game is being played and will insert the objects onto the grid
 * according to the stored locations in the xml files. 
 *
 */
public interface xmlParser {
	/**
	 * This method will be used to actually read the file and determine the information in the
	 * file in order to then construct objects and place them on the map. This will also
	 * pass back a back-end representation of this map with the objects and their locations. 
	 * @param f
	 * @return 
	 */
	public Map<Object, int[][]> readFile(File f);
	
	/**
	 * This method will take the objects that have been specified by the xml file
	 * and will then place these objects on the game's map. 
	 * @param object
	 * @param x  x coordinate of the object
	 * @param y  y coordinate of the object
	 */
	public void addObjectToGame(Object object, int x, int y);
	
	/**
	 * This method is used to add the created objects to the backend map that
	 * can be accessed in order to determine information about the game. 
	 * @param object
	 */
	public void addObjectToBackEnd(Object object);
	
	/**
	 * This method creates and returns the various objects of the game based upon the
	 * information stored in the xml file, specifically the string specifying the name of the object. 
	 * @param object
	 * @return
	 */
	public Object createObject(String objectName);
}