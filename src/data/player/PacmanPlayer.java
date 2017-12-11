package data.player;

import java.io.Serializable;

/**
 * 
 * @author nathanlewis
 *
 */
public class PacmanPlayer implements Serializable {
	
	private static final long serialVersionUID = 4508454545924283257L;
	protected int posX, posY;
	
	public PacmanPlayer() {
		posX=0;
		posY=0;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

}
