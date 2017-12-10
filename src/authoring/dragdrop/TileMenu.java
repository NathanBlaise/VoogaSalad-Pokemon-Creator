package authoring.dragdrop;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import data.model.Tile;
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
import javafx.scene.text.Font;
import util.FilePathConverter;
	

	/**
	 * The tileMenu which can select tiles displayed in a menu
	 * @author supertony cy122
	 *
	 */
	public class TileMenu extends TitledPane{

		
		
		/*instance variable*/
		private ListView<HBox> paneListView = new ListView<>();
		private Map<Tile, Image> imageMap = new HashMap<Tile, Image>();
		private ArrayList<Tile> tiles;

		/**
		 * Constructor for the class
		 * @param tiles Available tiles for the world
		 */
		public TileMenu(List<Tile> tiles){
			this.tiles = new ArrayList<Tile>(tiles);
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
			for(Tile tile: tiles){
				imageMap.put(tile, Path2Image.scale(Path2Image.showImage(tile.getWholePic()), 48,48, true).snapshot(null, null));
			}
		}
		
		

		/**
		 * private method to create a list view as the content of the titled pane
		 * @return a list view
		 */
		private Node createListView() {
			for(Entry<Tile, Image> entry : imageMap.entrySet()) {
			    ImageView sourceImage = addTileImageToListView(entry);
			    addDragEventHandlerToImage(entry, sourceImage);
			    addDropEventForImage(sourceImage);
			}
			return paneListView;
		}



		private void addDropEventForImage(ImageView sourceImage) {
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


		/**
		 * Adds drag event handler that is necessary for the drag and drop operation
		 * @param entry Specifies a kind of tile in the form of <Tile, Image>
		 * @param sourceImage The image shown in the ListView
		 */
		private void addDragEventHandlerToImage(Entry<Tile, Image> entry, ImageView sourceImage) {
		    //add drag event handler
		    sourceImage.setOnDragDetected(new EventHandler <MouseEvent>() {
		    public void handle(MouseEvent event) {
		        /* drag was detected, start drag-and-drop gesture*/
		        //System.out.println("onDragDetected");
			Tile tile = entry.getKey();
		        /* allow any transfer mode */
		        Dragboard db = sourceImage.startDragAndDrop(TransferMode.ANY);
		     
		        /* put a string on dragboard */
		        ClipboardContent content = new ClipboardContent();
		        Image dragImage = scaleImageBasedOnTileDimensions(sourceImage, tile);
		        content.putImage(dragImage);
		        content.put(DataFormat.lookupMimeType("Type")==null?new DataFormat("Type"):DataFormat.lookupMimeType("Type"), "Tile");
		        content.put(DataFormat.lookupMimeType("Tile")==null?new DataFormat("Tile"):DataFormat.lookupMimeType("Tile"), tile);
		        db.setContent(content);     
		        event.consume();
     }
  });
		}
		//image scaling from https://stackoverflow.com/questions/27894945/how-do-i-resize-an-imageview-image-in-javafx
		private Image scaleImageBasedOnTileDimensions(ImageView source, Tile tileData) {
		    Image sourceImage = source.getImage();
		    double originalWidth = sourceImage.getWidth();
		    double originalHeight = sourceImage.getHeight();
		    int widthCoefficient = tileData.getWidth();
		    int heightCoefficient = tileData.getHeight();
//		    System.out.println("Scaled by " + widthCoefficient + "x" + heightCoefficient);
		    double scaledWidth = originalWidth * widthCoefficient;
		    double scaledHeight = originalHeight * heightCoefficient;
		    Image scaledImage = Path2Image.scale(Path2Image.showImage(tileData.getWholePic()), 
			    (int)scaledWidth,(int)scaledHeight, true).snapshot(null, null);
		    return scaledImage;
		}


		/**
		 * adds the tile specified by the entry to the ListView
		 * @param entry Specifies a kind of tile
		 * @return The Imageview created for this tile in the ListView
		 */
		private ImageView addTileImageToListView(Entry<Tile, Image> entry) {
		    HBox finalPane = new HBox();
		    VBox totalPane = new VBox(); //for name and picture of a tile
		    HBox subPane = new HBox(); // for name of a tile
		    Label name = new Label(entry.getKey().getName() + ": " + 
		        new Integer(entry.getKey().getWidth()).toString() + "x" + 
		        new Integer(entry.getKey().getHeight()).toString());
		    name.setFont(new Font(11));
		    subPane.getChildren().add(name);
		    subPane.setAlignment(Pos.BOTTOM_LEFT); 
		    totalPane.getChildren().add(subPane);
		    ImageView sourceImage = new ImageView(entry.getValue());
		    totalPane.getChildren().add(sourceImage);
		    //totalPane.getChildren().add()
		    finalPane.getChildren().add(totalPane);
		    paneListView.getItems().add(finalPane);
		    return sourceImage;
		}




	}

