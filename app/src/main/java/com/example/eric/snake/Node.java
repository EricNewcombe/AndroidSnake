package com.example.eric.snake;

/**
 * Created by Eric on 16-12-15.
 */
public class Node {

    private Node next, prev;
    private Position pos;

    public Node(int x, int y) {
        this.pos = new Position(x, y);
        this.next = null;
        this.prev = null;
    }

    public Node() {
        this(0, 0);
    }

    public Node ( Position pos, Node next ) {
        this.pos = pos;
        this.next = next;
    }

    public Node ( Position p ) {
        this(p,null);
    }

    public void setNextNode ( Node n ) {
        this.next = n;
    }

    public Node getNextNode () {
        return this.next;
    }

    public void setPreviousNode ( Node p ) {
        this.prev = p;
    }

    public Node getPreviousNode () {
        return this.prev;
    }

    // Return a shallow copy as opposed to a reference to the pos object
    public Position getPosition() {
        return new Position(pos.x, pos.y);
    }

    public void setPosition ( Position pos ) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return pos.toString();
    }
}
