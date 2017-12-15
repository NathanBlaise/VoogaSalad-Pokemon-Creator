package engine.UI;

import authoring.ScreenDisplay;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * 
 * @author cy122
 * 
 * This provides a animation about fading in/out a javafx node
 *
 */

public class Fade {
	
	public static FadeTransition fadeIn(Node node, EventHandler<ActionEvent> endAction){
		FadeTransition ft = new FadeTransition(Duration.millis(500), node);
	    ft.setFromValue(0);
	    ft.setToValue(1.0);
	    ft.setCycleCount(1);
	    ft.setAutoReverse(true);
	    ft.setOnFinished(endAction);
	    return ft;
	}
	
	public static FadeTransition fadeOut(Node node, EventHandler<ActionEvent> endAction){
		FadeTransition ft = new FadeTransition(Duration.millis(500), node);
	    ft.setFromValue(1.0);
	    ft.setToValue(0);
	    ft.setCycleCount(1);
	    ft.setAutoReverse(true);
	    ft.setOnFinished(endAction);
	    return ft;
	}
	
	public static void FadeFromOneSceneToAnother(ScreenDisplay originScene, ScreenDisplay newScene, Callback<Integer, Integer> behaviour){
		Fade.fadeOut(originScene.getRoot(), e->{
			Fade.fadeIn(originScene.getRoot(), h->{}).play();
			if(originScene!=newScene){
				Fade.fadeIn(newScene.getRoot(), h->{}).play();
			}
			behaviour.call(0);
		}).play();	
	}
}
