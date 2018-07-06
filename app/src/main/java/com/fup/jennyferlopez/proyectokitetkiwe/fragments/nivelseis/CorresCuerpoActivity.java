package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelseis;

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
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.LaberintoActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel23Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelsiete.Nivel72Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelsiete.Nivel7Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

public class CorresCuerpoActivity extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles, imgToast, img_y, img_pxh, img_ch, img_th, img_huevo, imgAyuda;
    TextView tv_title;
    int id_user;
    GestorBd db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corres_cuerpo);
        db=new GestorBd(getApplication());

        tv_title = (TextView) findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        img_y = (ImageView) findViewById(R.id.imgcab);
        img_pxh = (ImageView) findViewById(R.id.imgpie);
        img_ch = (ImageView) findViewById(R.id.imgcuello);
        img_th = (ImageView) findViewById(R.id.imgmano);
        img_huevo = (ImageView) findViewById(R.id.imgdos);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);

        img_y.setOnClickListener(this);
        img_pxh.setOnClickListener(this);
        img_ch.setOnClickListener(this);
        img_th.setOnClickListener(this);

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
        b.putString("text_uno", "Selecciona la imagen de los cuadros ");
        b.putString("text_dos", "que falte en los huevitos");
        b.putInt("img_uno", R.drawable.con_cabeza);
        b.putInt("img_dos", R.drawable.rama_dos);
        Intent irActivity= new Intent(CorresCuerpoActivity.this, SplashTodosActivity.class);
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
        }else if (id==R.id.imgcuello){
            img_huevo.setImageResource(R.drawable.reemplazar_cuello);

            Puntos puntos= new Puntos(id_user, 3);
            db.insertarPuntos(puntos);
            List<Puntos> pts=db.sumaPuntos(id_user);
            int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
            tv_puntos.setText(""+ p);

            Thread timerThread = new Thread(){
                public void run(){
                    try{
                        sleep(1200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }finally{
                        Intent ir=new Intent(getApplicationContext(), QuizFinal6Activity.class);
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
