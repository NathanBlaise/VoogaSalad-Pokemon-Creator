package engine.UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

/**
 * 
 * @author cy122
 *
 */
public class Map2GridPane {

	/**
	 * https://stackoverflow.com/questions/2318020/merging-two-images
	 * http://java-buddy.blogspot.com/2013/01/convert-javaawtimagebufferedimage-to.html
	 * overlap one image on another image
	 * @param base
	 * @param overlap
	 * @return
	 */
	
	/*Final Variable*/
	final static String SHOP_PATH = "images/pokemonCenter.png";
	
	public Image overlapImage(String base, String overlap){
		if(overlap==null){
			overlap = new String(base);
		}
		try {
			BufferedImage image = ImageIO.read(new File(base));

			BufferedImage overlay = ImageIO.read(new File(overlap));
			
			//check if it is the shop_path
		    
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
			e.printStackTrace();//handled by exiting the program
			System.exit(1);
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
	private BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(img, 0, 0, newW, newH, null);
	    g2d.dispose();
	    return dimg;
	} 
}
