import start.SplashScreen;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
import authoring.BasicAuthorScreen;
import authoring.SceneController;
import authoring.ScreenDisplay;
import authoring.StageDelegate;
import authoring.dragdrop.EditMapScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application implements StageDelegate{
	
	private Stage myStage;
	private ArrayList <Scene> myList;
	private SceneController scControl;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		myStage = primaryStage;
		scControl = new SceneController(this);
		myList = scControl.getScList();
		new SplashScreen(primaryStage,this);
		
	}

	
	@Override
	public void GoButtonPressed() {
		if (scControl.currentIndex < myList.size() - 1)
		// pass the map to the next scene
	    scControl.passMapForward();
		scControl.currentIndex +=1;
		myStage.setScene(myList.get(scControl.currentIndex));
		System.out.println("I am currently at scene " + scControl.currentIndex );
		
	}

	@Override
	public void BackButtonPressed() {
		if (scControl.currentIndex > 0)
			scControl.passMapBackward();
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
