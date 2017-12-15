package engine.pokemonScene;

import authoring.ScreenDisplay;
import engine.game.UserPage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

/**
 * @author supertony
 * The pokemon Scene after player click the pokemon colomn in channel
 *
 */
public class pokemonScene extends ScreenDisplay {
	
	public final Image POKE_TEST = new Image("file:images/261_scaled.gif",45,45,false,false);

	/*instance variable*/
	private pokemonSceneArt pSceArt = new pokemonSceneArt();
	private Button cancel = new Button("CANCEL");
	public pokemonScene(int width, int height, Paint background,  UserPage userPage) {
		super(width, height, background);
		
		this.rootAdd(new ImageView(pSceArt.BG));
		this.rootAdd(pSceArt.drawColBox(), 330, 20);
		this.rootAdd(cancel,650,450);
		this.rootAdd(new curPokemonCol(POKE_TEST, 3,"TONY", 100, 100),0,150);
		cancel.addEventFilter(MouseEvent.MOUSE_CLICKED, e->userPage.goBackToOriScene());
		
		
	}
	
	
	
	

}
