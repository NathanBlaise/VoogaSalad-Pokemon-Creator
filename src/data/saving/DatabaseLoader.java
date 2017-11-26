package data.saving;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import data.Database;

public class DatabaseLoader {

	/**
	 * 
	 * @return - key is the simple name, value is the map.
	 */
	public static Map<String, String> getAvailableDatabase() {
		File f = new File("src"+File.separator+"resources"+File.separator+"databases");
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
	public static String getDatabasePath(String name) {
		// TODO Auto-generated method stub
		return "src"+File.separator+"resources"+File.separator+"databases"+File.separator+name+".xml";
	}
	

	public static Database loadDatabase(String pathName) {
		Database result; 
		File f = new File(pathName);
		if(f.exists()) { 
			XMLDecoder decoder=null;
			try {
				decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(pathName)));
			} catch (FileNotFoundException e) {
				System.out.println("ERROR: File " + pathName + " not found");
			}
			result = (Database) decoder.readObject();
			decoder.close();
			return result;
		}else{
			result = getDefaultDatabase();
			return result;
		}
	}
	
	private static Database getDefaultDatabase() {
		// TODO Auto-generated method stub
		return null;
	}
}
