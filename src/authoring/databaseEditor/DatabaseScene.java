package authoring.databaseEditor;

import java.util.List;

import data.model.PokemonSpecie;
import data.player.Player;
import javafx.geometry.Side;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.input.MouseEvent;
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

	public DatabaseScene(Paint white, StageDelegate app) {
		super(white,app);
		super.getGoSceneButton().setText("Finish");
		super.getGoSceneButton().addEventHandler(MouseEvent.MOUSE_CLICKED, e->{app.GoButtonPressed(); app.getStage().close();});
//		app.GoButtonPressed());
		List<PokemonSpecie> species = app.getDatabase().getModel().getPokemonSpecies();
		Player player = app.getDatabase().getPlayer();
		int XLength = app.getDatabase().getMap().getXlength();
		int YLength = app.getDatabase().getMap().getYlength();
		tabPane = new TabPane(new PlayerTab(player, species, XLength, YLength, new Callback<Player, Integer>(){
			@Override
			public Integer call(Player param) {
				app.getDatabase().setPlayer(param);
				return null;
			}		
		}).getTab());
		tabPane.setSide(Side.TOP);
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabPane.setPrefSize(getWidth(), getButtonY());
		this.getRootChildren().add(tabPane);
	}

}
