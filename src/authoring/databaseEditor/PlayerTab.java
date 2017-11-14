package authoring.databaseEditor;

import java.io.File;

import authoring.eventManage.EventImage;
import data.PropertyReader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class PlayerTab{
	private BorderPane root;
	private Tab tab;
	private String path = "../resources/PathMap.properties";
	
	public PlayerTab(){
		root = new BorderPane();
		tab = new Tab(new PropertyReader("../resources/English.properties").getString("PlayerTabName"), root);
		root.setRight(placeChooser());
		root.setCenter(imageChooser());
	}
	
	private GridPane placeChooser(){
		GridPane grid = new GridPane();
		grid.add(new Label(new PropertyReader("../resources/English.properties").getString("changeX")), 0, 0);
		grid.add(new TextField(), 1, 0);
		grid.add(new Label(new PropertyReader("../resources/English.properties").getString("changeY")), 0, 1);
		grid.add(new TextField(), 1, 1);
		return grid;
	}
	
	public BorderPane imageChooser(){
		System.out.print(new File(new PropertyReader(path).getString("DefaultImage")).getAbsolutePath());
		EventImage eventImage = new EventImage(
				new File(new PropertyReader(path).getString("DefaultImage")).getAbsolutePath());
		eventImage.addFileChooser(eventImage);
		return eventImage;
	}
	
	public Tab getTab(){
		return tab;
	}
}
