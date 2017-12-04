package engine.movement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

/**
 * Class to register key press input, can be used anywhere allowing easy addition for two players
 * @author nathanlewis
 *
 */

public class Input {
	
	private Scene myScene;
	
	private Map<String, ArrayList<Function<String, Integer>>> pressHandler = new InputHandler(); //this is for register a handler for different kind of component
	private Map<String, ArrayList<Function<String, Integer>>> releaseHandler = new InputHandler(); //this is for register a handler for different kind of component
	
	
	private Set<String> inputList = new HashSet<String>(){
		private static final long serialVersionUID = -7808020477777447026L;
		@Override
		public boolean add(String element){
			boolean flag = false;
			flag = super.add(element);
			if(pressHandler.keySet().contains(element)){
				for(int i=0;;i++){
					if(i>=pressHandler.get(element).size()){
						break;
					}else{
						pressHandler.get(element).get(i).apply(element);
					}
				}
			}
			return flag;
		}
		
		@Override
		public boolean remove(Object element){
			boolean flag = false;
			flag = super.remove(element);
			if(releaseHandler.keySet().contains(element)){
				for(int i=0;;i++){
					if(i>=releaseHandler.get(element).size()){
						break;
					}else{
						releaseHandler.get(element).get(i).apply((String)element);
					}
				}
			}
			return flag;
		}
	};
//	
//	private KeyCode upKey = KeyCode.UP;
//    private KeyCode downKey = KeyCode.DOWN;
//    private KeyCode leftKey = KeyCode.LEFT;
//    private KeyCode rightKey = KeyCode.RIGHT;
	
	public Input (Scene scene) {
		myScene = scene;
	}
	
	/*
	 * Adds listeners for keys, need to be removed when scene changes
	 */
	public void addListeners() {
        myScene.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> inputList.add(event.getCode().toString()));
        myScene.addEventFilter(KeyEvent.KEY_RELEASED, (event) -> inputList.remove(event.getCode().toString()));
    }

    public void removeListeners() {
        myScene.removeEventFilter(KeyEvent.KEY_PRESSED, (event) -> inputList.remove(event.getCode().toString()));
        myScene.removeEventFilter(KeyEvent.KEY_RELEASED, (event) -> inputList.remove(event.getCode().toString()));
    }
    
    public void releaseAllKeys(){
    	inputList.clear();
    }
    
//	/*
//	 * Registers each key press, if up and down are pressed for example it won't work
//	 */
//    public boolean isMoveUp() {
//        return inputList.contains( upKey.toString()) && !inputList.contains( downKey.toString());
//    }
//
//    public boolean isMoveDown() {
//        return inputList.contains( downKey.toString()) && !inputList.contains( upKey.toString());
//    }
//
//    public boolean isMoveLeft() {
//        return inputList.contains( leftKey.toString()) && !inputList.contains( rightKey.toString());  
//    }
//
//    public boolean isMoveRight() {
//        return inputList.contains( rightKey.toString()) && !inputList.contains( leftKey.toString());
//    }

	public Map<String, ArrayList<Function<String, Integer>>> getInputHandler() {
		return pressHandler;
	}
	
	public Map<String, ArrayList<Function<String, Integer>>> getReleaseHandler() {
		return releaseHandler;
	}

	public Set<String> getInputList() {
		return new HashSet<String>(inputList);
	}
	
	private class InputHandler extends HashMap<String, ArrayList<Function<String, Integer>>>{
		private static final long serialVersionUID = -7597339312567242941L;
		@Override
		public ArrayList<Function<String, Integer>> get(Object key){
			if(!this.keySet().contains(key)){
				this.put((String)key, new ArrayList<Function<String, Integer>>());
			}
			return super.get(key);
		}	
	};
}
