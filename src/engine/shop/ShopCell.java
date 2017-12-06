package engine.shop;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.stream.Collectors;

import data.items.Item;
import data.player.Player;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

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
    private Map<String, Image> imageCollection;
    private Player mainPlayer;
    
    public ShopCell(Map<String, Image> images, Player player) {
    		super();
    		mainPlayer = player;
    		imageCollection = images;
    		hbox.getChildren().addAll(imageView,label,pane,button);
    		HBox.setHgrow(pane, Priority.ALWAYS);
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
                    imageCollection.get(
                            item
                    )
            );
            label.setText(item);
            setGraphic(hbox);
            button.setOnAction((event) -> {
					try {
						Class<?> clazz = Class.forName(item);
						Constructor<?> ctor = clazz.getConstructor(String.class);
						Object object = ctor.newInstance();
						mainPlayer.addItem((Item) object);
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
}
