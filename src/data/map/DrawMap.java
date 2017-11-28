package data.map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Used by Game Scene to draw the Game Map on the screen
 * @author nathanlewis
 *
 */

public class DrawMap extends DrawPane{

	public DrawMap(GameMap map) {
		super(map);
		drawCells();
	}
	
	/*
	 * Used in game scene to retrieve the grid pane for drawing
	 */
	public GridPane getPane() {
		return myPane;
	}
	
	/*
	 * Adds the cell image to the grid pane
	 */
	private void drawCells() {
		for (int i = 0; i < myMap.getXlength(); i++) {
			for (int j = 0; j < myMap.getYlength(); j++) {
				myPane.add(getCellImage(myMap.getCells()[i][j]), j, i);
			}
		}
	}
	
	/*
	 * Returns cell image view based on tile path
	 */
	private ImageView getCellImage(Cell cell) {
		ImageView image = createImageView(cell.getTilePath());
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
			e.printStackTrace();
			System.out.println("Tile image could not be loaded");
		}
		Image image = SwingFXUtils.toFXImage(bufImage, null);
		ImageView graphic = new ImageView(image);
		graphic.setFitHeight(PANE_CELL_SIZE);
		graphic.setFitWidth(PANE_CELL_SIZE);
		return graphic;
	}
	
}
