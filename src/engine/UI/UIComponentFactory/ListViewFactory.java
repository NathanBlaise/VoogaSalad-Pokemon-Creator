package engine.UI.UIComponentFactory;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.control.ContextMenu;
import javafx.util.Callback;

public class ListViewFactory {
	public static ListView createListView(ContextMenu rightClickContextMenu, Callback choices, ChangeListener changeAction, String add, String remove){
		ListView result = new ListView();
		result.setEditable(true);
		result.setCellFactory(choices); 
		ContextMenu contextMenu = createClickMenu(result, rightClickContextMenu, add, remove, h->{}, h->{});
		result.setOnMouseClicked(e->{
			if(e.getButton() == MouseButton.SECONDARY){
				contextMenu.show(result, e.getScreenX(), e.getScreenY());
			}
		});
		result.getSelectionModel().selectedItemProperty().addListener(changeAction);
		return result;
	}
	
	private static void add(ListView list){
		list.getItems().add("");
	}
	
	private static void remove(ListView list){
		if(list.getItems().size()!=0){
			list.getItems().remove(list.getItems().size()-1);
		}
	}
	
	public static ContextMenu createClickMenu(ListView<String> result, ContextMenu clickContextMenu, String add, String remove, EventHandler<ActionEvent> addHandler, EventHandler<ActionEvent> removeHandler){
		ContextMenu contextMenu = new ContextMenu();
		contextMenu.getItems().addAll(UIComponentFactory.createMenuItem(add, h -> {add(result); addHandler.handle(h);}));
		contextMenu.getItems().addAll(UIComponentFactory.createMenuItem(remove, h -> {remove(result);removeHandler.handle(h);}));
		contextMenu.getItems().addAll(clickContextMenu.getItems());
		return contextMenu;
	}
}
