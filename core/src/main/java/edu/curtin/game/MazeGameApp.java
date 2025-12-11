package edu.curtin.game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.*;
import edu.curtin.game.GridArea;
import edu.curtin.game.GridAreaIcon;

/**
 * Main application class for the 2D maze/puzzle game.
 * 
 * This class extends JavaFX Application to create a GUI-based maze game that:
 * - Parses game configuration from input files
 * - Displays a 2D grid with player, items, obstacles, and goals
 * - Handles player movement via keyboard and GUI buttons
 * - Supports internationalization with locale switching
 * - Loads and manages plugins and scripts dynamically
 * - Provides a complete game experience with win conditions
 * 
 * The game follows the assignment requirements for:
 * - 2D grid-based gameplay
 * - Command-line input file parameter
 * - Internationalization support
 * - Plugin/script integration
 * - Proper build system integration
 */
public class MazeGameApp extends Application {
    // Core game state and API
    private GameState gameState;           // Manages player position, grid contents, visibility
    private GameAPIImpl gameAPI;          // Provides interface for plugins/scripts
    
    // GUI components
    private GridArea gridArea;            // Displays the 2D game grid
    private TextArea statusArea;         // Shows game status and inventory
    private Label statusLabel;           // Current game status text
    private Label dateLabel;             // Displays localized current date
    private TextField localeField;       // Input field for locale changes
    
    // Internationalization
    private ResourceBundle messages;     // Loaded text messages for current locale
    private Locale currentLocale;       // Currently selected locale
    
    // Plugin system
    private List<Plugin> loadedPlugins; // List of successfully loaded plugins
    private Map<String, Button> pluginButtons; // Buttons for plugin menu options
    
    // Movement controls (stored as instance variables for game over handling)
    private Button upBtn;
    private Button downBtn;
    private Button leftBtn;
    private Button rightBtn;
    
