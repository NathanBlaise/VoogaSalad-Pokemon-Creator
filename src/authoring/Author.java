package authoring;


import data.Database;
import data.saving.DatabaseSaver;
import javafx.stage.Stage;

/**
 * 
 * @author cy122, hy133
 * @author Dan Sun for refactoring
 * 
 * Author holds the key components and provides the 
 * functionality needed by the authoring window
 *
 */

public class Author implements StageDelegate{
	/*final variable*/
	final static int EDITMAPSCENE = 0;
	final static int EDITEVENTIMAGESCENE = 1;
	/*Instance Variable*/
	private Stage myStage;
//	private SceneList myList;
	private SceneController scControl;
	private String savePath;
	private Database database;
	
	/**
	 * 
	 * @param database - the database
	 * @param savePath - the path to save the Database
	 * @param primaryStage - the stage to show the data
	 */
	public Author(Database database, String savePath, Stage primaryStage){
		myStage = primaryStage;
		this.database = database;
		this.savePath = savePath;
		scControl = new SceneController(this);
//		myList = scControl.getScList();
	}
	
	
	@Override
	public void GoButtonPressed() {
		saveDatabase();
		scControl.goToNextEditingScene(myStage);
		//functionality encapsuled in sceneController
//		if (scControl.currentIndex < myList.size() - 1) {
//		scControl.currentIndex +=1;
//		myStage.setScene(myList.get(scControl.currentIndex));
//		}
		//System.out.println("I am currently at scene " + scControl.currentIndex );
		
	}

	@Override
	public void BackButtonPressed() {
		saveDatabase();
		scControl.goToPreviousEditingScene(myStage);
		//functionlaity encapsuled in sceneController
//		if (scControl.currentIndex > 0)
//		{
//		    scControl.currentIndex -=1;
//		    myStage.setScene(myList.get(scControl.currentIndex));
//		}
			//System.out.println("I am currently at scene " + scControl.currentIndex );
	}

	@Override
	public void toFirstAuthorScene() {
		scControl.goToFirstEditingScene(myStage);
		myStage.centerOnScreen();
		//System.out.println("0");	
	}


	@Override
	public Database getDatabase() {
		return database;
	}


	@Override
	public void saveDatabase() {
		DatabaseSaver.save(database, savePath);
	}
	
	@Override
	public Stage getStage(){
		return myStage;
	}

}
