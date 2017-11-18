package authoring;

import java.util.ArrayList;

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
	
	
	public Author(Stage primaryStage){
		myStage = primaryStage;
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

}
