package data.map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import data.event.Event;
import data.event.EventPacmanEnemy;
import data.event.EventPokemon;
import engine.UI.Path2Image;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Used by Game Scene to draw the Game Map on the screen
 * @author nathanlewis
 *
 */


public class DrawMap extends DrawPane{
	
	/*FINAL VIARABLE*/
	final static int TILE_SIZE = 48;
	
	HashMap<Event,ImageView> movingEnemies;
	

	public DrawMap(GameMap map, GraphicsContext gc) {
		super(map);
		generateTiles(gc);
	}
	
	/*
	 * Used in game scene to retrieve the grid pane for drawing
	 */
	public GridPane getPane() {
		return myPane;
	}
	
	
	/*
	 * Returns cell image view based on tile path
	 */
	private ImageView getCellEventImage(Cell cell) {
		ImageView image = new ImageView();
		image = createImageView(cell.getEvent().getImagePath());
		return image;
	}
	
	/*
	 * Simple method to return an image view based on tile path name
	 */
	private ImageView createImageView(String filename) {
		BufferedImage bufImage = null;
		try {
			bufImage = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();//handled by exiting the program
			System.out.println("Tile image could not be loaded");
			System.exit(1);
		}
		Image image = SwingFXUtils.toFXImage(bufImage, null);
		ImageView graphic = new ImageView(image);
		graphic.setFitHeight(PANE_CELL_SIZE);
		graphic.setFitWidth(PANE_CELL_SIZE);
		return graphic;
	}
	
	

	
	/*
	 * Simple method to generate all the tiles below the NPC
	 */
	
	private void generateTiles(GraphicsContext gc) {
		movingEnemies = new HashMap<Event,ImageView>();
		for (int i = 0; i < myMap.getXlength(); i++) {
			for (int j = 0; j < myMap.getYlength(); j++) {
				Cell cell = myMap.getCells()[i][j];
				gc.drawImage(Path2Image.scale(Path2Image.showImage(cell.getTilePath()), TILE_SIZE, TILE_SIZE, false).snapshot(null, null),  j * TILE_SIZE, i * TILE_SIZE);
				if (cell.isObstacle()) {
					// add empty imageView
					ImageView defaultPicture = new ImageView(Path2Image.showImage("images/default.png"));					
					String style_outter = "-fx-border-color: black;"
							+ "-fx-border-width: 10;";
					defaultPicture.setStyle(style_outter);
					myPane.add(defaultPicture, j, i);	
				}
				if(cell.getEvent()!=null) {
					if(!(cell.getEvent() instanceof EventPokemon)) {
						ImageView target = getCellEventImage(myMap.getCells()[i][j]);
						target.setFitHeight(TILE_SIZE);
						target.setFitWidth(TILE_SIZE);
						if(cell.getEvent() instanceof EventPacmanEnemy) {
							System.out.println("Added enemy image");
							movingEnemies.put(cell.getEvent(), target);
							((EventPacmanEnemy) cell.getEvent()).setXPos(j);
							((EventPacmanEnemy) cell.getEvent()).setYPos(i);
							cell.setObstacle(false);
						} else myPane.add(target, j, i);
					}
				}
			}
		}
	}
	
	/**
	 * Method used to get moving image views and event specifics
	 * @return
	 */
	public Map<Event,ImageView> getMovingEvents(){
		return movingEnemies;
	}
	
}
