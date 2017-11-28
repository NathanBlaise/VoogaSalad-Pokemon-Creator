package engine.movement;

import java.util.BitSet;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Class to register key press input, can be used anywhere allowing easy addition for two players
 * @author nathanlewis
 *
 */

public class Input {
	
	private Scene myScene;
	
	private BitSet keyboardBitSet = new BitSet();
	
	private KeyCode upKey = KeyCode.UP;
    private KeyCode downKey = KeyCode.DOWN;
    private KeyCode leftKey = KeyCode.LEFT;
    private KeyCode rightKey = KeyCode.RIGHT;
	
	public Input (Scene scene) {
		myScene = scene;
	}
	
	/*
	 * Adds listeners for keys, need to be removed when scene changes
	 */
	public void addListeners() {
        myScene.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> keyboardBitSet.set(event.getCode().ordinal(), true));
        myScene.addEventFilter(KeyEvent.KEY_RELEASED, (event) -> keyboardBitSet.set(event.getCode().ordinal(), false));
    }

    public void removeListeners() {
        myScene.removeEventFilter(KeyEvent.KEY_PRESSED, (event) -> keyboardBitSet.set(event.getCode().ordinal(), true));
        myScene.removeEventFilter(KeyEvent.KEY_RELEASED, (event) -> keyboardBitSet.set(event.getCode().ordinal(), false));
    }
    
	/*
	 * Registers each key press, if up and down are pressed for example it won't work
	 */
    public boolean isMoveUp() {
        return keyboardBitSet.get( upKey.ordinal()) && !keyboardBitSet.get( downKey.ordinal());
    }

    public boolean isMoveDown() {
        return keyboardBitSet.get( downKey.ordinal()) && !keyboardBitSet.get( upKey.ordinal());
    }

    public boolean isMoveLeft() {
        return keyboardBitSet.get( leftKey.ordinal()) && !keyboardBitSet.get( rightKey.ordinal());  
    }

    public boolean isMoveRight() {
        return keyboardBitSet.get( rightKey.ordinal()) && !keyboardBitSet.get( leftKey.ordinal());
    }
    
}
