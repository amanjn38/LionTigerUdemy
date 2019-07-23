package com.example.liontigerudemy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnReset;
    private GridLayout gridLayout;

    enum Player
    {
        ONE, TWO, No
    }

    Player currentPlayer = Player.ONE;

    Player[] playerChoices = new Player[9];
    int [][] winnerRowsColumn = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    private boolean gameover = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 9; i++) {

            playerChoices[i] = Player.No;
        }
        btnReset = findViewById(R.id.btnReset);
        gridLayout = findViewById(R.id.gridLayout);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

        public void imageViewIsTapped(View imageView)
        {
            ImageView tappedImageView = (ImageView) imageView;

            int tag= Integer.parseInt(tappedImageView.getTag().toString());

            if(playerChoices[tag] == Player.No && gameover == false) {


                tappedImageView.setTranslationX(-2000);


                playerChoices[tag] = currentPlayer;

                if (currentPlayer == Player.ONE) {
                    tappedImageView.setImageResource(R.drawable.zero);
                    currentPlayer = Player.TWO;
                } else if (currentPlayer == Player.TWO) {
                    tappedImageView.setImageResource(R.drawable.key);
                    currentPlayer = Player.ONE;
                }
                tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600f).setDuration(1000);

                Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();

                for (int[] winnerColumns : winnerRowsColumn) {
                    if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]] &&
                            playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]] &&
                            playerChoices[winnerColumns[0]] != Player.No) {

                        btnReset.setVisibility(View.VISIBLE);
                        gameover = true;
                        if (currentPlayer == Player.ONE) {
                            Toast.makeText(this, "Player 2 is the winner", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Player 1 is the winner", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        }
        //Reset game function
    private void resetGame() {
        for (int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView imageView = (ImageView)gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);

        }
         currentPlayer = Player.ONE;
        for (int i = 0; i < 9; i++) {

            playerChoices[i] = Player.No;
        }
        gameover = false;
    }

}
