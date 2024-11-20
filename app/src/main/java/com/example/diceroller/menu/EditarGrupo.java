package com.example.diceroller.menu;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diceroller.DBHelper;
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
            entryId = intent.getIntExtra("id", -1);
            String cant = intent.getIntExtra("cantidad",-1)+"";
            editTextCantidad.setText(cant);
            String val = intent.getIntExtra("valor_maximo",-1)+"";
            editTextValorMaximo.setText(val);
        }

        editTextCantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Validar que el texto contenga solo números
                if (!charSequence.toString().matches("[0-9]*")) {
                    // Si contiene caracteres no numéricos, eliminar el último carácter
                    editTextCantidad.setText(charSequence.toString().replaceAll("[^0-9]", ""));
                    editTextCantidad.setSelection(editTextCantidad.getText().length()); // Poner el cursor al final
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextValorMaximo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Validar que el texto contenga solo números
                if (!charSequence.toString().matches("[0-9]*")) {
                    // Si contiene caracteres no numéricos, eliminar el último carácter
                    editTextValorMaximo.setText(charSequence.toString().replaceAll("[^0-9]", ""));
                    editTextValorMaximo.setSelection(editTextValorMaximo.getText().length()); // Poner el cursor al final
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Button btnGuardar = findViewById(R.id.buttonGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cantidad = Integer.parseInt(editTextCantidad.getText().toString());
                int valor_maximo = Integer.parseInt(editTextValorMaximo.getText().toString());

                if (entryId == -1) {
                    dbHelper.addEntry(cantidad, valor_maximo);  // Nuevo elemento
                } else {
                    dbHelper.updateEntry(entryId, cantidad, valor_maximo);  // Actualizar elemento existente
                }
                finish();
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
