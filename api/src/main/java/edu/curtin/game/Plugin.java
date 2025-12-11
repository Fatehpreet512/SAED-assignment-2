package edu.curtin.game;

/**
 * Interface that all plugins must implement.
 * 
 * Plugins are dynamically loaded Java classes that extend the game's functionality.
 * They can interact with the game through the GameAPI interface and respond to
 * game events through callback mechanisms.
 * 
 * Plugin Lifecycle:
 * 1. Plugin class is loaded using reflection
 * 2. initialize() is called with the GameAPI instance
 * 3. Plugin can register for callbacks and set up menu options
 * 4. Plugin responds to game events and user interactions
 * 5. cleanup() is called when the plugin is unloaded
 * 
 * Required Plugin Implementations:
 * - edu.curtin.gameplugins.Teleport: Teleports player to random location
 * - edu.curtin.gameplugins.Penalty: Creates obstacles for slow moves
 * - edu.curtin.gameplugins.Reveal: Reveals hidden items when "map" items are acquired
 * - edu.curtin.gameplugins.Prize: Awards special items after 5 achievements
 */
public interface Plugin {
    /**
     * Initialize the plugin with the game API.
     * 
     * This method is called when the plugin is first loaded. The plugin should:
     * - Store the API reference for later use
     * - Register for any required callbacks
     * - Set up any necessary data structures
     * - Request menu options if needed
     * 
     * @param api The game API instance that provides access to game state
     */
    void initialize(GameAPI api);
    
    /**
     * Get the name of this plugin.
     * 
     * This name is used for identification and debugging purposes.
     * It should be unique and descriptive.
     * 
     * @return The plugin name
     */
    String getName();
    
    /**
     * Get the menu text for this plugin (if any).
     * 
     * If the plugin wants to provide a menu option/button to the user,
     * it should return the text to display. If no menu option is needed,
     * return null.
     * 
     * @return The menu text, or null if no menu option
     */
    String getMenuText();
    
    /**
     * Clean up resources when the plugin is unloaded.
     * 
     * This method is called when the plugin is being removed from the game.
     * The plugin should:
     * - Unregister from any callbacks
     * - Clean up any resources
     * - Remove any menu options
     */
    void cleanup();
}
