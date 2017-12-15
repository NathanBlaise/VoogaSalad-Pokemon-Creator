package engine.pokemonScene;

import authoring.ScreenDisplay;
import data.Database;
import data.player.Player;
import engine.UI.Path2Image;
import engine.battle.BattleScene;
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
	public pokemonScene(int width, int height, Paint background,  UserPage userPage, Player player) {
		super(width, height, background);
		
		
		this.rootAdd(new ImageView(pSceArt.BG));
		this.rootAdd(pSceArt.drawColBox(player), 330, 20);
		this.rootAdd(cancel,650,450);
		this.rootAdd(new curPokemonCol(Path2Image.showImage(player.getPokemons()[0].getCurrentImagePath()) ,player.getPokemons()[0].getCurrentLevel() ,player.getPokemons()[0].getName(), 100,100),0,150);
		cancel.addEventFilter(MouseEvent.MOUSE_CLICKED, e->userPage.goBackToOriScene());
		
		
	}
	
	
	public pokemonScene(int width, int height, Paint background, BattleScene bSce, Player player) {
		super(width, height, background);
		
		this.rootAdd(new ImageView(pSceArt.BG));
		
		this.rootAdd(pSceArt.drawColBox(bSce,player), 330, 20);
		this.rootAdd(cancel,650,450);
		System.out.println(player.getPokemons()[0].getCurrentImagePath());
		this.rootAdd(new curPokemonCol(Path2Image.showImage(player.getPokemons()[0].getCurrentImagePath()) ,player.getPokemons()[0].getCurrentLevel() ,player.getPokemons()[0].getName(), 100, 100, bSce),0,150);
		cancel.addEventFilter(MouseEvent.MOUSE_CLICKED, e->bSce.goBackToOriScene());
		
		
	}
	
	
	
	

}
