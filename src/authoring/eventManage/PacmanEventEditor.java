package authoring.eventManage;

import data.event.Event;
import data.event.EventPacmanEnemy;
import data.model.PacmanEnemy;
import engine.UI.Path2Image;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * 
 * @author nathanlewis
 *
 */
public class PacmanEventEditor {
	
	private Stage stage;
	private Callback<Event, Integer> saver;
	private PacmanEnemy pacmanEnemy;
	
	public PacmanEventEditor(EventPacmanEnemy selectedEventPacman, Callback<Event, Integer> saver) {
		this.saver = saver;
		stage = new Stage();
		pacmanEnemy = selectedEventPacman.getPacmanEnemy();
		editPacmanEnemy();
		stage.show();
	}
	
	private void editPacmanEnemy() {
		HBox hbox = new HBox(20);
		hbox.getChildren().addAll(new ImageView(Path2Image.showImage(pacmanEnemy.getImagePath())), setSlider());
		Button save = new Button("Save");
		save.setOnAction((e) -> {
			saver.call(new EventPacmanEnemy(pacmanEnemy));
			stage.close();
		});
		BorderPane bp = new BorderPane();
		bp.setCenter(hbox);
		bp.setBottom(save);
		stage.setScene(new Scene(bp));
		stage.getScene().getStylesheets().add("resources/sceneStyle.css");
	}
	
	private HBox setSlider() {
		Label speed = new Label("Speed:");
		Slider speedSlider = new Slider(1,10,5);
		speedSlider.prefWidth(250);
		speedSlider.setShowTickMarks(true);
		speedSlider.setSnapToTicks(true);
		speedSlider.setValue(pacmanEnemy.getSpeed());
		Label sliderCaption = new Label(Double.toString(speedSlider.getValue()));
		speedSlider.valueProperty().addListener((obs,oldVal,newVal) -> {
			speedSlider.setValue(((int) Math.round(newVal.doubleValue())));
			sliderCaption.setText(Double.toString(speedSlider.getValue()));
			pacmanEnemy.setSpeed((int) speedSlider.getValue());
		});
		HBox sliderBox = new HBox(20);
		sliderBox.getChildren().addAll(speed,speedSlider,sliderCaption);
		return sliderBox;
	}
	
}
