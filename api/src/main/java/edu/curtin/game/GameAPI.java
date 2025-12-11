package edu.curtin.game;

import java.util.List;
import java.util.Map;

/**
 * Main API interface for plugins and scripts to interact with the game.
 * 
 * This interface defines the contract between the core game engine and
 * dynamically loaded plugins/scripts. It provides methods to:
 * 
 * Player Management:
 * - Query and modify player location
 * - Manage player inventory (add/remove items)
 * - Track recently acquired items
 * 
 * Grid Management:
 * - Query grid dimensions
 * - Check grid square contents (items, obstacles, goals)
 * - Modify grid square visibility
 * - Place/remove items and obstacles
 * 
 * Game State:
 * - Get current game date
 * - Advance the game date
 * - Check win conditions
 * 
 * The GameAPI implementation (GameAPIImpl) provides the actual functionality,
 * while this interface ensures plugins/scripts can only access approved methods.
 */
public interface GameAPI {
    /**
     * Get the player's current location.
     * 
     * @return A 2-element array [x, y] representing the player's position
     *         where x is the column and y is the row
     */
    int[] getPlayerLocation();
    
    /**
     * Set the player's location.
     * 
     * @param x The new X coordinate (column)
     * @param y The new Y coordinate (row)
     */
    void setPlayerLocation(int x, int y);
    
    /**
     * Get the player's inventory.
     * 
     * @return A map of item names to their quantities
     */
    Map<String, Integer> getPlayerInventory();
    
    /**
     * Add an item to the player's inventory.
     * 
     * @param itemName The name of the item to add
     * @param quantity The quantity to add (default 1)
     */
    void addItemToInventory(String itemName, int quantity);
    
    /**
     * Remove an item from the player's inventory.
     * 
     * @param itemName The name of the item to remove
     * @param quantity The quantity to remove
     * @return true if the item was removed, false if not enough items
     */
    boolean removeItemFromInventory(String itemName, int quantity);
    
    /**
     * Get the most recently acquired item.
     * 
     * @return The name of the most recently acquired item, or null if none
     */
    String getLastAcquiredItem();
    
    /**
     * Get the grid size.
     * @return A 2-element array [width, height] representing the grid dimensions
     */
    int[] getGridSize();
    
    /**
     * Get the contents of a specific grid square.
     * @param x The X coordinate
     * @param y The Y coordinate
     * @return A string describing the contents ("item", "obstacle", "goal", or "empty")
     */
    String getGridSquareContents(int x, int y);
    
    /**
     * Get the item name at a specific location.
     * @param x The X coordinate
     * @param y The Y coordinate
     * @return The item name, or null if no item at that location
     */
    String getItemAt(int x, int y);
    
    /**
     * Get the obstacle requirements at a specific location.
     * @param x The X coordinate
     * @param y The Y coordinate
     * @return A list of required item names, or null if no obstacle at that location
     */
    List<String> getObstacleRequirements(int x, int y);
    
    /**
     * Check if a grid square is visible to the player.
     * @param x The X coordinate
     * @param y The Y coordinate
     * @return true if the square is visible, false if hidden
     */
    boolean isGridSquareVisible(int x, int y);
    
    /**
     * Set the visibility of a grid square.
     * @param x The X coordinate
     * @param y The Y coordinate
     * @param visible true to make visible, false to hide
     */
    void setGridSquareVisible(int x, int y, boolean visible);
    
    /**
     * Remove an item from the grid.
     * @param x The X coordinate
     * @param y The Y coordinate
     * @return true if an item was removed, false if no item at that location
     */
    boolean removeItemFromGrid(int x, int y);
    
    /**
     * Add an item to the grid.
     * @param x The X coordinate
     * @param y The Y coordinate
     * @param itemName The name of the item to add
     * @return true if the item was added, false if the location is occupied
     */
    boolean addItemToGrid(int x, int y, String itemName);
    
    /**
     * Add an obstacle to the grid at a specific location.
     * 
     * @param x The X coordinate
     * @param y The Y coordinate
     * @param requiredItem The item required to pass through this obstacle
     * @return true if the obstacle was added, false if the location is occupied
     */
    boolean addObstacleToGrid(int x, int y, String requiredItem);
    
    /**
     * Request a menu option for this plugin.
     * @param pluginName The name of the plugin
     * @param menuText The text to display in the menu
     */
    void requestMenuOption(String pluginName, String menuText);
    
    /**
     * Get the current game date.
     * @return The current game date as a string
     */
    String getCurrentDate();
    
    /**
     * Advance the game date by one day.
     */
    void advanceDate();
    
    /**
     * Get the number of days elapsed.
     * @return The number of days since the game started
     */
    int getDaysElapsed();
    
    /**
     * Add a callback to receive game events.
     * @param callback The callback to add
     */
    void addCallback(Callback callback);
    
    /**
     * Remove a callback from receiving game events.
     * @param callback The callback to remove
     */
    void removeCallback(Callback callback);
    
    /**
     * Notify all callbacks of a player move event.
     * @param direction The direction of movement
     * @param newX The new X coordinate
     * @param newY The new Y coordinate
     */
    void notifyPlayerMove(String direction, int newX, int newY);
    
    /**
     * Notify all callbacks of an item acquisition event.
     * @param itemName The name of the item acquired
     * @param itemCount The total count of this item
     */
    void notifyItemAcquired(String itemName, int itemCount);
    
    /**
     * Notify all callbacks of a menu selection event.
     * @param pluginName The name of the plugin whose menu was selected
     */
    void notifyMenuSelected(String pluginName);
}
