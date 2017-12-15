package authoring.dragdrop;

import java.util.ArrayList;
import java.util.List;

import authoring.eventManage.NPCEventEditor;
import authoring.eventManage.PacmanEventEditor;
import authoring.eventManage.PokemonEventEditor;
import authoring.eventManage.DIYEventEditor;
import data.event.Event;
import data.event.EventDIY;
import data.event.EventNPC;
import data.event.EventPacmanEnemy;
import data.event.EventPokemon;
import data.map.Cell;
import data.model.NPC;
import data.model.PacmanEnemy;
import data.model.Pokemon;
import data.model.PokemonSpecie;
import data.model.Tile;
import engine.UI.Map2GridPane;
import engine.UI.UIComponentFactory.UIComponentFactory;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
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
 * @author supertony cy122
 *
 */
public class DBCell {
	/*final variables*/

	
	/*instance variables*/
	private Cell cell;
	
	private int row;
	private int col;
	private GridPane myGrid;
	private CellDelegate app;
	
	/*ordinary cell constructor*/
	public DBCell(int row, int col, Cell cell, GridPane grid, CellDelegate app) {
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
	public void UpdateTile(String tilePath, boolean obstacle) {
		cell.setTilePath(tilePath);
		cell.setObstacle(obstacle);
		app.updateCellList(this);
		myGrid.add(setDragTarget(cell), col, row);
	
	}
	
	
	/**
	 * @param newImage: the path of image you want to change 
	 * add a imageView into a specific cell given by the coordinate of int row, int col
	 */
	public void UpdateShopTileImage(Tile tile) {
		app.updateCellList(this);
		// retrieve the cell list from the dbMap
		DBCell[][] cellList = app.getCellList();
		
		int index = 0;
		int up = row - tile.getHeight()/2;
		int left = col - tile.getWidth()/2;
		for (int i = up; i < up+tile.getHeight(); i++) {
			for (int j = left; j < left+tile.getWidth(); j++) {
				cellList[i][j].UpdateTile(tile.getImagePaths().get(index), tile.isObstacle());
				index++;
			}
		}
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
	            
	             if (event.getGestureSource() != image &&
	                     event.getDragboard().hasImage()) {
	              
	                 event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	             }
	             Dragboard db = event.getDragboard();
	             if(DataFormat.lookupMimeType("Type")==null){
	            	 new DataFormat("Type");
	             }
	             if(DataFormat.lookupMimeType("Tile")==null){
					new DataFormat("Tile");
				 }
	             if((db.getContent(DataFormat.lookupMimeType("Type"))!=null)&&
	     				(db.getContent(DataFormat.lookupMimeType("Type")).equals("TileFromMap"))){	
	            	 Tile tile = (Tile)db.getContent(DataFormat.lookupMimeType("Tile"));	
		             if(tile!=null){
			        	UpdateTile(tile.getWholePic(), tile.isObstacle());
			         }
	             }
	             
	             event.consume();
	         }
	     });
		
		//add drag event handler
		image.setOnDragDetected(new EventHandler <MouseEvent>() {
	         public void handle(MouseEvent event) {
	             /* drag was detected, start drag-and-drop gesture*/
	             //System.out.println("onDragDetected");
	             
	             /* allow any transfer mode */
	             Dragboard db = image.startDragAndDrop(TransferMode.ANY);
	             
	             /* put a string on dragboard */
	             ClipboardContent content = new ClipboardContent();
            	 content.putImage(image.getImage());
            	 content.put(DataFormat.lookupMimeType("Type")==null?new DataFormat("Type"):DataFormat.lookupMimeType("Type"), "TileFromMap");
            	 content.put(DataFormat.lookupMimeType("Tile")==null?new DataFormat("Tile"):DataFormat.lookupMimeType("Tile"), new Tile(null, cell.isObstacle(), 1, 1, cell.getTilePath(), new ArrayList<String>(){
            		 
					private static final long serialVersionUID = -8210445998841861129L;

					{
						this.add(cell.getTilePath());
					}}));
	             db.setContent(content);
	             
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
	                	/**
	                	 * check the type of drag items and trigger the related events
	                	 */
	                	dealWithDrag(db);
	                	success = true;
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
		contextMenu.getItems().addAll(UIComponentFactory.createMenuItem("obstacle from "+cell.isObstacle()+" to "+(!cell.isObstacle()), h -> {
			cell.setObstacle(!cell.isObstacle());
		}));
		contextMenu.getItems().addAll(UIComponentFactory.createMenuItem("row: "+row+", column: "+col, h -> {
			
		}));
		contextMenu.getItems().addAll(UIComponentFactory.createMenuItem("edit the event", h -> {
			if(cell.getEvent()!=null){
				if(cell.getEvent() instanceof EventPokemon){
					new PokemonEventEditor(app.getDatabase().getModel().getPokemonSpecies(), ((EventPokemon)cell.getEvent()).getPokemon(), (m)->{UpdatEvent(m); return null;});
				}else if(cell.getEvent() instanceof EventNPC){
					new NPCEventEditor((EventNPC)cell.getEvent(), app.getDatabase().getModel().getPokemonSpecies(), (m)->{UpdatEvent(m); return null;});
				}else if(cell.getEvent() instanceof EventPacmanEnemy) {
					new PacmanEventEditor((EventPacmanEnemy)cell.getEvent(),(m)->{UpdatEvent(m); return null;});
				}else if(cell.getEvent() instanceof EventDIY){
					new DIYEventEditor((EventDIY)cell.getEvent(), app.getDatabase(), (m)->{UpdatEvent(m); return null;});
				}
			}else{
				new DIYEventEditor(new EventDIY(cell.getTilePath()), app.getDatabase(), (m)->{UpdatEvent(m); return null;});
			}
		}));
		contextMenu.getItems().addAll(UIComponentFactory.createMenuItem("remove the event", h -> {
			if(cell.getEvent()!=null){
				UpdatEvent(null);
			}
		}));
		contextMenu.show(image, e.getScreenX(), e.getScreenY());
	}


	private void dealWithDrag(Dragboard db) {
        if(DataFormat.lookupMimeType("Type")==null){
       	 	new DataFormat("Type");
        }
		// add a new stuff just for the shop
		if((db.getContent(DataFormat.lookupMimeType("Type"))!=null)&&
				(db.getContent(DataFormat.lookupMimeType("Type")).equals("Tile"))){
			Tile tile = (Tile)db.getContent(DataFormat.lookupMimeType("Tile"));
			
			if (app.checkSurroundingCells(col, row, tile.getWidth(), tile.getHeight()) == true){
				UpdateShopTileImage(tile);
			}
		
		} else if (db.getContent(DataFormat.lookupMimeType("Type")).equals("NPC")){
			NPC npc = (NPC)db.getContent(DataFormat.lookupMimeType("NPC"));
			@SuppressWarnings("unchecked")
			List<PokemonSpecie> pokemons = (List<PokemonSpecie>)db.getContent(DataFormat.lookupMimeType("PokemonList"));
			new NPCEventEditor(new EventNPC(npc), pokemons, (e)->{UpdatEvent(e); return null;});
		} else if (db.getContent(DataFormat.lookupMimeType("Type")).equals("Pokemon")){
			PokemonSpecie pokemonSpecie = (PokemonSpecie)db.getContent(DataFormat.lookupMimeType("PokemonSpecie"));
			@SuppressWarnings("unchecked")
			List<PokemonSpecie> pokemons = (List<PokemonSpecie>)db.getContent(DataFormat.lookupMimeType("PokemonList"));
			new PokemonEventEditor(pokemons, new Pokemon(pokemonSpecie,""), (e)->{UpdatEvent(e); return null;});
		} else if (db.getContent(DataFormat.lookupMimeType("Type")).equals("PacmanEnemy")) {
			PacmanEnemy pacmanEnemy = (PacmanEnemy)db.getContent(DataFormat.lookupMimeType("PacmanEnemy"));
			new PacmanEventEditor(new EventPacmanEnemy(pacmanEnemy), (e) -> {UpdatEvent(e); return null;});
		}
	}


	public Cell getCell() {
		return cell;
	}
	
	
}
