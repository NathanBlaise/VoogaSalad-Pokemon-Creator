package data.saving;

import data.Database;

public class DatabaseSaver {

	public static void save(Database database, String savePath) {
		try {
			new xmlWriter().writeXML(database, savePath);
		} catch (Exception e) {
			e.printStackTrace();//handled by exiting the program
			System.exit(1);
		}
	}
}
