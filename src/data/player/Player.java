package data.player;

import java.io.Serializable;
import data.model.Pokemon;


/**
 * The main character which the user plays the role of
 * 
 * @author 
 *
 */
public class Player implements Serializable{
	private static final long serialVersionUID = 556462866183029469L;
	//only holds these attributes for now
	private int posX, posY;
	private Pokemon[] pokemons; //the pokemons for battle
	
	public Player(){
		posX=0;
		posY=0;
		pokemons = new Pokemon[6];
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

	public Pokemon[] getPokemons() {
		return pokemons;
	}

	public void setPokemons(Pokemon[] pokemons) {
		for(int i=0;i<6 && i<pokemons.length;i++){
			if(pokemons[i]!=null){
				this.pokemons[i] = new Pokemon(pokemons[i]);
			}
		}
	}
}
