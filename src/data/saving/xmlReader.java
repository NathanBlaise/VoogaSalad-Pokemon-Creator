package data.saving;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class xmlReader {

	public xmlReader() {
		// TODO Auto-generated constructor stub
	}
	
	public void readXML(String file) {
		
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File " + file + " not found");
		}
		Object obj = (Object) decoder.readObject();
		//this should return the exact object passed to the xml file to be stored in the first place
	}

}
