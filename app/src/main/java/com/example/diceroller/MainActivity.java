package com.example.diceroller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.diceroller.activities.LanzarDado;
import com.example.diceroller.activities.Opciones;
import com.example.diceroller.activities.VerGrupos;
import com.example.diceroller.activities.DadosBasicos;

public class MainActivity extends AppCompatActivity {
    private Button btnUltimaTirada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("Dados", MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean("is_dark_mode", false);

        // Configurar el modo de tema según la preferencia guardada
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            ImageView logo = findViewById(R.id.imageLogo);
            logo.setImageResource(R.drawable.dice_icon_dark);
            ImageView backgroundImage = findViewById(R.id.backgroundImage);
            backgroundImage.setImageResource(R.drawable.background_image_dark);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        Button btnUnDado = findViewById(R.id.buttonUnDado);

        btnUnDado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DadosBasicos.class);
                startActivity(intent);
            }
        });

        Button btnGrupos = findViewById(R.id.buttonGrupos);

        btnGrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VerGrupos.class);
                startActivity(intent);
            }
        });

        btnUltimaTirada = findViewById(R.id.buttonUltimaTirada);

        btnUltimaTirada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LanzarDado.class);
                startActivity(intent);
            }
        });

        Button btnOpciones = findViewById(R.id.buttonOpciones);

        btnOpciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Opciones.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences("Dados", MODE_PRIVATE);
        boolean ultima_tirada = sharedPreferences.getBoolean("ultima_tirada", false);
        btnUltimaTirada.setEnabled(ultima_tirada);
    }
}