    /**
     * Main entry point for the application.
     * Validates command-line arguments and launches the JavaFX application.
     * 
     * @param args Command-line arguments - expects exactly one input file path
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java MazeGameApp <input_file>");
            System.exit(1);
        }
        launch(args);
    }
    
    /**
     * Initializes and starts the JavaFX application.
     * Sets up the game state, GUI components, and event handlers.
     * 
     * @param stage The primary stage for the application
     */
    @Override
    public void start(Stage stage) {
        // Initialize with system default locale
        currentLocale = Locale.getDefault();
        loadMessages();
        
        // Parse command line arguments
        String inputFile = getParameters().getRaw().get(0);
        
        try {
            // Parse input file and initialize game state
            GameConfigParser parser = new GameConfigParser();
            // Handle relative paths - if file doesn't exist, try parent directory
            if (!new java.io.File(inputFile).exists()) {
                inputFile = "../" + inputFile;
            }
            GameConfig config = parser.parseFile(inputFile);
            
            gameState = new GameState(
                config.getGridWidth(), config.getGridHeight(),
                config.getStartX(), config.getStartY(),
                config.getGoalX(), config.getGoalY()
            );
            
            // Initialize API and load plugins
            gameAPI = new GameAPIImpl(gameState);
            loadedPlugins = new ArrayList<>();
            pluginButtons = new HashMap<>();
            
            // Load plugins and scripts from config
            loadPlugins(config);
            loadScripts(config);
            
            // Set up items and obstacles from config
            setupGameFromConfig(config);
            
        } catch (Exception e) {
            System.err.println("Error initializing game: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
        // Create GUI
        createGUI(stage);
        
        // Update display
        updateDisplay();
    }
    
    /**
     * Loads localized messages for the current locale.
     * Falls back to English messages if the requested locale is not available.
     */
    private void loadMessages() {
        try {
            messages = ResourceBundle.getBundle("Messages", currentLocale);
        } catch (Exception e) {
            // Fallback to default messages
            messages = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }
    }
    
    /**
     * Creates and configures the GUI components for the game.
     * Sets up the grid display, status area, controls, and plugin buttons.
     * 
     * @param stage The JavaFX stage to configure
     */
    private void createGUI(Stage stage) {
        // Create grid area - displays the 2D game world
        System.out.println("Creating grid with dimensions: " + gameState.getGridWidth() + "x" + gameState.getGridHeight());
        gridArea = new GridArea(gameState.getGridWidth(), gameState.getGridHeight());
        gridArea.setStyle("-fx-background-color: #006000;");
        
        // Create status area - shows inventory and game messages
        statusArea = new TextArea();
        statusArea.setEditable(false);
        statusArea.setPrefRowCount(10);
        
        // Create status label - current game state
        statusLabel = new Label(messages.getString("status.ready"));
        
        // Create date label - localized current date
        dateLabel = new Label(messages.getString("date.current") + ": " + gameAPI.getCurrentDate());
        
        // Create locale field - for changing language
        localeField = new TextField();
        localeField.setPromptText(messages.getString("locale.prompt"));
        localeField.setOnAction(e -> changeLocale());
        
        // Create movement control buttons
        upBtn = new Button(messages.getString("move.up"));
        downBtn = new Button(messages.getString("move.down"));
        leftBtn = new Button(messages.getString("move.left"));
        rightBtn = new Button(messages.getString("move.right"));
        
        // Set up button event handlers
        upBtn.setOnAction(e -> movePlayer("up"));
        downBtn.setOnAction(e -> movePlayer("down"));
        leftBtn.setOnAction(e -> movePlayer("left"));
        rightBtn.setOnAction(e -> movePlayer("right"));
        
        // Create plugin buttons - dynamic buttons for loaded plugins
        VBox pluginBox = new VBox(5);
        pluginBox.setPadding(new Insets(5));
        Label pluginLabel = new Label(messages.getString("plugins.title"));
        pluginBox.getChildren().add(pluginLabel);
        
        // Add buttons for plugins that request menu options
        for (Plugin plugin : loadedPlugins) {
            if (plugin.getMenuText() != null) {
                Button pluginBtn = new Button(plugin.getMenuText());
                pluginBtn.setOnAction(e -> executePlugin(plugin));
                pluginButtons.put(plugin.getName(), pluginBtn);
                pluginBox.getChildren().add(pluginBtn);
            }
        }
        
        // Create main layout
        VBox leftPanel = new VBox(10);
        leftPanel.setPadding(new Insets(10));
        leftPanel.getChildren().addAll(
            statusLabel,
            dateLabel,
            new Label(messages.getString("locale.title")),
            localeField,
            new Separator(),
            new Label(messages.getString("controls.title")),
            upBtn,
            new HBox(5, leftBtn, rightBtn),
            downBtn,
            new Separator(),
            pluginBox
        );
        
        // Create main split pane
        SplitPane mainSplit = new SplitPane();
        mainSplit.getItems().addAll(gridArea, new VBox(statusArea, leftPanel));
        mainSplit.setDividerPositions(0.7);
        
        // Make the split pane focusable
        mainSplit.setFocusTraversable(true);
        
        // Create scene
        Scene scene = new Scene(mainSplit, 1200, 800);
        
        // Add keyboard controls to both scene and split pane
        scene.setOnKeyPressed(this::handleKeyPress);
        scene.setOnKeyReleased(this::handleKeyRelease);
        mainSplit.setOnKeyPressed(this::handleKeyPress);
        mainSplit.setOnKeyReleased(this::handleKeyRelease);
        
        // Add click handler to ensure focus
        mainSplit.setOnMouseClicked(event -> {
            System.out.println("Mouse clicked - requesting focus");
            mainSplit.requestFocus();
        });
        
        // Add focus listener to debug focus changes
        mainSplit.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            System.out.println("Focus changed: " + wasFocused + " -> " + isNowFocused);
        });
        
        stage.setTitle(messages.getString("game.title"));
        stage.setScene(scene);
        stage.show();
        
        // Request focus for keyboard input - try multiple approaches
        System.out.println("Requesting initial focus");
        mainSplit.requestFocus();
        Platform.runLater(() -> {
            System.out.println("Platform.runLater - requesting focus");
            mainSplit.requestFocus();
            stage.requestFocus();
        });
        
        // Additional focus attempts
        Platform.runLater(() -> {
            System.out.println("Second Platform.runLater - requesting focus");
            mainSplit.requestFocus();
        });
    }
    
    private void handleKeyPress(KeyEvent event) {   //handles the key press event
        System.out.println("Key pressed: " + event.getCode());
        switch (event.getCode()) {
            case UP:
            case W:
                System.out.println("Moving up");
                movePlayer("up");   //moves the player up
                break;
            case DOWN:
            case S:
                System.out.println("Moving down");
                movePlayer("down");   //moves the player down
                break;
            case LEFT:
            case A:
                System.out.println("Moving left");
                movePlayer("left");   //moves the player left
                break;
            case RIGHT:
            case D:
                System.out.println("Moving right");
                movePlayer("right");   //moves the player right
                break;
            default:
                System.out.println("Unhandled key: " + event.getCode());
                break;
        }
    }
    
