package com.example.diceroller.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diceroller.R;

public class VerGrupos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seegroups);

        //Crear script para ver grupos en base de datos y menu con abrir o borrar al pinchar

        Button btnNuevo = findViewById(R.id.buttonNuevo);

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerGrupos.this, EditarGrupo.class);
                startActivity(intent);
            }
        });

        ImageButton btnVolver = findViewById(R.id.imageButtonVolver2);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
