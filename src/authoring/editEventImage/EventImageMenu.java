package authoring.editEventImage;

import java.util.Map.Entry;
import java.util.TreeMap;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * The EventImageMenu which displays all the event images you can choose over 
 * @author supertony
 *
 */
public class EventImageMenu extends TitledPane{

	/*final variable*/
	final static String CAPTAIN_MAP_IMAGE = "file:images/CaptainMap.png";
	final static String CAPTAIN_BATTLE_IMAGE = "file:images/CaptainNPC.png";
	final static String POKE_1_GIF = "file:images/pokemon_sprites/1.gif";
	final static String POKE_10_GIF = "file:images/pokemon_sprites/10.gif";


	/*instance variable*/
	//private ListView<HBox> paneListView = new ListView<>();
	private TreeMap<String, Image> PokeMap = new TreeMap<String, Image>();
	private TreeMap<String, Image> NPCMap = new TreeMap<String, Image>();
	private TabPane tabMenu = new TabPane();

	public EventImageMenu() {

		// init image
		initImageMap();


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
		
		
		this.addTab("Pokemon", createListView(PokeMap));
		this.addTab("Trainer", createListView(NPCMap));
		


		//set the size of the pane 
		this.setPrefSize(190, 546);
	}



	private void initImageMap() {
		//Image captainMenu = new Image(CAPTAIN_MAP_IMAGE,48,48,false,false);
		Image captainMap = new Image(CAPTAIN_BATTLE_IMAGE,48,48,false,false);
		Image poke1 = new Image(POKE_1_GIF);
		Image poke2 = new Image(POKE_10_GIF);

		PokeMap.put("Bulbasaur", poke1);
		PokeMap.put("Caterpie",poke2 );
		//NPCMap.put("captain", captainMenu);
		NPCMap.put("captain", captainMap);
	}



	/**
	 * private method to create a list view as the content of the titled pane
	 * @return a list view
	 */
	private Node createListView(TreeMap<String, Image> myMap) {
		//create a subPane
		ListView<HBox> paneListView = new ListView<>();

		for(Entry<String, Image> entry : myMap.entrySet()) {
			HBox finalPane = new HBox();
			VBox totalPane = new VBox();
			HBox subPane = new HBox();

			Label  name = new Label(entry.getKey());
			name.setFont(new Font(11));
			subPane.getChildren().add(name);


			Label gitControl = new Label(" [GUI master]");
			gitControl.setTextFill(Color.GOLDENROD);
			gitControl.setFont(new Font(11));
			subPane.getChildren().add(gitControl);
			gitControl.setAlignment(Pos.BASELINE_CENTER);

			subPane.setAlignment(Pos.BOTTOM_LEFT); 

			totalPane.getChildren().add(subPane);
			ImageView sourceImage = new ImageView(entry.getValue());

			totalPane.getChildren().add(sourceImage);
			//totalPane.getChildren().add()
			finalPane.getChildren().add(totalPane);
			paneListView.getItems().add(finalPane);


			//add drag event handler
			sourceImage.setOnDragDetected(new EventHandler <MouseEvent>() {
				public void handle(MouseEvent event) {
					/* drag was detected, start drag-and-drop gesture*/
					System.out.println("onDragDetected");

					/* allow any transfer mode */
					Dragboard db = sourceImage.startDragAndDrop(TransferMode.ANY);

					/* put a string on dragboard */
					ClipboardContent content = new ClipboardContent();
					// if you want to add a shop tile, it needs to resize to the original one
					if (entry.getKey().equals("captain")) {
						Image source = new Image(CAPTAIN_MAP_IMAGE,48,48,false,false);
						content.putImage(source);
						content.putString(entry.getKey());
					} 
					else {
						content.putImage(sourceImage.getImage());
					}
					db.setContent(content);

					event.consume();
				}
			});

			//set drop down
			sourceImage.setOnDragDone(new EventHandler <DragEvent>() {
				public void handle(DragEvent event) {
					/* the drag-and-drop gesture ended */
					System.out.println("onDragDone");
					/* if the data was successfully moved, clear it */
					if (event.getTransferMode() == TransferMode.MOVE) {
						//TO-DO
					}

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
		tabMenu.getTabs().addAll(tabSize);
	}





}


