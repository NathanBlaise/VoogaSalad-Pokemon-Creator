package data.player;

import java.io.Serializable;

import data.database.NPC;


/**
 * The main character which the user plays the role of
 * 
 * @author 
 *
 */
public class Player extends NPC implements Serializable{
	//only holds these attributes for now
	int posX, posY;

}
