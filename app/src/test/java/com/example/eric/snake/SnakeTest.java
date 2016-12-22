package com.example.eric.snake;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Eric on 2016-12-21.
 */
public class SnakeTest {

    @Test
    public void testMovement() throws Exception {
        Snake s = new Snake( 5, 10, 10 );

        Position p1 = new Position( 1, 1 );

        s.setDirection(Snake.direction.DOWN);
        s.tick();

        s.setDirection(Snake.direction.RIGHT);
        s.tick();

        ArrayList<Position> snakePositions = s.getPositions();
        assertTrue( p1.equals( snakePositions.get( 0 ) ) );

    }

    @Test
    public void testGrowth() throws Exception {

        // Linearly move it down

        Snake s = new Snake( 3, 10, 10 );

        // TODO add code to generate the location of the apple
        for ( int i = 0; i < 5; i++ ) {
            s.tick();
        }

        Position p1 = new Position ( 0, 0 );

        s.grow();

        ArrayList<Position> snakePositions = s.getPositions();

        assertTrue( snakePositions.size() == 4 );

        s.tick();

        snakePositions = s.getPositions();

        Position theoreticalTailPosition = new Position( 0, 3 );

        assertTrue( theoreticalTailPosition.equals( snakePositions.get( snakePositions.size() - 1 ) ) );

    }

    @Test
    public void testDirection() {
        Snake s = new Snake( 4, 10, 10 );

        assertTrue( s.setDirection( Snake.direction.RIGHT) );
        assertFalse ( s.setDirection( Snake.direction.LEFT) );

        s.tick();

        assertTrue( s.setDirection( Snake.direction.DOWN ) );
        assertFalse( s.setDirection( Snake.direction.UP ) );

        s.tick();

        assertTrue( s.setDirection(Snake.direction.LEFT ) );
        assertFalse( s.setDirection(Snake.direction.RIGHT ) );

        s.tick();

        assertTrue( s.setDirection( Snake.direction.UP ) );
        assertFalse( s.setDirection(Snake.direction.DOWN ) );

    }

}