    private void handleKeyRelease(KeyEvent event) {   //handles the key release event
        if (event.getCode() == KeyCode.ESCAPE) {
            // Close the application
            System.exit(0);
        }
    }
    
    private void movePlayer(String direction) {   //moves the player in the given direction
        int newX = gameState.getPlayerX();
        int newY = gameState.getPlayerY();
        
        switch (direction) {
            case "up":
                newY = Math.max(0, newY - 1);   //moves the player up
                break;
            case "down":
                newY = Math.min(gameState.getGridHeight() - 1, newY + 1);   //moves the player down
                break;
            case "left":
                newX = Math.max(0, newX - 1);   //moves the player left
                break;
            case "right":
                newX = Math.min(gameState.getGridWidth() - 1, newX + 1);   //moves the player right
                break;
        }
        
        // Check if move is valid
        if (canMoveTo(newX, newY)) {
            // Move player
            gameState.setPlayerPosition(newX, newY);   //sets the player position
            gameAPI.advanceDate();
            
            // Check for items
            if ("item".equals(gameState.getGridContents(newX, newY))) {
                String itemName = gameState.getItemName(newX, newY);   //gets the item name
                gameState.addItem(itemName);
                gameState.setGridContents(newX, newY, "empty");   //sets the grid contents to empty
                gameState.setItemName(newX, newY, null);
                
                statusArea.appendText(messages.getString("item.acquired") + ": " + itemName + "\n");
                gameAPI.notifyItemAcquired(itemName, gameState.getInventory().get(itemName));   //notifies the game API that the player has acquired an item
            }
            
            // Notify plugins of move
            gameAPI.notifyPlayerMove(direction, newX, newY);   //notifies the game API that the player has moved
            
            // Check for win condition
            if (gameState.isGameWon()) {   //checks if the game has been won
                statusArea.appendText(messages.getString("game.won") + "\n");
                statusArea.appendText(messages.getString("game.days") + ": " + gameState.getCurrentDay() + "\n");
                
                // Disable movement controls
                disableMovementControls();
                
                // Show game over dialog
                showGameOverDialog();
            }
            
            updateDisplay();
        } else {
            // Check if it's an obstacle
            if ("obstacle".equals(gameState.getGridContents(newX, newY))) {
                List<String> requirements = gameState.getObstacleRequirements(newX, newY);
                if (requirements != null) {
                    statusArea.appendText(messages.getString("obstacle.blocked") + ": ");
                    statusArea.appendText(String.join(", ", requirements) + "\n");
                }
            }
        }
    }
    
    private boolean canMoveTo(int x, int y) {   //checks if the player can move to the given x and y coordinates
        String contents = gameState.getGridContents(x, y);
        
        if ("empty".equals(contents) || "goal".equals(contents)) {
            return true;
        }
        
        if ("item".equals(contents)) {
            return true;
        }
        
        if ("obstacle".equals(contents)) {
            List<String> requirements = gameState.getObstacleRequirements(x, y);
            if (requirements != null) {
                for (String requirement : requirements) {
                    if (gameState.getInventory().getOrDefault(requirement, 0) == 0) {
                        return false;
                    }
                }
            }
            return true;
        }
        
        return false;
    }
    
    private void updateDisplay() {   //updates the display
        System.out.println("Updating display - Player at (" + gameState.getPlayerX() + "," + gameState.getPlayerY() + ")");
        System.out.println("Grid dimensions: " + gameState.getGridWidth() + "x" + gameState.getGridHeight());
        
        // Clear existing icons
        gridArea.getIcons().clear();
        
        // Add player icon (using text display for now)
        gridArea.getIcons().add(new GridAreaIcon(
            gameState.getPlayerX(), gameState.getPlayerY(), 0.0, 1.0,
            (java.io.InputStream) null,
            "P" // Player symbol
        ));
        
        // Add goal icon
        if (gameState.isVisible(gameState.getGoalX(), gameState.getGoalY())) {
            gridArea.getIcons().add(new GridAreaIcon(
                gameState.getGoalX(), gameState.getGoalY(), 0.0, 1.0,
                (java.io.InputStream) null,
                "G" // Goal symbol
            ));
        }
        
        // Add visible items and obstacles
        for (int y = 0; y < gameState.getGridHeight(); y++) {
            for (int x = 0; x < gameState.getGridWidth(); x++) {
                if (gameState.isVisible(x, y)) {
                    String contents = gameState.getGridContents(x, y);
                    if ("item".equals(contents)) {
                        String itemName = gameState.getItemName(x, y);
                        gridArea.getIcons().add(new GridAreaIcon(
                            x, y, 0.0, 0.8,
                            (java.io.InputStream) null,
                            "I" // Item symbol
                        ));
                    } else if ("obstacle".equals(contents)) {
                        gridArea.getIcons().add(new GridAreaIcon(
                            x, y, 0.0, 0.8,
                            (java.io.InputStream) null,
                            "X" // Obstacle symbol
                        ));
                    }
                }
            }
        }
        
        // Update status
        statusLabel.setText(messages.getString("status.ready"));
        dateLabel.setText(messages.getString("date.current") + ": " + gameAPI.getCurrentDate());
        
        // Update inventory display
        statusArea.clear();
        statusArea.appendText(messages.getString("inventory.title") + ":\n");
        for (Map.Entry<String, Integer> entry : gameState.getInventory().entrySet()) {
            statusArea.appendText("  " + entry.getKey() + ": " + entry.getValue() + "\n");
        }
        
        gridArea.requestLayout();
    }
    
