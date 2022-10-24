package com.example.arcade_machine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityTicTacToe extends AppCompatActivity {

    Button btnBack;
    GridLayout grid;

    Integer player = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        play();
    }

    private void play() {
        player = 0;
        grid = findViewById(R.id.grid);
        grid.removeAllViews();
        for (int i = 0; i < 9; i++) {
            Button btn = new Button(this);
            btn.setText(" ");

            btn.setOnClickListener(v -> {
                if(btn.getText() == " ")
                    btn.setText((player++%2==0) ? "X" : "O");
                else
                    Toast.makeText(this, "This field is already taken", Toast.LENGTH_SHORT).show();

                checkBoard();
            });
            grid.addView(btn);
        }
        grid.refreshDrawableState();
    }

    private void checkBoard() {
        ArrayList<Button> buttons = new ArrayList<>();
        for (int i = 0; i < grid.getChildCount(); i++) {
            buttons.add((Button) grid.getChildAt(i));
        }

        if (player == 9){
            Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( buttons.get(4).getText() != " " && (
            buttons.get(0).getText() == buttons.get(4).getText() &&
            buttons.get(0).getText() == buttons.get(8).getText() ||
            buttons.get(2).getText() == buttons.get(4).getText() &&
            buttons.get(2).getText() == buttons.get(6).getText()
        ) ) {
            Toast.makeText(this, "Player " + buttons.get(4).getText() + " won!", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < 3; i++) {
            if ( buttons.get(i).getText() != " " &&
                buttons.get(i).getText() == buttons.get(i+3).getText() &&
                buttons.get(i).getText() == buttons.get(i+6).getText()
            ) {
                Toast.makeText(this, buttons.get(i).getText() + " won", Toast.LENGTH_SHORT).show();
                return;
            }
            if ( buttons.get(i*3).getText() != " " &&
                buttons.get(i*3).getText() == buttons.get(i*3+1).getText() &&
                buttons.get(i*3).getText() == buttons.get(i*3+2).getText()
            ) {
                Toast.makeText(this, buttons.get(i*3).getText() + " won", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }
}