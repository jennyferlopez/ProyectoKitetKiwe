package com.fup.jennyferlopez.proyectokitetkiwe.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.VocalesActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ServicioUsuario;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class NivelesActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences preferences;
    String avatarSeleccionado;
    ImageView correAvaatr, icAvatarNiveles;
    RelativeLayout rlLayout;
    TextView tv_puntos;
    String userName, activity, pass, pathImg;
    int id_user;
    ServicioUsuario servicioUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles);

        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        correAvaatr = (ImageView) findViewById(R.id.correAvatar);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);

        correAvaatr.setOnClickListener(this);
        rlLayout= (RelativeLayout) findViewById(R.id.lyNivel1);
        loadPreference();
        //cargarTextV();
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

      //  avatarSeleccionado=db.buscarAvatar(id_user);

        if (avatarSeleccionado.equals(null)) {
            correAvaatr.setBackgroundResource(Integer.parseInt(null));
            icAvatarNiveles.setBackgroundResource(Integer.parseInt(null));
        } else if (avatarSeleccionado.equals("1")) {
            correAvaatr.setBackgroundResource(R.drawable.nino_uno);
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_uno_n);
        } else if (avatarSeleccionado.equals("2")) {
            correAvaatr.setBackgroundResource(R.drawable.nino_dos);
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_dos_n);
        } else if (avatarSeleccionado.equals("3")) {
            correAvaatr.setBackgroundResource(R.drawable.nino_tres);
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_tres_n);
        } else if (avatarSeleccionado.equals("4")) {
            correAvaatr.setBackgroundResource(R.drawable.nina_uno);
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_uno_n);
        } else if (avatarSeleccionado.equals("5")) {
            correAvaatr.setBackgroundResource(R.drawable.nina_dos);;
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_dos_n);
        } else if (avatarSeleccionado.equals("6")) {
            correAvaatr.setBackgroundResource(R.drawable.nina_tres);;
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_tres_n);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.correAvatar){
            Intent irRecVocales= new Intent(this, VocalesActivity.class);
            startActivity(irRecVocales);
            SumarPuntos(3);
            finish();
        }
    }

    private void SumarPuntos(int puntos) {
        userName =preferences.getString(Preference.USER_NAME, "");
        User usuario_por_id = servicioUsuario.obtenerUsuarioPorId(userName);
        if (usuario_por_id!=null) {

            int p=Integer.parseInt(String.valueOf(usuario_por_id.getPuntos()));

            servicioUsuario.actualizarPuntos(usuario_por_id,puntos+p);
            int p1=Integer.parseInt(String.valueOf(usuario_por_id.getPuntos()));
            tv_puntos.setText(""+ p1);
        }
    }
    private void actualizarActivity() {
        userName =preferences.getString(Preference.USER_NAME, "");
        User usuario_por_id = servicioUsuario.obtenerUsuarioPorId(userName);
        if (usuario_por_id!=null) {
            servicioUsuario.actualizaractivity(usuario_por_id,"NivelesActivity");
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
    }
}