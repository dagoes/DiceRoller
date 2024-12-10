package com.example.diceroller.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

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

        Button btnTheme = findViewById(R.id.buttonTheme);

        btnTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentNightMode = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;

                if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
                    // Cambiar a modo claro
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sharedPreferences.edit().putBoolean("is_dark_mode", false).apply();
                } else {
                    // Cambiar a modo oscuro
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    sharedPreferences.edit().putBoolean("is_dark_mode", true).apply();
                }
            }
        });

        Button btnBorrar = findViewById(R.id.buttonBorrar);

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
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
        try(DBHelper dbHelper = new DBHelper(this)){
            dbHelper.emptyTable();
            Toast.makeText(Opciones.this,"Base de datos borrada",Toast.LENGTH_SHORT).show();
        } catch(Exception ignored){
            Toast.makeText(Opciones.this,"No se pudo borrar la base de datos",Toast.LENGTH_SHORT).show();
        }
        //Cambiar el estado de sonido a activado
        switchSonido.setChecked(true);
        // Cambiar a modo claro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //Vaciar SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