    private void changeLocale() {   //changes the locale
        try {
            String localeTag = localeField.getText().trim();
            if (!localeTag.isEmpty()) {
                currentLocale = Locale.forLanguageTag(localeTag);
                loadMessages();
                updateDisplay();
                statusArea.appendText(messages.getString("locale.changed") + ": " + currentLocale + "\n");
            }
        } catch (Exception e) {
            statusArea.appendText(messages.getString("locale.error") + ": " + e.getMessage() + "\n");
        }
    }
    
    private void loadPlugins(GameConfig config) {   //loads the plugins
        PluginManager pluginManager = new PluginManager(gameAPI);
        loadedPlugins = pluginManager.loadPlugins(config.getPlugins());
        
        // Add plugin buttons to UI
        for (Plugin plugin : loadedPlugins) {
            if (plugin.getMenuText() != null) {
                Button pluginBtn = new Button(plugin.getMenuText());
                pluginBtn.setOnAction(e -> executePlugin(plugin));
                pluginButtons.put(plugin.getName(), pluginBtn);
            }
        }
    }
    
    private void loadScripts(GameConfig config) {   //loads the scripts
        ScriptManager scriptManager = new ScriptManager(gameAPI);
        Map<String, String> scripts = new HashMap<>();
        for (int i = 0; i < config.getScripts().size(); i++) {
            scripts.put("Script" + i, config.getScripts().get(i));
        }
        scriptManager.executeScripts(scripts);
    }
    
    private void setupGameFromConfig(GameConfig config) {   //sets up the game from the config
        // Set up items
        System.out.println("Loading " + config.getItems().size() + " items...");
        for (GameConfig.Item item : config.getItems()) {
            System.out.println("Item: " + item.getName() + " at " + item.getLocations().size() + " locations");
            for (int[] location : item.getLocations()) {
                gameState.setGridContents(location[0], location[1], "item");
                gameState.setItemName(location[0], location[1], item.getName());
                System.out.println("  Placed at (" + location[0] + "," + location[1] + ")");
            }
        }
        
        // Set up obstacles
        for (GameConfig.Obstacle obstacle : config.getObstacles()) {
            for (int[] location : obstacle.getLocations()) {
                gameState.setGridContents(location[0], location[1], "obstacle");
                gameState.setObstacleRequirements(location[0], location[1], obstacle.getRequirements());
            }
        }
        
        // Check if player starts on an item and pick it up automatically
        int playerX = gameState.getPlayerX();
        int playerY = gameState.getPlayerY();
        if ("item".equals(gameState.getGridContents(playerX, playerY))) {
            String itemName = gameState.getItemName(playerX, playerY);
            if (itemName != null) {
                gameState.addItem(itemName);
                gameState.setGridContents(playerX, playerY, "empty");
                gameState.setItemName(playerX, playerY, null);
                System.out.println("Player automatically picked up starting item: " + itemName);
            }
        }
    }
    
    private void executePlugin(Plugin plugin) {   //executes the plugin
        gameAPI.notifyMenuSelected(plugin.getName());
    }
    
    private void disableMovementControls() {   //disables the movement controls
        upBtn.setDisable(true);
        downBtn.setDisable(true);
        leftBtn.setDisable(true);
        rightBtn.setDisable(true);
    }
    
    private void showGameOverDialog() {   //shows the game over dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(messages.getString("game.over.title"));
        alert.setHeaderText(messages.getString("game.won"));
        alert.setContentText(messages.getString("game.days") + ": " + gameState.getCurrentDay());
        
        alert.showAndWait();
    }
}
