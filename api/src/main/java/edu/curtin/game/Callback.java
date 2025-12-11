package edu.curtin.game;

/**
 * Base interface for all game callbacks.
 * 
 * This interface defines the callback mechanisms that allow plugins and scripts
 * to be notified about game events and respond accordingly. Plugins and scripts
 * can implement this interface to receive notifications about:
 * 
 * Player Movement:
 * - When the player moves in any direction
 * - Provides direction and new coordinates
 * 
 * Item Acquisition:
 * - When the player picks up an item
 * - Provides item name and current count
 * 
 * Menu Selection:
 * - When the plugin's menu option/button is selected
 * - Allows plugins to respond to user interactions
 * 
 * Callback Registration:
 * Plugins register for callbacks during initialization, and the game engine
 * calls the appropriate methods when events occur. Scripts can also implement
 * this interface to receive the same notifications.
 */
public interface Callback {
    /**
     * Called when the player moves in any direction.
     * 
     * This callback is triggered whenever the player successfully moves,
     * whether through keyboard input, GUI buttons, or programmatic movement.
     * 
     * @param direction The direction of movement ("up", "down", "left", "right")
     * @param newX The new X coordinate of the player (column)
     * @param newY The new Y coordinate of the player (row)
     */
    void onPlayerMove(String direction, int newX, int newY);
    
    /**
     * Called when the player acquires a new item.
     * 
     * This callback is triggered when the player moves onto a square containing
     * an item, causing the item to be added to their inventory.
     * 
     * @param itemName The name of the item acquired
     * @param itemCount The total number of this item the player now has
     */
    void onItemAcquired(String itemName, int itemCount);
    
    /**
     * Called when the plugin's menu option or button is selected.
     * 
     * This callback is triggered when the user clicks on a plugin's menu button
     * or selects its menu option. This allows plugins to provide interactive
     * functionality to the user.
     * 
     * @param pluginName The name of the plugin whose menu was selected
     */
    void onMenuSelected(String pluginName);
}
