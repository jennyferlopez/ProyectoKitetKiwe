package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.activities.SplashTodosActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres.ColorCorres3Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

public class CompTrenActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles, imgToast, img_t, img_th, img_tx, img_txh, img_huevo, imgAyuda;
    TextView tv_title;
    int id_user;
    GestorBd db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_tren);
        db=new GestorBd(getApplication());

        tv_title = (TextView) findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        img_t = (ImageView) findViewById(R.id.img_t2);
        img_th = (ImageView) findViewById(R.id.img_th2);
        img_tx = (ImageView) findViewById(R.id.img_tx2);
        img_txh = (ImageView) findViewById(R.id.img_txh2);
        img_huevo = (ImageView) findViewById(R.id.img_huevoCol);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);

        imgAyuda = (ImageView) findViewById(R.id.img_ayuda);
        imgAyuda.setOnClickListener(this);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);

        img_t.setOnClickListener(this);
        img_th.setOnClickListener(this);
        img_tx.setOnClickListener(this);
        img_txh.setOnClickListener(this);

        loadPreference();
        cargarTextV();
        toastInstruccion();

        loadSplash();
    }

    private void loadSplash() {
        final Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        imgAyuda.startAnimation(zoomAnimation);
        Bundle b= new Bundle();
        b.putString("text_uno", "Selecciona la consonante basica faltante");
        b.putString("text_dos", "correspondiente para completar los huevitos");
        b.putInt("img_uno", R.drawable.img_th2);
        b.putInt("img_dos", R.drawable.img_huevos_uno);
        Intent irActivity= new Intent(CompTrenActivity.this, SplashTodosActivity.class);
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


    private void toastInstruccion() {

        Toast toasta = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_instrucciones,
                (ViewGroup) findViewById(R.id.lytLayout));

        imgToast =(ImageView) layout.findViewById(R.id.imgToast);
        TextView txtMsg = (TextView)layout.findViewById(R.id.tvMsjToast);
        if (avatarSeleccionado.equals(null)) {
            imgToast.setBackgroundResource(Integer.parseInt(null));
        } else if (avatarSeleccionado.equals("1")) {
            imgToast.setBackgroundResource(R.drawable.nino_uno);
        } else if (avatarSeleccionado.equals("2")) {
            imgToast.setBackgroundResource(R.drawable.nino_dos);
        } else if (avatarSeleccionado.equals("3")) {
            imgToast.setBackgroundResource(R.drawable.nino_tres);
        } else if (avatarSeleccionado.equals("4")) {
            imgToast.setBackgroundResource(R.drawable.nina_uno);
        } else if (avatarSeleccionado.equals("5")) {
            imgToast.setBackgroundResource(R.drawable.nina_dos);
        } else if (avatarSeleccionado.equals("6")) {
            imgToast.setBackgroundResource(R.drawable.nina_tres);
        }
        txtMsg.setText("esta es la instruccion");
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        txtMsg.setTypeface(font);
        toasta.setDuration(Toast.LENGTH_LONG);
        toasta.setView(layout);
        toasta.setGravity(Gravity.CENTER, 0, 0);
        toasta.show();

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (v.getId() == R.id.img_ayuda) {
            loadSplash();
        }else if (id==R.id.img_t2){
            img_huevo.setBackgroundResource(R.drawable.huevo_t);
            Thread timerThread = new Thread(){
                public void run(){
                    try{
                        sleep(1200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }finally{
                        Intent ir=new Intent(getApplicationContext(), CompTren2Activity.class);
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
