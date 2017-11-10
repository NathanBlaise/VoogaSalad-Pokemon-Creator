package engine;

public interface Battle {
  /**
   * This method is called
   *
   * @param pokemon currently being controlled by player
   * @param ability the pokemon's move chosen by the player
   *
   */
   public void PlayerTurn(Pokemon pokemon, Ability ability);

   /**
    * Executes the AI logic for an enemy trainer
    *
    * @param opponent enemey trainer
    * @param ability the pokemon's move chosen by the AI
    *
    */
    public void NPCTurn(Pokemon pokemon, Ability ability);

    /**
     * Executes the AI logic for a wild pokemon
     *
     * @param opponent wild pokemon
     * @param ability the pokemon's move chosen by the AI
     *
     */
     public void NPCTurn(Pokemon opponent);


    /**
     * Signals when the battle is over
     *
     * @return true if all pokemon HP at 0 for player or NPC
     */
     public boolean isBattleOver();

}
