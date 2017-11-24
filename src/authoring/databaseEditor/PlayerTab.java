package authoring.databaseEditor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import tests.authoring.Specie1;
import tests.authoring.Specie2;
import authoring.eventManage.InstructionNPCFightEditor;
import data.PropertyReader;
import data.event.Instruction;
import data.model.NPC;
import data.model.PokemonSpecie;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class PlayerTab implements Callback<Instruction, Integer>{
	private BorderPane root;
	private Tab tab;
	private String path = "../resources/PathMap.properties";
	
	public PlayerTab(){
		root = new BorderPane();
		tab = new Tab(new PropertyReader("../resources/English.properties").getString("PlayerTabName"), root);
		VBox vbox = new VBox();
		vbox.getChildren().add(placeChooser());
		vbox.getChildren().add(imageChooser());
		root.setLeft(vbox);
		root.setCenter(choosePokemon());
	}
	
	private GridPane placeChooser(){
		GridPane grid = new GridPane();
		grid.add(new Label(new PropertyReader("../resources/English.properties").getString("changeX")), 0, 0);
		grid.add(new TextField(), 0, 1);
		grid.add(new Label(new PropertyReader("../resources/English.properties").getString("changeY")), 0, 2);
		grid.add(new TextField(), 0, 3);
		return grid;
	}
	
	private Node choosePokemon(){
		List<PokemonSpecie> species = new ArrayList<PokemonSpecie>();
		species.add(new Specie1());
		species.add(new Specie2());
		return new InstructionNPCFightEditor(new NPC(new PropertyReader(path).getString("UserImage"), "Jason"), species, this).showEditor();
	}
	
	public ImageView imageChooser(){
		Image image = new Image(new File(new PropertyReader(path).getString("UserImage")).toURI().toString());
		ImageView eventImage = new ImageView(image);
		return eventImage;
	}
	
	public Tab getTab(){
		return tab;
	}

	@Override
	public Integer call(Instruction param) {
		root.setPrefSize(1000, 600);
		return null;
	}
}
