package data.saving;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class xmlWriter {

	//private String fileName;
	
	public xmlWriter() {
		//could include file name here, depending upon how this is called
	}
	
	public void writeXML(Object obj, String file) {
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File");
		}
		encoder.writeObject(obj);
		encoder.close();
	}
}
