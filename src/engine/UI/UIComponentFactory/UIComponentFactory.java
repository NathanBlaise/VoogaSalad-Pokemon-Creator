package engine.UI.UIComponentFactory;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

/**
 * 
 * @author cy122
 * This class is a factory for creating the components of UI, such as buttons, menuItems.
 *
 */

public class UIComponentFactory {
	static public MenuItem createMenuItem(String title, EventHandler<ActionEvent> handler){
		MenuItem result =  new MenuItem(title);
		result.setOnAction(handler);
		return result;
	}
}
