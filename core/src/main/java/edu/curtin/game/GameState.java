package edu.curtin.game;

import java.util.*;

/**
 * Represents the current state of the game including grid, player position, inventory, etc.
 * 
 * This class manages all the core game data including:
 * - Grid dimensions and layout
 * - Player position and movement
 * - Goal location and win condition
 * - Player inventory and item management
 * - Grid visibility (hidden/visible squares)
 * - Grid contents (items, obstacles, goals)
 * - Obstacle requirements for traversal
 * - Game date progression
 * 
 * The GameState serves as the central data model that plugins and scripts
 * can query and modify through the GameAPI interface.
 */
public class GameState {
    // Grid dimensions
    private int gridWidth;              // Number of columns in the grid
    private int gridHeight;             // Number of rows in the grid
    
    // Player and goal positions
    private int playerX;                // Current player X coordinate (column)
    private int playerY;                // Current player Y coordinate (row)
    private int goalX;                  // Goal X coordinate (column)
    private int goalY;                  // Goal Y coordinate (row)
    
    // Player inventory management
    private Map<String, Integer> inventory;  // Item name -> quantity mapping
    private String lastAcquiredItem;          // Most recently acquired item name
    
    // Grid visibility and contents
    private boolean[][] visible;              // Which squares are visible to player
    private String[][] gridContents;         // Content type: "item", "obstacle", "goal", "empty"
    private String[][] itemNames;             // Item names at each grid position
    private List<String>[][] obstacleRequirements; // Required items for each obstacle
    
    // Game date progression
    private int currentDay;                   // Current day number (starts at 0)
    private String startDate;                 // Starting date as string
    
    /**
     * Initializes a new game state with the specified parameters.
     * Sets up the grid, initializes visibility, and prepares data structures.
     * 
     * @param gridWidth Width of the game grid (number of columns)
     * @param gridHeight Height of the game grid (number of rows)
     * @param startX Starting X coordinate for the player
     * @param startY Starting Y coordinate for the player
     * @param goalX Goal X coordinate
     * @param goalY Goal Y coordinate
     */
    @SuppressWarnings("unchecked")
    public GameState(int gridWidth, int gridHeight, int startX, int startY, int goalX, int goalY) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.playerX = startX;
        this.playerY = startY;
        this.goalX = goalX;
        this.goalY = goalY;
        this.inventory = new HashMap<String, Integer>();
        this.lastAcquiredItem = null;
        this.visible = new boolean[gridHeight][gridWidth];
        this.gridContents = new String[gridHeight][gridWidth];
        this.itemNames = new String[gridHeight][gridWidth];
        @SuppressWarnings("unchecked")
        List<String>[][] temp = new List[gridHeight][gridWidth];
        this.obstacleRequirements = temp;
        this.currentDay = 0;
        this.startDate = java.time.LocalDate.now().toString();
        
        // Initialize grid - all squares start as empty and hidden
        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
                gridContents[y][x] = "empty";
                visible[y][x] = false;
            }
        }
        
        // Set goal
        gridContents[goalY][goalX] = "goal";
        
        // Make start area visible
        updateVisibility();
    }
    
    public int getGridWidth() { return gridWidth; }   //getter for the grid width
    public int getGridHeight() { return gridHeight; }   //getter for the grid height
    public int getPlayerX() { return playerX; }   //getter for the player x coordinate
    public int getPlayerY() { return playerY; }   //getter for the player y coordinate
    public int getGoalX() { return goalX; }   //getter for the goal x coordinate
    public int getGoalY() { return goalY; }   //getter for the goal y coordinate
    public Map<String, Integer> getInventory() { return new HashMap<String, Integer>(inventory); }
    public String getLastAcquiredItem() { return lastAcquiredItem; }   //getter for the last acquired item
    public boolean isVisible(int x, int y) { return visible[y][x]; }
    public String getGridContents(int x, int y) { return gridContents[y][x]; }
    public String getItemName(int x, int y) { return itemNames[y][x]; }   //getter for the item name at the given x and y coordinates
    public List<String> getObstacleRequirements(int x, int y) { 
        return obstacleRequirements[y][x] != null ? new ArrayList<String>(obstacleRequirements[y][x]) : null; 
    }
    public int getCurrentDay() { return currentDay; }   //getter for the current day
    public String getStartDate() { return startDate; }   //getter for the start date
    
    public void setPlayerPosition(int x, int y) {   //setter for the player position
        this.playerX = x;
        this.playerY = y;
        updateVisibility();
    }
    
    public void addItem(String itemName) {   //adds an item to the inventory
        inventory.put(itemName, inventory.getOrDefault(itemName, 0) + 1);
        lastAcquiredItem = itemName;
    }
    
    public boolean removeItem(String itemName, int quantity) {   //removes an item from the inventory
        int current = inventory.getOrDefault(itemName, 0);
        if (current >= quantity) {
            inventory.put(itemName, current - quantity);
            if (inventory.get(itemName) == 0) {
                inventory.remove(itemName);
            }
            return true;
        }
        return false;
    }
    
    public void setGridContents(int x, int y, String contents) {   //setter for the grid contents at the given x and y coordinates
        gridContents[y][x] = contents;
    }
    
    public void setItemName(int x, int y, String itemName) {   //setter for the item name at the given x and y coordinates
        itemNames[y][x] = itemName;
    }
    
    public void setObstacleRequirements(int x, int y, List<String> requirements) {   //setter for the obstacle requirements at the given x and y coordinates
        obstacleRequirements[y][x] = requirements != null ? new ArrayList<String>(requirements) : null;
    }
    
    public void addObstacleRequirement(int x, int y, String requirement) {   //adds a single obstacle requirement at the given x and y coordinates
        if (obstacleRequirements[y][x] == null) {
            obstacleRequirements[y][x] = new ArrayList<String>();
        }
        obstacleRequirements[y][x].add(requirement);
    }
    
    public void setVisible(int x, int y, boolean visible) {   //setter for the visibility at the given x and y coordinates
        this.visible[y][x] = visible;
    }
    
    public void advanceDay() {   //advances the day
        currentDay++;
    }
    
    private void updateVisibility() {
        // Make current position and adjacent squares visible
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                int newX = playerX + dx;
                int newY = playerY + dy;
                if (newX >= 0 && newX < gridWidth && newY >= 0 && newY < gridHeight) {
                    visible[newY][newX] = true;
                }
            }
        }
    }
    
    public boolean isGameWon() {   //checks if the game has been won
        return playerX == goalX && playerY == goalY;
    }
}
