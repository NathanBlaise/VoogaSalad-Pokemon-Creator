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
	private int row;
	private int col;
	private GridPane myGrid;
	
	/*ordinary cell constructor*/
	public DBCell(int row, int col, Image image, DBEvent event, GridPane grid) {
		this.row = row;
		this.col = col;
		this.image = new ImageView(image);
		this.myEvent = event;
		this.myGrid = grid;
		setDragTarget();
	}
	
	
	/*default cell constructor for the initial map*/
	public DBCell(int row, int col, GridPane grid) {
		this.row = row;
		this.col = col;
		this.image = new ImageView(new Image(REGTILE_PATH));
		this.myEvent = new DBEvent();
		this.myGrid = grid;
		setDragTarget();
		
	}
	
	
	/**
	 * @param myGrid: the grid pane you want to put the cell in
	 * add a imageView into a specific cell given by the coordinate of int row, int col
	 */
	public void UpdateDBCell(GridPane grid) {
		grid.add(image,row,col);
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
	                if (db.hasString() ) {
	                	myGrid.add(new ImageView(db.getImage()), row-1,col);
	                	myGrid.add(new ImageView(), row-1, col+1);
	                	myGrid.add(new ImageView(), row-1, col-1);
	                	myGrid.add(new ImageView(), row, col-1);
	                	myGrid.add(new ImageView(), row, col+1);
	                	myGrid.add(new ImageView(), row, col);
	                	myGrid.add(new ImageView(), row-2, col+1);
	                	myGrid.add(new ImageView(), row-2, col-1);
	                	myGrid.add(new ImageView(), row-2, col);
	                	
	                	success = true;
	                } else {
	                 image = new ImageView(db.getImage());
	                 UpdateDBCell(myGrid);
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
