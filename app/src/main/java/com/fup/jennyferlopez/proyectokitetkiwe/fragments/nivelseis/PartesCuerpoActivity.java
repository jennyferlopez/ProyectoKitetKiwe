package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelseis;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcinco.Nivel51Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.CompTren4Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ServicioUsuario;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class PartesCuerpoActivity extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles, imgToast, img_cabeza, img_cuello, img_codo, img_mano, img_rodilla, img_pie;
    TextView tv_title, tv_cabeza, tv_cuello, tv_codo, tv_mano, tv_rodilla, tv_pie;
    int id_user;
    ServicioUsuario servicioUsuario;
    Button btnSiguiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partes_cuerpo);
        btnSiguiente= (Button) findViewById(R.id.fab);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irNivel= new Intent(getApplication(), Nivel61Activity.class);
                startActivity(irNivel);
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        img_cabeza = (ImageView) findViewById(R.id.img_cabeza);
        img_cuello = (ImageView) findViewById(R.id.img_cuello);
        img_codo = (ImageView) findViewById(R.id.img_codo);
        img_mano = (ImageView) findViewById(R.id.img_mano);
        img_rodilla = (ImageView) findViewById(R.id.img_rodilla);
        img_pie = (ImageView) findViewById(R.id.img_pie);
        tv_cabeza = (TextView) findViewById(R.id.tv_cabeza);
        tv_cuello = (TextView) findViewById(R.id.tv_cuello);
        tv_codo = (TextView) findViewById(R.id.tv_codo);
        tv_mano = (TextView) findViewById(R.id.tv_mano);
        tv_rodilla = (TextView) findViewById(R.id.tv_rodilla);
        tv_pie = (TextView) findViewById(R.id.tv_pie);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);
        tv_cabeza.setTypeface(font);
        tv_cuello.setTypeface(font);
        tv_codo.setTypeface(font);
        tv_mano.setTypeface(font);
        tv_rodilla.setTypeface(font);
        tv_pie.setTypeface(font);

        img_cabeza.setOnClickListener(this);
        img_cuello.setOnClickListener(this);
        img_codo.setOnClickListener(this);
        img_mano.setOnClickListener(this);
        img_rodilla.setOnClickListener(this);
        img_pie.setOnClickListener(this);

        loadPreference();
        loadRealm();
        actualizarPuntos();
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
        } else if (avatarSeleccionado.equals("1")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_uno_n);
        } else if (avatarSeleccionado.equals("2")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_dos_n);
        } else if (avatarSeleccionado.equals("3")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_tres_n);
        } else if (avatarSeleccionado.equals("4")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_uno_n);
        } else if (avatarSeleccionado.equals("5")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_dos_n);
        } else if (avatarSeleccionado.equals("6")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_tres_n);
        }
    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (v.getId() == R.id.img_cabeza) {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.cabeza);
            mp.start();
        }else if (v.getId() == R.id.img_cuello) {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.cuello);
            mp.start();
        }else if (v.getId() == R.id.img_codo) {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.brazo);
            mp.start();
        }else if (v.getId() == R.id.img_mano) {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.mano);
            mp.start();
        }else if (v.getId() == R.id.img_rodilla) {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.pierna);
            mp.start();
        }else if (v.getId() == R.id.img_pie) {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.pie);
            mp.start();
        }
    }

    private void actualizarPuntos() {
        userName =preferences.getString(Preference.USER_NAME, "");
        User usuario_por_id = servicioUsuario.obtenerUsuarioPorId(userName);
        if (usuario_por_id!=null) {
            int p=Integer.parseInt(String.valueOf(usuario_por_id.getPuntos()));
            tv_puntos.setText(""+ p);
        }
    }

}
