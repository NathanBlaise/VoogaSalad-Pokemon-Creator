package authoring.dragdrop;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;


/**
 * An individual cell inside the DBMap
 * Include two major parts: 1. The first layer image 2. The second layer DBEvent
 * Also Include Row and Col of a cell inside the grid
 * @author supertony
 *
 */
public class DBCell {
	/*final variables*/
	final static String REGTILE_PATH = "file:images/reg_tile_scaled.png";
	/*instance variables*/
	private ImageView image;
	private DBEvent myEvent;
	private Boolean openState;
	private int row;
	private int col;
	private GridPane myGrid;
	private cellDelegate app;
	
	/*ordinary cell constructor*/
	public DBCell(int row, int col, Image image, DBEvent event, GridPane grid, cellDelegate app) {
		this.row = row;
		this.col = col;
		this.image = new ImageView(image);
		this.myEvent = event;
		this.myGrid = grid;
		this.app = app;
		this.openState = true;
		setDragTarget();
		myGrid.add(this.image,col,row);
	
	}
	
	
	/*default cell constructor for the initial map*/
	public DBCell(int row, int col, GridPane grid,cellDelegate app) {
		this.row = row;
		this.col = col;
		this.image = new ImageView(new Image(REGTILE_PATH));
		this.myEvent = new DBEvent();
		this.myGrid = grid;
		this.app = app;
		setDragTarget();
		myGrid.add(this.image,col,row);
		this.openState = true;
		
	}
	
	
	/**
	 * @param newImage: the image you want to change 
	 * add a imageView into a specific cell given by the coordinate of int row, int col
	 */
	public DBCell UpdateDBCell(ImageView newImage) {
		this.image = newImage;
		app.updateCellList(this);
		myGrid.add(image,col,row);
		setDragTarget();
		return this;
	}
	
	
	public int getCellRow() {
		return row;
	}
	
	public int getCellCol() {
		return col;
	}
	
	public boolean getState() {
		return openState;
	}
	
	public void setState(boolean newState) {
		this.openState = newState;
	}

	
	/**
	 * Add event handlers to deal with drag board things
	 */
	private void setDragTarget(){
		 image.setOnDragOver(new EventHandler <DragEvent>() {
	         public void handle(DragEvent event) {
	             /* data is dragged over the target */
	             //System.out.println("onDragOver");
	             
	             /* accept it only if it is  not dragged from the same node 
	              * and if it has a string data */
	             if (event.getGestureSource() != image &&
	                     event.getDragboard().hasImage()) {
	                 /* allow for both copying and moving, whatever user chooses */
	                 event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	             }
	             
	             event.consume();
	         }
	     });

	     image.setOnDragEntered(new EventHandler <DragEvent>() {
	         public void handle(DragEvent event) {
	             /* the drag-and-drop gesture entered the target */
	             //System.out.println("onDragEntered");
	             /* show to the user that it is an actual gesture target */
	             if (event.getGestureSource() != image &&
	                     event.getDragboard().hasImage()) {
	                 
	             }
	             
	             event.consume();
	         }
	     });

	     image.setOnDragExited(new EventHandler <DragEvent>() {
	         public void handle(DragEvent event) {
	             /* mouse moved away, remove the graphical cues */
	             
	             event.consume();
	         }
	     });
	     
	    image.setOnDragDropped(new EventHandler <DragEvent>() {
	         public void handle(DragEvent event) {
	             /* data dropped */
	             System.out.println("onDragDropped");
	             /* if there is an image data on dragboard, read it and use it */
	             Dragboard db = event.getDragboard();
	             boolean success = false;
	             if (db.hasImage()) {
	            	 	System.out.println(app.checkSurroundingCells(col, row) == true );
	                if (db.hasString()){
	                		if (app.checkSurroundingCells(col, row) == true) {
	                	myGrid.add(new ImageView(db.getImage()), col-1,row);
	                	app.UpdateSurroundingCells(col,row);
	                	success = true;
	                		}

	                } else {
	                 UpdateDBCell(new ImageView(db.getImage()));
	                 success = true;
	                }
	             }
	             /* let the source know whether the string was successfully 
	              * transferred and used */
	             event.setDropCompleted(success);
	             
	             event.consume();
	         }
	     });

		
	}
	
	
}
