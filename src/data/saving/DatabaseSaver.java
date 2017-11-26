package data.saving;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import data.Database;

public class DatabaseSaver {

	public static void save(Database database, String savePath) {
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(savePath)));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File");
		}
		encoder.writeObject(database);
		encoder.close();
	}

}
