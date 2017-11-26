package authoring;

import java.util.ArrayList;

import data.Database;
import data.saving.DatabaseSaver;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author cy122, hy133
 * 
 * Author holds the key components and display the authoring environment to user
 *
 */

public class Author implements StageDelegate{
	/*final variable*/
	final static int EDITMAPSCENE = 0;
	final static int EDITEVENTIMAGESCENE = 1;
	/*Instance Variable*/
	private Stage myStage;
	private ArrayList <Scene> myList;
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
		myList = scControl.getScList();
	}
	
	
	@Override
	public void GoButtonPressed() {
		if (scControl.currentIndex < myList.size() - 1)
		scControl.currentIndex +=1;
		myStage.setScene(myList.get(scControl.currentIndex));
		System.out.println("I am currently at scene " + scControl.currentIndex );
		
		
		if (scControl.currentIndex ==  EDITEVENTIMAGESCENE) {
			scControl.passMapForward();
		}
		
	}

	@Override
	public void BackButtonPressed() {
		if (scControl.currentIndex > 0)
			scControl.currentIndex -=1;
			myStage.setScene(myList.get(scControl.currentIndex));
			System.out.println("I am currently at scene " + scControl.currentIndex );
		
		if (scControl.currentIndex == EDITMAPSCENE) {
			scControl.passMapBackward();
		}
	}

	@Override
	public void toFirstAuthorScene() {

		myStage.setScene(myList.get(scControl.currentIndex));
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

}
