package com.fup.jennyferlopez.proyectokitetkiwe.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fup.jennyferlopez.proyectokitetkiwe.modelos.Imagen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuri on 16/04/2016.
 */
public class ImagenDao {

    public static final String TBL_NAME = "imagen";
    public static final String I_ID = "i_id";
    public static final String I_ID_CATEGORIA = "c_id";
    public static final String I_DIRECCION = "i_direccion";

    SQLiteDatabase db;

    public ImagenDao(Context context){
        DatabaseHelper helper =  new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insertImagen(Imagen imagen){
        ContentValues cV = new ContentValues();

        List<Imagen> l;
        l = getAllImagen();
        imagen.setId(l.size()+1);

        cV.put(I_ID, imagen.getId());
        cV.put(I_ID_CATEGORIA, imagen.getId_c());
        cV.put(I_DIRECCION, imagen.getDireccion());

        db.insert(TBL_NAME, null, cV);
    }

    public void updateImagen(Imagen imagen){
        ContentValues cV = new ContentValues();
        cV.put(I_ID_CATEGORIA, imagen.getId_c());
        cV.put(I_DIRECCION, imagen.getDireccion());

        db.update(TBL_NAME, cV, "_id=?", new String[]{"" + imagen.getId()});
    }

    public void deleteImagen(long id){
        db.delete(TBL_NAME, "_id=?", new String[]{"" + id});
    }

    public List<Imagen> getAllImagen(){
        String sql = "SELECT * FROM "+TBL_NAME;
        return  cursorToList(sql);
    }

    public List<Imagen> getAllImagenByName(String id_ca){
        String sql ="SELECT * FROM "+TBL_NAME+" WHERE "+I_ID_CATEGORIA+" LIKE '%"+id_ca+"%'";
        return  cursorToList(sql);
    }

    private List<Imagen> cursorToList(String sql){
        List<Imagen> data =  new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, null);

        for(int i=0; i<cursor.getCount();i++){
            cursor.moveToPosition(i);

            Imagen im = new Imagen();
            im.setId(cursor.getInt(0));
            im.setId_c(cursor.getInt(1));
            im.setDireccion(cursor.getString(2));

            data.add(im);
        }

        return data;
    }

}
