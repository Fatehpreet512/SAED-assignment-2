package edu.curtin.game;

import java.util.*;
import java.lang.reflect.Constructor;

/**
 * Manages loading and execution of plugins using Java reflection.
 * 
 * This class handles the dynamic loading of plugin classes specified in the
 * input file. It uses reflection to:
 * - Load plugin classes by their fully qualified names
 * - Verify that classes implement the Plugin interface
 * - Instantiate plugins using their default constructors
 * - Initialize plugins with the GameAPI
 * - Manage plugin lifecycle and execution
 * 
 * Plugins can be loaded from any package as long as they implement the
 * Plugin interface and are available on the classpath.
 */
public class PluginManager {
    private GameAPI gameAPI;              // API instance passed to plugins
    private List<Plugin> loadedPlugins;   // List of successfully loaded plugins
    private Map<String, Plugin> pluginMap; // Plugin name -> instance mapping
    
    /**
     * Initializes the plugin manager with a GameAPI instance.
     * 
     * @param gameAPI The API instance that plugins will use to interact with the game
     */
    public PluginManager(GameAPI gameAPI) {
        this.gameAPI = gameAPI;
        this.loadedPlugins = new ArrayList<>();
        this.pluginMap = new HashMap<>();
    }
    
    /**
     * Load a plugin by its fully qualified class name using reflection.
     * 
     * This method:
     * 1. Loads the class using Class.forName()
     * 2. Verifies it implements the Plugin interface
     * 3. Creates an instance using the default constructor
     * 4. Initializes the plugin with the GameAPI
     * 5. Adds it to the loaded plugins list
     * 
     * @param className The fully qualified class name (e.g., "edu.curtin.gameplugins.Teleport")
     * @return The loaded plugin instance, or null if loading failed
     */
    public Plugin loadPlugin(String className) {
        try {
            // Load the class using reflection
            Class<?> pluginClass = Class.forName(className);
            
            // Check if it implements the Plugin interface
            if (!Plugin.class.isAssignableFrom(pluginClass)) {
                System.err.println("Class " + className + " does not implement Plugin interface");
                return null;
            }
            
            // Create instance using default constructor
            Constructor<?> constructor = pluginClass.getConstructor();
            Plugin plugin = (Plugin) constructor.newInstance();
            
            // Initialize the plugin with the GameAPI
            plugin.initialize(gameAPI);
            
            // Add to loaded plugins
            loadedPlugins.add(plugin);
            pluginMap.put(plugin.getName(), plugin);
            
            System.out.println("Successfully loaded plugin: " + plugin.getName());
            return plugin;
            
        } catch (Exception e) {
            System.err.println("Failed to load plugin " + className + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Load multiple plugins from a list of class names.
     * @param classNames List of fully qualified class names
     * @return List of successfully loaded plugins
     */
    public List<Plugin> loadPlugins(List<String> classNames) {
        List<Plugin> loaded = new ArrayList<>();
        for (String className : classNames) {
            Plugin plugin = loadPlugin(className);
            if (plugin != null) {
                loaded.add(plugin);
            }
        }
        return loaded;
    }
    
    /**
     * Get all loaded plugins.
     * @return List of loaded plugins
     */
    public List<Plugin> getLoadedPlugins() {
        return new ArrayList<>(loadedPlugins);
    }
    
    /**
     * Get a specific plugin by name.
     * @param name The plugin name
     * @return The plugin instance, or null if not found
     */
    public Plugin getPlugin(String name) {
        return pluginMap.get(name);
    }
    
    /**
     * Execute a plugin's menu action.
     * @param pluginName The name of the plugin to execute
     */
    public void executePlugin(String pluginName) {
        Plugin plugin = pluginMap.get(pluginName);
        if (plugin != null) {
            gameAPI.notifyMenuSelected(pluginName);
        } else {
            System.err.println("Plugin not found: " + pluginName);
        }
    }
    
    /**
     * Unload all plugins and clean up resources.
     */
    public void unloadAll() {
        for (Plugin plugin : loadedPlugins) {
            try {
                plugin.cleanup();
            } catch (Exception e) {
                System.err.println("Error cleaning up plugin " + plugin.getName() + ": " + e.getMessage());
            }
        }
        loadedPlugins.clear();
        pluginMap.clear();
    }
}
