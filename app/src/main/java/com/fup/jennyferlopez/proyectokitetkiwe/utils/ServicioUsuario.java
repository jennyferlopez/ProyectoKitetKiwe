package com.fup.jennyferlopez.proyectokitetkiwe.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.activities.MenuActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;

import java.net.URL;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;

import static io.realm.internal.SyncObjectServerFacade.getApplicationContext;

/**
 * Created by jlo-1 on 09/04/2018.
 */

public class ServicioUsuario {
    private Realm realm;

    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    public ServicioUsuario(Realm realm){
        this.realm = realm;
    }
    RealmAsyncTask transaction;
    String nombre, contraseña, imagen, activity;
    int puntos;

    // OBTENER USUARIOS
    public User[] obtenerUsuarios(){
        RealmResults<User> results = realm.where(User.class).findAll();

        return results.toArray(new User[results.size()]);
    }

    public User obtenerUsuarioPorId(String nombre){
        User resultado = realm.where(User.class).equalTo("nombreUsuario",nombre).findFirst();
        return resultado;

    }

    // ACTUALIZAR
    public void actualizarUsuarioNombre(User user, String id, String nombre, String contraseña, String imagen, String activity, int puntos){
        realm.beginTransaction();
        user.setImagen(imagen);
        user.setActivity(activity);
        user.setPuntos(puntos);
        realm.commitTransaction();
       // ActualizarRegistro actualizarRegistro= new ActualizarRegistro();
        //actualizarRegistro.execute();
    }
 public void actualizaractivity(User user,  String activity){
        realm.beginTransaction();
        user.setActivity(activity);
        realm.commitTransaction();
       // ActualizarRegistro actualizarRegistro= new ActualizarRegistro();
        //actualizarRegistro.execute();
    }
 public void actualizarPuntos(User user,  int puntos){
        realm.beginTransaction();
        user.setPuntos(puntos);
        realm.commitTransaction();
       // ActualizarRegistro actualizarRegistro= new ActualizarRegistro();
        //actualizarRegistro.execute();
    }

    // GUARDAR
    public void crearUsuario(String nomb,  String contra, String imag, String act, int pun){
        nombre=nomb;
        contraseña=contra;
        imagen= imag;
        activity=act;
        puntos=pun;
        GuardarRegistro guardarRegistro= new GuardarRegistro();
        guardarRegistro.execute();
    }

    private class GuardarRegistro extends AsyncTask<URL, Integer, Boolean> {
        protected Boolean doInBackground(URL... urls) {
            boolean var = true;
            try {
                realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                User u = realm.createObject(User.class, UUID.randomUUID().toString());
                u.setNombreUsuario(nombre);
                u.setContraseña(contraseña);
                u.setImagen(imagen);
                u.setActivity(activity);
                u.setPuntos(puntos);
                realm.commitTransaction();
            } catch (Exception e) {
                Log.d("error ", String.valueOf(e));
                var = false;
            }

            return var;
        }

        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(getApplicationContext(), "agregado", Toast.LENGTH_SHORT).show();
                Intent ir= new Intent(getApplicationContext(), MenuActivity.class);
                getApplicationContext().startActivity(ir);
                preferences = getApplicationContext().getSharedPreferences(Preference.PREFERENCE_NAME, Context.MODE_PRIVATE);
                editor = preferences.edit();
                editor.putString(Preference.USER_NAME, nombre);
                editor.apply();
            } else {
                Toast.makeText(getApplicationContext(), "no guardado", Toast.LENGTH_SHORT).show();
            }
        }


    }
    private class ActualizarRegistro extends AsyncTask<URL, Integer, Boolean> {
        protected Boolean doInBackground(URL... urls) {
            boolean var = true;
            try {
                realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                User u = realm.createObject(User.class, UUID.randomUUID().toString());
                u.setNombreUsuario(nombre);
                u.setContraseña(contraseña);
                u.setImagen(imagen);
                u.setActivity(activity);
                u.setPuntos(puntos);
                realm.commitTransaction();
            } catch (Exception e) {
                Log.d("error ", String.valueOf(e));
                var = false;
            }

            return var;
        }

        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(getApplicationContext(), "agregado", Toast.LENGTH_SHORT).show();
                Intent ir= new Intent(getApplicationContext(), MenuActivity.class);
                getApplicationContext().startActivity(ir);
                preferences = getApplicationContext().getSharedPreferences(Preference.PREFERENCE_NAME, Context.MODE_PRIVATE);
                editor = preferences.edit();
                editor.putString(Preference.USER_NAME, nombre);
                editor.apply();
            } else {
                Toast.makeText(getApplicationContext(), "no guardado", Toast.LENGTH_SHORT).show();
            }
        }

        // ELIMINAR
        public void eliminarUsuario(String id) {
            User User = obtenerUsuarioPorId(id);
            realm.beginTransaction();
            User.deleteFromRealm();
            realm.commitTransaction();
        }
    }
}
