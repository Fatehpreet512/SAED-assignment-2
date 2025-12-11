package edu.curtin.game;

import org.python.core.*;
import org.python.util.PythonInterpreter;
import java.util.*;
import java.io.StringWriter;

/**
 * Manages execution of Python scripts using Jython interpreter.
 * 
 * This class handles the execution of Python scripts embedded in the input files.
 * It uses Jython (Python interpreter for Java) to:
 * - Execute Python code dynamically
 * - Set up callback mechanisms for game events
 * - Provide access to the GameAPI from Python scripts
 * - Handle script errors and exceptions gracefully
 * 
 * Scripts can define callback classes that respond to game events like
 * player movement, item acquisition, and menu selections.
 */
public class ScriptManager {
    private GameAPI gameAPI;              // API instance accessible to scripts
    private PythonInterpreter interpreter; // Jython interpreter for script execution
    private Map<String, PyObject> scriptCallbacks; // Script name -> callback object mapping
    
    /**
     * Initializes the script manager with a GameAPI instance.
     * Sets up the Jython interpreter and makes the API available to scripts.
     * 
     * @param gameAPI The API instance that scripts will use to interact with the game
     */
    public ScriptManager(GameAPI gameAPI) {
        this.gameAPI = gameAPI;
        this.interpreter = new PythonInterpreter();
        this.scriptCallbacks = new HashMap<>();
        
        // Set up the API object in the Python interpreter
        // This makes the GameAPI accessible to Python scripts as 'api'
        interpreter.set("api", gameAPI);
    }
    
    /**
     * Execute a Python script and set up callbacks.
     * 
     * This method:
     * 1. Executes the Python code in the Jython interpreter
     * 2. Searches for callback classes defined in the script
     * 3. Sets up callback mechanisms for game events
     * 4. Handles any execution errors gracefully
     * 
     * @param scriptName A name for the script (for identification and debugging)
     * @param scriptContent The Python code to execute
     * @return true if script executed successfully, false otherwise
     */
    public boolean executeScript(String scriptName, String scriptContent) {
        try {
            // Execute the script in the Jython interpreter
            interpreter.exec(scriptContent);
            
            // Look for callback classes in the script and set them up
            setupCallbacks(scriptName);
            
            System.out.println("Successfully executed script: " + scriptName);
            return true;
            
        } catch (Exception e) {
            System.err.println("Failed to execute script " + scriptName + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Execute multiple scripts.
     * @param scripts Map of script names to script content
     * @return List of successfully executed script names
     */
    public List<String> executeScripts(Map<String, String> scripts) {
        List<String> executed = new ArrayList<>();
        for (Map.Entry<String, String> entry : scripts.entrySet()) {
            if (executeScript(entry.getKey(), entry.getValue())) {
                executed.add(entry.getKey());
            }
        }
        return executed;
    }
    
    /**
     * Set up callbacks for a script by looking for callback classes.
     * @param scriptName The name of the script
     */
    private void setupCallbacks(String scriptName) {
        try {
            // Look for common callback class names
            String[] callbackClassNames = {
                "MyCallback", "GameCallback", "ScriptCallback", "Callback"
            };
            
            for (String className : callbackClassNames) {
                if (interpreter.get(className) != null) {
                    PyObject callbackClass = interpreter.get(className);
                    if (callbackClass != null && callbackClass.isCallable()) {
                        // Create instance of the callback class
                        PyObject callbackInstance = callbackClass.__call__();
                        scriptCallbacks.put(scriptName, callbackInstance);
                        
                        // Set up the callback with the game API
                        setupCallbackInstance(scriptName, callbackInstance);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to setup callbacks for script " + scriptName + ": " + e.getMessage());
        }
    }
    
    /**
     * Set up a callback instance with the game API.
     * @param scriptName The name of the script
     * @param callbackInstance The callback instance
     */
    private void setupCallbackInstance(String scriptName, PyObject callbackInstance) {
        try {
            // Create a Java callback wrapper
            Callback javaCallback = new Callback() {
                @Override
                public void onPlayerMove(String direction, int newX, int newY) {
                    callScriptMethod(callbackInstance, "handleEvent", 
                        "move", direction, newX, newY);
                }
                
                @Override
                public void onItemAcquired(String itemName, int itemCount) {   //handles the item acquired event
                    callScriptMethod(callbackInstance, "handleEvent", 
                        "item", itemName, itemCount);
                }
                
                @Override
                public void onMenuSelected(String pluginName) {   //handles the menu selected event
                    callScriptMethod(callbackInstance, "handleEvent", 
                        "menu", pluginName);
                }
            };
            
            // Register the callback with the game API
            gameAPI.addCallback(javaCallback);
            
        } catch (Exception e) {
            System.err.println("Failed to setup callback instance for script " + scriptName + ": " + e.getMessage());
        }
    }
    
    /**
     * Call a method on a Python object.
     * @param pyObject The Python object
     * @param methodName The method name
     * @param args The arguments to pass
     */
    private void callScriptMethod(PyObject pyObject, String methodName, java.lang.Object... args) {
        try {
            PyObject method = pyObject.__getattr__(methodName);
            if (method != null && method.isCallable()) {
                PyObject[] pyArgs = new PyObject[args.length];
                for (int i = 0; i < args.length; i++) {
                    pyArgs[i] = Py.java2py(args[i]);
                }
                method.__call__(pyArgs);
            }
        } catch (Exception e) {
            System.err.println("Failed to call script method " + methodName + ": " + e.getMessage());
        }
    }
    
    /**
     * Get the Python interpreter for direct access.
     * @return The Python interpreter
     */
    public PythonInterpreter getInterpreter() {
        return interpreter;
    }
    
    /**
     * Clean up resources.
     */
    public void cleanup() {
        if (interpreter != null) {
            interpreter.close();
        }
        scriptCallbacks.clear();
    }
}
