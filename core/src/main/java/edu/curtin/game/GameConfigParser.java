package edu.curtin.game;

import edu.curtin.game.parser.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser for the game configuration DSL (Domain-Specific Language).
 * 
 * This parser handles the specially-formatted input files that define:
 * - Grid size and player/goal positions
 * - Item locations and properties
 * - Obstacle locations and requirements
 * - Plugin declarations
 * - Script declarations (Python code)
 * 
 * The parser supports multiple character encodings (UTF-8, UTF-16, UTF-32)
 * as required by the assignment specifications.
 * 
 */
public class GameConfigParser {   //class for the game config parser
    
    /**
     * Parses a game configuration file and returns a GameConfig object.
     * 
     * @param filename Path to the input file (.utf8.map, .utf16.map, or .utf32.map)
     * @return GameConfig object containing all parsed game data
     * @throws IOException if the file cannot be read
     */
    public GameConfig parseFile(String filename) throws IOException {
        // Determine encoding from file extension
        Charset encoding = determineEncoding(filename);
        
        // Read file content
        String content = readFile(filename, encoding);
        
        // Parse using regex-based approach (temporary solution)
        return parseContent(content);
    }
    
    /**
     * Determines the character encoding based on the file extension.
     * 
     * @param filename The input filename
     * @return Charset object for the appropriate encoding
     */
    private Charset determineEncoding(String filename) {   //determines the character encoding based on the file extension
        if (filename.endsWith(".utf16.map")) {
            return StandardCharsets.UTF_16;
        } else if (filename.endsWith(".utf32.map")) {
            return Charset.forName("UTF-32");
        } else {
            return StandardCharsets.UTF_8; // Default for .utf8.map
        }
    }
    
