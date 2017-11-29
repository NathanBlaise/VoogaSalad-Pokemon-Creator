package authoring.dragdrop;

import java.util.List;

import authoring.eventManage.NPCEventEditor;
import authoring.eventManage.PokemonEventEditor;
import data.event.Event;
import data.event.EventNPC;
import data.event.EventPokemon;
import data.map.Cell;
import data.model.NPC;
import data.model.Pokemon;
import data.model.PokemonSpecie;
import engine.UI.Map2GridPane;
import engine.UI.UIComponentFactory.UIComponentFactory;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
		ImageView image = new ImageView(new Map2GridPane().overlapImage(base, overlap));
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
	             //System.out.println("onDragDropped");
	             /* if there is an image data on dragboard, read it and use it */
	             Dragboard db = event.getDragboard();
	             boolean success = false;
	             if (db.hasImage()) {
	            	 	//System.out.println(app.checkSurroundingCells(col, row) == true );
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
	                	dealWithDrag(db);
	                	success = true;
	                }
	             }
	             /* let the source know whether the string was successfully 
	              * transferred and used */
	             event.setDropCompleted(success);
	             
	             event.consume();
	         }     
	     });
	    
	    image.setOnMouseClicked(e->{
	    	if (e.getButton() == MouseButton.SECONDARY) {
	            showContentMenu(e, image);
	        }
	    });

		return image;
	}
	    
	private void showContentMenu(MouseEvent e, ImageView image) {
		ContextMenu contextMenu = new ContextMenu();
		contextMenu.getItems().addAll(UIComponentFactory.createMenuItem("remove the event", h -> {
			if(cell.getEvent()!=null){
				UpdatEvent(null);
			}
		}));
		contextMenu.getItems().addAll(UIComponentFactory.createMenuItem("edit the event", h -> {
			if(cell.getEvent()!=null){
				if(cell.getEvent() instanceof EventPokemon){
					new PokemonEventEditor(app.getDatabase().getModel().getPokemonSpecies(), ((EventPokemon)cell.getEvent()).getPokemon(), (m)->{UpdatEvent(m); return null;});
				}else if(cell.getEvent() instanceof EventNPC){
					new NPCEventEditor((EventNPC)cell.getEvent(), app.getDatabase().getModel().getPokemonSpecies(), (m)->{UpdatEvent(m); return null;});
				}
			}
		}));
		contextMenu.show(image, e.getScreenX(), e.getScreenY());
	}


	private void dealWithDrag(Dragboard db) {
		if((db.getContent(DataFormat.lookupMimeType("Type"))!=null)&&
				(db.getContent(DataFormat.lookupMimeType("Type")).equals("Tile"))){
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
	}


 

	public Cell getCell() {
		return cell;
	}
	
	
}
