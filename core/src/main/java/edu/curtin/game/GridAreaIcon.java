// WARNING: don't modify this file, unless you're sure you know what you're doing!

package edu.curtin.game;

import java.io.InputStream;
import javafx.scene.image.Image;

/**
 * Represents an icon to be displayed in a GridArea pane.
 * 
 * This class is part of the assignment 1 starter code, adapted for the maze game.
 * It represents a single icon that can be positioned, rotated, and scaled within the grid.
 * 
 * In the maze game context, GridAreaIcon objects represent:
 * - Player position (P)
 * - Goal location (G) 
 * - Items (I)
 * - Obstacles (X)
 * - Hidden squares (###)
 * 
 * Key features:
 * - Position: X,Y coordinates within the grid
 * - Rotation: Angle in degrees
 * - Scale: Size multiplier
 * - Image: Optional image (currently using text captions instead)
 * - Caption: Text to display (used for game symbols)
 * - Visibility: Whether the icon is shown
 * 
 * Usage:
 * After modifying any properties, call requestLayout() on the GridArea
 * to trigger a redraw of the display.
 */
public class GridAreaIcon
{
    private double x;                    // X coordinate within the grid
    private double y;                    // Y coordinate within the grid
    private double rotation;             // Rotation angle in degrees
    private double scale;                // Scale multiplier for size
    private Image image;                 // Optional image (currently null for text-based display)
    private String caption;              // Text caption to display
    private boolean shown = true;        // Whether the icon is visible

    /**
     * Create a new GridAreaIcon with the specified properties.
     * 
     * @param x X coordinate within the grid
     * @param y Y coordinate within the grid
     * @param rotation Rotation angle in degrees
     * @param scale Scale multiplier for size
     * @param image Image to display (can be null for text-only)
     * @param caption Text caption to display
     */
    public GridAreaIcon(double x, double y, double rotation, double scale, Image image, String caption)
    {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.scale = scale;
        this.image = image;
        this.caption = caption;
    }

    /**
     * Create a new GridAreaIcon with an InputStream for the image.
     * 
     * This constructor is used when loading images from resources, but in the
     * current implementation, we use text-based display instead of images.
     * 
     * @param x X coordinate within the grid
     * @param y Y coordinate within the grid
     * @param rotation Rotation angle in degrees
     * @param scale Scale multiplier for size
     * @param imageStream InputStream for the image (not used in current implementation)
     * @param caption Text caption to display
     */
    public GridAreaIcon(double x, double y, double rotation, double scale, InputStream imageStream, String caption)
    {
        this(x, y, rotation, scale, (Image) null, caption); // Always use null for images, we'll use text
    }
    //getters for the x, y, rotation, scale, image, caption, and shown properties
    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getRotation()
    {
        return rotation;
    }

    public double getScale()
    {
        return scale;
    }

    public Image getImage()
    {
        return image;
    }

    public String getCaption()
    {
        return caption;
    }

    public boolean isShown()
    {
        return shown;
    }
    //setters for the x, y, rotation, scale, image, caption, and shown properties
    public void setPosition(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void setRotation(double rotation)
    {
        this.rotation = rotation;
    }

    public void setScale(double scale)
    {
        this.scale = scale;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    public void setCaption(String caption)
    {
        this.caption = caption;
    }

    public void setShown(boolean shown)
    {
        this.shown = shown;
    }
}
