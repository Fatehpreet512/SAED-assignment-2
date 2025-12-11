package edu.curtin.gameplugins;

import edu.curtin.game.*;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

/**
 * Plugin that counts items acquired and obstacles traversed.
 * When the total reaches 5, adds a special prize item to the player's inventory.
 */
public class Prize implements Plugin, Callback {
    private GameAPI api;
    private int itemsAcquired = 0;
    private int obstaclesTraversed = 0;
    private boolean prizeGiven = false;
    
    // Track which obstacles have been traversed to avoid double-counting
    private Set<String> traversedObstacles = new HashSet<>();
    
    @Override
    public void initialize(GameAPI api) {   //initializes the prize plugin
        this.api = api;
        api.addCallback(this);
        System.out.println("Prize plugin initialized - will award prize when (items + obstacles) >= 5");
    }
    
    @Override
    public String getName() {
        return "Prize";   //returns prize plugin name
    }
    
    @Override
    public String getMenuText() {
        return null; // No menu option needed
    }
    
    @Override
    public void cleanup() {
        if (api != null) { //if api is not null, remove callback
            api.removeCallback(this);
        }
    }
    
    @Override
    public void onPlayerMove(String direction, int newX, int newY) {
        // Check if the player moved into an obstacle square (successfully traversed it)
        String contents = api.getGridSquareContents(newX, newY);
        if ("obstacle".equals(contents)) {
            // Create unique key for this obstacle location
            String obstacleKey = newX + "," + newY;
            
            // Only count each obstacle once
            if (!traversedObstacles.contains(obstacleKey)) {
                traversedObstacles.add(obstacleKey);
                obstaclesTraversed++;
                System.out.println("Obstacle traversed at (" + newX + ", " + newY + ")! Total obstacles: " + obstaclesTraversed);
                checkForPrize();
            }
        }
    }
    
    @Override
    public void onItemAcquired(String itemName, int itemCount) {
        itemsAcquired++;   //increments the number of items acquired
        System.out.println("Item acquired! Total: " + itemsAcquired);
        checkForPrize();
    }
    
    @Override
    public void onMenuSelected(String pluginName) {
        // No menu action needed
    }
    
    private void checkForPrize() {
        int total = itemsAcquired + obstaclesTraversed;   //calculates the total number of items acquired and obstacles traversed

        if (total >= 5 && !prizeGiven) {
            givePrize();   //gives the prize if the total number of items acquired and obstacles traversed is greater than or equal to 5
            prizeGiven = true;
        }
    }
    
    private void givePrize() {
        String prizeItem = "Golden Trophy";   //sets the prize item to a golden trophy
        api.addItemToInventory(prizeItem, 1);   //adds the prize item to the inventory
        
        System.out.println("Congratulations! You've earned the " + prizeItem + "!");
        System.out.println("Items acquired: " + itemsAcquired);  
        System.out.println("Obstacles traversed: " + obstaclesTraversed);   
        System.out.println("Total: " + (itemsAcquired + obstaclesTraversed));
    }
}
