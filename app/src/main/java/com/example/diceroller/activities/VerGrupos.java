package com.example.diceroller.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diceroller.database.*;
import com.example.diceroller.R;

import java.util.List;

public class VerGrupos extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seegroups);

        dbHelper = new DBHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

    @Override
    protected void onResume() {
        super.onResume();
        // Recargar la lista de entradas
        loadEntries();
    }

    private void loadEntries() {
        List<Entry> entryList = dbHelper.getAllEntries();
        EntryAdapter adapter = new EntryAdapter(entryList, this, this::onItemClick);
        recyclerView.setAdapter(adapter);
    }

    private void onItemClick(Entry entry) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(entry.getCantidad()+"D"+entry.getValorMaximo());
        builder.setItems(new CharSequence[]{"Lanzar","Editar", "Borrar"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:  // Llamar a LanzarDado para editar
                        SharedPreferences sharedPreferences = getSharedPreferences("Dados", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("cantidad", entry.getCantidad());
                        editor.putInt("valor_maximo", entry.getValorMaximo());
                        editor.putBoolean("ultima_tirada", true);
                        editor.apply();
                        Intent intent = new Intent(VerGrupos.this, LanzarDado.class);
                        startActivity(intent);
                        break;
                    case 1:  // Llamar a EditarGrupo para editar
                        Intent editIntent = new Intent(VerGrupos.this, EditarGrupo.class);
                        editIntent.putExtra("id", entry.getId());
                        editIntent.putExtra("cantidad", entry.getCantidad());
                        editIntent.putExtra("valor_maximo", entry.getValorMaximo());
                        startActivity(editIntent);
                        break;
                    case 2:  // Eliminar el elemento de la base de datos
                        dbHelper.deleteEntry(entry.getId());
                        loadEntries(); // Recargar la lista después de eliminar
                        Toast.makeText(VerGrupos.this, "Grupo borrado", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
