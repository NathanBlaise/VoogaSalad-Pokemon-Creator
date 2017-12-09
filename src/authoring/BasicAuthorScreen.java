package authoring;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;

// basic class for editing screen
public class BasicAuthorScreen extends ScreenDisplay{
	
	//final variable
	private final static int WIDTH = 1000;
	private final static int HEIGHT = 600;

	
	//instance variable
	private Button NextScene;
	private Button BackScene;
	private StageDelegate stageHelper;

	
	private final static int BUTTONX = 600;
	private final static int BUTTONY = 500;

	public BasicAuthorScreen(int width, int height, Paint background,StageDelegate stageHelper) {
		super(width, height, background);
		// TODO Auto-generated constructor stub
		this.stageHelper = stageHelper;
		ButtonInit();
		this.getScene().getStylesheets().add("resources/sceneStyle.css");
		
	}
	
	/**
	 * @param background: background color
	 * @param stageHelper: the stageDelegate interface which helps to add event handler
	 * @param index: the scene index in the Array
	 */
	public BasicAuthorScreen(Paint background,StageDelegate stageHelper) {
	
		super(WIDTH, HEIGHT,background);
		// TODO Auto-generated constructor stub
		this.stageHelper = stageHelper;
		ButtonInit();
		
	}
	
	
	
	
	private void ButtonInit() {
		HBox ButtonBox = new HBox();
		NextScene = new Button("next >");
		BackScene = new Button("< back");
		
		//Set EventHandler for Buttons; Once clicked, go to the next scene or the back scene
		NextScene.addEventHandler(MouseEvent.MOUSE_CLICKED, e->stageHelper.GoButtonPressed());
		BackScene.addEventHandler(MouseEvent.MOUSE_CLICKED, e->stageHelper.BackButtonPressed());
		
		NextScene.getStylesheets().add("resources/sceneStyle.css");
        BackScene.getStylesheets().add("resources/sceneStyle.css");
		
		ButtonBox.getChildren().add(BackScene);
		ButtonBox.getChildren().add(NextScene);
		ButtonBox.setLayoutX(BUTTONX);
		ButtonBox.setLayoutY(BUTTONY);
		this.rootAdd(ButtonBox);
		
	}
	
	protected int getHeight(){
		return HEIGHT;
	}
	
	protected int getWidth(){
		return WIDTH;
	}
	
	protected int getButtonX(){
		return BUTTONX;
	}
	
	protected int getButtonY(){
		return BUTTONY;
	}
	
	protected Button getBackSceneButton(){
		return BackScene;
	}
	
	protected Button getGoSceneButton(){
		return NextScene;
	}
	
}
