package com.fup.jennyferlopez.proyectokitetkiwe.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fup.jennyferlopez.proyectokitetkiwe.modelos.Categoria;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuri on 16/04/2016.
 */
public class CategoriaDao {

    public static final String TBL_NAME="categoria";
    public static final String C_ID="c_id";
    public static final String C_NOMBRE="nombre";

    SQLiteDatabase db;

    public CategoriaDao(Context context){
        DatabaseHelper helper =  new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insertCategoria(Categoria categoria){
        ContentValues cV = new ContentValues();
        List<Categoria> l;
        l = getAllCategoria();
        categoria.setId(l.size()+1);

        cV.put(C_NOMBRE, categoria.getNombre());
        cV.put(C_ID, categoria.getId());

        List<Categoria> l2 = getAllCategoriaByName(categoria.getNombre());
        if(l2.size() == 0)
            db.insert(TBL_NAME, null,cV);
    }

    public void updateCategoria(Categoria categoria){
        ContentValues cV = new ContentValues();
        cV.put(C_NOMBRE, categoria.getNombre());

        db.update(TBL_NAME, cV, "_id=?", new String[]{"" + categoria.getId()});
    }

   public void deleteCategoria(Categoria id){
        db.delete(TBL_NAME, "_id=?", new String[]{"" + id});
    }

    //public void deleteCategoria(Categoria c) {
      //  db.delete(TBL_NAME, "_id=?", new String[]{"" + c.getId()});

   // }

    public List<Categoria> getAllCategoria(){
        String sql = "SELECT * FROM "+TBL_NAME;
        return  cursorToList(sql);
    }

    public List<Categoria> getAllCategoriaByName(String name){
        String sql ="SELECT * FROM "+TBL_NAME+" WHERE "+C_NOMBRE+" LIKE '%"+name+"%'";
        return  cursorToList(sql);
    }

    private List<Categoria> cursorToList(String sql){
        List<Categoria> data =  new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, null);

        for(int i=0; i<cursor.getCount();i++){
            cursor.moveToPosition(i);

            Categoria c = new Categoria();
            c.setId(cursor.getInt(0));
            c.setNombre(cursor.getString(1));

            data.add(c);
        }
        return data;
    }


}
