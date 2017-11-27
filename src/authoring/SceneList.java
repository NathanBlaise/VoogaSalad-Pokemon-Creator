package authoring;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * This class is used for generating the scene
 * @author cy122
 *
 */
public class SceneList{
	private ArrayList<String> generator = new ArrayList<String>();
	private Color color;
	private StageDelegate stageHelper;
	
	
	public SceneList(Color color, StageDelegate stageHelper){
		this.color = color;
		this.stageHelper = stageHelper;
	}
	
	public void add(String className){
		generator.add(className);
	}
	
	public Scene get(int index){
		try {
			String className = generator.get(index);
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = clazz.getConstructor(new Class[]{Paint.class, StageDelegate.class});
			Object myObject = (Object) constructor.newInstance(color, stageHelper);
			Method getSceneMethod = clazz.getMethod("getScene", new Class[]{});
			Scene result = (Scene) getSceneMethod.invoke(myObject);
			return result;
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public int size(){
		return generator.size();
	}
}
