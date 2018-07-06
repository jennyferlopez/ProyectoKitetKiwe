package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelsiete;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.activities.SplashTodosActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcinco.LetraFaltante2Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.LaberintoActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

public class LetraFalfamiliaActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles, imgAyuda, img_nx, img_kxh, img_tx, img_n, img_huevo;
    TextView tv_title;
    int id_user;
    GestorBd db;
    TextView tv_reemplazar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letra_falfamilia);
        db=new GestorBd(getApplication());

        tv_title = (TextView) findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        img_nx = (ImageView) findViewById(R.id.img_nx2);
        img_kxh = (ImageView) findViewById(R.id.img_kxh2);
        img_tx = (ImageView) findViewById(R.id.img_tx2);
        img_n = (ImageView) findViewById(R.id.img_n2);
        img_huevo = (ImageView) findViewById(R.id.img_huevoCol);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        tv_reemplazar = (TextView) findViewById(R.id.tv_remmplazar);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);
        tv_reemplazar.setTypeface(font);

        img_nx.setOnClickListener(this);
        img_kxh.setOnClickListener(this);
        img_tx.setOnClickListener(this);
        img_n.setOnClickListener(this);

        loadPreference();
        cargarTextV();
        imgAyuda = (ImageView) findViewById(R.id.img_ayuda);
        imgAyuda.setOnClickListener(this);
        loadSplash();
    }

    private void loadSplash() {
        final Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        imgAyuda.startAnimation(zoomAnimation);
        Bundle b= new Bundle();
        b.putString("text_uno", "Sekecciona las letras de los cuadros");
        b.putString("text_dos", "y completa la palabra correspondiente a la imagen que se muestra");
        b.putInt("img_uno", R.drawable.img_kxh2);
        b.putInt("img_dos", R.drawable.n_abuelo);
        Intent irActivity= new Intent(LetraFalfamiliaActivity.this, SplashTodosActivity.class);
        irActivity.putExtras(b);
        startActivity(irActivity);
    }

    private void cargarTextV() {
        id_user =db.obtenerId(userName);
        List<Puntos> pts=db.sumaPuntos(id_user);
        pts=db.sumaPuntos(id_user);
        int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
        tv_puntos.setText(""+ p);
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
        if (v.getId() == R.id.img_ayuda) {
            loadSplash();
        }else if (id==R.id.img_nx2){
            tv_reemplazar.setText("TAZUL");
            Thread timerThread = new Thread(){
                public void run(){
                    try{
                        sleep(1200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }finally{
                        Intent ir=new Intent(getApplicationContext(), LetraFalfamilia2Activity.class);
                        startActivity(ir);
                        finish();
                    }
                }
            };
            timerThread.start();
        }else{
            Toast.makeText(this, "sigue intentando", Toast.LENGTH_SHORT).show();
        }
    }
}
