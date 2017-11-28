package data.saving;

import data.Database;

public class DatabaseSaver {

	public static void save(Database database, String savePath) {
		try {
			new xmlWriter().writeXML(database, savePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
