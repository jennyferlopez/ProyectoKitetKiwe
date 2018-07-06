package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.activities.SplashTodosActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel22Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres.ColorCorresActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

public class SonCorresNumActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles, ic_play,imgAyuda;
    ImageView img_uno, img_dos, img_tres,img_cuatro, img_cinco, img_seis, img_siete, img_ocho, img_nueve, img_diez;
    int i =0, num;
    int sonidos[]= new int[10];
    TextView tv_title;
    GestorBd db;
    int cont_intentos=0, cont_good=0, cont_fail=0, id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_son_corres_num);
        db=new GestorBd(getApplication());
        tv_title = (TextView) findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        ic_play =(ImageView) findViewById(R.id.con_play);
        img_uno =(ImageView) findViewById(R.id.img_uno);
        img_dos =(ImageView) findViewById(R.id.img_dos);
        img_tres =(ImageView) findViewById(R.id.img_tres);
        img_cuatro =(ImageView) findViewById(R.id.img_cuatro);
        img_cinco =(ImageView) findViewById(R.id.img_cinco);
        img_seis =(ImageView) findViewById(R.id.img_seis);
        img_siete =(ImageView) findViewById(R.id.img_siete);
        img_ocho =(ImageView) findViewById(R.id.img_ocho);
        img_nueve =(ImageView) findViewById(R.id.img_nueve);
        img_diez =(ImageView) findViewById(R.id.img_diez);

        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);

        sonidos[0]= R.raw.cuatro;
        sonidos[1]= R.raw.dos;
        sonidos[2]= R.raw.seis;
        sonidos[3]= R.raw.diez;
        sonidos[4]= R.raw.uno;
        sonidos[5]= R.raw.nueve;
        sonidos[6]= R.raw.cinco;
        sonidos[7]= R.raw.ocho;
        sonidos[8]= R.raw.siete;
        sonidos[9]= R.raw.tres;
        imgAyuda = (ImageView) findViewById(R.id.img_ayuda);
        imgAyuda.setOnClickListener(this);
        ic_play.setOnClickListener(this);
        img_uno.setOnClickListener(this);
        img_dos.setOnClickListener(this);
        img_tres.setOnClickListener(this);
        img_cuatro.setOnClickListener(this);
        img_cinco.setOnClickListener(this);
        img_seis.setOnClickListener(this);
        img_siete.setOnClickListener(this);
        img_ocho.setOnClickListener(this);
        img_nueve.setOnClickListener(this);
        img_diez.setOnClickListener(this);
        loadPreference();
        cargarTextV();
        loadSplash();
    }

    private void loadSplash() {
        final Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        imgAyuda.startAnimation(zoomAnimation);
        Bundle b= new Bundle();
        b.putString("text_uno", "Selecciona la imagen de sonido");
        b.putString("text_dos", "y seleciona el numero correspondiente");
        b.putInt("img_uno", R.drawable.con_play);
        b.putInt("img_dos", R.drawable.img_uno);
        Intent irActivity= new Intent(SonCorresNumActivity.this, SplashTodosActivity.class);
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
        if (v.getId() == R.id.img_ayuda) {
            loadSplash();
        }else if (v.getId() == R.id.con_play) {
            MediaPlayer mp = MediaPlayer.create(this, sonidos[i]);
            mp.start();
            num=1;
            if (i==10){
                Toast.makeText(this, "final", Toast.LENGTH_SHORT).show();
            }
        }else if (v.getId() == R.id.img_cuatro){
            if (num==1 && i==0){
                i=i+1;
                img_cuatro.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_dos){
            if (i==1){
                i=i+1;
                img_dos.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_seis){
            if (i==2){
                i=i+1;
                img_seis.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_diez){
            if (i==3){
                i=i+1;
                img_diez.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_uno){
            if (i==4){
                i=i+1;
                img_uno.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_nueve){
            if (i==5){
                i=i+1;
                img_nueve.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_cinco){
            if (i==6){
                i=i+1;
                img_cinco.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_ocho){
            if (i==7){
                i=i+1;
                img_ocho.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_siete){
            if (i==8){
                i=i+1;
                img_siete.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_tres){
            if (i==9){
                i=i+1;
                img_tres.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
                cargarPuntos();
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }
    }

    private void cargarPuntos() {
        if (cont_good ==10) {
            Intent ir = new Intent(getApplication(), Nivel44Activity.class);
            startActivity(ir);
            finish();
        }if (cont_good==10 && cont_intentos ==10){
            Puntos puntos= new Puntos(id_user, 3);
            db.insertarPuntos(puntos);
            List<Puntos> pts=db.sumaPuntos(id_user);
            int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
            tv_puntos.setText(""+ p);
        }else if (cont_good==10 && (cont_intentos >10 || cont_intentos <13)){
            id_user =db.obtenerId(userName);
            Puntos puntos= new Puntos(id_user, 2);
            db.insertarPuntos(puntos);
            List<Puntos> pts=db.sumaPuntos(id_user);
            int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
            tv_puntos.setText(""+ p);
        }else if (cont_good==10 && (cont_intentos >=13 || cont_intentos <=16)){
            id_user =db.obtenerId(userName);
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            List<Puntos> pts=db.sumaPuntos(id_user);
            int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
            tv_puntos.setText(""+ p);
        }else if (cont_good<10 && cont_intentos >16){
            toastWarning();
        }
    }

    private void toast() {
        Toast.makeText(this, "Intenta de nuevo", Toast.LENGTH_SHORT).show();
    }
    private void toastWarning() {
        Toast toasta = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_personalizado,
                (ViewGroup) findViewById(R.id.lytLayout));

        TextView txtMsg = (TextView)layout.findViewById(R.id.tvMsjToast);
        ImageView imgToast =(ImageView) layout.findViewById(R.id.imgToast);
        imgToast.setBackgroundResource(R.drawable.toast_warning);
        txtMsg.setText("te recomendamos volver al nivel de reconocimiento tienes "+ cont_intentos +" fallidos");
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        txtMsg.setTypeface(font);
        toasta.setDuration(Toast.LENGTH_SHORT);
        toasta.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0,0);
        toasta.setView(layout);
        toasta.show();
    }

}
