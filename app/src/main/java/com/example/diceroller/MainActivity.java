package com.example.diceroller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

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
        ultimaTirada();

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
    protected void onResume() {
        super.onResume();
        ultimaTirada();
    }

    private void ultimaTirada() {
        SharedPreferences sharedPreferences = getSharedPreferences("Dados", MODE_PRIVATE);
        Boolean ultima_tirada = sharedPreferences.getBoolean("ultima_tirada", false);
        btnUltimaTirada.setEnabled(ultima_tirada);
    }
}