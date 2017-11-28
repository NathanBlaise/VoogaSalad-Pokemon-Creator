package data.saving;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class xmlReader {

	public xmlReader() {
		//empty for now, not sure if this will change
	}
	
	public Object readXML(String file) {
		
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File " + file + " not found");
		}
		return decoder.readObject();
		//this should return the exact object passed to the xml file to be stored in the first place
	}

}
