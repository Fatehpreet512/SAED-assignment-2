package edu.curtin.gameplugins;

import edu.curtin.game.*;
import java.util.Random;

/**
 * Plugin that allows the player to teleport to a random location.
 * This plugin provides the player with one opportunity
 * to teleport to a random point in the grid by selecting a menu option.
 * Features:
 * - One-time use: Teleport can only be used once per game
 * - Random destination: Teleports to a random valid grid location
 * - Menu integration: Provides a button for user interaction
 * - State tracking: Remembers if teleport has been used
 */
public class Teleport implements Plugin, Callback {
    private GameAPI api;                // Reference to the game API
    private boolean teleportUsed = false; // Tracks if teleport has been used
    private Random random = new Random(); // Random number generator for destination
    
    /**
     * Initialize the teleport plugin.
     * 
     * Sets up the plugin by:
     * - Storing the API reference
     * - Registering for game callbacks
     * - Requesting a menu option for teleportation
     */
    @Override
    public void initialize(GameAPI api) {   //initializes the teleport plugin
        this.api = api;
        api.addCallback(this);
        api.requestMenuOption("Teleport", "Teleport to random location");
    }
    
    /**
     * Get the plugin name.
     * 
     * @return "Teleport" - the name of this plugin
     */
    @Override
    public String getName() {
        return "Teleport";   //returns teleport plugin name
    }
    
    /**
     * Get the menu text for the teleport button.
     * 
     * The text changes based on whether the teleport has been used:
     * - "Teleport to random location" if available
     * - "Teleport (Used)" if already used
     * 
     * @return The appropriate menu text
     */
    @Override
    public String getMenuText() {
        return teleportUsed ? "Teleport (Used)" : "Teleport to random location";
    }
    
    /**
     * Clean up when the plugin is unloaded.
     * 
     * Removes the callback registration to prevent memory leaks.
     */
    @Override
    public void cleanup() {
        if (api != null) { //if api is not null, remove callback
            api.removeCallback(this);
        }
    }
    
    /**
     * Handle player movement events.
     * 
     * No action needed for this plugin when the player moves.
     * 
     * @param direction The direction of movement
     * @param newX The new X coordinate
     * @param newY The new Y coordinate
     */
    @Override
    public void onPlayerMove(String direction, int newX, int newY) {
        // No action needed on player move
    }
    
    /**
     * Handle item acquisition events.
     * 
     * No action needed for this plugin when items are acquired.
     * 
     * @param itemName The name of the acquired item
     * @param itemCount The total count of this item
     */
    @Override
    public void onItemAcquired(String itemName, int itemCount) {
        // No action needed on item acquisition
    }
    
    /**
     * Handle menu selection events.
     * 
     * When the teleport menu option is selected, this method:
     * 1. Checks if teleport is still available
     * 2. Generates a random destination within the grid
     * 3. Moves the player to the random location
     * 4. Marks teleport as used
     * 5. Updates the menu text
     * 
     * @param pluginName The name of the plugin whose menu was selected
     */
    @Override
    public void onMenuSelected(String pluginName) {
        if ("Teleport".equals(pluginName) && !teleportUsed) {
            performTeleport();   //performs the teleport if the teleport menu option is selected and the teleport has not been used yet
        }
    }
    
    private void performTeleport() {
        if (teleportUsed) {   //if the teleport has been used, return
            return;
        }
        
        int[] gridSize = api.getGridSize();   //gets the grid size
        int maxX = gridSize[0] - 1;
        int maxY = gridSize[1] - 1;
        
        // Find a random empty location
        int attempts = 0;
        int newX, newY;
        do {
            newX = random.nextInt(maxX + 1);
            newY = random.nextInt(maxY + 1);
            attempts++;
        } while (!"empty".equals(api.getGridSquareContents(newX, newY)) && attempts < 100);
        
        // If we couldn't find an empty spot, just pick any spot
        if (attempts >= 100) {
            newX = random.nextInt(maxX + 1);
            newY = random.nextInt(maxY + 1);
        }
        
        // Teleport the player
        api.setPlayerLocation(newX, newY);
        teleportUsed = true;
        
        System.out.println("Player teleported to (" + newX + ", " + newY + ")");
    }
}
