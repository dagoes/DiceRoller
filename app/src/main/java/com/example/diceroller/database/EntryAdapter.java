package com.example.diceroller.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.diceroller.R;

import java.util.List;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder> {
    private List<Entry> entries;
    private Context context;
    private OnItemClickListener onItemClickListener;

    // Interfaz para manejar los clics en los elementos
    public interface OnItemClickListener {
        void onItemClick(Entry entry);
    }

    // Constructor del adaptador
    public EntryAdapter(List<Entry> entries, Context context, OnItemClickListener onItemClickListener) {
        this.entries = entries;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflar el layout de cada item de la lista
        View view = LayoutInflater.from(context).inflate(R.layout.item_entry, parent, false);
        return new EntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {
        // Obtener el elemento de la lista y asignarlo a la vista
        Entry entry = entries.get(position);
        String dado = entry.getCantidad()+"D"+entry.getValorMaximo();
        holder.cantidadDValorMaximoTextView.setText(dado);

        // Agregar el listener para manejar el clic en un elemento
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(entry));
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    // Clase interna ViewHolder para cada elemento
    public static class EntryViewHolder extends RecyclerView.ViewHolder {
        TextView cantidadDValorMaximoTextView;

        public EntryViewHolder(View itemView) {
            super(itemView);
            cantidadDValorMaximoTextView = itemView.findViewById(R.id.cantidadDValorMaximoTextView);
        }
    }
}