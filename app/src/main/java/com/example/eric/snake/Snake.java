package com.example.eric.snake;



/**
 * Created by Eric on 16-12-15.
 */
public class Snake {

    private Node head, tail;
    private int xSpeed, ySpeed;
    private int speedMultiplier;
    private enum direction {UP, DOWN, LEFT, RIGHT}
    private direction currentDirection;

    public Snake(int startingLength) {
        head = new Node();
        initializeBody(startingLength);
        currentDirection = direction.DOWN;

    }

    private void initializeBody( int bodyLength ) {
        Node currentNode = head;
        for ( int i = 1; i <= bodyLength; i++ ) {
            currentNode.setNextNode( new Node(0,-i), currentNode );
            currentNode = currentNode.getNextNode();
        }
        tail = currentNode;
    }

    /**
     * Changes the direction of the snake
     * @param d The new direction the snake should be moving
     */
    public void setDirection(direction d) {
        // Check to see if the new direction will be the opposite of the current direction
        if (    d == currentDirection ||
                (currentDirection == direction.UP && d == direction.DOWN) ||
                (currentDirection == direction.DOWN && d == direction.UP) ||
                (currentDirection == direction.LEFT && d == direction.RIGHT) ||
                (currentDirection == direction.RIGHT && d == direction.LEFT)) {
            return;
        };


        if ( d == direction.UP || d == direction.DOWN ){
            xSpeed = 0;
            ySpeed = d == direction.UP ? -1 : 1;
        } else {
            xSpeed = d == direction.LEFT ? -1 : 1;
            ySpeed = 0;
        }

    }

    /**
     * Updates the snake position, then returns whether the snake has collided with itself
     * or a wall
     * @return Whether the snake has collided with something
     */
    public boolean tick() {
        move();
        return checkCollision() ? false : true;
    }

    /**
     * Moves the snake head based on the position it is travelling and then updates the positioning
     * of the body
     */
    private void move() {

        Position newHeadPos = head.getPosition();
        newHeadPos.shift(xSpeed, ySpeed);

        Node newHead = new Node(newHeadPos, head);


        Node currentNode = tail;

        while ( currentNode != null ) {

            if ( currentNode.getPreviousNode() == null ) { break; }

            currentNode.setPosition(currentNode.getPreviousNode().getPosition());
            currentNode = currentNode.getPreviousNode();
        }

        head = newHead;

    }

    /**
     * Grows the snake by one segment when food is eater
     */
    public void grow() {
        Node newTail = new Node(0,0);
        tail.setNextNode(newTail);
        newTail.setPrevNode(tail);

        tail = newTail;
    }

    private boolean checkCollision () {

        Node currentNode = tail;

        while ( currentNode.getPreviousNode() != null ) {
            if ( currentNode.equals(head) ) { break; }
            if ( currentNode.getPosition().equals(head.getPosition()) ) {
                return true;
            }
        }
        return false;
    }

    public void setSpeedMultiplier(int speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

    @Override
    public String toString() {
        String s = "";
        Node currentNode = head;

        while ( currentNode.getNextNode() != null ) {
            s += currentNode.toString() + ", ";
            currentNode = currentNode.getNextNode();
        }

        return s;
    }
}
