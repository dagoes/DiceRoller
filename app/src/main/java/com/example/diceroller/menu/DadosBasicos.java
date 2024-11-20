package com.example.diceroller.menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diceroller.R;

public class DadosBasicos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basicdice);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridLayout.setColumnCount(2);
            gridLayout.setRowCount(4);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridLayout.setColumnCount(4);
            gridLayout.setRowCount(2);
        }

        Button btnD2 = findViewById(R.id.btnD2);

        btnD2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("Dados", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("cantidad", 1);
                editor.putInt("valor_maximo", 2);
                editor.apply();
                lanzar();
            }
        });

        Button btnD4 = findViewById(R.id.btnD4);

        btnD4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("Dados", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("cantidad", 1);
                editor.putInt("valor_maximo", 4);
                editor.apply();
                lanzar();
            }
        });

        Button btnD6 = findViewById(R.id.btnD6);

        btnD6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("Dados", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("cantidad", 1);
                editor.putInt("valor_maximo", 6);
                editor.apply();
                lanzar();
            }
        });

        Button btnD8 = findViewById(R.id.btnD8);

        btnD8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("Dados", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("cantidad", 1);
                editor.putInt("valor_maximo", 8);
                editor.apply();
                lanzar();
            }
        });

        Button btnD10 = findViewById(R.id.btnD10);

        btnD10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("Dados", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("cantidad", 1);
                editor.putInt("valor_maximo", 10);
                editor.apply();
                lanzar();
            }
        });

        Button btnD00 = findViewById(R.id.btnD00);

        btnD00.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("Dados", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("cantidad", 1);
                editor.putInt("valor_maximo", -10);
                editor.apply();
                lanzar();
            }
        });

        Button btnD12 = findViewById(R.id.btnD12);

        btnD12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("Dados", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("cantidad", 1);
                editor.putInt("valor_maximo", 12);
                editor.apply();
                lanzar();
            }
        });

        Button btnD20 = findViewById(R.id.btnD20);

        btnD20.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("Dados", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("cantidad", 1);
                editor.putInt("valor_maximo", 20);
                editor.apply();
                lanzar();
            }
        });

        ImageButton btnVolver = findViewById(R.id.imageButtonVolver1);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected void lanzar() {
        Intent intent = new Intent(DadosBasicos.this, LanzarDado.class);
        startActivity(intent);
    }
}