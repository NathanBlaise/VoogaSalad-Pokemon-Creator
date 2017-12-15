package engine.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ItemColomn extends HBox{
	/*final variable*/
	private final Image PANE_POINT = new Image("file:dialog/dialogue_pointer.png",20,20,false,false);
	private final Image WHITE_POINT = new Image("file:dialog/dialogue_bg.png",20,20,false,false);
	/*private variable*/
	private String ItemColomnName;
	private ImageView ArrowView;
	private int myNum = -1;
	private String Explanation = new String();
	
	public ItemColomn(String name){
		ArrowView = new ImageView();
		ItemColomnName = name;
		this.getChildren().add(ArrowView);
		this.getChildren().add(useFont(ItemColomnName,25));
		this.setSpacing(10);
		
	}
	
	public ItemColomn(String name, String explanation){
		ArrowView = new ImageView();
		ItemColomnName = name;
		this.getChildren().add(ArrowView);
		this.getChildren().add(useFont(ItemColomnName,25));
		Explanation = explanation;
		this.setSpacing(10);
		
	}
	
	
	public ItemColomn(String name, String explanation,int num){
		ArrowView = new ImageView();
		ItemColomnName = name;
		myNum = num;
		
		
		this.getChildren().add(ArrowView);
		this.getChildren().add(useFont(ItemColomnName,25));
		this.getChildren().add(useFont(myNum,25));
		Explanation = explanation;
		this.setSpacing(10);
		
	}
	
	public void selectItemColomn() {
		ArrowView.setImage(PANE_POINT);
	}
	
	
	public void deselectItemColomn() {
		ArrowView.setImage(WHITE_POINT);
	}
	
	
	/**
	 * @param target: target String
	 * @param Size: size of the text
	 * @return The Text with the certain target and certain size
	 */
	private Text useFont(String target, int Size) {
		while(target.length() <= 6) target = target + " ";
		Text ans = new Text(target);
		ans.setFont(getFont(Size));
		
		
		return ans;
		
	}
	
	
	/**
	 * @param target: Integer Target
	 * @param Size: size of the text
	 * @return The Text with the certain target and certain size
	 */
	private Text useFont(int intTarget, int Size) {
		String rawTarget = Integer.toString(intTarget);
	    String target = " x " + rawTarget;
		while(target.length() <= 3) target = target + " ";
		Text ans = new Text(target);
		ans.setFont(getFont(Size));
		
		
		return ans;
		
	}
	
	
	
	
	/**
	 * @return the specific Pokemon font
	 */
	private Font getFont(int fontSize) {
		Font f = new Font(30) ;
		try {
			f = Font.loadFont(new FileInputStream(new File("./font/font.ttf")), fontSize);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}
	
	
	/**
	 * @return pass the explanation of command
	 */
	
	public String getExplanation() {
		return Explanation;
	}
}
