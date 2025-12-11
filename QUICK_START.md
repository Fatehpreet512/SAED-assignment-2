# 🎮 2D Maze Game Engine - Quick Start

## Project Structure

```
maze-game-engine/
├── 📁 api/                    # API interfaces
│   └── src/main/java/edu/curtin/game/
│       ├── Callback.java      # Event callbacks
│       ├── GameAPI.java       # Main API interface  
│       └── Plugin.java        # Plugin interface
│
├── 📁 core/                   # Core game implementation
│   ├── src/main/java/edu/curtin/game/
│   │   ├── GameAPIImpl.java   # API implementation
│   │   ├── GameConfig.java    # Configuration classes
│   │   ├── GameConfigParser.java # Input file parser
│   │   ├── GameState.java     # Game state management
│   │   ├── GridArea.java      # JavaFX grid display
│   │   ├── GridAreaIcon.java  # Grid icons
│   │   ├── MazeGameApp.java   # Main application
│   │   ├── PluginManager.java # Plugin system
│   │   └── ScriptManager.java # Python scripts
│   └── src/main/resources/
│       ├── Messages.properties    # English
│       └── Messages_es.properties # Spanish
│
├── 📁 plugins/                # Plugin implementations
│   └── src/main/java/edu/curtin/gameplugins/
│       ├── Teleport.java      # Random teleportation
│       ├── Penalty.java       # Slow-move penalties
│       ├── Reveal.java        # Map reveal with "map" items
│       └── Prize.java         # Achievement rewards
│
├── 📄 demoinput.utf8.map     # Demo game configuration
├── 📄 README.md              # Full documentation
├── 📄 PROJECT_STRUCTURE.md   # Detailed structure
└── 📄 build.gradle           # Build configuration
```

## 🚀 How to Run

```bash
# Build the project
./gradlew build

# Run the game (ignoring PMD warnings)
./gradlew run --args="demoinput.utf8.map" -x pmdMain

# Or install and run
./gradlew install
./core/build/install/core/bin/core demoinput.utf8.map
```

## 🎮 Controls

- **Arrow Keys** or **WASD**: Move player
- **Escape**: Exit game
- **Locale Field**: Change language (try "es" for Spanish)

## 📝 Input File Format

The game uses a simple DSL for configuration:

```
size (10, 10)                    # Grid size
start (1, 1)                     # Player start
goal (8, 8)                      # Goal location

item "Wooden Sword" {            # Item definition
    at (2, 3), (5, 7)           # Locations
    message "Use wisely."        # Description
}

obstacle {                       # Obstacle definition
    at (3, 3), (4, 4)           # Locations
    requires "Wooden Sword"      # Required items
}

plugin edu.curtin.gameplugins.Teleport  # Load plugin
```