package authoring.dragdrop;


import java.util.Map.Entry;
import java.util.TreeMap;

import engine.UI.Path2Image;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
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
		final static String REGTILE_PATH = "images/reg_tile_scaled.png";
		final static String GRASS_PATH = "images/grass_tile.png";
		final static String FLOWER_PATH = "images/flower_tile.png";
		final static String SHOP_PATH = "images/shopCenterSplitedWithBackground/Center.jpg";
		final static String HOUSE_PATH = "images/houseSplitedWithBackground/House.jpg";
		final static String SPACE_PATH = "images/space.png";
		final static String TREE_PATH = "images/tree.png";
		final static String SPECIALTILEPATH = "images/grass_battle.png";
		final static String WATER_PATH = "images/watercenter.png";
		final static String STONE_PATH = "images/stonecenter.png";
		final static String BRIDGE_PATH = "images/bridge.png";
		final static String BRIDGE2_PATH = "images/bridge2.png";
		final static String PURE_WATER_PATH = "images/purewatercenter.png";
		final static String LEAF_PATH = "images/leafcenter.png";
		final static String EDGE_PATH = "images/edgecenter.png";
		
		
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
			imageMap.put(REGTILE_PATH, Path2Image.showImage(REGTILE_PATH));
			imageMap.put(GRASS_PATH, Path2Image.showImage(GRASS_PATH));
			imageMap.put(FLOWER_PATH, Path2Image.showImage(FLOWER_PATH));
			imageMap.put(SHOP_PATH, Path2Image.scale(Path2Image.showImage(SHOP_PATH), 48,48, true).snapshot(null, null));
			imageMap.put(SPACE_PATH, Path2Image.scale(Path2Image.showImage(SPACE_PATH), 48,48, true).snapshot(null, null));
			imageMap.put(TREE_PATH, Path2Image.scale(Path2Image.showImage(TREE_PATH), 48,48, false).snapshot(null, null));
			imageMap.put(HOUSE_PATH, Path2Image.scale(Path2Image.showImage(HOUSE_PATH), 48,48, true).snapshot(null, null));
			imageMap.put(STONE_PATH, Path2Image.scale(Path2Image.showImage(STONE_PATH), 48,48, true).snapshot(null, null));
			imageMap.put(WATER_PATH, Path2Image.scale(Path2Image.showImage(WATER_PATH), 48,48, true).snapshot(null, null));
			imageMap.put(BRIDGE_PATH, Path2Image.scale(Path2Image.showImage(BRIDGE_PATH), 48,48, true).snapshot(null, null));
			imageMap.put(BRIDGE2_PATH, Path2Image.scale(Path2Image.showImage(BRIDGE2_PATH), 48,48, true).snapshot(null, null));
			imageMap.put(LEAF_PATH, Path2Image.scale(Path2Image.showImage(LEAF_PATH), 48,48, true).snapshot(null, null));
			imageMap.put(PURE_WATER_PATH, Path2Image.scale(Path2Image.showImage(PURE_WATER_PATH), 48,48, true).snapshot(null, null));
			imageMap.put(EDGE_PATH, Path2Image.scale(Path2Image.showImage(EDGE_PATH), 48,48, true).snapshot(null, null));
			//imageMap.put(SPECIALTILEPATH ,Path2Image.showImage(SPECIALTILEPATH));
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

			//though Chenning thinks this label is redundant
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
		             //System.out.println("onDragDetected");
		             
		             /* allow any transfer mode */
		             Dragboard db = sourceImage.startDragAndDrop(TransferMode.ANY);
		             
		             /* put a string on dragboard */
		             ClipboardContent content = new ClipboardContent();
		             
		             // if you want to add a shop tile, it needs to resize to the original one
		             if (entry.getKey().equals(SHOP_PATH)) {
		            	 	Image source = Path2Image.showImage(SHOP_PATH);
		            	 	content.putImage(source);
		            	 	content.putString(entry.getKey());
		            	 	content.put(DataFormat.lookupMimeType("Type")==null?new DataFormat("Type"):DataFormat.lookupMimeType("Type"), "Shop Tile");
		             } 
		             
		             else if (entry.getKey().equals(HOUSE_PATH)) {
		            	 	Image source = Path2Image.showImage(HOUSE_PATH);
		            	 	content.putImage(source);
		            	 	content.putString(entry.getKey());
		            	 	content.put(DataFormat.lookupMimeType("Type")==null?new DataFormat("Type"):DataFormat.lookupMimeType("Type"), "Shop Tile");
		             } 
		             
		             
		             else {
		            	 content.putImage(sourceImage.getImage());
		            	 content.put(DataFormat.lookupMimeType("Type")==null?new DataFormat("Type"):DataFormat.lookupMimeType("Type"), "Tile");
		             }
		             
		             
	
		             content.put(DataFormat.lookupMimeType("Path")==null?new DataFormat("Path"):DataFormat.lookupMimeType("Path"), entry.getKey());
		             db.setContent(content);
		             
		             event.consume();
		         }
		     });
			
			//set drop down
			 sourceImage.setOnDragDone(new EventHandler <DragEvent>() {
		         public void handle(DragEvent event) {
		             /* the drag-and-drop gesture ended */
		             //System.out.println("onDragDone");
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

		

		
//		public void printIndex() {
//			paneListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//				@Override
//				public void handle(MouseEvent event) {
//					System.out.println("clicked on " + ((Label) paneListView.getSelectionModel().getSelectedItem().getChildren().get(2)).getText());
//				}
//			});
//		}




	}

