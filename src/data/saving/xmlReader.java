package data.saving;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class xmlReader <K>{

	public xmlReader() {
		//empty for now, not sure if this will change
	}
	

	@SuppressWarnings({ "resource", "unchecked" })
	public K readXML(String file) {
		
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File " + file + " not found");
		}
		K obj = (K) decoder.readObject();
		//this should return the exact object passed to the xml file to be stored in the first place
		return obj;
	}

}
