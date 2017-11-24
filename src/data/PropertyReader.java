package data;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * 
 * @author cy122
 * 
 * a reader of property file
 *
 */

public class PropertyReader{

	private Properties property = new Properties();

	private class NullReader extends Properties{
		private static final long serialVersionUID = 7546893500824357729L;

		@Override
		public String getProperty(String key){
			return new String("");
		}
	}

	/**
	 * 
	 * @param relativePath
	 */
	public PropertyReader(String relativePath){
		try {
			InputStream in = getClass().getResourceAsStream(relativePath);
			property.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			System.out.print("file not found!");
			property = new NullReader();
			e.printStackTrace();
		} catch (IOException e) {
			System.out.print("file format wrong!");
			property = new NullReader();
			e.printStackTrace();
		}
	}
	
	public String getString(String key){
		return property.getProperty(key);
	}
	
	public Map<String,String> getMap(String name){
		Map<String,String> result = new HashMap<String,String>();
		String[] keys = property.getProperty(name).split(",");
		for(String key:keys){
			String value = property.getProperty(key);
			if(value!=null){
				result.put(key, value);
			}
		}
		return result;
	}
}
