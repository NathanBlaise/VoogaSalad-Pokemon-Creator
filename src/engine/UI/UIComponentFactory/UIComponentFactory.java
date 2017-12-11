package engine.UI.UIComponentFactory;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

/**
 * 
 * @author cy122
 * This class is a factory for creating the components of UI, such as buttons, menuItems.
 *
 */

public class UIComponentFactory {
    	/**
    	 * creates an MenuItem with the specified action 
    	 * @param title The title of the menu item created
    	 * @param handler The action to perform when the MenuItem is acted upon
    	 * @return The MenuItem created
    	 */
	static public MenuItem createMenuItem(String title, EventHandler<ActionEvent> handler){
		MenuItem result =  new MenuItem(title);
		result.setOnAction(handler);
		return result;
	}
	
	public static HBox intSlider(int initialValue, int left, int right, Callback<Integer, Integer> saver, String label){
		HBox result =new HBox();
		Label levelLabel = new Label(label + String.valueOf(initialValue));
		Slider slider = new Slider();
        levelLabel.textProperty().bind(
                Bindings.format(
                    label+": %.0f",
                    slider.valueProperty()
                )
         );
		slider.setMin(left);
		slider.setMax(right);
		slider.setValue(initialValue);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit((left+right)/2+1);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(1);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    saver.call(new_val.intValue());
            }
        });
		result.getChildren().addAll(slider, levelLabel);
		return result;
	}

	public static HBox doubleSlider(double initialValue, double left, double right, Callback<Double, Integer> saver, String label) {
		HBox result =new HBox();
		Label levelLabel = new Label(label + String.valueOf(initialValue));
		Slider slider = new Slider();
        levelLabel.textProperty().bind(
                Bindings.format(
                    label+": %.0f",
                    slider.valueProperty()
                )
         );
		slider.setMin(left);
		slider.setMax(right);
		slider.setValue(initialValue);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit((left+right)/2+1);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(1);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    saver.call(new_val.doubleValue());
            }
        });
		result.getChildren().addAll(slider, levelLabel);
		return result;
	}
}
