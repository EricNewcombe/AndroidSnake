package com.example.eric.snake;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO move this to a proper file
        //TODO calculate the width and height of the playing board based on device screen size
        int width = 15, height = 15;
        Snake s = new Snake(4, width, height);
        for ( int i = 0; i < 10; i++ ) {

            Log.d(TAG, s.toString());
            s.tick();
            if ( i == 5 ) {
                s.setDirection(Snake.direction.RIGHT);
                s.grow();
            }

        }

    }
}
