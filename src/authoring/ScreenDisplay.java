package authoring;

import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class ScreenDisplay {
		// Final Variable
		public double FRAMES_PER_SECOND = 1;
		public double MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
		public double SECOND_DELAY = 100.0 / FRAMES_PER_SECOND;
		
		//Private Variable
		protected Timeline animation = new Timeline();
		private Scene myScene;
		private Pane root = new Pane();

		/**
		 * Constructor: Screen Display class
		 */

		public ScreenDisplay(int width, int height, Paint background) {
			init();
			myScene = new Scene(root, width, height, background);

		}

	

		public Scene getScene() {
			return myScene;
		}
		
		public ObservableList<Node> getRootChildren() {
			return root.getChildren();
		}
		
		public void init() {
			//frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> this.step(SECOND_DELAY));
			animation.setCycleCount(Timeline.INDEFINITE);
			//animation.getKeyFrames().add(frame);
		}

		public void rootAdd(Node object) {
			root.getChildren().add(object);
		}
		
		public void rootAdd(Node object, int row, int col) {
			root.getChildren().add(object);
			object.setLayoutX(row);
			object.setLayoutY(col);
			
		}

		public void rootRemove(Node object) {
			root.getChildren().remove(object);
		}
		
		protected boolean rootContain(Node object) {
			return root.getChildren().contains(object);
		}
		
		protected void rootClear(){
			root.getChildren().clear();
		}
		
		protected Pane getRoot(){
			return root;
		}
		
}