    /**
     * Reads the entire file content using the specified encoding.
     * 
     * @param filename Path to the file
     * @param encoding Character encoding to use
     * @return Complete file content as a string
     * @throws IOException if the file cannot be read
     */
    private String readFile(String filename, Charset encoding) throws IOException {   //reads the entire file content using the specified encoding
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename), encoding))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        }
    }
    
    /**
     * Parses the file content using regex-based approach.
     * - size (rows,cols)
     * - start (r,c) and goal (r,c)
     * - item "name" { at (r,c), ... message "..." }
     * - obstacle { at (r,c), ... requires "item1", "item2" }
     * - plugin class.name
     * - script !{ python code }
     * 
     * @param content The file content to parse
     * @return GameConfig object with all parsed data
     */
    private GameConfig parseContent(String content) {   //parses the file content using regex-based approach
        return parseSimple(content);
    }
    
    private GameConfig parseSimple(String content) {   //parses the file content using regex-based approach
        GameConfig config = new GameConfig();
        
        // Split by lines and process each line
        String[] lines = content.split("\\n");
        StringBuilder currentDeclaration = new StringBuilder();
        
        boolean inScript = false;   //boolean to check if the current line is a script
        for (String line : lines) {
            String trimmedLine = line.trim();
            if (trimmedLine.isEmpty()) continue;   //if the line is empty, continue
            
            // Check if this line starts a new declaration
            if (trimmedLine.matches("^(size|start|goal|item|obstacle|plugin|script)\\s+.*")) {
                // Process any previous declaration
                if (currentDeclaration.length() > 0) {
                    processDeclaration(currentDeclaration.toString(), config);
                }
                // Start new declaration
                currentDeclaration = new StringBuilder(trimmedLine);
                inScript = trimmedLine.startsWith("script");
            } else {
                // Continue current declaration
                // For scripts, preserve line breaks and indentation; for others, use spaces
                if (inScript) {
                    currentDeclaration.append("\n").append(line);  // Keep original indentation
                } else {
                    currentDeclaration.append(" ").append(trimmedLine);
                }
            }
        }
        
        // Process the last declaration
        if (currentDeclaration.length() > 0) {
            processDeclaration(currentDeclaration.toString(), config);
        }
        
        return config;
    }
    
    private void processDeclaration(String declaration, GameConfig config) {   //processes the declaration
        declaration = declaration.trim();
        if (declaration.isEmpty()) return;   //if the declaration is empty, return
        
        if (declaration.startsWith("size")) {
            parseSize(declaration, config);   //parses the size declaration
        } else if (declaration.startsWith("start")) {
            parseStart(declaration, config);   //parses the start declaration
        } else if (declaration.startsWith("goal")) {
            parseGoal(declaration, config);   //parses the goal declaration
        } else if (declaration.startsWith("item")) {
            parseItem(declaration, config);   //parses the item declaration
        } else if (declaration.startsWith("obstacle")) {
            parseObstacle(declaration, config);   //parses the obstacle declaration
        } else if (declaration.startsWith("plugin")) {
            parsePlugin(declaration, config);   //parses the plugin declaration
        } else if (declaration.startsWith("script")) {
            parseScript(declaration, config);   //parses the script declaration
        }
    }
    
    private void parseSize(String line, GameConfig config) {
        // Parse size (10,10) format
        String[] parts = line.split("\\(");
        if (parts.length > 1) {
            String coords = parts[1].replace(")", "").trim();
            String[] coordsArray = coords.split(",");
            if (coordsArray.length == 2) {
                config.setGridWidth(Integer.parseInt(coordsArray[0].trim()));
                config.setGridHeight(Integer.parseInt(coordsArray[1].trim()));
            }
        }
    }
    
    private void parseStart(String line, GameConfig config) {
        // Parse start (1,5) format
        String[] parts = line.split("\\(");
        if (parts.length > 1) {
            String coords = parts[1].replace(")", "").trim();
            String[] coordsArray = coords.split(",");
            if (coordsArray.length == 2) {
                config.setStartX(Integer.parseInt(coordsArray[0].trim()));
                config.setStartY(Integer.parseInt(coordsArray[1].trim()));
            }
        }
    }
    
    private void parseGoal(String line, GameConfig config) {
        // Parse goal (9,8) format
        String[] parts = line.split("\\(");
        if (parts.length > 1) {
            String coords = parts[1].replace(")", "").trim();
            String[] coordsArray = coords.split(",");
            if (coordsArray.length == 2) {
                config.setGoalX(Integer.parseInt(coordsArray[0].trim()));
                config.setGoalY(Integer.parseInt(coordsArray[1].trim()));
            }
        }
    }
    
    private void parsePlugin(String line, GameConfig config) {
        // Parse plugin edu.curtin.gameplugins.Teleport format
        String[] parts = line.split("\\s+");
        if (parts.length > 1 && !parts[1].trim().isEmpty() && !parts[1].trim().equals("=")) {
            config.getPlugins().add(parts[1].trim());
        }
    }
    
    private void parseItem(String line, GameConfig config) {
        // Parse item "name" { at (x,y) message "text" } format
        try {
            System.out.println("Parsing item: " + line);
            
            // Extract item name
            int nameStart = line.indexOf('"');
            int nameEnd = line.indexOf('"', nameStart + 1);
            if (nameStart == -1 || nameEnd == -1) {
                System.out.println("No item name found");
                return;
            }
            
            String itemName = line.substring(nameStart + 1, nameEnd);
            System.out.println("Item name: " + itemName);
            
            // Extract locations - look for "at (" pattern
            int atIndex = line.indexOf("at (");
            if (atIndex == -1) {
                System.out.println("No 'at' found");
                return;
            }
            
            String locationsPart = line.substring(atIndex + 3); // Changed from +4 to +3 to include the (
            int messageIndex = locationsPart.indexOf("message");
            if (messageIndex != -1) {
                locationsPart = locationsPart.substring(0, messageIndex);
            }
            
            System.out.println("Locations part: " + locationsPart);
            
            // Parse coordinates - look for (x,y) pattern
            System.out.println("Looking for coordinates in: " + locationsPart);
            
            // Use regex to find all (x,y) patterns
            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\((\\d+)\\s*,\\s*(\\d+)\\)");
            java.util.regex.Matcher matcher = pattern.matcher(locationsPart);
            
            while (matcher.find()) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                System.out.println("Adding item at (" + x + "," + y + ")");
                config.addItem(itemName, x, y, "Item message");
            }
        } catch (Exception e) {
            System.out.println("Error parsing item: " + e.getMessage());
        }
    }
    
    private void parseObstacle(String line, GameConfig config) {
        // Parse obstacle { at (x,y), (x,y) requires "item1", "item2" } format
        try {
            System.out.println("Parsing obstacle: " + line);
            
            // Extract requirements first
            int requiresIndex = line.indexOf("requires");
            if (requiresIndex == -1) {
                System.out.println("No 'requires' found in obstacle");
                return;
            }
            
            String requirementsPart = line.substring(requiresIndex + 8).trim();
            System.out.println("Requirements part: " + requirementsPart);
            
            // Parse requirements - look for quoted strings
            java.util.regex.Pattern reqPattern = java.util.regex.Pattern.compile("\"([^\"]+)\"");
            java.util.regex.Matcher reqMatcher = reqPattern.matcher(requirementsPart);
            java.util.List<String> requirements = new java.util.ArrayList<>();
            
            while (reqMatcher.find()) {
                requirements.add(reqMatcher.group(1));
                System.out.println("Found requirement: " + reqMatcher.group(1));
            }
            
            if (requirements.isEmpty()) {
                System.out.println("No requirements found");
                return;
            }
            
            // Extract locations
            int atIndex = line.indexOf("at");
            if (atIndex == -1) {
                System.out.println("No 'at' found in obstacle");
                return;
            }
            
            String locationsPart = line.substring(atIndex + 2, requiresIndex);
            System.out.println("Obstacle locations part: " + locationsPart);
            
            // Use regex to find all (x,y) patterns
            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\((\\d+)\\s*,\\s*(\\d+)\\)");
            java.util.regex.Matcher matcher = pattern.matcher(locationsPart);
            
            while (matcher.find()) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                System.out.println("Adding obstacle at (" + x + "," + y + ")");
                
                // Add obstacle with all requirements
                for (String requirement : requirements) {
                    config.addObstacle(x, y, requirement);
                }
            }
        } catch (Exception e) {
            System.out.println("Error parsing obstacle: " + e.getMessage());
        }
    }
    
    private void parseScript(String line, GameConfig config) {
        // Parse script !{ ... } format
        int start = line.indexOf("!{");
        if (start != -1) {
            // Find the matching closing brace by counting braces
            int braceCount = 0;
            int end = start + 2; // Start after "!{"
            //loop through the line and find the matching closing brace
            for (int i = start + 2; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == '{') {
                    braceCount++;   
                } else if (c == '}') {
                    if (braceCount == 0) {
                        end = i;
                        break;
                    } else {
                        braceCount--;
                    }
                }
            }
            //if the end is greater than the start plus 2, extract the script content
            if (end > start + 2) {
                String scriptContent = line.substring(start + 2, end);
                System.out.println("Extracted script content: " + scriptContent);
                
                // The script content needs to preserve the original indentation from the file
                // Since the script content is extracted with proper indentation, use it as-is
                System.out.println("Using script content as-is: " + scriptContent);
                
                config.getScripts().add(scriptContent);
            }
        }
    }
}
