package edu.curtin.game;

import java.util.*;

/**
 * Data class representing the parsed game configuration from input files.
 * 
 * This class serves as a container for all the game data parsed from the DSL input file:
 * - Grid dimensions and player/goal positions
 * - Item definitions with locations and messages
 * - Obstacle definitions with locations and requirements
 * - Plugin class names to be loaded
 * - Script content to be executed
 * 
 * The GameConfigParser populates this object, which is then used by MazeGameApp
 * to initialize the game state and load plugins/scripts.
 */
public class GameConfig {
    // Grid configuration
    private int gridWidth;              // Number of columns in the grid
    private int gridHeight;             // Number of rows in the grid
    
    // Player and goal positions
    private int startX;                 // Starting X coordinate (column)
    private int startY;                 // Starting Y coordinate (row)
    private int goalX;                  // Goal X coordinate (column)
    private int goalY;                  // Goal Y coordinate (row)
    
    // Game content
    private List<Item> items;           // List of items with their locations and properties
    private List<Obstacle> obstacles;   // List of obstacles with their locations and requirements
    private List<String> plugins;       // List of plugin class names to load
    private List<String> scripts;       // List of Python script content to execute
    
    /**
     * Initializes a new GameConfig with empty collections.
     */
    public GameConfig() {   //initializes the game config
        this.items = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        this.plugins = new ArrayList<>();
        this.scripts = new ArrayList<>();
    }
    
    // Getters and setters for grid configuration
    public int getGridWidth() { return gridWidth; }
    public void setGridWidth(int gridWidth) { this.gridWidth = gridWidth; }
    
    public int getGridHeight() { return gridHeight; }
    public void setGridHeight(int gridHeight) { this.gridHeight = gridHeight; }
    
    // Getters and setters for player/goal positions
    public int getStartX() { return startX; }
    public void setStartX(int startX) { this.startX = startX; }
    
    public int getStartY() { return startY; }
    public void setStartY(int startY) { this.startY = startY; }
    
    public int getGoalX() { return goalX; }
    public void setGoalX(int goalX) { this.goalX = goalX; }
    
    public int getGoalY() { return goalY; }
    public void setGoalY(int goalY) { this.goalY = goalY; }
    
    // Getters and setters for game content
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
    
    public List<Obstacle> getObstacles() { return obstacles; }
    public void setObstacles(List<Obstacle> obstacles) { this.obstacles = obstacles; }
    
    public List<String> getPlugins() { return plugins; }
    public void setPlugins(List<String> plugins) { this.plugins = plugins; }
    
    public List<String> getScripts() { return scripts; }
    public void setScripts(List<String> scripts) { this.scripts = scripts; }
    
    // Helper methods for parsing
    public void addItem(String name, int x, int y, String message) {
        // Check if item already exists
        for (Item item : items) {
            if (item.getName().equals(name)) {
                item.addLocation(x, y);
                return;
            }
        }
        // Create new item
        Item newItem = new Item(name, message);
        newItem.addLocation(x, y);
        items.add(newItem);
    }
    
    public void addObstacle(int x, int y, String requirement) {
        // Check if obstacle already exists
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getRequirements().contains(requirement)) {
                obstacle.addLocation(x, y);
                return;
            }
        }
        // Create new obstacle
        Obstacle newObstacle = new Obstacle();
        newObstacle.addLocation(x, y);
        newObstacle.addRequirement(requirement);
        obstacles.add(newObstacle);
    }
    
    public static class Item {   //class for items
        private String name;
        private String message;
        private List<int[]> locations;
        
        public Item(String name, String message) {   //initializes the item
            this.name = name;
            this.message = message;
            this.locations = new ArrayList<>();
        }
        //getters and setters for the item
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        //getter and setter for the item message
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        //getter and setter for the item locations
        public List<int[]> getLocations() { return locations; }
        public void setLocations(List<int[]> locations) { this.locations = locations; }
        //method to add a location to the item
        public void addLocation(int x, int y) {
            locations.add(new int[]{x, y});
        }
    }
    
    public static class Obstacle {   //class for obstacles
        private List<int[]> locations;
        private List<String> requirements;
        
        public Obstacle() {   //initializes the obstacle
            this.locations = new ArrayList<>();
            this.requirements = new ArrayList<>();
        }
        //getters and setters for the obstacle
        public List<int[]> getLocations() { return locations; }
        public void setLocations(List<int[]> locations) { this.locations = locations; }
        //getter and setter for the obstacle requirements
        public List<String> getRequirements() { return requirements; }
        public void setRequirements(List<String> requirements) { this.requirements = requirements; }
        //method to add a location to the obstacle
        public void addLocation(int x, int y) {
            locations.add(new int[]{x, y});
        }
        
        //method to add a requirement to the obstacle
        public void addRequirement(String requirement) {
            requirements.add(requirement);
        }
    }
}
