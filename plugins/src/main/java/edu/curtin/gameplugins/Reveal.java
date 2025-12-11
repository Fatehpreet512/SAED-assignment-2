package edu.curtin.gameplugins;

import edu.curtin.game.*;
import java.util.List;

/**
 * Plugin that reveals the goal and all hidden items when the player acquires an item with "map" in the name.
 */
public class Reveal implements Plugin, Callback {
    private GameAPI api;
    private boolean hasRevealed = false;
    
    @Override
    public void initialize(GameAPI api) {   //initializes the reveal plugin
        this.api = api;
        api.addCallback(this);
        System.out.println("Reveal plugin initialized - will reveal goal and hidden items when a 'map' item is acquired");
    }
    
    @Override
    public String getName() {
        return "Reveal";   //returns reveal plugin name
    }
    
    @Override
    public String getMenuText() {
        return null; //returns null if no menu option is needed
    }
    
    @Override
    public void cleanup() {
        if (api != null) { //if api is not null, remove callback
            api.removeCallback(this);
        }
    }
    
    @Override
    public void onPlayerMove(String direction, int newX, int newY) {
        // No action needed on player move
    }
    
    @Override
    public void onItemAcquired(String itemName, int itemCount) {
        // Check if the item has "map" in its name (case insensitive)
        if (itemName.toLowerCase().contains("map") && !hasRevealed) {
            revealGoalAndItems();   //reveals the goal and all hidden items if the item has "map" in its name and the goal and all hidden items have not been revealed yet
            hasRevealed = true;
        }
    }
    
    @Override
    public void onMenuSelected(String pluginName) {
        // No menu action needed
    }
    
    private void revealGoalAndItems() {
        int[] gridSize = api.getGridSize();   //gets the grid size
        int width = gridSize[0];
        int height = gridSize[1];
        
        // Reveal the goal
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String contents = api.getGridSquareContents(x, y);
                if ("goal".equals(contents)) {
                    api.setGridSquareVisible(x, y, true);
                    System.out.println("Goal revealed at (" + x + ", " + y + ")");
                }
            }
        }
        
        // Reveal all hidden items
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String contents = api.getGridSquareContents(x, y);
                if ("item".equals(contents) && !api.isGridSquareVisible(x, y)) {
                    api.setGridSquareVisible(x, y, true);
                    String itemName = api.getItemAt(x, y);
                    System.out.println("Hidden item revealed at (" + x + ", " + y + "): " + itemName);
                }
            }
        }
        
        System.out.println("Map item acquired! Goal and all hidden items revealed!");
    }
}
