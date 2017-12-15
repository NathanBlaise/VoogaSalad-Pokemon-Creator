package engine.game;

import javafx.animation.AnimationTimer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * text that can print the letters on by one on the screen
 * @author nathanlewis
 *
 */
public class ScrollingText extends Text {
	
	private String myString;
	private Text myText;
	

	public ScrollingText() {
		super();
	}
	
	/**
	 * @param string - the text shown
	 */
	public ScrollingText(String string) {
		super(string);
	}
	
	/**
	 * 
	 * @param string - the text shown
	 * @param f - the font of text
	 */
	public ScrollingText(String string, Font f) {
		super(string);
		this.setFont(f);
	}
	
	/**
	 * 
	 * @param f - font of text
	 */
	public ScrollingText(Font f) {
		super();
		this.setFont(f);
	}
	
	/**
	 * show the text using animation method
	 */
	public void animateText() {
		myString = this.getText();
		this.setText("");
		myText = this;
		AnimationTimer timer = new AnimationTimer() {
			private int count = 0;
			private double lastUpdate = 0;
			private String temp;
			@Override
			public void handle(long now) {
				if(now - lastUpdate >= 100000000) {
					temp = myString;
					myText.setText(temp.substring(0, count+1));
					count++;
					lastUpdate = now;
				}
				if(count == myString.length()) {
					stop();
				}
			}
		}; 
		timer.start();
	} 
	
}
