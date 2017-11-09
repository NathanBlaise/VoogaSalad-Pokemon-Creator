package authoring.dragdrop;


import java.util.Map.Entry;
import java.util.TreeMap;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
	

	/**
	 * The tileMenu which can select tiles displayed in a menu
	 * @author supertony
	 *
	 */
	public class TileMenu extends TitledPane{
		
		/*final variable*/
		final static String REGTILE_PATH = "file:images/reg_tile_scaled.png";
		final static String GRASS_PATH = "file:images/grass_tile.png";
		final static String FLOWER_PATH = "file:images/flower_tile.png";
		final static String SHOP_PATH = "file:images/pokemonCenter.png";
		
		
		/*instance variable*/
		private ListView<HBox> paneListView = new ListView<>();
		private TreeMap<String, Image> imageMap = new TreeMap<String, Image>();
		

		
		public TileMenu() {

			// init image
			initImageMap();


			// set style
			this.getStylesheets().add(this.getClass().getResource("tab.css").toExternalForm());

			// set graphic of the title
			HBox graphic = new HBox();
			graphic.setSpacing(5);
			graphic.setAlignment(Pos.CENTER_LEFT);
			graphic.getChildren().add(new Label("Tile Menu"));
			this.setGraphic(graphic);

			// set the content of the pane view
			this.setContent(createListView());

			//set the size of the pane 
			this.setPrefSize(190, 546);
		}



		private void initImageMap() {
			Image regTile = new Image(REGTILE_PATH);
			Image grassTile = new Image(GRASS_PATH);
			Image flowerTile = new Image(FLOWER_PATH);
			Image shopTile = new Image(SHOP_PATH,48,48,false,false);
			
			imageMap.put("Default Tile", regTile);
			imageMap.put("grass Tile", grassTile);
			imageMap.put("Flower Tile", flowerTile);
			imageMap.put("Shop Tile", shopTile);
		}
		
		

		/**
		 * private method to create a list view as the content of the titled pane
		 * @return a list view
		 */
		private Node createListView() {
			//create a subPane
			
			for(Entry<String, Image> entry : imageMap.entrySet()) {
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
		             if (entry.getKey().equals("Shop Tile")) {
		            	 	Image source = new Image(SHOP_PATH);
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

		

		
		public void printIndex() {
			paneListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					System.out.println("clicked on " + ((Label) paneListView.getSelectionModel().getSelectedItem().getChildren().get(2)).getText());
				}
			});
		}




	}

