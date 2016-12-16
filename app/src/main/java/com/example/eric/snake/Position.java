package com.example.eric.snake;

/**
 * Created by Eric on 16-12-15.
 */
public class Position {

    public int x, y;

    public Position (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void shift ( int x, int y ) {
        this.x += x;
        this.y += y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals( Position position ) {
        return position.x == this.x && position.y == this.y;
    }
}
