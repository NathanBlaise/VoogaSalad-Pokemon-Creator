package data.saving;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import data.map.GameMap;

public class xmlSerializeTest2 {

		public xmlSerializeTest2() {
			// TODO Auto-generated constructor stub
		}

		public static void main(String args[]) throws IOException{

			GameMap map = createMap();
			
			//System.out.println(pok.getMoves()[3]);
			
			
			/*
		    FileOutputStream fos = new FileOutputStream("xmlSerializeTest1.xml");
		    XMLEncoder encoder = new XMLEncoder(fos);
		    encoder.setExceptionListener(new ExceptionListener() {
		            public void exceptionThrown(Exception e) {
		                System.out.println("Exception! :"+e.toString());
		            }
		    });
		    encoder.writeObject(doubleMap);
		    encoder.close();
		    fos.close();
		    */
			
			
			
		    
			XMLEncoder encoder=null;
			try{
			encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("src/resources/defaultDatabase.xml")));
			}catch(FileNotFoundException fileNotFound){
				System.out.println("ERROR: While Creating or Opening the File");
			}
			encoder.writeObject(map);
			encoder.close();
			
			XMLDecoder decoder=null;
			try {
				decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("src/resources/defaultDatabase.xml")));
			} catch (FileNotFoundException e) {
				System.out.println("ERROR: File " + "xmlSerializeTest5.xml" + " not found");
			}
			GameMap result = (GameMap) decoder.readObject();
			System.out.println(result);
		}
		
		private static GameMap createMap(){
			GameMap map = new GameMap(2,3);
			
			return map;
		}

}
