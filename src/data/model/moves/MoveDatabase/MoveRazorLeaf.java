package data.model.moves.MoveDatabase;

import data.model.moves.MoveDamage;

/**
 * https://pokemondb.net/move/razor-leaf
 * @author cy122
 * @author Dan Sun
 *
 */
public class MoveRazorLeaf extends MoveDamage{

    /**
     * 
     */
    private static final long serialVersionUID = 5470561365501600209L;

    private final static int power = 35;
    private final static int maxPP = 10;
    private final static String name = "RazorLeaf";
    private final static String elemental = "Grass";
    
    public MoveRazorLeaf(){
	super(name, elemental, maxPP, power);
    }
}