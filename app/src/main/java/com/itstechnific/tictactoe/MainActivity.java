package com.itstechnific.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Boolean gameActive = true;
    ImageView imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10;
    TextView textView2;

//gamestates
//        O--0
//        x--1
//        2--null

    int activePlayer;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winPositions = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        textView2 = findViewById(R.id.textView2);
    }

    public void onTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset();
        }
        if(gamestate[tappedImage] == 2 && gameActive){
            gamestate[tappedImage]= activePlayer;
//            img.setTranslationY(-1000f);
            if (activePlayer==0){
                img.setImageResource(R.drawable.x);
                textView2.setText("O's Turn - Tap to Play");
                activePlayer =1;
            }
            else{
                img.setImageResource(R.drawable.zero);
                textView2.setText("X's Turn - Tap to Play");
                activePlayer=0;
            }

//            img.animate().translationYBy(1000f).setDuration(200);

            winCase();

        }




    }


    private void gameReset() {

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameActive = true;
                activePlayer = 0;
                for (int i=0;i<gamestate.length;i++){
                    gamestate[i] =2;
                }
                imageView2.setImageResource(0);
                imageView3.setImageResource(0);
                imageView4.setImageResource(0);
                imageView5.setImageResource(0);
                imageView6.setImageResource(0);
                imageView7.setImageResource(0);
                imageView8.setImageResource(0);
                imageView9.setImageResource(0);
                imageView10.setImageResource(0);
                textView2.setText("O's Turn - Tap to Play");
            }
        });


    }
    private void winCase(){
        for (int[] winposition: winPositions){
            if (gamestate[winposition[0]]==gamestate[winposition[1]]&&
                    gamestate[winposition[1]]==gamestate[winposition[2]]&&
                    gamestate[winposition[0]]!=2){
                // somebody has won
                String str;
                gameActive=false;
                if(gamestate[winposition[0]]==0){
                    str = "X has won - Tap to Play";
                }
                else{
                    str = "O has won - Tap to Play";
                }
                textView2.setText(str);
                gameReset();
            }

        }
        boolean emptySquare = false;
        for (int squareState : gamestate) {
            if (squareState == 2) {
                emptySquare = true;
                break;
            }
        }

        if (!emptySquare && gameActive) {
            // Game is a draw
            gameActive = false;
            textView2.setText("Game Drawn - Tap to Play");
            gameReset();
        }

    }
}