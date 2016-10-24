package com.example.mauri.appcoleira;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "idcoleiradb";

    // Contacts table name

    private static final String TABLE_CONTACTS = "contacts";

    // Dados Coleira Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_IDCOLEIRA = "IdColeira";


    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_IDCOLEIRA + " TEXT" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    // Add dados
    public void addDataBDLocal(IDColeiraCadastrado data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_IDCOLEIRA, data.getIDColeira());

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    //Getting one data

    public IDColeiraCadastrado getIDColeiraBD(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID, KEY_IDCOLEIRA}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        IDColeiraCadastrado contact = new IDColeiraCadastrado(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        // return contact
        return contact;
    }

    // Getting all data from table

    public List<IDColeiraCadastrado> getAllData() {
        List<IDColeiraCadastrado> contactList = new ArrayList<IDColeiraCadastrado>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                IDColeiraCadastrado contact = new IDColeiraCadastrado();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setIDColeira(cursor.getString(1));

                // Add dado to list

                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        //return data list
        return contactList;
    }

    //Getting dados Count
    //will return total number of contacts in SQLite database.

    public int getDadosColeiraCount() {
        String countQuerry = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuerry, null);
        cursor.close();

        //return count
        return cursor.getCount();
    }

    //Updating a data

    public int updateData(IDColeiraCadastrado data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_IDCOLEIRA, data.getIDColeira());

        //implementar os outros se necessário

        //updating row

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(data.getId())});
    }


    //Deleting a data

    public void deleteData(DadosColeira data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_IDCOLEIRA + "=?",
                new String[]{data.getIdColeira()});
        db.close();
    }
}
