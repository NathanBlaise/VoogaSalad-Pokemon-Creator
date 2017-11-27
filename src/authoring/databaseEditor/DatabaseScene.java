package authoring.databaseEditor;

import java.util.ArrayList;
import java.util.List;

import tests.authoring.Specie1;
import tests.authoring.Specie2;
import data.model.PokemonSpecie;
import data.player.Player;
import javafx.geometry.Side;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import authoring.BasicAuthorScreen;
import authoring.StageDelegate;
/**
 * This scene is going to only edit the player for now
 * @author cy122
 *
 */
public class DatabaseScene extends BasicAuthorScreen {
	private TabPane tabPane;
//	AnchorPane anchorPaneContent = new AnchorPane();

	public DatabaseScene(Paint white, StageDelegate app) {
		super(white,app);
		List<PokemonSpecie> species = new ArrayList<PokemonSpecie>();
		species.add(new Specie1());
		species.add(new Specie2());
		tabPane = new TabPane(new PlayerTab(new Player(), species, 6, 4, new Callback<Player, Integer>(){
			@Override
			public Integer call(Player param) {
				
				return null;
			}		
		}).getTab());
		tabPane.setSide(Side.TOP);
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabPane.setPrefSize(getWidth(), getButtonY());
		this.getRootChildren().add(tabPane);
	}

}
