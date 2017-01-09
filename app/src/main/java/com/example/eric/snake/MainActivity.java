package com.example.eric.snake;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";

    public static GameView board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        board = new GameView(this);
        board.setBackgroundColor(Color.BLACK);

        board.setOnTouchListener(new BoardTouchListener());

        setContentView(board);

    }

}
