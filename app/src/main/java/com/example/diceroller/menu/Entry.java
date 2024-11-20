package com.example.diceroller.menu;

public class Entry {
    private int id, cantidad, valor_maximo;

    public Entry(int id, int cantidad, int valor_maximo) {
        this.id = id;
        this.cantidad = cantidad;
        this.valor_maximo = valor_maximo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getValorMaximo() {
        return valor_maximo;
    }

    public void setValorMaximo(int valor_maximo) {
        this.valor_maximo = valor_maximo;
    }
}
