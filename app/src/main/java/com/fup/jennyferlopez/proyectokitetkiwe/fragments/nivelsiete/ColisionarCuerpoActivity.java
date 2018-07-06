package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelsiete;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcinco.Nivel54Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.LaberintoActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

public class ColisionarCuerpoActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles;
    TextView tv_title;
    private int modificarX=0;
    private int modificary=0;
    boolean detectView;
    float x, x1, y, y1, h,h1, l, l1;
    float xi, xi1, yi, yi1, hi,hi1, li, li1;
    float xm, xm1, ym, ym1, hm,hm1, lm, lm1;
    float xg, xg1, yg, yg1, hg,hg1, lg, lg1;

    GestorBd db;

    ImageView img_a, img_e, img_i, img_u, snd_a, snd_e, snd_i, snd_u, imgAyuda;
    int cont_intentos=0, cont_good=0, cont_fail=0, id_user;
    float temp_x, temp_y;
    List<Puntos> pts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colisionar_cuerpo);
        db=new GestorBd(getApplication());

        img_a= (ImageView) findViewById(R.id.nv_col_a);
        img_e= (ImageView) findViewById(R.id.nv_col_e);
        img_i= (ImageView) findViewById(R.id.nv_col_i);
        img_u= (ImageView) findViewById(R.id.nv_col_u);
        snd_a= (ImageView) findViewById(R.id.ns_col_a);
        snd_e= (ImageView) findViewById(R.id.ns_col_e);
        snd_i= (ImageView) findViewById(R.id.ns_col_i);
        snd_u= (ImageView) findViewById(R.id.ns_col_u);

        snd_a.setOnClickListener(this);
        snd_e.setOnClickListener(this);
        snd_i.setOnClickListener(this);
        snd_u.setOnClickListener(this);

        img_a.setOnTouchListener(handlerMover);
        img_a.setOnLongClickListener(detect);

        img_e.setOnTouchListener(handlerMover);
        img_e.setOnLongClickListener(detect);

        img_i.setOnTouchListener(handlerMover);
        img_i.setOnLongClickListener(detect);

        img_u.setOnTouchListener(handlerMover);
        img_u.setOnLongClickListener(detect);


        tv_title = (TextView) findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);
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
        b.putString("text_uno", "Arrastra con click sostenidola palabra");
        b.putString("text_dos", "a la imagen correspondiente");
        b.putInt("img_uno", R.drawable.tx_espiral);
        b.putInt("img_dos", R.drawable.img_bandera);
        Intent irActivity= new Intent(ColisionarCuerpoActivity.this, SplashTodosActivity.class);
        irActivity.putExtras(b);
        startActivity(irActivity);
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

    View.OnLongClickListener detect= new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            detectView=true;
            return false;
        }
    };
    View.OnTouchListener handlerMover=new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {
            PointF DownPT= new PointF();
            PointF StartPT= new PointF();

            x=snd_a.getX();
            y=snd_a.getY();
            h=snd_a.getWidth();
            l=snd_a.getHeight();

            xi=snd_e.getX();
            yi=snd_e.getY();
            hi=snd_e.getWidth();
            li=snd_e.getHeight();

            xm=snd_i.getX();
            ym=snd_i.getY();
            hm=snd_i.getWidth();
            lm=snd_i.getHeight();

            xg=snd_u.getX();
            yg=snd_u.getY();
            hg=snd_u.getWidth();
            lg=snd_u.getHeight();

            int eid= motionEvent.getAction();

            switch (eid){
                case MotionEvent.ACTION_MOVE:
                    if (detectView){
                        StartPT = new PointF(v.getX(), v.getY());
                        PointF mv=new PointF(motionEvent.getX()-DownPT.x, motionEvent.getY()-DownPT.y);
                        v.setX((StartPT.x+mv.x)-modificarX);
                        v.setY((StartPT.y+mv.y)-modificary);
                    }

                    break;
                case MotionEvent.ACTION_DOWN:
                    DownPT.x=motionEvent.getX();
                    DownPT.y=motionEvent.getY();
                    int contT=0;
                    if (contT==0) {
                        int id=v.getId();
                        if (id==R.id.nv_col_a){
                            temp_x = DownPT.x=img_a.getX();
                            temp_y = DownPT.y=img_a.getY();
                            contT=contT+1;
                        }else if (id==R.id.nv_col_e){
                            temp_x = DownPT.x=img_e.getX();
                            temp_y = DownPT.y=img_e.getY();
                            contT=contT+1;
                        }else if (id==R.id.nv_col_i){
                            temp_x = DownPT.x=img_i.getX();
                            temp_y = DownPT.y=img_i.getY();
                            contT=contT+1;
                        }else if (id==R.id.nv_col_u){
                            temp_x = DownPT.x=img_u.getX();
                            temp_y = DownPT.y=img_u.getY();
                            contT=contT+1;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:

                    switch (v.getId()){
                        case R.id.nv_col_a:
                            x1=v.getX();
                            y1=v.getY();
                            h1=v.getWidth();
                            l1=v.getHeight();

                            if ((x>=x1 && x<=(x1+h1) && y >= y1 && y <= (y1+l1)) || ((x+h)>=x1 && x<=(x1+h1) && y >= y1 && y <= (y1+l1))
                                    || (x>=x1 && x<=(x1+h1) && (y+l) >= y1 && (y+l) <= (y1+l1)) || ((x+h)>=x1 && (x+h)<=(x1+h1) && (y+l) >= y1 && (y+l) <= (y1+l1))){

                                toastSuccess();
                                snd_a.setImageResource(R.drawable.toast_good);
                                img_a.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                img_a.setX(temp_x);
                                img_a.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;

                    }
                    switch (v.getId()){
                        case R.id.nv_col_e:
                            xi1=v.getX();
                            yi1=v.getY();
                            hi1=v.getWidth();
                            li1=v.getHeight();

                            if ((xi>=xi1 && xi<=(xi1+hi1) && yi >= yi1 && yi <= (yi1+li1)) || ((xi+hi)>=xi1 && xi<=(xi1+hi1) && yi >= yi1 && yi <= (yi1+li1))
                                    || (xi>=xi1 && xi<=(xi1+hi1) && (yi+li) >= yi1 && (yi+li) <= (yi1+li1)) || ((xi+hi)>=xi1 && (xi+hi)<=(xi1+hi1) && (yi+li) >= yi1 && (yi+li) <= (yi1+li1))){
                                toastSuccess();
                                snd_e.setImageResource(R.drawable.toast_good);
                                img_e.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                img_e.setX(temp_x);
                                img_e.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;

                    }
                    switch (v.getId()){
                        case R.id.nv_col_i:
                            xm1=v.getX();
                            ym1=v.getY();
                            hm1=v.getWidth();
                            lm1=v.getHeight();

                            if ((xm>=xm1 && xm<=(xm1+hm1) && ym >= ym1 && ym <= (ym1+lm1)) || ((xm+hm)>=xm1 && xm<=(xm1+hm1) && ym >= ym1 && ym <= (ym1+lm1))
                                    || (xm>=xm1 && xm<=(xm1+hm1) && (ym+lm) >= ym1 && (ym+lm) <= (ym1+lm1)) || ((xm+hm)>=xm1 && (xm+hm)<=(xm1+hm1) && (ym+lm) >= ym1 && (ym+lm) <= (ym1+lm1))){
                                toastSuccess();

                                snd_i.setImageResource(R.drawable.toast_good);
                                img_i.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                img_i.setX(temp_x);
                                img_i.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;

                    }
                    switch (v.getId()){
                        case R.id.nv_col_u:
                            xg1=v.getX();
                            yg1=v.getY();
                            hg1=v.getWidth();
                            lg1=v.getHeight();

                            if ((xg>=xg1 && xg<=(xg1+hg1) && yg >= yg1 && yg <= (yg1+lg1)) || ((xg+hg)>=xg1 && xg<=(xg1+hg1) && yg >= yg1 && yg <= (yg1+lg1))
                                    || (xg>=xg1 && xg<=(xg1+hg1) && (yg+lg) >= yg1 && (yg+lg) <= (yg1+lg1)) || ((xg+hg)>=xg1 && (xg+hg)<=(xg1+hg1) && (yg+lg) >= yg1 && (yg+lg) <= (yg1+lg1))){
                                toastSuccess();

                                snd_u.setImageResource(R.drawable.toast_good);
                                img_u.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                img_u.setX(temp_x);
                                img_u.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;
                    }
                    detectView=false;
                    break;

                default:
                    break;

            }
            cargarPuntos();
            return false;
        }

        private void cargarPuntos() {
            if (cont_good ==4) {
                Intent irMenu = new Intent(getApplication(), Nivel72Activity.class);
                startActivity(irMenu);
                finish();
            }if (cont_good==4 && cont_intentos ==4){
                Puntos puntos= new Puntos(id_user, 3);
                db.insertarPuntos(puntos);
                List<Puntos> pts=db.sumaPuntos(id_user);
                int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
                tv_puntos.setText(""+ p);
            }else if (cont_good==4 && (cont_intentos >4 || cont_intentos <7)){
                id_user =db.obtenerId(userName);
                Puntos puntos= new Puntos(id_user, 2);
                db.insertarPuntos(puntos);
                List<Puntos> pts=db.sumaPuntos(id_user);
                int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
                tv_puntos.setText(""+ p);
            }else if (cont_good==4 && (cont_intentos >=7 || cont_intentos <=10)){
                id_user =db.obtenerId(userName);
                Puntos puntos= new Puntos(id_user, 1);
                db.insertarPuntos(puntos);
                List<Puntos> pts=db.sumaPuntos(id_user);
                int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
                tv_puntos.setText(""+ p);
            }else if (cont_good<4 && cont_intentos >10){
               // toastWarning();
            }
        }
    };
    private void cargarTextV() {
        id_user =db.obtenerId(userName);
        List<Puntos> pts=db.sumaPuntos(id_user);
        pts=db.sumaPuntos(id_user);
        int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
        tv_puntos.setText(""+ p);
    }

    private void toastWarning() {
        Toast toasta = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_personalizado,
                (ViewGroup) findViewById(R.id.lytLayout));

        TextView txtMsg = (TextView)layout.findViewById(R.id.tvMsjToast);
        ImageView imgToast =(ImageView) layout.findViewById(R.id.imgToast);
        imgToast.setBackgroundResource(R.drawable.toast_warning);
        txtMsg.setText("te recomendamos volver al nivel de reconocimiento tienes "+ cont_intentos +"fallidos");
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        txtMsg.setTypeface(font);
        toasta.setDuration(Toast.LENGTH_SHORT);
        toasta.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0,0);
        toasta.setView(layout);
        toasta.show();
    }

    private void toastFail() {
        Toast toasta = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_personalizado,
                (ViewGroup) findViewById(R.id.lytLayout));

        TextView txtMsg = (TextView)layout.findViewById(R.id.tvMsjToast);
        ImageView imgToast =(ImageView) layout.findViewById(R.id.imgToast);
        imgToast.setBackgroundResource(R.drawable.toast_fail);
        txtMsg.setText(R.string.toast_fail);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        txtMsg.setTypeface(font);
        toasta.setDuration(Toast.LENGTH_SHORT);
        toasta.setView(layout);
        toasta.show();
    }

    public void toastSuccess() {
        Toast toasta = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_personalizado,
                (ViewGroup) findViewById(R.id.lytLayout));

        TextView txtMsg = (TextView)layout.findViewById(R.id.tvMsjToast);
        ImageView imgToast =(ImageView) layout.findViewById(R.id.imgToast);
        imgToast.setBackgroundResource(R.drawable.toast_good);
        txtMsg.setText(R.string.col_good);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        txtMsg.setTypeface(font);
        toasta.setDuration(Toast.LENGTH_SHORT);
        toasta.setView(layout);
        toasta.show();

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_ayuda) {
            loadSplash();
        }else if (v.getId() == R.id.ns_col_a) {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.blanco);
            mp.start();
        }else if (v.getId() == R.id.ns_col_e) {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.rojo);
            mp.start();
        }else if (v.getId() == R.id.ns_col_i) {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.azul);
            mp.start();
        }else if (v.getId() == R.id.ns_col_u) {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.negro);
            mp.start();
        }
    }
}
