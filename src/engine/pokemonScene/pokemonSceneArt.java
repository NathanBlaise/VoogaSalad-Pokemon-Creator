package engine.pokemonScene;

import data.player.Player;
import engine.UI.Path2Image;
import engine.battle.BattleScene;
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
	

	
	
	
	public VBox drawColBox(Player player) {
		VBox verticalCol = new VBox();
		verticalCol.getChildren().add(new pokemonCol(Path2Image.showImage(player.getPokemons()[1].getCurrentImagePath()) ,player.getPokemons()[1].getCurrentLevel() ,player.getPokemons()[1].getName(), 100, 100));
		verticalCol.getChildren().add(new pokemonCol(Path2Image.showImage(player.getPokemons()[2].getCurrentImagePath()) ,player.getPokemons()[2].getCurrentLevel() ,player.getPokemons()[2].getName(), 100, 100));
		verticalCol.getChildren().add(new pokemonCol(Path2Image.showImage(player.getPokemons()[3].getCurrentImagePath()) ,player.getPokemons()[3].getCurrentLevel() ,player.getPokemons()[3].getName(), 100, 80));
		verticalCol.getChildren().add(new pokemonCol(Path2Image.showImage(player.getPokemons()[4].getCurrentImagePath()) ,player.getPokemons()[4].getCurrentLevel() ,player.getPokemons()[4].getName(), 100, 100));
		verticalCol.getChildren().add(new pokemonCol(Path2Image.showImage(player.getPokemons()[5].getCurrentImagePath()) ,player.getPokemons()[5].getCurrentLevel() ,player.getPokemons()[5].getName(), 100, 100));
		
		verticalCol.setSpacing(15);
		
		return verticalCol;
	}
	
	
	
	public VBox drawColBox(BattleScene bs, Player player) {
		VBox verticalCol = new VBox();
		verticalCol.getChildren().add(new pokemonCol(Path2Image.showImage(player.getPokemons()[1].getCurrentImagePath()) ,player.getPokemons()[1].getCurrentLevel() ,player.getPokemons()[1].getName(),100, 100,bs));
		verticalCol.getChildren().add(new pokemonCol(Path2Image.showImage(player.getPokemons()[2].getCurrentImagePath()) ,player.getPokemons()[2].getCurrentLevel() ,player.getPokemons()[2].getName(), 100, 100,bs));
		verticalCol.getChildren().add(new pokemonCol(Path2Image.showImage(player.getPokemons()[3].getCurrentImagePath()) ,player.getPokemons()[3].getCurrentLevel() ,player.getPokemons()[3].getName(), 100, 80,bs));
		verticalCol.getChildren().add(new pokemonCol(Path2Image.showImage(player.getPokemons()[4].getCurrentImagePath()) ,player.getPokemons()[4].getCurrentLevel() ,player.getPokemons()[4].getName(), 100, 100,bs));
		verticalCol.getChildren().add(new pokemonCol(Path2Image.showImage(player.getPokemons()[5].getCurrentImagePath()) ,player.getPokemons()[5].getCurrentLevel() ,player.getPokemons()[5].getName(),100,100,bs));
		
		verticalCol.setSpacing(15);
		
		return verticalCol;
	}
	
	
	
	
	
	
	
	
}
