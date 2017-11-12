package engine.UI;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
		FadeTransition ft = new FadeTransition(Duration.millis(3000), node);
	    ft.setFromValue(0);
	    ft.setToValue(1.0);
	    ft.setCycleCount(1);
	    ft.setAutoReverse(true);
	    ft.setOnFinished(endAction);
	    return ft;
	}
	
	public static FadeTransition fadeOut(Node node, EventHandler<ActionEvent> endAction){
		FadeTransition ft = new FadeTransition(Duration.millis(3000), node);
	    ft.setFromValue(1.0);
	    ft.setToValue(0);
	    ft.setCycleCount(1);
	    ft.setAutoReverse(true);
	    ft.setOnFinished(endAction);
	    return ft;
	}
}
