package data.event;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author 
 *
 * event holds a image which presents itself and a series of instructions which will be triggered after it's notified
 *
 */

public class Event implements Serializable{
	int posX, posY; // the position to display the event
	
	ArrayList<Instruction> instructions;
	//since javafx's image cannot be serialized, here it stores the path (prefer absolute path) of the image
	String imagePath;
}
