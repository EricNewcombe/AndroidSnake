package com.example.eric.snake;


import java.util.ArrayList;

/**
 * Created by Eric on 16-12-15.
 */
public class Snake {

    private Node head, tail;
    private int xSpeed, ySpeed;
    private int speedMultiplier;
    protected enum Direction {UP, DOWN, LEFT, RIGHT}
    private Direction currentDirection, nextDirection;
    private static final String TAG = "Snake";
    private Position apple;

    //TODO Export these to a gameboard file
    private int boardWidth, boardHeight;

    public Snake(int startingLength, int boardWidth, int boardHeight) {
        head = new Node();
        initializeBody( startingLength );
        setDirection(Direction.DOWN);
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    /**
     * Initializes the body nodes of the snake
     * @param snakeLength Number of nodes to add to the body
     */
    private void initializeBody( int snakeLength ) {

        Node currentNode = head;
        Node newNode = null;

        for ( int i = 1; i <= snakeLength; i++ ) {

            // Create the next node in the body and set its previous reference to the node before it
            newNode = new Node(0, -i);
            newNode.setPreviousNode(currentNode);

            // Set the current node's next reference to the new node
            currentNode.setNextNode( newNode );

            // Iterate to the next node
            currentNode = currentNode.getNextNode();

        }

        tail = currentNode;

    }

    /**
     * Changes the Direction of the snake
     * @param d The new Direction the snake should be moving
     */
    public boolean setDirection(Direction d) {

        // Check to see if the new Direction will be the opposite of the current Direction
        if (    (currentDirection == Direction.UP && d == Direction.DOWN) ||
                (currentDirection == Direction.DOWN && d == Direction.UP) ||
                (currentDirection == Direction.LEFT && d == Direction.RIGHT) ||
                (currentDirection == Direction.RIGHT && d == Direction.LEFT)) {
            System.out.println(d + " " + currentDirection);
            return false;
        };

        if ( currentDirection == null ) { currentDirection = d; }

        if ( d == Direction.UP || d == Direction.DOWN ){
            xSpeed = 0;
            ySpeed = d == Direction.UP ? -1 : 1;
        } else {
            xSpeed = d == Direction.LEFT ? -1 : 1;
            ySpeed = 0;
        }

        nextDirection = d;

        return true;

    }

    /**
     * Updates the snake position, then returns whether the snake has collided with itself
     * or a wall
     * @return Whether the snake has collided with something
     */
    public boolean tick() {
        // Log.d(TAG, "speed: (" + this.xSpeed + ", " + this.ySpeed + ")");
        updateCurrentDirection();
        move();
        return checkCollision() ? false : true;
    }

    private void updateCurrentDirection() {
        currentDirection = nextDirection;
    }

    /**
     * Moves the snake head based on the position it is travelling and then updates the positioning
     * of the body
     */
    private void move() {

        // Iterrate over the body of the snake setting the previous position
        Node currentNode = tail;
        Node prevNode = null;

        while ( currentNode != null ) {

            if ( currentNode.getPreviousNode() == null ) { break; }

            prevNode = currentNode.getPreviousNode();
            Position newPosition = prevNode.getPosition();

//            Log.d(TAG, "move: " + newPosition);

            currentNode.setPosition(newPosition);
            currentNode = currentNode.getPreviousNode();

        }

        // move the head
        Position newHeadPos = head.getPosition();
        newHeadPos.shift(xSpeed, ySpeed);
        head.setPosition(newHeadPos);

    }

    /**
     * Grows the snake by one segment when food is eater
     */
    public void grow() {
        Node newTail = new Node(0,0);
        tail.setNextNode(newTail);
        newTail.setPreviousNode(tail);

        tail = newTail;
    }

    /**
     * Checks to see if the snake head is in the bounds of the map, and if it is not colliding with itself
     * @return True if there is is a collision with the wall or the snake eats itself
     */
    private boolean checkCollision () {

        // Check if head is out of bounds
        Position headPos = head.getPosition();
        if ( headPos.x < 0 || headPos.y < 0 || headPos.x > boardWidth || headPos.y > boardHeight ) {
            return true;
        }

        // Check if snake eating itself by iterating over the body
        Node currentNode = head.getNextNode();

        while ( currentNode.getNextNode() != null ) {

            // Check to see if the head is in the same tile as the body node
            if ( currentNode.getPosition().equals( head.getPosition() ) ) {
                return true;
            }
            currentNode = currentNode.getNextNode();
        }
        //Log.e(TAG, "checkCollision: Collided" );
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

    /**
     * Loops through and gets all the positions of every segment of the snake
     * @return positions of all parts of the snake
     */
    public ArrayList<Position> getPositions() {
        ArrayList<Position> positions = new ArrayList<>();

        Node currentNode = head;

        while ( currentNode.getNextNode() != null ) {
            positions.add(currentNode.getPosition());
            currentNode = currentNode.getNextNode();
        }

        return positions;
    }

    /**
     * Generates the position of the apple
     */
    public Position generateApple() {
        // TODO generate the position of the apple based on the size of the map
        Position apple = new Position(1,1);

        return apple;
    }
}
