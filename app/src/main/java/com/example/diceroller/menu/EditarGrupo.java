package com.example.diceroller.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diceroller.R;

public class EditarGrupo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editgroup);

        Button btnGuardar = findViewById(R.id.buttonGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //crear script para crear/actualizar una entrada en la base de datos
            }
        });

        Button btnLanzar = findViewById(R.id.buttonIrALanzar);

        btnLanzar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Script para recoger datos
                Intent intent = new Intent(EditarGrupo.this, LanzarDado.class);
                startActivity(intent);
            }
        });

        Button btnVolver = findViewById(R.id.buttonVolver2);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
