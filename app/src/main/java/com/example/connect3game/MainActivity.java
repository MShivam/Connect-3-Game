package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//import android.widget.GridLayout;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0: empty, 1: yellow, 2: red
    int[] gameState = {0,0,0,0,0,0,0,0,0};
    int player = 1;
    int[][] winningPositions = {{0,1,2},{0,3,6},{0,4,8},{1,4,7},{2,5,8},{3,4,5},{6,7,8},{2,4,6}};
    boolean gameOn = true;

    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        Log.i("Tag", counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 0 && gameOn) {

            gameState[tappedCounter] = player;

            counter.setTranslationY(-1500);

            if (player == 1) {
                counter.setImageResource(R.drawable.yellow);
                player = 2;
            } else {
                counter.setImageResource(R.drawable.red);
                player = 1;
            }

            counter.animate().translationYBy(1500).rotation(1800).setDuration(500);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 0) {
                    String winner = "";
                    gameOn = false;
                    if (player == 2) {
                        winner = "Yellow";
                    }
                    else {
                        winner = "Red";
                    }
                    //Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();
                    Button button = (Button) findViewById(R.id.button);
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(winner + " has won!");
                    textView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){
        Button button = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);
            gameState[i] = 0;
        }
        player = 1;
        gameOn = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}