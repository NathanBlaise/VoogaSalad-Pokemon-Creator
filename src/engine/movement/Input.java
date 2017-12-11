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
			if((pressHandler.keySet().contains(element))&&(!inputList.contains(element))){
				for(int i=0;;i++){
					if(i>=pressHandler.get(element).size()){
						break;
					}else{
						pressHandler.get(element).get(i).apply(element);
					}
				}
			}
			flag = super.add(element);
			return flag;
		}
		
		@Override
		public boolean remove(Object element){
			boolean flag = false;
			if((releaseHandler.keySet().contains(element))&&(inputList.contains(element))){
				for(int i=0;;i++){
					if(i>=releaseHandler.get(element).size()){
						break;
					}else{
						releaseHandler.get(element).get(i).apply((String)element);
					}
				}
			}
			flag = super.remove(element);
			return flag;
		}
	};
	
	public Input (Scene scene) {
		myScene = scene;
	}
	
	/*
	 * Adds listeners for keys and remove, allows the keys to be activated/deactived
	 */
	public void addListeners() {
        myScene.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> inputList.add(event.getCode().toString()));
        myScene.addEventFilter(KeyEvent.KEY_RELEASED, (event) -> inputList.remove(event.getCode().toString()));
    }

    public void removeListeners() {
        myScene.removeEventFilter(KeyEvent.KEY_PRESSED, (event) -> inputList.remove(event.getCode().toString()));
        myScene.removeEventFilter(KeyEvent.KEY_RELEASED, (event) -> inputList.remove(event.getCode().toString()));
    }
    
    /*
     * Clears all keys the user is holding
     */
    public void releaseAllKeys(){
    	inputList.clear();
    }

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
				this.put((String)key, new ArrayList<Function<String, Integer>>(){
					private static final long serialVersionUID = -4253313903226658332L;
					@Override
					public boolean add(Function<String, Integer> element){
						if(this.contains(element)){
							return false;
						}else{
							return super.add(element);
						}
					}
				});
			}
			return super.get(key);
		}	
	};
}
