package engine.battle;

import java.util.Observable;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author nathanlewis
 *
 */

public class HealthBar {
	
	private Rectangle healthBar;
	private Rectangle healthBackgroundBar;
	private double finalWidth;
	private double totalHealth;
	private double totalWidth;
	private double lastHealth;
	private boolean increase;
	private Pane healthPane = new Pane();
	
	/**
	 * Using this constructor the user can 
	 * @param health
	 * @param width
	 * @param height
	 * @param xPos
	 * @param yPos
	 */
	public HealthBar(double health, double width, double height, double xPos, double yPos) {
		
		healthBar = new Rectangle(xPos,yPos,width,height);
		
		healthBar.setArcHeight(10);
		healthBar.setArcWidth(10);
		healthBar.setFill(Color.LIGHTGREEN);
		totalWidth = width;
		totalHealth = health;
		lastHealth = health;
		increase = false;
		
		
	}
	
	public HealthBar(double health, double width, double height) {
		healthBackgroundBar = new Rectangle(width,height);
		healthBackgroundBar.setArcHeight(10);
		healthBackgroundBar.setArcWidth(10);
		healthBackgroundBar.setFill(Color.DARKGREY);
		
		
		healthBar = new Rectangle(width,height);
		healthBar.setArcHeight(10);
		healthBar.setArcWidth(10);
		healthBar.setFill(Color.LIGHTGREEN);
		totalWidth = width;
		totalHealth = health;
		lastHealth = health;
		increase = false;
		
		healthPane.getChildren().add(healthBackgroundBar);
		healthPane.getChildren().add(healthBar);
	}

	
	public Rectangle getHealthBar() {
		return healthBar;
	}
	
	public void setHealth(double health, boolean animate) {
		System.out.println("New Health: " + health + "Last Health :" + lastHealth);
		if(health != lastHealth) {
			if(health > 0) finalWidth = (health/totalHealth) * totalWidth;
			else if(health <= 0) finalWidth = 0;
			double diff = Math.abs(lastHealth - health);
			if(animate) {
				increase = lastHealth < health? true:false;
				double delta = ((diff/totalHealth) * totalWidth)/10;
				AnimationTimer timer = new AnimationTimer() {
					private long lastUpdate = 0;
					@Override
					public void handle(long now) {
						if(now - lastUpdate >= 100000000) {
							
							if(!increase) {
								double newWidth = healthBar.getWidth() - delta;
								healthBar.setWidth(newWidth);
								if(newWidth <= finalWidth) {
									healthBar.setWidth(finalWidth);
									stop();
								}
							} else if(increase) {
								double newWidth = healthBar.getWidth() + delta;
								healthBar.setWidth(newWidth);
								if(newWidth >= finalWidth) {
									healthBar.setWidth(finalWidth);
									stop();
								}
							}
							changeColorBasedOnPercentHealth();
							
							lastUpdate = now;
						}
					}
					
				}; timer.start();
			} else {
				System.out.println("Width changed");
				healthBar.setWidth(finalWidth);
			}
			
			lastHealth = health;
		}
	}

	private void changeColorBasedOnPercentHealth() {
	    if(healthBar.getWidth() < totalWidth * 0.3) healthBar.setFill(Color.RED);
	    else if(healthBar.getWidth() < totalWidth * 0.6) healthBar.setFill(Color.ORANGE);
	    else healthBar.setFill(Color.LIGHTGREEN);
	}

	public Pane getPane() {
		return healthPane;
	}
}
