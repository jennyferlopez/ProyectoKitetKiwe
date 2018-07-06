package com.fup.jennyferlopez.proyectokitetkiwe.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.DialogFragmentAvatar;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcinco.Nivel5Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.Nivel41Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.Nivel42Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.Nivel43Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.Nivel44Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.Nivel4Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel21Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel22Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel23Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel24Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel2Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelseis.Nivel6Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelsiete.Nivel7Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres.Nivel32Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres.Nivel3Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres.Niveles31Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres.Niveles33Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres.Niveles34Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.Niveles11Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.Niveles12Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.Niveles13Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.Niveles14Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ServicioUsuario;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView cambiar_avatar, glosario, himno, jugar, galeria, simbolos, manual_usuario;
    SharedPreferences preferences;
    String avatarSeleccionado;
    GestorBd bd;
    String userName, activity, pass, pathImg;
    int id_user;
    GestorBd db;
    ServicioUsuario servicioUsuario;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        db=new GestorBd(getApplicationContext());

        cambiar_avatar=(ImageView)findViewById(R.id.cambiarAvatar);
        glosario=(ImageView)findViewById(R.id.glosario);
        himno=(ImageView)findViewById(R.id.himno);
        jugar=(ImageView)findViewById(R.id.jugar);
        galeria=(ImageView)findViewById(R.id.galeriaFotos);
        simbolos=(ImageView)findViewById(R.id.simbolos);
        manual_usuario=(ImageView)findViewById(R.id.manualDeUsuario);
        bd=new GestorBd(getApplicationContext());

        cambiar_avatar.setOnClickListener(this);
        glosario.setOnClickListener(this);
        himno.setOnClickListener(this);
        jugar.setOnClickListener(this);
        galeria.setOnClickListener(this);
        simbolos.setOnClickListener(this);
        manual_usuario.setOnClickListener(this);

        preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        loadPreference();

        //actualizarActivity();
        loadRealm();
    }

    private void loadRealm() {
        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("Test1")
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        servicioUsuario = new ServicioUsuario(Realm.getDefaultInstance());
        User[] usuarios = servicioUsuario.obtenerUsuarios();

        for (int i = 0; i < usuarios.length; i++) {
            Log.d("RESULTADOS", usuarios[i].getNombreUsuario());
        }
    }
    private void loadLogin() {
        preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        userName =preferences.getString(Preference.USER_NAME, "");
        if (!userName.equals("null")){
        User usuario_por_id = servicioUsuario.obtenerUsuarioPorId(userName);
        if (usuario_por_id==null) {
        }else {
                activity= usuario_por_id.getActivity();
                avatarSeleccionado=usuario_por_id.getImagen();
                editor.putString(Preference.AVATAR_SEECCIONADO, avatarSeleccionado);
                editor.commit();
                Intent ir = null;
                if (activity.equals("MenuActivity")) {
                    ir = new Intent(this, MenuActivity.class);
                }else if (activity.equals("NivelesActivity")) {
                    ir = new Intent(this, NivelesActivity.class);
                }else if (activity.equals("Niveles11Activity")) {
                    ir = new Intent(this, Niveles11Activity.class);
                }else if (activity.equals("Niveles12Activity")) {
                    ir = new Intent(this, Niveles12Activity.class);
                }else if (activity.equals("Niveles13Activity")) {
                    ir = new Intent(this, Niveles13Activity.class);
                }else if (activity.equals("Niveles14Activity")) {
                    ir = new Intent(this, Niveles14Activity.class);
                }else if (activity.equals("Nivel2Activity")) {
                    ir = new Intent(this, Nivel2Activity.class);
                }else if (activity.equals("Nivel21Activity")) {
                    ir = new Intent(this, Nivel21Activity.class);
                }else if (activity.equals("Nivel22Activity")) {
                    ir = new Intent(this, Nivel22Activity.class);
                }else if (activity.equals("Nivel23Activity")) {
                    ir = new Intent(this, Nivel23Activity.class);
                }else if (activity.equals("Nivel24Activity")) {
                    ir = new Intent(this, Nivel24Activity.class);
                }else if (activity.equals("Nivel3Activity")) {
                    ir = new Intent(this, Nivel3Activity.class);
                }else if (activity.equals("Niveles31Activity")) {
                    ir = new Intent(this, Niveles31Activity.class);
                }else if (activity.equals("Nivel32Activity")) {
                    ir = new Intent(this, Nivel32Activity.class);
                }else if (activity.equals("Niveles33Activity")) {
                    ir = new Intent(this, Niveles33Activity.class);
                }else if (activity.equals("Niveles34Activity")) {
                    ir = new Intent(this, Niveles34Activity.class);
                }else if (activity.equals("Nivel4Activity")) {
                    ir = new Intent(this, Nivel4Activity.class);
                }else if (activity.equals("Nivel41Activity")) {
                    ir = new Intent(this, Nivel41Activity.class);
                }else if (activity.equals("Nivel42Activity")) {
                    ir = new Intent(this, Nivel42Activity.class);
                }else if (activity.equals("Nivel43Activity")) {
                    ir = new Intent(this, Nivel43Activity.class);
                }else if (activity.equals("Nivel44Activity")) {
                    ir = new Intent(this, Nivel44Activity.class);
                }else if (activity.equals("Nivel5Activity")) {
                    ir = new Intent(this, Nivel5Activity.class);
                }else if (activity.equals("Nivel6Activity")) {
                    ir = new Intent(this, Nivel6Activity.class);
                }else if (activity.equals("Nivel7Activity")) {
                    ir = new Intent(this, Nivel7Activity.class);
                }else{
                    ir = new Intent(this, NivelesActivity.class);
                }
                startActivity(ir);
            }
        }


        }

    @SuppressLint("CommitPrefEdits")
    private void loadPreference() {
        preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        avatarSeleccionado = preferences.getString(Preference.AVATAR_SEECCIONADO, "");
        editor = preferences.edit();
    }


    @Override
    public void onClick(View v) {

        int id=v.getId();
        if (id==R.id.cambiarAvatar){
            FragmentManager fm = getFragmentManager();
            DialogFragmentAvatar dialogFragment = new DialogFragmentAvatar ();
            dialogFragment.show(fm ,"");
        }else  if (id==R.id.glosario){
            Intent irGlosario = new Intent(this,GlosarioCardsActivity.class);
            startActivity(irGlosario);

        }else  if (id==R.id.himno){
            Intent irMusica = new Intent(this, MusicaActivity.class);
            startActivity(irMusica);

        }else  if (id==R.id.jugar){
            userName =preferences.getString(Preference.USER_NAME, "");
            User usuario_por_id = servicioUsuario.obtenerUsuarioPorId(userName);
            if (usuario_por_id.getImagen().equals("img")){
                Toast.makeText(this, "Debes seleccionar un avatar", Toast.LENGTH_LONG).show();
                FragmentManager fm = getFragmentManager();
                DialogFragmentAvatar dialogFragment = new DialogFragmentAvatar ();
                dialogFragment.show(fm ,"");
            }else {
                loadLogin();
            }
        }else  if (id==R.id.galeriaFotos){
            Intent irImagenes = new Intent(this, ImagenesActivity.class);
            startActivity(irImagenes);

        }else  if (id==R.id.simbolos){
            Intent irSimbolos = new Intent(this, SimbolosActivity.class);
            startActivity(irSimbolos);

        }else  if (id==R.id.manualDeUsuario){
            Intent irManual = new Intent(this, ManualActivity.class);
            startActivity(irManual);
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {new AlertDialog.Builder(this).setIcon(R.drawable.logo).setTitle(R.string.cerrar_app)
                .setMessage(R.string.salir_app).setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(Preference.IS_LOGGED, false);
                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(), LogingActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
