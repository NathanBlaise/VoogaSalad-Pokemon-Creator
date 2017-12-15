package engine.pokemonScene;

import engine.battle.HealthBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class curPokemonCol extends Pane {
	/*final variable*/
	public final Image SELECT_BOX =new Image("file:images/selectFirstPoke.png",240,160,false,false);
	public final Image DESELECT_BOX =new Image("file:images/firstPoke.png",240,160,false,false);
	public final Image OPEN_BALL =new Image("file:images/PokeballSelect.png",50,50,false,false);
	public final Image CLOSE_BALL = new Image("file:images/PokeballUnselect.png",50,50,false,false);
	/*instance variable*/
	//use composition
	private pokemonCol pCol;
	private ImageView bg;
	private VBox nameLevel; 
	private ImageView pokeBall;
	private ImageView myImage;
	private HealthBar hBar;
	public curPokemonCol (Image pokeImage,int le,String nam, int fullHea, int curHea) {
		pCol = new pokemonCol(pokeImage,le,nam,fullHea,curHea);
		bg = new ImageView(DESELECT_BOX);
		pokeBall = new ImageView(CLOSE_BALL);
		this.addEventFilter(MouseEvent.MOUSE_ENTERED, e->selectCol());
		this.addEventFilter(MouseEvent.MOUSE_EXITED, e->DeSelectCol());
		
		myImage = new ImageView(pokeImage);
		hBar= new HealthBar(curHea, 149,12);
		// basic set up
		this.getChildren().add(bg);
		bg.setLayoutX(20);
		bg.setLayoutY(0);
		nameLevel = new VBox();
		pCol.setUpVerticalBox(nameLevel, 15);
		this.getChildren().add(nameLevel);
		nameLevel.setLayoutX(105);
		nameLevel.setLayoutY(30);
		this.getChildren().add(pokeBall);
		this.getChildren().add(myImage);
		myImage.setLayoutX(0);
		myImage.setLayoutY(0);
		
		this.getChildren().add(hBar.getPane());
		hBar.getPane().relocate(91, 106);
	}

	private void DeSelectCol() {
		bg.setImage(DESELECT_BOX);
		pokeBall.setImage(CLOSE_BALL);
	}

	private Object selectCol() {
		bg.setImage(SELECT_BOX);
		pokeBall.setImage(OPEN_BALL);
		
		return null;
	}

	
}
