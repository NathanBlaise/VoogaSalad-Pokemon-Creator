package engine.UI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class PokemonLabel extends Label {
	
	private final int FONTSIZE = 12;
	private String myString;
	private Label myLabel;
	private AnimationTimer timer;
	
	public PokemonLabel() {
		super();
		this.setFont(getPokemonFont());
	}
	
	public PokemonLabel(String string) {
		super(string);
		this.setFont(getPokemonFont());
	}
	
	
	/**
	 * @return the specific Pokemon font
	 */
	public Font getPokemonFont() {
		Font f = new Font(30) ;
		try {
			f = Font.loadFont(new FileInputStream(new File("./font/font.ttf")), FONTSIZE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}
	
	
	public void stopTimer() {
		timer.stop();
	}
	
	public void animateText() {
		myString = this.getText();
		this.setText("");
		myLabel = this;
			timer = new AnimationTimer() {
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
