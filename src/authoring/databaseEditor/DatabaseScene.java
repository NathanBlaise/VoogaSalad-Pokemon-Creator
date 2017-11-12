package authoring.databaseEditor;

import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.paint.Color;
import authoring.BasicAuthorScreen;
import authoring.StageDelegate;

public class DatabaseScene extends BasicAuthorScreen {
	private TabPane tabPane = new TabPane();

	public DatabaseScene(Color white, StageDelegate app) {
		super(white,app);
		Tab tabMonster = new Tab("Edit Monster");
		Tab tabNPC = new Tab("Edit NPC");
		Tab tabPlayer = new Tab("Edit Player");
		tabPane.getTabs().add(tabMonster);
		tabPane.getTabs().add(tabNPC);
		tabPane.getTabs().add(tabPlayer);
		tabPane.setSide(Side.LEFT);
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		this.rootAdd(tabPane);
	}

}
