package com.example.diceroller.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diceroller.R;
import com.example.diceroller.database.DBHelper;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class Opciones extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SwitchMaterial switchSonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        sharedPreferences = getSharedPreferences("Dados", MODE_PRIVATE);
        boolean switchState = sharedPreferences.getBoolean("sonido", true);

        switchSonido = findViewById(R.id.switchSonido);
        switchSonido.setChecked(switchState);

        switchSonido.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Guardar el estado del switch en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("sonido", isChecked);
            editor.apply();
        });

        Button btnBorrar = findViewById(R.id.buttonBorrar);

        btnBorrar.setOnClickListener(v -> {
            new AlertDialog.Builder(Opciones.this)
                    .setTitle("Confirmar Borrado")
                    .setMessage("¿Estás seguro de que deseas borrar todos los datos?")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            borrarDatos();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setCancelable(false)
                    .show();
        });

        ImageButton btnVolver = findViewById(R.id.imageButtonVolver3);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void borrarDatos() {
        //Vaciar Tabla de SQLite
        try(DBHelper dbHelper = new DBHelper(this);){
            dbHelper.emptyTable();
        } catch(Exception ignored){}
        //Vaciar SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        switchSonido.setChecked(true);
    }
}
