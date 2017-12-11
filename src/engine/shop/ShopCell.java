package engine.shop;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import data.items.Item;
import data.player.Player;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * 
 * @author nathanlewis
 *
 */

public class ShopCell extends ListCell<String> {
    private ImageView imageView = new ImageView();
    private Label label = new Label();
    private Pane pane = new Pane();
    private Button button = new Button("Buy");
    private HBox hbox = new HBox();
    private Map<String, Item> itemCollection;
    private Player mainPlayer;
    private Callback<Integer, Integer> callback;
    
    public ShopCell(Map<String, Item> itemCollection, Player player, Callback<Integer, Integer> callback) {
    		super();
    		mainPlayer = player;
    		this.itemCollection = itemCollection;
    		hbox.getChildren().addAll(imageView,label,pane,button);
    		HBox.setHgrow(pane, Priority.ALWAYS);
    		this.callback = callback;
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        if (empty || item == null) {
            imageView.setImage(null);
            setGraphic(null);
            
        } else {
            imageView.setImage(
            		new Image("file:images/items/" + item.toLowerCase() + ".png")
            );
            label.setText(item);
            setGraphic(hbox);
            button.setText("$"+new Integer(itemCollection.get(item).getItemPrice()).toString());
            button.setOnAction((event) -> {
					try {
						Class<?> clazz = Class.forName("data.items."+item);
						Constructor<?> ctor = clazz.getConstructor();
						Item object = (Item)ctor.newInstance();
						if(mainPlayer.getCurrency()>=object.getItemPrice()){
							mainPlayer.addItem(((Item)object).getItemName());
							mainPlayer.setCurrency(mainPlayer.getCurrency()-object.getItemPrice());
							callback.call(null);
							popUpWarning("Successfully buy "+object.getItemName()+"!");
						}else {
							popUpWarning("Not Enough Money");
						}
						
					} catch (ClassNotFoundException e) {
						System.out.println("Reflection for item failed");
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						System.out.println("No method found for constructor of item");
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					}  catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} 
    			});
        }
    }

	private void popUpWarning(String text) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        BorderPane dialogVbox = new BorderPane();
        dialogVbox.setCenter(new Text(text));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
	}
    
    
}
