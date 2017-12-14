package authoring.editEventImage;

import java.util.List;

import data.model.NPC;
import data.model.PacmanEnemy;
import data.model.PokemonSpecie;
import engine.UI.Path2Image;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


/**
 * The EventImageMenu which displays all the event images you can choose over 
 * @author supertony cy122
 *
 */
public class EventImageMenu extends TitledPane{

	/*instance variable*/
	private TabPane tabMenu = new TabPane();

	public EventImageMenu(List<PokemonSpecie> pokemonSpecies, List<NPC> NPCs) {


		// set style
		//this.getStylesheets().add(this.getClass().getResource("tab.css").toExternalForm());

		// set graphic of the title
		HBox graphic = new HBox();
		graphic.setSpacing(5);
		graphic.setAlignment(Pos.CENTER_LEFT);
		graphic.getChildren().add(new Label("Event Image Menu"));
		this.setGraphic(graphic);

		// set the content of the pane view


		this.setContent(tabMenu);
		
		
		this.addTab("Pokemon", createPokemonListView(pokemonSpecies));
		this.addTab("Trainer", createNPCListView(pokemonSpecies, NPCs));
		


		//set the size of the pane 
		this.setPrefSize(190, 546);
	}
	
	public EventImageMenu(List<PacmanEnemy> pacmanEnemies) {
		HBox graphic = new HBox();
		graphic.setSpacing(5);
		graphic.setAlignment(Pos.CENTER_LEFT);
		graphic.getChildren().add(new Label("Event Image Menu"));
		this.setGraphic(graphic);
		this.setContent(createPacmanEnemyListView(pacmanEnemies));
		this.setPrefSize(190, 546);
	}

	private Node createPacmanEnemyListView(List<PacmanEnemy> pacmanEnemies) {
		ListView<HBox> paneListView = new ListView<>();
		for(PacmanEnemy enemy:pacmanEnemies) {
			HBox totalPane = new HBox();
			ImageView sourceImage = new ImageView(Path2Image.showImage(enemy.getImagePath()));
			sourceImage.setFitWidth(48);
			sourceImage.setFitHeight(48);
			totalPane.getChildren().add(sourceImage);
			paneListView.getItems().add(totalPane);
			
			sourceImage.setOnDragDetected(new EventHandler <MouseEvent>() {
				public void handle(MouseEvent event) {
					/* drag was detected, start drag-and-drop gesture*/
					//System.out.println("onDragDetected");

					/* allow any transfer mode */
					Dragboard db = sourceImage.startDragAndDrop(TransferMode.ANY);

					/* put a string on dragboard */
					ClipboardContent content = new ClipboardContent();
					// if you want to add a shop tile, it needs to resize to the original one
					content.putImage(sourceImage.getImage());
					content.put(DataFormat.lookupMimeType("Type")==null?new DataFormat("Type"):DataFormat.lookupMimeType("Type"), "PacmanEnemy");
		            content.put(DataFormat.lookupMimeType("PacmanEnemy")==null?new DataFormat("PacmanEnemy"):DataFormat.lookupMimeType("PacmanEnemy"), enemy);
		            db.setContent(content);

					event.consume();
				}
			});
		}
		return paneListView;
	}

	/**
	 * private method to create a list view as the content of the titled pane
	 * @return a list view
	 */
	private Node createNPCListView(List<PokemonSpecie> pokemons, List<NPC> NPCs){
		//create a subPane
		ListView<HBox> paneListView = new ListView<>();

		for(NPC npc : NPCs) {
			HBox finalPane = new HBox();
			VBox totalPane = new VBox();
			HBox subPane = new HBox();

			Label  name = new Label(npc.getName());
			name.setFont(new Font(11));
			subPane.getChildren().add(name);
			
			subPane.setAlignment(Pos.BOTTOM_LEFT); 

			totalPane.getChildren().add(subPane);
			ImageView sourceImage = new ImageView(Path2Image.showImage(npc.getImagePath()));

			totalPane.getChildren().add(sourceImage);
			finalPane.getChildren().add(totalPane);
			paneListView.getItems().add(finalPane);


			//add drag event handler
			sourceImage.setOnDragDetected(new EventHandler <MouseEvent>() {
				public void handle(MouseEvent event) {
					/* drag was detected, start drag-and-drop gesture*/
					//System.out.println("onDragDetected");

					/* allow any transfer mode */
					Dragboard db = sourceImage.startDragAndDrop(TransferMode.ANY);

					/* put a string on dragboard */
					ClipboardContent content = new ClipboardContent();
					// if you want to add a shop tile, it needs to resize to the original one
					content.putImage(sourceImage.getImage());
					content.put(DataFormat.lookupMimeType("Type")==null?new DataFormat("Type"):DataFormat.lookupMimeType("Type"), "NPC");
		            content.put(DataFormat.lookupMimeType("NPC")==null?new DataFormat("NPC"):DataFormat.lookupMimeType("NPC"), npc);
		            content.put(DataFormat.lookupMimeType("PokemonList")==null?new DataFormat("PokemonList"):DataFormat.lookupMimeType("PokemonList"), pokemons);
		            db.setContent(content);

					event.consume();
				}
			});
		}
		return paneListView;
	}
	
	
	
	/**
	 * private method to create a list view as the content of the titled pane
	 * @return a list view
	 */
	private Node createPokemonListView(List<PokemonSpecie> pokemons) {
		//create a subPane
		ListView<HBox> paneListView = new ListView<>();
		for(PokemonSpecie pokemon: pokemons) {
			HBox finalPane = new HBox();
			VBox totalPane = new VBox();
			HBox subPane = new HBox();

			Label  name = new Label(pokemon.getSpecieName());
			name.setFont(new Font(11));
			subPane.getChildren().add(name);

			subPane.setAlignment(Pos.BOTTOM_LEFT); 

			totalPane.getChildren().add(subPane);
			ImageView sourceImage = Path2Image.scale(Path2Image.showImage(pokemon.getLevelEvolutionImagePath().get(1)),48,48,true);

			totalPane.getChildren().add(sourceImage);
			finalPane.getChildren().add(totalPane);
			paneListView.getItems().add(finalPane);


			//add drag event handler
			sourceImage.setOnDragDetected(new EventHandler <MouseEvent>() {
				public void handle(MouseEvent event) {
					/* drag was detected, start drag-and-drop gesture*/
					//System.out.println("onDragDetected");

					/* allow any transfer mode */
					Dragboard db = sourceImage.startDragAndDrop(TransferMode.ANY);

					/* put a string on dragboard */
					ClipboardContent content = new ClipboardContent();
					// if you want to add a shop tile, it needs to resize to the original one
					content.putImage(sourceImage.getImage());
					content.put(DataFormat.lookupMimeType("Type")==null?new DataFormat("Type"):DataFormat.lookupMimeType("Type"), "Pokemon");
		            content.put(DataFormat.lookupMimeType("PokemonSpecie")==null?new DataFormat("PokemonSpecie"):DataFormat.lookupMimeType("PokemonSpecie"), pokemon);
		            content.put(DataFormat.lookupMimeType("PokemonList")==null?new DataFormat("PokemonList"):DataFormat.lookupMimeType("PokemonList"), pokemons);
		            
					db.setContent(content);

					event.consume();
				}
			});
		}
		return paneListView;
	}




	/*add two tabs in titlepane*/
	private void addTab(String tabName,Node tabContext ) {
		Tab tabSize = new Tab();
		tabSize.setText(tabName);
		tabSize.setContent(tabContext);
		tabSize.setClosable(false);
		tabMenu.getTabs().addAll(tabSize);
	}
}


