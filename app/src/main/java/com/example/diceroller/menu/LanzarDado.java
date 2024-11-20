package com.example.diceroller.menu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diceroller.R;

public class LanzarDado extends AppCompatActivity {
    private int cantidad, valor_maximo;
    private TextView textDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_throwdice);

        textDados = findViewById(R.id.textDados);

        SharedPreferences sharedPreferences = getSharedPreferences("Dados", MODE_PRIVATE);
        cantidad = sharedPreferences.getInt("cantidad", 0);
        valor_maximo = sharedPreferences.getInt("valor_maximo", 0);

        String dados = cantidad+"D";
        if (valor_maximo == -10) {
            dados = dados+"00";
        } else {
            dados = dados+valor_maximo;
        }
        textDados.setText(dados);

        Button btnLanzar = findViewById(R.id.buttonLanzar);

        btnLanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button btnVolver = findViewById(R.id.buttonVolver1);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
