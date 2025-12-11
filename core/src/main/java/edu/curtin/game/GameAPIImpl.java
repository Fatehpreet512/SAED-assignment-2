package edu.curtin.game;

import java.util.*;

/**
 * Implementation of the GameAPI interface.
 * Provides access to game state for plugins and scripts.
 */
public class GameAPIImpl implements GameAPI {
    private GameState gameState;
    private List<Callback> callbacks;
    
    public GameAPIImpl(GameState gameState) {   //initializes the game api implementation
        this.gameState = gameState;
        this.callbacks = new ArrayList<>();
    }
    
    @Override
    public void addCallback(Callback callback) {
        callbacks.add(callback);   //adds a callback to the list of callbacks
    }
    
    @Override
    public void removeCallback(Callback callback) {
        callbacks.remove(callback);   //removes a callback from the list of callbacks
    }
    
    @Override
    public int[] getPlayerLocation() {
        return new int[]{gameState.getPlayerX(), gameState.getPlayerY()};   //returns the player's location
    }
    
    @Override
    public void setPlayerLocation(int x, int y) {
        gameState.setPlayerPosition(x, y);   //sets the player's location
    }
    
    @Override
    public Map<String, Integer> getPlayerInventory() {
        return gameState.getInventory();   //returns the player's inventory
    }
    
    @Override
    public void addItemToInventory(String itemName, int quantity) {
        for (int i = 0; i < quantity; i++) {   //adds an item to the player's inventory
            gameState.addItem(itemName);
        }
    }
    
    @Override
    public boolean removeItemFromInventory(String itemName, int quantity) {
        return gameState.removeItem(itemName, quantity);   //removes an item from the player's inventory
    }
    
    @Override
    public String getLastAcquiredItem() {
        return gameState.getLastAcquiredItem();   //returns the last acquired item
    }
    
    @Override
    public int[] getGridSize() {
        return new int[]{gameState.getGridWidth(), gameState.getGridHeight()};   //returns the grid size
    }
    
    @Override
    public String getGridSquareContents(int x, int y) {
        if (x < 0 || x >= gameState.getGridWidth() || y < 0 || y >= gameState.getGridHeight()) {   //if the x or y is out of bounds, return empty
            return "empty";
        }
        return gameState.getGridContents(x, y);   //returns the contents of the grid square at the given x and y coordinates
    }
    
    @Override
    public String getItemAt(int x, int y) {
        if (x < 0 || x >= gameState.getGridWidth() || y < 0 || y >= gameState.getGridHeight()) {   //if the x or y is out of bounds, return null
            return null;
        }
        return gameState.getItemName(x, y);   //returns the item name at the given x and y coordinates
    }
    
    @Override
    public List<String> getObstacleRequirements(int x, int y) {
        if (x < 0 || x >= gameState.getGridWidth() || y < 0 || y >= gameState.getGridHeight()) {   //if the x or y is out of bounds, return null
            return null;
        }
        return gameState.getObstacleRequirements(x, y);   //returns the obstacle requirements at the given x and y coordinates
    }
    
    @Override
    public boolean isGridSquareVisible(int x, int y) {
        if (x < 0 || x >= gameState.getGridWidth() || y < 0 || y >= gameState.getGridHeight()) {   //if the x or y is out of bounds, return false
            return false;
        }
        return gameState.isVisible(x, y);   //returns true if the grid square at the given x and y coordinates is visible
    }
    
    @Override
    public void setGridSquareVisible(int x, int y, boolean visible) {
        if (x >= 0 && x < gameState.getGridWidth() && y >= 0 && y < gameState.getGridHeight()) {   //if the x or y is out of bounds, return
            gameState.setVisible(x, y, visible);   //sets the visibility of the grid square at the given x and y coordinates    
        }
    }
    
    @Override
    public boolean removeItemFromGrid(int x, int y) {
        if (x < 0 || x >= gameState.getGridWidth() || y < 0 || y >= gameState.getGridHeight()) {
            return false;   //returns false if the x or y is out of bounds
        }
        if ("item".equals(gameState.getGridContents(x, y))) {
            gameState.setGridContents(x, y, "empty");   //sets the contents of the grid square at the given x and y coordinates to empty
            gameState.setItemName(x, y, null);
            return true;   //returns true if the item was removed
        }
        return false;   //returns false if the item was not removed
    }
    
    @Override   //similar to removeItemFromGrid, but adds an item to the grid square at the given x and y coordinates
    public boolean addItemToGrid(int x, int y, String itemName) {
        if (x < 0 || x >= gameState.getGridWidth() || y < 0 || y >= gameState.getGridHeight()) {
            return false;
        }
        if ("empty".equals(gameState.getGridContents(x, y))) {
            gameState.setGridContents(x, y, "item");
            gameState.setItemName(x, y, itemName);
            return true;
        }
        return false;
    }
    
    @Override   //adds an obstacle to the grid square at the given x and y coordinates
    public boolean addObstacleToGrid(int x, int y, String requiredItem) {
        if (x < 0 || x >= gameState.getGridWidth() || y < 0 || y >= gameState.getGridHeight()) {
            return false;
        }
        if ("empty".equals(gameState.getGridContents(x, y))) {
            gameState.setGridContents(x, y, "obstacle");
            gameState.addObstacleRequirement(x, y, requiredItem);
            return true;
        }
        return false;
    }
    
    @Override
    public void requestMenuOption(String pluginName, String menuText) {
        // This will be handled by the UI
        System.out.println("Plugin " + pluginName + " requested menu option: " + menuText);
    }
    
    @Override
    public String getCurrentDate() {
        return java.time.LocalDate.parse(gameState.getStartDate()).plusDays(gameState.getCurrentDay()).toString();   //returns the current date
    }
    
    @Override
    public void advanceDate() {
        gameState.advanceDay();   //advances the game date by one day
    }
    
    @Override
    public int getDaysElapsed() {
        return gameState.getCurrentDay();   //returns the number of days elapsed
    }
    
    @Override
    public void notifyPlayerMove(String direction, int newX, int newY) {
        for (Callback callback : callbacks) {
            callback.onPlayerMove(direction, newX, newY);   //notifies the callback that the player has moved
        }
    }
    
    @Override
    public void notifyItemAcquired(String itemName, int itemCount) {
        for (Callback callback : callbacks) {
            callback.onItemAcquired(itemName, itemCount);   //notifies the callback that the player has acquired an item
        }
    }
    
    @Override
    public void notifyMenuSelected(String pluginName) {
        for (Callback callback : callbacks) {
            callback.onMenuSelected(pluginName);   //notifies the callback that the player has selected a menu option
        }
    }
}
