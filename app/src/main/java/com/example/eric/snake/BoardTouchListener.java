package com.example.eric.snake;

import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.content.ContentValues.TAG;

/**
 * Created by Eric on 2017-01-09.
 */

public class BoardTouchListener implements View.OnTouchListener {

    private float xStart, xEnd, yStart, yEnd;

    private final int minimumSwipeDistance = 100;


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:

                xStart = event.getX();
                yStart = event.getY();

                Log.d(TAG, "Down: (" + xStart + ", " + yStart + ")");

                return true;
            case MotionEvent.ACTION_UP:

                xEnd = event.getX();
                yEnd = event.getY();

                Log.d(TAG, "Up: (" + xEnd + ", " + yEnd + ")");

                // Calculate the difference in x and y positions
                float xDifference = xStart - xEnd;
                float yDifference = yStart - yEnd;

                Log.d(TAG, "( " + xDifference + ", " + yDifference + ")");

                if ( Math.abs(xDifference) > Math.abs(yDifference) ) {

                    if ( Math.abs(xDifference) < minimumSwipeDistance ) { return true; }

                    MainActivity.board.setSnakeDirection(xDifference < 0 ? Snake.Direction.RIGHT : Snake.Direction.LEFT );
                    Log.d(TAG, "onFling: horizontal swipe");
                } else {

                    if ( Math.abs(yDifference) < minimumSwipeDistance ) { return true; }

                    MainActivity.board.setSnakeDirection(yDifference < 0 ? Snake.Direction.DOWN : Snake.Direction.UP );
                    Log.d(TAG, "onFling: vertical swipe");
                }

                return true;

            default:
                return false;
        }
    }

}
