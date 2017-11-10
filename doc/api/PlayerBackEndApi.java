import util.Coordinates;

/**
 * This defines methods that can be called on the player,
 * which is the character that the user controls
 * distinguish from the architecture layer that lets 
 * the user plays the game. The backend holds player
 * position, and the items and pokemons the player possesses
 * 
 * @author Dan Sun
 *
 */
public interface PlayerBackEndApi {
    
    /**
     * This method moves the location of the player as specified by the movement object 
     * @param Movement specifies the current movement
     * @return The location of the player after the movement
     */
    Coordinates move(Movement movement);
    
    /**
     * This function adds an item to the player's inventory
     * @param item The item to be added
     * @return A collection of all the items the player currently posseses after adding 
     */
    ItemCollection addItem(Item item);
    
    /**
     * This function makes the player consume an item
     * @param item The item to be consumed
     * @return A collection of all the items the player currently posseses after consuming 
     */
    ItemCollection consumeItem(Item item);
    
    /**
     * This function removes an Item from the player's inventory
     * @param item The item to be removed
     * @return A collection of all the items the player currently posseses after removing 
     */
    ItemCollection removeItem(Item item);
    
    /**
     * This function adds a pokemon to the player's possesion
     * @param pokemon The pokemon to be added
     * @return A collection of all the pokemons the player currently posseses after adding 
     */
    PokemonCollection addPokemon(Pokemon pokemon);
    
    /**
     * This function removes a pokemon from the player's inventory
     * @param Pokemon The pokemon to be removed
     * @return A collection of all the pokemons the player currently posseses after removing
     */
    PokemonCollecttion removePokemon(Pokemon);
    
    /**
     * This method modifies a particular attribute (such as gold) of 
     * the player
     * @param attributeType specifies which attribute to modify among 
     * the ones that the player has
     * @param operation specifies the operation to be performed. It should contain
     * information about the kind of operation to be performed (e.g. increase/decrease)
     * and the amount of change
     */
    void modifyAttribute(AttributeType attributeType, Operation operation);
    
}
