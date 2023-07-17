package com.valentinowilliamrendy202102331.projectakhir_202102331;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="project.db";

    public DBHelper(Context context) {super(context, "project.db", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key, password TEXT)");
        sqLiteDatabase.execSQL("create table obat(kode TEXT primary key, nama TEXT, jenis TEXT, golongan TEXT, stok TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        sqLiteDatabase.execSQL("drop table if exists obat");
    }

    public Boolean inserData(String username, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);

        long result = db.insert("users", null, values);
        if (result ==1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[]{username, password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean insertDataOBAT (String kode, String nama, String jenis, String golongan, String stok){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("kode",kode);
        values.put("nama",nama);
        values.put("jenis", jenis);
        values.put("golongan", golongan);
        values.put("stok", stok);
        long result = db.insert("obat", null,values);
        if (result ==1) return false;
        else
            return true;
    }
    public Cursor tampilDataOBAT(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from obat", null);
        return cursor;

    }

    public boolean hapusDataOBAT(String kode){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from obat where kode=?", new String[]{kode});
        if (cursor.getCount()>0) {
            long result = db.delete("obat", "kode=?", new String[]{kode});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Boolean editDataOBAT(String kode, String nama, String jenis, String golongan, String stok) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nama",nama);
        values.put("jenis", jenis);
        values.put("golongan", golongan);
        values.put("stok", stok);

        Cursor cursor = db.rawQuery("Select * from obat where kode=?", new String[]{kode});
        if (cursor.getCount()>0) {
            long result = db.update("obat", values, "kode=?", new String[]{kode});
            if (result == 1) {
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }

    public Boolean cekkode(String kode){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from obat where kode=?", new String[] {kode});
        if (cursor.getCount()>0)
            return true;
        else
            return false;

    }
}