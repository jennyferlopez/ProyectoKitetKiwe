package com.fup.jennyferlopez.proyectokitetkiwe.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.Niveles11Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Usuario;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ServicioUsuario;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static io.realm.internal.SyncObjectServerFacade.getApplicationContext;


public class LogingActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnRegistrarse, btn_menu;
    TextView  tv_nom, tv_pass;
    EditText edit_User, edit_Password;
    GestorBd db;
    String activity;
    String pathimg;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    ServicioUsuario servicioUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging);
        db=new GestorBd(getApplicationContext());
        edit_User=(EditText)findViewById(R.id.edt_correoLogin);
        edit_Password=(EditText)findViewById(R.id.edt_contraseñaLogin);
        tv_nom = (TextView) findViewById(R.id.tvNombreU);
        tv_pass= (TextView) findViewById(R.id.tvPassU);
        btnRegistrarse= (Button) findViewById(R.id.btn_registrase);
        btn_menu= (Button) findViewById(R.id.btn_menu);

        String font_url ="font/dklemonyellowsun.otf";

        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_nom.setTypeface(font);
        tv_pass.setTypeface(font);
        btnRegistrarse.setTypeface(font);
        btn_menu.setTypeface(font);
        edit_User.setTypeface(font);
        edit_Password.setTypeface(font);
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogingActivity.this,RegistrarseActivity.class));
            }
        });
        preferences =  getSharedPreferences(Preference.PREFERENCE_NAME, Context.MODE_PRIVATE);
        activity= String.valueOf(LogingActivity.class);
        pathimg=String.valueOf(R.drawable.avatar_blanco);
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

        for ( int i = 0; i< usuarios.length; i++){
            Log.d("RESULTADOS",usuarios[i].getNombreUsuario());
        }

    }

    public void cancelar(View view) {
        Intent irLogin= new Intent(this, LogingActivity.class);
        startActivity(irLogin);
        finish();
    }
    public void irMenu(View view) {
        String user = edit_User.getText().toString();
        String pass = edit_Password.getText().toString();

        User usuario_por_id = servicioUsuario.obtenerUsuarioPorId(user);
        if (usuario_por_id==null) {
            Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
        } else   if (usuario_por_id.getContraseña().equals(pass)){
            preferences = getApplicationContext().getSharedPreferences(Preference.PREFERENCE_NAME, Context.MODE_PRIVATE);
            editor = preferences.edit();
            editor.putString(Preference.USER_NAME, user);
            editor.apply();
            Intent irMenu=new Intent(this, MenuActivity.class);
            startActivity(irMenu);
            finish();
        }else {
            Toast.makeText(getApplicationContext(), "la contraseña es incorrecta", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this).setIcon(R.drawable.logo).setTitle(R.string.cerrar_app)
                .setMessage(R.string.salir_app).setNegativeButton(android.R.string.cancel, null)//sin listener
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Salir
                        System.runFinalization();
                        System.exit(0);
                        LogingActivity.this.finish();
                    }
                }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
