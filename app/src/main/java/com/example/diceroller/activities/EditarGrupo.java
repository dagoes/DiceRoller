package com.example.diceroller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diceroller.database.DBHelper;
import com.example.diceroller.R;

public class EditarGrupo extends AppCompatActivity {
    private EditText editTextCantidad, editTextValorMaximo;
    private DBHelper dbHelper;
    private int entryId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editgroup);

        dbHelper = new DBHelper(this);

        editTextCantidad = findViewById(R.id.editTextCantidad);
        editTextValorMaximo = findViewById(R.id.editTextValorMaximo);

        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            //Si es un grupo existente recibir sus datos
            entryId = intent.getIntExtra("id", -1);
            String cant = intent.getIntExtra("cantidad",-1)+"";
            editTextCantidad.setText(cant);
            String val = intent.getIntExtra("valor_maximo",-1)+"";
            editTextValorMaximo.setText(val);
        } else {
            //Si es un grupo nuevo cambiar titulo
            TextView titulo = findViewById(R.id.tituloEditar);
            titulo.setText(R.string.nuevo_grupo);
        }

        Button btnGuardar = findViewById(R.id.buttonGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if  (modificar()) finish();
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

    public boolean modificar(){
        boolean hecho = false;
        if (TextUtils.isEmpty(editTextCantidad.getText().toString()) || //prevenir fallos en el codigo por variable vacia
                TextUtils.isEmpty(editTextValorMaximo.getText().toString()) ||
                editTextCantidad.getText().toString().matches("0") || //prevenir fallos en la bbdd por valor nulo
                editTextValorMaximo.getText().toString().matches("0")) {
            Toast.makeText(EditarGrupo.this,"Rellene ambos campos con valores validos",Toast.LENGTH_SHORT).show();
        } else {
            int cantidad = Integer.parseInt(editTextCantidad.getText().toString());
            int valor_maximo = Integer.parseInt(editTextValorMaximo.getText().toString());
            if (entryId == -1) {
                //Si es nuevo crear entrada en la tabla
                dbHelper.addEntry(cantidad, valor_maximo);  // Nuevo elemento
                Toast.makeText(EditarGrupo.this,"Nuevo grupo creado",Toast.LENGTH_SHORT).show();
            } else {
                //Si no editar entrada
                dbHelper.updateEntry(entryId, cantidad, valor_maximo);  // Actualizar elemento existente
                Toast.makeText(EditarGrupo.this,"Grupo modificado",Toast.LENGTH_SHORT).show();
            }
            hecho = true;
        }
        return hecho;
    }
}
