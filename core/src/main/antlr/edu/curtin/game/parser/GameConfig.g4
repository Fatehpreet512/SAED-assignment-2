grammar GameConfig;

/**
 * ANTLR Grammar for the Game Configuration Domain-Specific Language (DSL).
 * 
 * This grammar defines the syntax for parsing game configuration files that specify:
 * - Grid dimensions and player/goal positions
 * - Item locations and properties
 * - Obstacle locations and requirements
 * - Plugin class names to load
 * - Python script content to execute
 * 
 * The grammar supports the assignment requirements for:
 * - Flexible spacing and line breaks
 * - Multiple character encodings (UTF-8, UTF-16, UTF-32)
 * - Unicode compatibility normalization for item names
 * - Complex nested structures for items and obstacles
 * 
 * Grammar Structure:
 * - config: Root rule that parses the entire file
 * - declarations: Sequence of game configuration declarations
 * - Individual declaration types: size, start, goal, item, obstacle, plugin, script
 * - Supporting rules: locations, strings, identifiers
 * 
 * Lexer Rules:
 * - INTEGER: Positive integers for coordinates and dimensions
 * - STRING: Quoted strings for item names and messages
 * - IDENTIFIER: Java class names for plugins
 * - SCRIPT_CONTENT: Python code between !{ and }
 * - WS: Whitespace (skipped)
 * - COMMENT: Single-line comments (skipped)
 */

// Parser rules - define the structure of valid input files
config : declarations EOF;

declarations : sizeDeclaration startDeclaration goalDeclaration (itemDeclaration | obstacleDeclaration | pluginDeclaration | scriptDeclaration)*;

// Basic game configuration declarations
sizeDeclaration : 'size' '(' INTEGER ',' INTEGER ')' ;    // Grid dimensions: size (rows, cols)
startDeclaration : 'start' '(' INTEGER ',' INTEGER ')' ;  // Player start: start (row, col)
goalDeclaration : 'goal' '(' INTEGER ',' INTEGER ')' ;   // Goal location: goal (row, col)

// Complex declarations with nested structures
itemDeclaration : 'item' STRING '{' atDeclaration messageDeclaration '}' ;           // Item with locations and message
obstacleDeclaration : 'obstacle' '{' atDeclaration requiresDeclaration '}' ;         // Obstacle with locations and requirements
pluginDeclaration : 'plugin' IDENTIFIER ('.' IDENTIFIER)* ;                          // Plugin class name
scriptDeclaration : 'script' '!{' SCRIPT_CONTENT '}' ;                               // Python script content

// Supporting declarations within items and obstacles
atDeclaration : 'at' locationList ;                    // List of grid locations
messageDeclaration : 'message' STRING ;               // Item description message
requiresDeclaration : 'requires' stringList ;          // List of required item names

// Location and string list parsing
locationList : location (',' location)* ;             // Comma-separated locations
location : '(' INTEGER ',' INTEGER ')' ;              // Single location: (row, col)
stringList : STRING (',' STRING)* ;                   // Comma-separated strings

// Lexer rules - define how to recognize tokens
INTEGER : [0-9]+ ;                                     // Positive integers for coordinates
STRING : '"' (~["\\] | '\\' .)* '"' ;                 // Quoted strings with escape sequences
IDENTIFIER : [a-zA-Z_][a-zA-Z0-9_]* ;                 // Java identifiers for class names

// Script content - match everything between !{ and } (handles nested braces)
SCRIPT_CONTENT : '!{' (~[}] | '}' ~[}])* '}' ;

// Skip whitespace and comments
WS : [ \t\r\n]+ -> skip ;                             // Whitespace (spaces, tabs, newlines)
COMMENT : '//' ~[\r\n]* -> skip ;                     // Single-line comments
