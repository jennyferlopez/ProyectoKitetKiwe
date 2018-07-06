package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos;

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
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.Niveles13Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.VocalAImagenActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

public class SonidoCorreActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles, ic_play;
    ImageView img_px, img_d, img_c,img_th, img_m, img_kxh, img_bx, img_s, img_ch, img_g, img_pxh, img_vx, img_txh, img_nx, img_y, img_kx ;
    TextView tv_title;
    GestorBd db;
    int i =0, num;
    int sonidos[]= new int[16];
    int cont_intentos=0, cont_good=0, cont_fail=0, id_user;
    ImageView imgAyuda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonido_corre);

        db=new GestorBd(getApplication());
        tv_title = (TextView) findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        ic_play =(ImageView) findViewById(R.id.con_play);
        img_px =(ImageView) findViewById(R.id.img_px);
        img_d =(ImageView) findViewById(R.id.img_d);
        img_c =(ImageView) findViewById(R.id.img_c);
        img_th =(ImageView) findViewById(R.id.img_th);
        img_m =(ImageView) findViewById(R.id.img_m);
        img_kxh =(ImageView) findViewById(R.id.img_kxh);
        img_bx =(ImageView) findViewById(R.id.img_bx);
        img_s =(ImageView) findViewById(R.id.img_s);
        img_ch =(ImageView) findViewById(R.id.img_ch);
        img_g =(ImageView) findViewById(R.id.img_g);
        img_pxh =(ImageView) findViewById(R.id.img_pxh);
        img_vx =(ImageView) findViewById(R.id.img_vx);
        img_txh =(ImageView) findViewById(R.id.img_txh);
        img_nx =(ImageView) findViewById(R.id.img_nx);
        img_y =(ImageView) findViewById(R.id.img_y);
        img_kx =(ImageView) findViewById(R.id.img_kx);

        imgAyuda = (ImageView) findViewById(R.id.img_ayuda);
        imgAyuda.setOnClickListener(this);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);

         sonidos[0]= R.raw.c_palatales_px;
         sonidos[1]= R.raw.cons_basic_d;
         sonidos[2]= R.raw.cons_basic_c;
         sonidos[3]= R.raw.c_aspi_th;
         sonidos[4]= R.raw.cons_basic_m;
         sonidos[5]= R.raw.c_aspal_kxh;
         sonidos[6]= R.raw.c_palatales_bx;
         sonidos[7]= R.raw.cons_basic_s;
         sonidos[8]= R.raw.c_aspi_ch;
         sonidos[9]= R.raw.cons_basic_g;
         sonidos[10]= R.raw.c_aspal_pxh;
         sonidos[11]= R.raw.c_palatales_vx;
         sonidos[12]= R.raw.c_aspal_txh;
         sonidos[13]= R.raw.c_palatales_nx;
         sonidos[14]= R.raw.cons_basic_y;
         sonidos[15]= R.raw.c_palatales_kx;

        ic_play.setOnClickListener(this);
        img_px.setOnClickListener(this);
        img_d.setOnClickListener(this);
        img_c.setOnClickListener(this);
        img_th.setOnClickListener(this);
        img_m.setOnClickListener(this);
        img_kxh.setOnClickListener(this);
        img_bx.setOnClickListener(this);
        img_s.setOnClickListener(this);
        img_ch.setOnClickListener(this);
        img_g.setOnClickListener(this);
        img_pxh.setOnClickListener(this);
        img_vx.setOnClickListener(this);
        img_txh.setOnClickListener(this);
        img_nx.setOnClickListener(this);
        img_y.setOnClickListener(this);
        img_kx.setOnClickListener(this);
        loadPreference();
        cargarTextV();
        loadSplash();
    }

    private void loadSplash() {
        final Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        imgAyuda.startAnimation(zoomAnimation);
        Bundle b= new Bundle();
        b.putString("text_uno", "Selecciona la imagen de sonido");
        b.putString("text_dos", "y seleciona la consonante correspondiente");
        b.putInt("img_uno", R.drawable.con_play);
        b.putInt("img_dos", R.drawable.img_ch);
        Intent irActivity= new Intent(SonidoCorreActivity.this, SplashTodosActivity.class);
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
            if (i==16){
                Toast.makeText(this, "final", Toast.LENGTH_SHORT).show();

            }
    }else if (v.getId() == R.id.img_px){
            if (num==1 && i==0){
                i=i+1;
                img_px.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_d){
            if (i==1){
                i=i+1;
                img_d.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_c){
            if (i==2){
                i=i+1;
                img_c.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_th){
            if (i==3){
                i=i+1;
                img_th.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_m){
            if (i==4){
                i=i+1;
                img_m.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_kxh){
            if (i==5){
                i=i+1;
                img_kxh.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_bx){
            if (i==6){
                i=i+1;
                img_bx.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_s){
            if (i==7){
                i=i+1;
                img_s.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_ch){
            if (i==8){
                i=i+1;
                img_ch.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_g){
            if (i==9){
                i=i+1;
                img_g.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_pxh){
            if (i==10){
                i=i+1;
                img_pxh.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_vx){
            if (i==11){
                i=i+1;
                img_vx.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_txh){
            if (i==12){
                i=i+1;
                img_txh.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_nx){
            if (i==13){
                i=i+1;
                img_nx.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_y){
            if (i==14){
                i=i+1;
                img_y.setVisibility(View.INVISIBLE);
                cont_good=cont_good+1;
                cont_intentos=cont_intentos+1;
            }else {
                toast();
                cont_fail=cont_fail+1;
                cont_intentos=cont_intentos+1;
            }
        }else if (v.getId() == R.id.img_kx){
            if (i==15){
                i=i+1;
                img_kx.setVisibility(View.INVISIBLE);
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
        if (cont_good ==16) {
            Intent ir = new Intent(getApplication(), Nivel22Activity.class);
            startActivity(ir);
            finish();
        }if (cont_good==16 && cont_intentos ==16){
            Puntos puntos= new Puntos(id_user, 3);
            db.insertarPuntos(puntos);
            List<Puntos> pts=db.sumaPuntos(id_user);
            int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
            tv_puntos.setText(""+ p);
        }else if (cont_good==16 && (cont_intentos >16 || cont_intentos <13)){
            id_user =db.obtenerId(userName);
            Puntos puntos= new Puntos(id_user, 2);
            db.insertarPuntos(puntos);
            List<Puntos> pts=db.sumaPuntos(id_user);
            int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
            tv_puntos.setText(""+ p);
        }else if (cont_good==16 && (cont_intentos >=20 || cont_intentos <=25)){
            id_user =db.obtenerId(userName);
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            List<Puntos> pts=db.sumaPuntos(id_user);
            int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
            tv_puntos.setText(""+ p);
        }else if (cont_good<4 && cont_intentos >25){
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

