package com.example.diceroller.activities;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diceroller.R;

import java.util.Random;

public class LanzarDado extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private int cantidad, valor_maximo, suma;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_throwdice);

        TextView textTitulo = findViewById(R.id.tituloLanzar);
        TextView textResultado = findViewById(R.id.textResultado);
        TextView textSuma = findViewById(R.id.textSuma);

        sharedPreferences = getSharedPreferences("Dados", MODE_PRIVATE);
        cantidad = sharedPreferences.getInt("cantidad", 0);
        valor_maximo = sharedPreferences.getInt("valor_maximo", 0);
        Boolean sonido = sharedPreferences.getBoolean("sonido", true);

        mediaPlayer = MediaPlayer.create(LanzarDado.this, R.raw.dice_roll);

        textTitulo.setText(crearTitulo());

        Button btnLanzar = findViewById(R.id.buttonLanzar);

        btnLanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sonido && mediaPlayer != null) mediaPlayer.start();
                textResultado.setText(lanzar());
                textSuma.setText(String.valueOf(suma));
            }
        });

        ImageButton btnVolver = findViewById(R.id.imageButtonVolver4);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Liberar los recursos de MediaPlayer cuando la actividad se destruye
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private String crearTitulo() {
        String titulo = "Lanzar "+cantidad+"D";
        if (valor_maximo == -10) {
            titulo = titulo +"00";
        } else {
            titulo = titulo +valor_maximo;
        }
        return titulo;
    }

    private String lanzar() {
        suma = 0;
        Random random = new Random();
        int numeroDado;
        if (valor_maximo == -10) {
            // Número aleatorio entre 0 y 9 multiplicado por 10
            numeroDado = random.nextInt(10)*10;
            suma = numeroDado;
            return String.valueOf(numeroDado);
        } else {
            StringBuilder resultado = new StringBuilder();
            for (int i = 0; i < cantidad; i++) {
                if (i > 0) resultado.append(", ");
                // Número aleatorio entre 1 y valor_maximo
                numeroDado = random.nextInt(valor_maximo) + 1;
                suma = suma + numeroDado;
                resultado.append(numeroDado);
            }
            return resultado.toString();
        }

    }
}
