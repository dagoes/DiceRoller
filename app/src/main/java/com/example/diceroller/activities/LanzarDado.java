package com.example.diceroller.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diceroller.R;

import java.util.Random;

public class LanzarDado extends AppCompatActivity {
    private int cantidad, valor_maximo, suma;
    private TextView textDados, textResultado, textSuma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_throwdice);

        textDados = findViewById(R.id.tituloLanzar);
        textResultado = findViewById(R.id.textResultado);
        textSuma = findViewById(R.id.textSuma);

        SharedPreferences sharedPreferences = getSharedPreferences("Dados", MODE_PRIVATE);
        cantidad = sharedPreferences.getInt("cantidad", 0);
        valor_maximo = sharedPreferences.getInt("valor_maximo", 0);

        String dados = "Lanzar "+cantidad+"D";
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
                suma = 0;
                Random random = new Random();
                StringBuilder resultado = new StringBuilder();

                if (valor_maximo == -10) {
                    // Número aleatorio entre 0 y 9 multiplicado por 10
                    int numeroDado = random.nextInt(10)*10;
                    textResultado.setText(String.valueOf(numeroDado));
                    textSuma.setText(String.valueOf(numeroDado));
                } else {
                    for (int i = 0; i < cantidad; i++) {
                        if (i > 0) resultado.append(", ");
                        // Número aleatorio entre 1 y valor_maximo
                        int numeroDado = random.nextInt(valor_maximo) + 1;
                        suma = suma + numeroDado;
                        resultado.append(numeroDado);
                    }
                    textResultado.setText(resultado.toString());
                    textSuma.setText(String.valueOf(suma));
                }
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
