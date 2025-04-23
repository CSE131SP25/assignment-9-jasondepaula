package assignment9;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
    Snake snake = new Snake();
    Food food = new Food(true);    // Good food
    Food badFood = new Food(false); // Bad food (exploding bomb)
    
    private int score = 0;
    
    public Game() {
        StdDraw.enableDoubleBuffering();
    }
    
    public void play() { 
        while (snake.isInbounds()) { // Keep playing if snake doesn't die
            int dir = getKeypress();
            System.out.println("Keypress: " + dir);
            
            snake.changeDirection(dir);
            snake.move();
            
            if (snake.eatFood(food)) { // New food (good)
                food = new Food(true);
                score++;
            }
            
            if (snake.eatFood(badFood)) { // New bad food (exploding)
                badFood.setExploding();  // Set bad food to explode
                score--;  // Penalty for hitting a bomb (or another consequence)
            }
            
            updateDrawing();
        }
    }

    private int getKeypress() {
        if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            return 1;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            return 2;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            return 3;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            return 4;
        } else {
            return -1;
        }
    }

    private void updateDrawing() {
        // Clear the screen, draw the snake, food, and bad food
        StdDraw.clear();
        snake.draw();
        food.draw();
        badFood.draw();
        
        // Draw score
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.textLeft(0.45, 0.98, "POINTS: " + score);
        StdDraw.pause(50);
        StdDraw.show();
    }
    
    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}

