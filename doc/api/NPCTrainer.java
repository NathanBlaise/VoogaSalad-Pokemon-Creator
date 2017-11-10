
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 * This API is to pass the information of Trainer's attributes from the back end to the front end
 * Also when editing it in the front end, it requires API to pass the information back to the back end/data set
 *
 */
public interface NPCTrainer {

	/**
	 * This method is called by the front end to display information of the trainer on the screen
	 * @param pass in a trainer type of object
	 * get all its info
	 */
	public Map<String,ArrayList<String>>GetTrainerIntribute(Trainer a);
	
	/**
	 * This method is called by the back end to get the new information of trainer once editing
	 * @param pass in a trainer type of object
	 * set it info
	 * Store as a map
	 * Key(String): Attribute name;
	 * Value(ArrayList): Specific items;
	 */
	public Map<String,ArrayList<String>>SetTrainerIntribute(Trainer a);
	

	
	/**
	 * This method is called to get a list which stores all the trainers
	 * @param pass in a map to get how many trainers on this map
	 * trainer arraylist stores all the trainer values
	 */
	public ArrayList<Trainer>SetTrainerIntribute(Map myMap);
	
	
}

