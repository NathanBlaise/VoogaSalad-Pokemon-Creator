package engine.UI;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class ScrollingLabel extends Label {
	
	private String myString;
	private Label myLabel;
	
	public ScrollingLabel() {
		super();
	}
	
	public ScrollingLabel(String string) {
		super(string);
	}
	
	public void animateText() {
		myString = this.getText();
		this.setText("");
		myLabel = this;
			AnimationTimer timer = new AnimationTimer() {
				private int count = 0;
				private double lastUpdate = 0;
				private String temp;
				@Override
				public void handle(long now) {
					if(now - lastUpdate >= 100000000) {
						temp = myString;
						myLabel.setText(temp.substring(0, count+1));
						count++;
						System.out.println(count+" "+myString);
						lastUpdate = now;
					}
					if(count == myString.length()) {
						System.out.println("Stop");
						stop();
					}
				}
				
			}; timer.start();
	} 
	
}
