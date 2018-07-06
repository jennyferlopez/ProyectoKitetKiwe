package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelseis;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.activities.MenuActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcinco.SopaAnimPlantActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ServicioUsuario;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Nivel64Activity extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences preferences;
    String avatarSeleccionado;
    ImageView correAvaatr, icAvatarNiveles;
    TextView tv_puntos;
    String userName, activity, pass, pathImg;
    int id_user;
    ServicioUsuario servicioUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel64);
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        correAvaatr = (ImageView) findViewById(R.id.correAvatar);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        correAvaatr.setOnClickListener(this);

        loadPreference();
        loadRealm();
        actualizarActivity();
    }

    private void loadRealm() {
        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("Test1")
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        servicioUsuario = new ServicioUsuario(Realm.getDefaultInstance());

    }


    private void loadPreference() {
        preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        avatarSeleccionado = preferences.getString(Preference.AVATAR_SEECCIONADO, "");
        userName =preferences.getString(Preference.USER_NAME, "");

        if (avatarSeleccionado.equals(null)) {
            icAvatarNiveles.setBackgroundResource(Integer.parseInt(null));
            correAvaatr.setBackgroundResource(Integer.parseInt(null));
        } else if (avatarSeleccionado.equals("1")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_uno_n);
            correAvaatr.setBackgroundResource(R.drawable.nino_uno);
        } else if (avatarSeleccionado.equals("2")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_dos_n);
            correAvaatr.setBackgroundResource(R.drawable.nino_dos);
        } else if (avatarSeleccionado.equals("3")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_tres_n);
            correAvaatr.setBackgroundResource(R.drawable.nino_tres);
        } else if (avatarSeleccionado.equals("4")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_uno_n);
            correAvaatr.setBackgroundResource(R.drawable.nina_uno);
        } else if (avatarSeleccionado.equals("5")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_dos_n);
            correAvaatr.setBackgroundResource(R.drawable.nina_dos);
        } else if (avatarSeleccionado.equals("6")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_tres_n);
            correAvaatr.setBackgroundResource(R.drawable.nina_tres);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.correAvatar){
            Intent irNumeros= new Intent(getApplication(), CorresCuerpoActivity.class);
            startActivity(irNumeros);
            Toast.makeText(this, "Nivel cinco", Toast.LENGTH_SHORT).show();
        }
    }
    private void actualizarActivity() {
        userName =preferences.getString(Preference.USER_NAME, "");
        User usuario_por_id = servicioUsuario.obtenerUsuarioPorId(userName);
        if (usuario_por_id!=null) {
            servicioUsuario.actualizaractivity(usuario_por_id,"Nivel65Activity");
            int p=Integer.parseInt(String.valueOf(usuario_por_id.getPuntos()));
            tv_puntos.setText(""+ p);
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent irMenu=new Intent(getApplication(), MenuActivity.class);
            startActivity(irMenu);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }}
