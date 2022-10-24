package com.example.arcade_machine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnTicTacToe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTicTacToe = findViewById(R.id.btnTicTacToe);

        btnTicTacToe.setOnClickListener(v -> playTicTacToe(false));
    }

    private void playTicTacToe(boolean bot) {
        Intent intent = new Intent(this, ActivityTicTacToe.class);
        startActivity(intent);
    }
}