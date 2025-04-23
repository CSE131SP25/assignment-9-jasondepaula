package assignment9;

import java.util.LinkedList;

public class Snake {

    private static final double SEGMENT_SIZE = 0.02;
    private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
    private LinkedList<BodySegment> segments;
    private double deltaX;
    private double deltaY;

    public Snake() {
        // FIXME - set up the segments instance variable
        BodySegment initialSegment = new BodySegment(0.5, 0.5, SEGMENT_SIZE);
        segments = new LinkedList<>();
        segments.add(initialSegment);
        deltaX = 0;
        deltaY = 0;
    }
    
    public void changeDirection(int direction) {
        if (direction == 1) { // up
            deltaY = MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 2) { // down
            deltaY = -MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 3) { // left
            deltaY = 0;
            deltaX = -MOVEMENT_SIZE;
        } else if (direction == 4) { // right
            deltaY = 0;
            deltaX = MOVEMENT_SIZE;
        }
    }
    
    /**
     * Moves the snake by updating the position of each of the segments
     * based on the current direction of travel
     */
    public void move() {
        // it basically just puts a new head down in front and deletes the last segment each move
        // get the current position of the head
        BodySegment head = segments.getFirst();
        double currentHeadX = head.getX();
        double currentHeadY = head.getY();
        
        double newHeadX = currentHeadX + deltaX;
        double newHeadY = currentHeadY + deltaY;
        
        BodySegment newSegment = new BodySegment(newHeadX, newHeadY, SEGMENT_SIZE);
        segments.addFirst(newSegment);
        segments.removeLast(); 
    }
    
    /**
     * Draws the snake by drawing each segment
     */
    public void draw() {
        for (BodySegment segment : segments) {
            segment.draw();
        }
    }
    
    /**
     * The snake attempts to eat the given food, growing if it does so successfully
     * @param f the food to be eaten
     * @return true if the snake successfully ate the food
     */
    public boolean eatFood(Food f) {
        BodySegment head = segments.getFirst();
        if (Math.abs(head.getX() - f.getX()) < SEGMENT_SIZE && Math.abs(head.getY() - f.getY()) < SEGMENT_SIZE) {
            BodySegment tail = segments.getLast();
            BodySegment newSegment = new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE);
            segments.addLast(newSegment);
            return true;
        }
        return false;
    }
    
    /**
     * Returns true if the head of the snake is in bounds
     * @return whether or not the head is in the bounds of the window
     */
    public boolean isInbounds() {
        BodySegment head = segments.getFirst();
        double headX = head.getX();
        double headY = head.getY();
        return headX >= 0 && headX <= 1 && headY >= 0 && headY <= 1;
    }
}
