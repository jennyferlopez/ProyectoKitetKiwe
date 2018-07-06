package com.fup.jennyferlopez.proyectokitetkiwe.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yuri on 16/04/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    static final String DB_NAME = "bdimagen.db";
    static int version = 5;

    private SQLiteDatabase db;

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, version);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql ="CREATE TABLE "+ CategoriaDao.TBL_NAME
                +" (c_id INTEGER AUTO_INCREMENT PRIMARY KEY,"
                +CategoriaDao.C_NOMBRE+" TEXT"
                +")";

        db.execSQL(sql);

        sql ="CREATE TABLE "+ ImagenDao.TBL_NAME
                +" (i_id INTEGER AUTO_INCREMENT PRIMARY KEY,"
                +ImagenDao.I_ID_CATEGORIA+" INTEGER,"
                +ImagenDao.I_DIRECCION+" TEXT"
                +")";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " +CategoriaDao.TBL_NAME);
        db.execSQL("DROP TABLE " +ImagenDao.TBL_NAME);
        onCreate(db);
    }
}
