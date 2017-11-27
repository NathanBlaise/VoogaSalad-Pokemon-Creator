package authoring.dragdrop;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import authoring.eventManage.NPCEventEditor;
import authoring.eventManage.PokemonEventEditor;
import data.event.Event;
import data.event.EventNPC;
import data.map.Cell;
import data.model.NPC;
import data.model.Pokemon;
import data.model.PokemonSpecie;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
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
	/*instance variables*/
	private Cell cell;
	
	private int row;
	private int col;
	private GridPane myGrid;
	private cellDelegate app;
	
	/*ordinary cell constructor*/
	public DBCell(int row, int col, Cell cell, GridPane grid, cellDelegate app) {
		this.row = row;
		this.col = col;
		this.cell = cell;
		this.myGrid = grid;
		this.app = app;
		myGrid.add(setDragTarget(this.cell),col,row);
	}
	
	
	/**
	 * @param newImage: the path of image you want to change 
	 * add a imageView into a specific cell given by the coordinate of int row, int col
	 */
	public DBCell UpdateTileImage(String newImagePath) {
		cell.setTilePath(newImagePath);
		app.updateCellList(this);
		myGrid.add(setDragTarget(cell), col, row);
		return this;
	}
	
	public DBCell UpdatEvent(Event event){
		cell.setEvent(event);
		app.updateCellList(this);
		myGrid.add(setDragTarget(cell), col, row);
		return this;
	}
	
	
	public int getCellRow() {
		return row;
	}
	
	public int getCellCol() {
		return col;
	}
	
	public boolean getState() {
		return cell.isOpenState();
	}
	
	public void setState(boolean newState) {
		cell.setOpenState(newState);
	}

	
	/**
	 * @param - an imageview responding to the drag and drop behavior
	 * Add event handlers to deal with drag board things
	 */
	private ImageView setDragTarget(Cell cell){
		String base, overlap;
		if((cell.getEvent()==null)||(cell.getEvent().getImagePath()==null||cell.getEvent().getImagePath().equals(""))){
			overlap = null;
		}else{
			overlap = cell.getEvent().getImagePath();
		}
		base = cell.getTilePath();
		ImageView image = new ImageView(overlapImage(base, overlap));
		//ImageView image = new ImageView(Path2Image.showImage(cell.getTilePath()));
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
	                if (db.hasString()&& db.getString()=="Shop Tile"){
	                		if (app.checkSurroundingCells(col, row) == true) {
	                			myGrid.add(new ImageView(db.getImage()), col-1,row);
	                			app.UpdateSurroundingCells(col,row);
	                			success = true;
	                		}
	                } else {
	                	/**
	                	 * check the type of drag items and trigger the related events
	                	 */
	                	if((db.getContent(DataFormat.lookupMimeType("Type"))!=null)&&(db.getContent(DataFormat.lookupMimeType("Type")).equals("Tile"))){
	                		String tilePath = (String)db.getContent(DataFormat.lookupMimeType("Path"));
	                		UpdateTileImage(tilePath);
	                	}else if(db.getContent(DataFormat.lookupMimeType("Type")).equals("NPC")){
	                		NPC npc = (NPC)db.getContent(DataFormat.lookupMimeType("NPC"));
	                		@SuppressWarnings("unchecked")
							List<PokemonSpecie> pokemons = (List<PokemonSpecie>)db.getContent(DataFormat.lookupMimeType("PokemonList"));
	                		new NPCEventEditor(new EventNPC(npc), pokemons, (e)->{UpdatEvent(e); return null;});
	                	}else if(db.getContent(DataFormat.lookupMimeType("Type")).equals("Pokemon")){
	                		PokemonSpecie pokemonSpecie = (PokemonSpecie)db.getContent(DataFormat.lookupMimeType("PokemonSpecie"));
	                		@SuppressWarnings("unchecked")
							List<PokemonSpecie> pokemons = (List<PokemonSpecie>)db.getContent(DataFormat.lookupMimeType("PokemonList"));
	                		new PokemonEventEditor(pokemons, new Pokemon(pokemonSpecie,""), (e)->{UpdatEvent(e); return null;});
	                	}
	                	success = true;
	                }
	             }
	             /* let the source know whether the string was successfully 
	              * transferred and used */
	             event.setDropCompleted(success);
	             
	             event.consume();
	         }
	     });

		return image;
	}

	/**
	 * https://stackoverflow.com/questions/2318020/merging-two-images
	 * http://java-buddy.blogspot.com/2013/01/convert-javaawtimagebufferedimage-to.html
	 * overlap one image on another image
	 * @param base
	 * @param overlap
	 * @return
	 */
	private Image overlapImage(String base, String overlap){
		if(overlap==null){
			overlap = new String(base);
		}
		try {
			BufferedImage image = ImageIO.read(new File(base));
			BufferedImage overlay = ImageIO.read(new File(overlap));
			image = resize(image,48,48);
			overlay = resize(overlay,48,48);

			// create the new image, canvas size is the max. of both image sizes
			int w = Math.max(image.getWidth(), overlay.getWidth());
			int h = Math.max(image.getHeight(), overlay.getHeight());
			BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

			// paint both images, preserving the alpha channels
			Graphics g = combined.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.drawImage(overlay, 0, 0, null);

			// Save as new image
			Image result = SwingFXUtils.toFXImage(combined, null);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * to resize the image
	 * https://stackoverflow.com/questions/16497853/scale-a-bufferedimage-the-fastest-and-easiest-way
	 * @param img - the orginal image
	 * @param newW - target width
	 * @param newH - target height
	 * @return
	 */
	private static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(img, 0, 0, newW, newH, null);
	    g2d.dispose();
	    return dimg;
	}  

	public Cell getCell() {
		return cell;
	}
	
	
}
