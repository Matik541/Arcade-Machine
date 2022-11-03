package com.example.arcade_machine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityTicTacToe extends AppCompatActivity {

    ArrayList<Button> buttons = new ArrayList<>();

    GridLayout grid;

    Button btnBack;
    Button btnReset;

    Integer player = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        View view = findViewById(android.R.id.content);

        btnBack = findViewById(R.id.btnBack);
        btnReset = findViewById(R.id.btnReset);

        grid = findViewById(R.id.grid);

//        buttons.add(findViewById(R.id.btn1));
//        buttons.add(findViewById(R.id.btn2));
//        buttons.add(findViewById(R.id.btn3));
//        buttons.add(findViewById(R.id.btn4));
//        buttons.add(findViewById(R.id.btn5));
//        buttons.add(findViewById(R.id.btn6));
//        buttons.add(findViewById(R.id.btn7));
//        buttons.add(findViewById(R.id.btn8));
//        buttons.add(findViewById(R.id.btn9));

//        for (Button button : buttons) {
//            button.setOnClickListener(v -> {
//                if (button.getText().toString().equals(""))
//                    button.setText( (player++%2 == 0) ? "X" : "O");
//                else
//                    Toast.makeText(this, "This place is already taken", Toast.LENGTH_SHORT).show();
//                if (checkBoard()){
//                    Toast.makeText(this, "Player " + (player%2 == 0 ? "O" : "X") + " won!", Toast.LENGTH_SHORT).show();
//                    for (Button b : buttons){
//                        b.setEnabled(false);
//
//                    }
//                }
//            });
//        }

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        btnReset.setOnClickListener(v -> play(view));

        play(view);
    }

    private void play(View view) {
        player = 0;
        grid.removeAllViews();
        buttons.clear();
        for (int i = 0; i < 9; i++) {
            Button btn = new Button(this);

            btn.setText("");
            btn.setWidth(200);
            btn.setHeight(200);
            btn.setTextSize(30);
            btn.setTextAppearance(R.style.PressStart2P);

            btn.setOnClickListener(v -> {
                if (btn.getText().toString().equals(""))
                    btn.setText( (player++%2 == 0) ? "X" : "O");
                else
                    Toast.makeText(this, "This place is already taken", Toast.LENGTH_SHORT).show();
                int [] win = checkBoard();
                if (win.length > 0){
                    Toast.makeText(this, "Player " + (player%2 == 0 ? "O" : "X") + " won!", Toast.LENGTH_SHORT).show();
                    for (Button b : buttons){
                        b.setEnabled(false);
                    }
//                    draw line from win[0] to win[2]
                    int [][] pos = {{0,0}, {0,0}};
                    buttons.get(win[0]).getLocationOnScreen(pos[0]);
                    buttons.get(win[2]).getLocationOnScreen(pos[1]);
                    System.out.println(pos[0][0] + " " + pos[0][1]);
                    System.out.println(pos[1][0] + " " + pos[1][1]);
                }
                else if (player == 9){
                    Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
                }
            });
            grid.addView(btn);
            buttons.add(btn);
        }
    }

    private int[] checkBoard() {
        if (!buttons.get(4).getText().toString().equals("") &&
            buttons.get(0).getText() == buttons.get(4).getText() &&
            buttons.get(0).getText() == buttons.get(8).getText()
        ) return new int[]{0, 4, 8};
        if (!buttons.get(4).getText().toString().equals("") &&
            buttons.get(2).getText() == buttons.get(4).getText() &&
            buttons.get(2).getText() == buttons.get(6).getText()
        ) return new int[]{2, 4, 6};
        for (int i = 0; i < 3; i++) {
            if (!buttons.get(i).getText().toString().equals("") &&
                buttons.get(i).getText() == buttons.get(i+3).getText() &&
                buttons.get(i).getText() == buttons.get(i+6).getText()
            ) return new int[]{i, i+3, i+6};
            if (!buttons.get(i*3).getText().toString().equals("") &&
                buttons.get(i*3).getText() == buttons.get(i*3+1).getText() &&
                buttons.get(i*3).getText() == buttons.get(i*3+2).getText()
            ) return new int[]{i*3, i*3+1, i*3+2};
        }
        return new int[]{};
    }
}