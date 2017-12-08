package authoring.databaseEditor;

import java.io.File;
import java.util.List;

import authoring.eventManage.InstructionNPCFightEditor;
import data.PropertyReader;
import data.event.Instruction;
import data.event.InstructionNPCFight;
import data.model.NPC;
import data.model.Pokemon;
import data.model.PokemonSpecie;
import data.player.Player;
import engine.UI.UIComponentFactory.UIComponentFactory;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class PlayerTab{
	private BorderPane root;
	private Tab tab;
	private String path = "../resources/PathMap.properties";
	private Callback<Player, Integer> saver;
	private Player player;
	
	public PlayerTab(Player player, List<PokemonSpecie> pokemonSpecies, int XLength, int YLength, Callback<Player, Integer> saver){
		this.player = player;
		this.saver = saver;
		root = new BorderPane();
		tab = new Tab(new PropertyReader("../resources/English.properties").getString("PlayerTabName"), root);
		VBox vbox = new VBox();
		vbox.getChildren().add(placeChooser(player.getPosX(), player.getPosY(), XLength, YLength));
		if((player.getPosX()>XLength-1)||(player.getPosX()<0)){
			player.setPosX(0);
		}
		if((player.getPosY()>YLength-1)||(player.getPosY()<0)){
			player.setPosY(0);
		}
		vbox.getChildren().add(imageChooser(new PropertyReader(path).getString("PlayerImageDown")));
		root.setLeft(vbox);
		root.setCenter(choosePokemon(player.getPokemons(), pokemonSpecies));
		root.setPrefSize(1000, 600);
		refresh(player);
	}
	
	private GridPane placeChooser(int posX, int posY, int XLength, int YLength){
		GridPane grid = new GridPane();
		grid.add(new Label(new PropertyReader("../resources/English.properties").getString("change Y coordinates")), 0, 0);
		HBox Xchanger = UIComponentFactory.intSlider(player.getPosX(), 0, XLength-1, new Callback<Integer, Integer>(){
			@Override
			public Integer call(Integer param) {
				player.setPosX(param);
				refresh(player);
				return null;
			}			
		}, "x coord");
		grid.add(Xchanger, 0, 1);
		grid.add(new Label(new PropertyReader("../resources/English.properties").getString("change X coordinates")), 0, 2);
		HBox Ychanger = UIComponentFactory.intSlider(player.getPosY(), 0, YLength-1, new Callback<Integer, Integer>(){
			@Override
			public Integer call(Integer param) {
				player.setPosY(param);
				refresh(player);
				return null;
			}			
		}, "y coord");
		grid.add(Ychanger, 0, 3);
		grid.add(new Label(new PropertyReader("../resources/English.properties").getString("change currency")), 0, 4);
		HBox currencyChanger = UIComponentFactory.doubleSlider(player.getCurrency(), 0.0, 480, new Callback<Double, Integer>(){
			@Override
			public Integer call(Double param) {
				player.setCurrency(param);
				refresh(player);
				return null;
			}			
		}, "currency");
		grid.add(currencyChanger, 0, 5);
		return grid;
	}
	
	private Node choosePokemon(Pokemon[] pokemons, List<PokemonSpecie> pokemonSpecies){
		return new InstructionNPCFightEditor(new InstructionNPCFight(new NPC(new PropertyReader(path).getString("PlayerImageDown"), "Jason"), pokemons), 
												pokemonSpecies, 
												new Callback<Instruction, Integer>(){
														@Override
														public Integer call(Instruction param) {
															if(param instanceof InstructionNPCFight){
																player.setPokemons(((InstructionNPCFight)param).getPokemons());
																refresh(player);
															}
															return null;
														}		
												}
				).showEditor();
	}
	
	public ImageView imageChooser(String path){
		Image image = new Image(new File(path).toURI().toString());
		ImageView eventImage = new ImageView(image);
		return eventImage;
	}
	
	public Tab getTab(){
		return tab;
	}
	
	private Integer refresh(Player player){
		saver.call(player);
		return null;
	}
	
	
}
