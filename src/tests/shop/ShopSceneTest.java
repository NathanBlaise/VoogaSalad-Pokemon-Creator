package tests.shop;

import engine.shop.ShopScene;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ShopSceneTest  extends Application {

	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		ShopScene ss = new ShopScene(720,480,Color.WHITE,null);
//		primaryStage.setScene(ss.getScene());
		primaryStage.show();
	}

}
