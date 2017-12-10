package data.saving;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import data.Database;
/**
 * 
 * @author Dan Sun for commenting
 *
 */
public class DatabaseLoader {

	private final static String path = "src"+File.separator+"resources"+File.separator+"databases";
	private final static String databasePath = "src"+File.separator+"resources"+File.separator+"Databases";
	private final static String databaseTypeName = "Databases_";
	
	
	/**
	 * 
	 * @return - A map that maps game type to absolute path of folder that 
	 * contains maps for a specific game type
	 */
	public static Map<String, String> getAvailableGameTypes(){
		File f = new File(path);
		File[] matchingFiles = f.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.contains(databaseTypeName)&&(!name.contains("default"));
		    }
		});
		Map<String, String> name2path =  new HashMap<String, String>();
		for(File file: matchingFiles){
			String name = file.getName();
			int pos = name.lastIndexOf("_");
			if (pos > 0) {
			    name = name.substring(pos+1);
			}
			name2path.put(name, file.getAbsolutePath());
		}
		return name2path;
	}
	
	/**
	 * 
	 * @return - A mapping form game name to absolute path its XML file 
	 */
	public static Map<String, String> getAvailableDatabase(String path) {
		File f = new File(path);
		File[] matchingFiles = f.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.endsWith("xml");
		    }
		});
		Map<String, String> name2path =  new HashMap<String, String>();
		for(File file: matchingFiles){
			String name = file.getName();
			int pos = name.lastIndexOf(".");
			if (pos > 0) {
			    name = name.substring(0, pos);
			}
			name2path.put(name, file.getAbsolutePath());
		}
		return name2path;
	}

	/**
	 * 
	 * @param name - a simple name
	 * @return
	 */
	public static String getDatabasePath(String type, String name) {
		// TODO Auto-generated method stub
		return databasePath+File.separator+databaseTypeName+type+File.separator+name+".xml";
	}
	
	/**
	 * 
	 * @param gameType Type of the game
	 * @param pathName path of the XML file
	 * @return The database specified by the XML file if it exists,
	 * otherwise returns default database of this game
	 */
	public static Database loadDatabase(String gameType, String pathName) {
		Database result; 
		File f = new File(pathName);
		if(f.exists()) { 
			result = (Database) new xmlReader<Database>().readXML(pathName);
			return result;
		}else{
			result = getDefaultDatabase(gameType);
			return result;
		}
	}
	
	private static Database getDefaultDatabase(String type) {
		Database databaseResult = new xmlReader<Database>().readXML(databasePath+File.separator+"default"+databaseTypeName+type+".xml");
		return databaseResult;
	}
}
