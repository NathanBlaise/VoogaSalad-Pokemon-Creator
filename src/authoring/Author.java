package authoring;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Author implements StageDelegate{
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
		
	}

	@Override
	public void BackButtonPressed() {
		if (scControl.currentIndex > 0)
			scControl.currentIndex -=1;
			myStage.setScene(myList.get(scControl.currentIndex));
			System.out.println("I am currently at scene " + scControl.currentIndex );
		
		
	}

	@Override
	public void toFirstAuthorScene() {

		myStage.setScene(myList.get(scControl.currentIndex));
		//System.out.println("0");
		
	}

}
