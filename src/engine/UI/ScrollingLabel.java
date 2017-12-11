package engine.UI;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * Made for UTILITY purposes, we use pokemonLabel
 * @author nathanlewis
 *
 */
public class ScrollingLabel extends Label {
	
	private String myString;
	private Label myLabel;
	
	public ScrollingLabel() {
		super();
	}
	
	public ScrollingLabel(String string) {
		super(string);
	}
	
	public ScrollingLabel(String string, Font f) {
		super(string);
		this.setFont(f);
	}
	
	public ScrollingLabel(Font f) {
		super();
		this.setFont(f);
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
						lastUpdate = now;
					}
					if(count == myString.length()) {
						stop();
					}
				}
				
			}; timer.start();
	} 
	
}
