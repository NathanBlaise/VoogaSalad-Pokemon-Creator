package engine.UI.UIComponentFactory;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.control.ContextMenu;
import javafx.util.Callback;
/**
 * 
 * @author DanSun: commenting cy122: creator
 *
 */
public class ListViewFactory {
    	/**
    	 * Creates a ListView
    	 * @param rightClickContextMenu A ContextMenu object that already exists
    	 * @param cellCreation Specifies how cell are to be created
    	 * @param changeAction Action to perform when GameMap changes
    	 * @param addText The text for adding 
    	 * @param removeText The text for removing
    	 * @return
    	 */
	public static ListView createListView(ContextMenu rightClickContextMenu, Callback cellCreation, ChangeListener changeAction, String addText, String removeText){
		ListView result = new ListView();
		result.setEditable(true);
		result.setCellFactory(cellCreation); 
		if((addText!=null)&&(removeText!=null)){
			ContextMenu contextMenu = createClickMenu(result, rightClickContextMenu, addText, removeText, h->{}, h->{});
			result.setOnMouseClicked(e->{
				if(e.getButton() == MouseButton.SECONDARY){
					contextMenu.show(result, e.getScreenX(), e.getScreenY());
				}
			});
		}
		result.getSelectionModel().selectedItemProperty().addListener(changeAction);
		return result;
	}
	/**
	 * Adds an empty item to the ListView
	 * @param list
	 */
	private static void add(ListView list){
		list.getItems().add(null);
	}
	/**
	 * remove the last item fro the ListView
	 * @param list
	 */
	private static void remove(ListView list){
		if(list.getItems().size()!=0){
			list.getItems().remove(list.getItems().size()-1);
		}
	}
	/**
	 * Creates a ContextMenu 
	 * @param listView provided if the MenuItems in ContextMenu perform actions on the ListView, null otherwise
	 * @param oldContextMenu An existing ContextMenu, whose content is added to the new ContextMenu at the end 
	 * @param addText Text for adding
	 * @param removeText Text for removing
	 * @param addHandler Action to perform when adding is selected
	 * @param removeHandler Action to perform when removing is selected
	 * @return
	 */
	public static ContextMenu createClickMenu(ListView listView, 
		ContextMenu oldContextMenu, String addText, String removeText, 
		EventHandler<ActionEvent> addHandler, EventHandler<ActionEvent> removeHandler){
		ContextMenu newContextMenu = new ContextMenu();
		if(listView!=null){
			if(addText!=null)
				newContextMenu.getItems().addAll(
					UIComponentFactory.createMenuItem(
						addText, h -> {add(listView); addHandler.handle(h);}));
			if(removeText!=null)
				newContextMenu.getItems().addAll(
					UIComponentFactory.createMenuItem(
						removeText, h -> {remove(listView);removeHandler.handle(h);}));
		}else{
			if(addText!=null)
				newContextMenu.getItems().addAll(
					UIComponentFactory.createMenuItem(
						addText, h -> {addHandler.handle(h);}));
			if(removeText!=null)
				newContextMenu.getItems().addAll(
					UIComponentFactory.createMenuItem(
						removeText, h -> {removeHandler.handle(h);}));
		}
		newContextMenu.getItems().addAll(oldContextMenu.getItems());
		return newContextMenu;
	}
}
