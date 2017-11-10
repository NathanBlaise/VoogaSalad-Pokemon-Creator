import java.io.File;
import java.util.Map;

/**
 * This API is responsible for specifying the methods needed in order to take game data
 * or data from the game creator and turning this information into an xml file that 
 * will eventually be read into the game by the xml parser. The idea of this class is to
 * be able to store enough information that the game's initial environment can be setup
 * using one file.  
 *
 */
public interface xmlWriter {

	/**
	 * This class is the main class that actually creates and writes things to a file.
	 * With the help of the below helper methods, this class organizes and determines
	 * the information needed for the xml file. 
	 * @param f
	 * @param map
	 */
	public void writeXML(File f, Map<Object, int[][]> map);
	
	/**
	 * This helper method is responsible for taking in the information about an 
	 * object--the name and location--and writing this specific object to the file. 
	 * This will be called for each object stored in the file. 
	 * @param object
	 * @param x
	 * @param y
	 */
	public void addObject(String objectName, int x, int y);
	
	/**
	 * This method is used to determine the name under which an object should be stored, 
	 * which is determined by looking at the class name and information stored in the object.
	 * @param object
	 */
	public void determineObjectType(Object object);
	
}
