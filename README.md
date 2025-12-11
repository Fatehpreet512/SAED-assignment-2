# 2D Maze/Puzzle Game Engine

A comprehensive 2D maze/puzzle game engine built with Java and JavaFX, featuring internationalization, plugin system, and script support.

## Features

### Core Game Features
- 2D grid-based maze game with player movement
- Item collection in an inventory
- Passing obstacles with required items
- Goal-based win condition
- Real-time game state display

### Internationalization (i18n)
- Locale switching on the grid itself
- English and Spanish translations included
- Localized date display

### Plugin System
- Dynamic plugin loading using reflection
- Four built-in plugins:
  - **Teleport**: Allows one-time teleportation to random location
  - **Penalty**: Creates obstacles if player takes too long to move
  - **Reveal**: Reveals goal and hidden items when acquiring "map" items
  - **Prize**: Awards special item after collecting 5 items/traversing obstacles

### Script System
- Python script execution using Jython
- Callback system for game events
- API access for game state manipulation

### Domain-Specific Language (DSL)
- Custom input file format for game configuration
- Support for UTF-8, UTF-16, and UTF-32 encodings
- Parsed using simple regex-based parser

```

## Building and Running

### Prerequisites
- Java 11 or higher
- Gradle 8.x

### Build
```bash
# Clean and rebuild
./gradlew clean build -x pmdMain
```

### Run (also, kill all the previous processes to ensure a smooth experience)
```bash
# Test with the demo input (has working plugins)     
./gradlew run --args="testinput.utf8.map" -x pmdMain```
# Kill all Java processes to prevent multiple windows  
pkill -f java                                         
# Stop Gradle daemons
./gradlew --stop
## Controls

- **Arrow Keys** or **WASD**: Move player
- **Escape**: Exit game
- **Locale Field**: Change language (enter IETF language tag)

## API for Plugins and Scripts

The game provides a comprehensive API for plugins and scripts:

### Callback Interface
- `onPlayerMove(direction, newX, newY)`: Called when player moves
- `onItemAcquired(itemName, itemCount)`: Called when item is collected
- `onMenuSelected(pluginName)`: Called when plugin menu is selected

### Game API
- Player location and movement
- Inventory management
- Grid state queries and modifications
- Visibility control
- Date/time management

## Internationalization

The game supports 2 languages through resource bundles:
- `Messages.properties`: Default English messages
- `Messages_es.properties`: Spanish messages
- more languages can be added by creating new property files