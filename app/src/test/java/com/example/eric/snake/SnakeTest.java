package com.example.eric.snake;

import com.example.eric.snake.Position;
import com.example.eric.snake.Snake;

import org.junit.Test;

import java.lang.Exception;
import java.util.ArrayList;

import dalvik.annotation.TestTargetClass;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class SnakeTest {

    @Test
    public void testMovement() throws Exception {
        Snake s = new Snake( 3, 10, 10 );

        Position p1 = new Position(1,1);

        s.setDirection(Snake.direction.DOWN);
        s.tick();
        s.setDirection(Snake.direction.RIGHT);
        s.tick();

        ArrayList<Position> snakePositions = s.getPositions();

        assertEquals( p1, snakePositions.get(0) );
    }

    @Test
    public void testGrowth() throws Exception {

        // Linearly move it down
        int numberOfTicks = 6;

        Snake s = new Snake( 3, 10, 10 );

        // TODO add code to generate the location of the apple
        for ( int i = 0; i < numberOfTicks; i++ ) {
            s.tick();
        }

        Position zeroes = new Position ( 0, 0 );

        s.grow();

        ArrayList<Position> snakePositions = s.getPositions();

        assertEquals
        s.tick();

        snakePositions = s.getPositions();

        Position theoreticalTailPosition = new Position( 0, numberOfTicks - snakePositions.size() );

        assertEquals( theoreticalTailPosition, snakePositions.get(snak))

    }
}