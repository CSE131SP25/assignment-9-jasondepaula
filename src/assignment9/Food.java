package assignment9;

import java.awt.Color;
import edu.princeton.cs.introcs.StdDraw;

public class Food {

    public static final double FOOD_SIZE = 0.02;
    private double x, y;
    private boolean isGood;
    private boolean isExploding;  
    private double explosionTime; 

    /**
     * Creates a new Food at a random location
     */
    public Food(boolean isGood) {
        this.isGood = isGood;
        this.isExploding = false;
        this.explosionTime = 0;
        respawn();
    }

    public void respawn() {
        this.x = Math.random() * (1 - FOOD_SIZE * 2) + FOOD_SIZE;
        this.y = Math.random() * (1 - FOOD_SIZE * 2) + FOOD_SIZE;
        this.isExploding = false;  
        this.explosionTime = 0;   
    }

    /**
     * Draws the Food
     */
    public void draw() {
        if (isExploding) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.filledCircle(x, y, FOOD_SIZE * 2); 
            explosionTime += 1;  // INCREMENT EXPLOSION TIME
            
            if (explosionTime > 10) {
                
                isExploding = false;
                respawn();  
            }
        } else {
            // Regular food appearance
            if (isGood) {
                StdDraw.setPenColor(Color.ORANGE);  
                StdDraw.filledCircle(x, y, FOOD_SIZE); 
            } else {
                StdDraw.setPenColor(Color.BLACK);  
                StdDraw.filledCircle(x, y, FOOD_SIZE);  
            }
        }
    }

    // Getters
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public boolean isGood() {
        return isGood;
    }

    public boolean isExploding() {
        return isExploding;
    }

    // Set the food to explode
    public void setExploding() {
        this.isExploding = true;
    }
}
