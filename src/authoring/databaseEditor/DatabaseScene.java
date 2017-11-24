package authoring.databaseEditor;

import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.paint.Color;
import authoring.BasicAuthorScreen;
import authoring.StageDelegate;

public class DatabaseScene extends BasicAuthorScreen {
	private TabPane tabPane;
//	AnchorPane anchorPaneContent = new AnchorPane();

	public DatabaseScene(Color white, StageDelegate app) {
		super(white,app);
		tabPane = new TabPane(new PlayerTab().getTab());
		tabPane.setSide(Side.TOP);
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabPane.setPrefSize(getWidth(), getButtonY());
		this.getRootChildren().add(tabPane);
	}

}
