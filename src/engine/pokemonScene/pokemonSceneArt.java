package engine.pokemonScene;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * @author supertony
 * The art of pokemon scene
 */
public class pokemonSceneArt {
	/*final variable*/
	public final Image BG =new Image("file:images/pokemonList.png");
	public final Image SELECT_BOX =new Image("file:images/selectBox.png",144,60,false,false);
	public final Image DESELECT_BOX =new Image("file:images/DeselectBox.png",144,60,false,false);
	public final Image OPEN_BALL =new Image("file:images/PokeballSelect.png",144,60,false,false);
	public final Image CLOSE_BALL = new Image("file:images/PokeballUnselect.png",144,60,false,false);
	public final Image FIRST_POKEMON = new Image("file:images/firstPoke.png",144,60,false,false);
	public final Image FIRST_POKEMON_SELECT = new Image("file:images/selectFirstPoke.png",144,60,false,false);
	public final Image POKE_TEST = new Image("file:images/261_scaled.gif",45,45,false,false);
	

	
	
	
	public VBox drawColBox() {
		VBox verticalCol = new VBox();
		verticalCol.getChildren().add(new pokemonCol(POKE_TEST, 3,"TONY", 100, 100));
		verticalCol.getChildren().add(new pokemonCol(POKE_TEST, 3,"TONY", 100, 100));
		verticalCol.getChildren().add(new pokemonCol(POKE_TEST, 3,"TONY", 100, 80));
		verticalCol.getChildren().add(new pokemonCol(POKE_TEST, 3,"TONY", 100, 100));
		verticalCol.getChildren().add(new pokemonCol(POKE_TEST, 3,"TONY", 100, 100));
		
		verticalCol.setSpacing(15);
		
		return verticalCol;
	}
	
	
	
	
	
	
	
	
}
