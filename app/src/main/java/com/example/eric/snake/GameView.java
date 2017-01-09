package com.example.eric.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Eric on 16-12-24.
 */
public class GameView extends View {

    final int GRID_SIZE = 20; // Size of each grid space in pixels

    Snake s;
    int score, width, height;
    Position applePosition;
    boolean playing;
    ArrayList<Position> snakePositions;
    Paint headColour, bodyColour;
    int frames;



    public GameView(Context context) {
        super(context);
        s = new Snake(3,5,5);
        applePosition = s.generateApple();
        score = 0;
        playing = true;
        frames = 0;

        // TODO get width and height in DP
        width = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;

        headColour = new Paint();
        headColour.setColor(Color.RED);
        headColour.setStyle(Paint.Style.FILL);

        bodyColour = new Paint();
        bodyColour.setColor(Color.WHITE);
        bodyColour.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if ( frames % 10 == 0 ) {


            playing = s.tick();

            // TODO check if colliding with apple and update its position

            // TODO check if score needs to be updated
        }

        snakePositions = s.getPositions();

        for (int i = 0; i < snakePositions.size(); i++) {
            Position point = snakePositions.get(i);
            canvas.drawRect(point.x * GRID_SIZE, point.y * GRID_SIZE,
                    (point.x + 1) * GRID_SIZE, (point.y + 1) * GRID_SIZE,
                    i == 0 ? headColour : bodyColour);
        }



        frames++;
        invalidate();
    }

    public void setSnakeDirection ( Snake.Direction d ) {
        s.setDirection(d);
        Log.d(TAG, "setSnakeDirection: " + d);
    }


}
