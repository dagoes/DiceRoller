package com.example.diceroller.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DiceRoller";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE dados (id INTEGER PRIMARY KEY AUTOINCREMENT, cantidad INTEGER NOT NULL, valor_maximo INTEGER NOT NULL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS dados");
        onCreate(db);
    }

    // CRUD Operations
    public void addEntry(int cantidad, int valor_maximo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cantidad", cantidad);
        values.put("valor_maximo", valor_maximo);
        db.insert("dados", null, values);
        db.close();
    }

    public List<Entry> getAllEntries() {
        List<Entry> dados = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("dados", null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int cantidad = cursor.getInt(cursor.getColumnIndex("cantidad"));
                int valor_maximo = cursor.getInt(cursor.getColumnIndex("valor_maximo"));
                dados.add(new Entry(id, cantidad, valor_maximo));
            }
            cursor.close();
        }
        db.close();
        return dados;
    }

    public void updateEntry(int id, int cantidad, int valor_maximo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cantidad", cantidad);
        values.put("valor_maximo", valor_maximo);
        db.update("dados", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteEntry(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("dados", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
