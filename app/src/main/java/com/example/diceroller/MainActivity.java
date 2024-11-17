package com.example.diceroller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diceroller.menu.Opciones;
import com.example.diceroller.menu.VerGrupos;
import com.example.diceroller.menu.DadosBasicos;

public class MainActivity extends AppCompatActivity {

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

        Button btnOpciones = findViewById(R.id.buttonOpciones);

        btnOpciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Opciones.class);
                startActivity(intent);
            }
        });

    }
}