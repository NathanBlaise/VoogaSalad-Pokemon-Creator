/**
 * This interface specifies method that should be supported by the front
 * end of the player (i.e. the character that the user controls)
 * The frontend is responsible for the name of the player
 * and the image of the player
 * @author Dan Sun
 *
 */
public interface PlayerFrontEndApi {
    
    /**
     * This method changes the name of the player
     * @param name the new name of the player
     * @return The name of the player after change
     */
    String changeName(String name);
    
    /**
     * Changes the image of the player
     * @param images The collection of images that specifies the image to use when the
     * player is facing different directions
     */
    void changeImage(ImageCollection images);
}
