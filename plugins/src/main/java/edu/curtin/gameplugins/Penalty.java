package edu.curtin.gameplugins;

import edu.curtin.game.*;
import java.util.Random;

/**
 * Plugin that creates penalty obstacles if the player takes too long to move.
 * Creates a new obstacle adjacent to the player if they took more than 5 seconds.
 */
public class Penalty implements Plugin, Callback {
    private GameAPI api;
    private long lastMoveTime = 0;  // 0 means no move has been made yet
    private Random random = new Random();
    
    @Override
    public void initialize(GameAPI api) {   //initializes the penalty plugin
        this.api = api;
        api.addCallback(this);
        System.out.println("Penalty plugin initialized - will penalize moves taking > 5 seconds");
    }
    
    @Override
    public String getName() {
        return "Penalty";   //returns penalty plugin name
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
        long currentTime = System.currentTimeMillis();
        
        // Skip penalty check for the first move (lastMoveTime == 0)
        if (lastMoveTime != 0) {
            long timeSinceLastMove = currentTime - lastMoveTime;
            
            System.out.println("DEBUG: Time since last move: " + (timeSinceLastMove / 1000.0) + " seconds");
            
            // Check if more than 5 seconds have passed
            if (timeSinceLastMove > 5000) {
                System.out.println("*** PENALTY TRIGGERED! Player took " + (timeSinceLastMove / 1000.0) + " seconds to move. ***");
                createPenaltyObstacle(newX, newY); //creates a penalty obstacle if more than 5 seconds have passed
            }
        } else {
            System.out.println("DEBUG: First move detected, starting timer");
        }
        
        // Update last move time for next move
        lastMoveTime = currentTime;
    }
    
    @Override
    public void onItemAcquired(String itemName, int itemCount) {
        // No action needed on item acquisition
    }
    
    @Override
    public void onMenuSelected(String pluginName) {
        // No menu action needed
    }
    
    private void createPenaltyObstacle(int playerX, int playerY) {
        // Find an empty adjacent square
        int[] directions = {-1, 0, 1};
        for (int dy : directions) {
            for (int dx : directions) {
                if (dx == 0 && dy == 0) {
                    continue; // Skip the player's position
                }
                
                int newX = playerX + dx;
                int newY = playerY + dy;
                
                // Check bounds
                int[] gridSize = api.getGridSize();
                if (newX < 0 || newX >= gridSize[0] || newY < 0 || newY >= gridSize[1]) {
                    continue;
                }
                
                // Check if the square is empty
                if ("empty".equals(api.getGridSquareContents(newX, newY))) {
                    // Create a penalty obstacle that requires a random item (impossible to obtain)
                    String penaltyItem = "Penalty Key " + random.nextInt(1000);
                    api.addObstacleToGrid(newX, newY, penaltyItem);
                    
                    // Make it visible
                    api.setGridSquareVisible(newX, newY, true);
                    
                    System.out.println("Penalty obstacle created at (" + newX + ", " + newY + 
                                     ") requiring: " + penaltyItem);
                    return;
                }
            }
        }
    }
}
