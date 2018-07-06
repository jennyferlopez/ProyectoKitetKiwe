package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres.ColorCorresActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.Niveles12Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

public class UnirNumerosUnoActivity extends AppCompatActivity  implements View.OnClickListener {

    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles, img_dos, txt_dos, img_cuatro, txt_cuatro, img_siete, txt_siete, img_uno, txt_uno, img_nueve, txt_nueve,imgAyuda;
    TextView tv_title;
    GestorBd db;
    private int modificarX=0;
    private int modificary=0;
    boolean detectView;
    float x, x1, y, y1, h,h1, l, l1;
    float xi, xi1, yi, yi1, hi,hi1, li, li1;
    float xm, xm1, ym, ym1, hm,hm1, lm, lm1;
    float xg, xg1, yg, yg1, hg,hg1, lg, lg1;
    float xn, xn1, yn, yn1, hn,hn1, ln, ln1;
    float temp_x, temp_y;
    int cont_intentos=0, cont_good=0, cont_fail=0, id_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unir_numeros_uno);

        db=new GestorBd(getApplication());

        img_uno= (ImageView) findViewById(R.id.img_uno);
        txt_uno= (ImageView) findViewById(R.id.txt_uno);
        img_dos= (ImageView) findViewById(R.id.img_dos);
        txt_dos= (ImageView) findViewById(R.id.txt_dos);
        img_cuatro= (ImageView) findViewById(R.id.img_cuatro);
        txt_cuatro= (ImageView) findViewById(R.id.txt_cuatro);
        img_siete= (ImageView) findViewById(R.id.img_siete);
        txt_siete= (ImageView) findViewById(R.id.txt_siete);
        img_nueve=(ImageView)findViewById(R.id.img_nueve);
        txt_nueve=(ImageView)findViewById(R.id.txt_nueve);

        img_uno.setOnTouchListener(handlerMover);
        img_uno.setOnLongClickListener(detect);

        img_dos.setOnTouchListener(handlerMover);
        img_dos.setOnLongClickListener(detect);

        img_cuatro.setOnTouchListener(handlerMover);
        img_cuatro.setOnLongClickListener(detect);

        img_siete.setOnTouchListener(handlerMover);
        img_siete.setOnLongClickListener(detect);

        img_nueve.setOnTouchListener(handlerMover);
        img_nueve.setOnLongClickListener(detect);

        tv_title = (TextView) findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);

        imgAyuda = (ImageView) findViewById(R.id.img_ayuda);
        imgAyuda.setOnClickListener(this);
        loadPreference();
        cargarTextV();
        loadSplash();
    }

    private void loadSplash() {
        final Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        imgAyuda.startAnimation(zoomAnimation);
        Bundle b= new Bundle();
        b.putString("text_uno", "Une con una linea la palabra ");
        b.putString("text_dos", "con el numero correspondiente");
        b.putInt("img_uno", R.drawable.txt_siete);
        b.putInt("img_dos", R.drawable.img_uno);
        Intent irActivity= new Intent(UnirNumerosUnoActivity.this, SplashTodosActivity.class);
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

            x=txt_uno.getX();
            y=txt_uno.getY();
            h=txt_uno.getWidth();
            l=txt_uno.getHeight();

            xi=txt_dos.getX();
            yi=txt_dos.getY();
            hi=txt_dos.getWidth();
            li=txt_dos.getHeight();

            xm=txt_cuatro.getX();
            ym=txt_cuatro.getY();
            hm=txt_cuatro.getWidth();
            lm=txt_cuatro.getHeight();

            xg=txt_siete.getX();
            yg=txt_siete.getY();
            hg=txt_siete.getWidth();
            lg=txt_siete.getHeight();

            xn=txt_nueve.getX();
            yn=txt_nueve.getY();
            hn=txt_nueve.getWidth();
            ln=txt_nueve.getHeight();

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
                        if (id==R.id.img_uno){
                            temp_x = DownPT.x=img_uno.getX();
                            temp_y = DownPT.y=img_uno.getY();
                            contT=contT+1;
                        }else if (id==R.id.img_dos){
                            temp_x = DownPT.x=img_dos.getX();
                            temp_y = DownPT.y=img_dos.getY();
                            contT=contT+1;
                        }else if (id==R.id.img_cuatro){
                            temp_x = DownPT.x=img_cuatro.getX();
                            temp_y = DownPT.y=img_cuatro.getY();
                            contT=contT+1;
                        }else if (id==R.id.img_siete){
                            temp_x = DownPT.x=img_siete.getX();
                            temp_y = DownPT.y=img_siete.getY();
                            contT=contT+1;
                        }else if (id==R.id.img_nueve){
                            temp_x = DownPT.x=img_nueve.getX();
                            temp_y = DownPT.y=img_nueve.getY();
                            contT=contT+1;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:

                    switch (v.getId()){
                        case R.id.img_uno:
                            x1=v.getX();
                            y1=v.getY();
                            h1=v.getWidth();
                            l1=v.getHeight();

                            if ((x>=x1 && x<=(x1+h1) && y >= y1 && y <= (y1+l1)) || ((x+h)>=x1 && x<=(x1+h1) && y >= y1 && y <= (y1+l1))
                                    || (x>=x1 && x<=(x1+h1) && (y+l) >= y1 && (y+l) <= (y1+l1)) || ((x+h)>=x1 && (x+h)<=(x1+h1) && (y+l) >= y1 && (y+l) <= (y1+l1))){

                                toastSuccess();
                                txt_uno.setImageResource(R.drawable.toast_good);
                                img_uno.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                img_uno.setX(temp_x);
                                img_uno.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;

                    }
                    switch (v.getId()){
                        case R.id.img_dos:
                            xi1=v.getX();
                            yi1=v.getY();
                            hi1=v.getWidth();
                            li1=v.getHeight();

                            if ((xi>=xi1 && xi<=(xi1+hi1) && yi >= yi1 && yi <= (yi1+li1)) || ((xi+hi)>=xi1 && xi<=(xi1+hi1) && yi >= yi1 && yi <= (yi1+li1))
                                    || (xi>=xi1 && xi<=(xi1+hi1) && (yi+li) >= yi1 && (yi+li) <= (yi1+li1)) || ((xi+hi)>=xi1 && (xi+hi)<=(xi1+hi1) && (yi+li) >= yi1 && (yi+li) <= (yi1+li1))){
                                toastSuccess();
                                txt_dos.setImageResource(R.drawable.toast_good);
                                img_dos.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                img_dos.setX(temp_x);
                                img_dos.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;

                    }
                    switch (v.getId()){
                        case R.id.img_cuatro:
                            xm1=v.getX();
                            ym1=v.getY();
                            hm1=v.getWidth();
                            lm1=v.getHeight();

                            if ((xm>=xm1 && xm<=(xm1+hm1) && ym >= ym1 && ym <= (ym1+lm1)) || ((xm+hm)>=xm1 && xm<=(xm1+hm1) && ym >= ym1 && ym <= (ym1+lm1))
                                    || (xm>=xm1 && xm<=(xm1+hm1) && (ym+lm) >= ym1 && (ym+lm) <= (ym1+lm1)) || ((xm+hm)>=xm1 && (xm+hm)<=(xm1+hm1) && (ym+lm) >= ym1 && (ym+lm) <= (ym1+lm1))){
                                toastSuccess();

                                txt_cuatro.setImageResource(R.drawable.toast_good);
                                img_cuatro.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                img_cuatro.setX(temp_x);
                                img_cuatro.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;

                    }
                    switch (v.getId()){
                        case R.id.img_siete:
                            xg1=v.getX();
                            yg1=v.getY();
                            hg1=v.getWidth();
                            lg1=v.getHeight();

                            if ((xg>=xg1 && xg<=(xg1+hg1) && yg >= yg1 && yg <= (yg1+lg1)) || ((xg+hg)>=xg1 && xg<=(xg1+hg1) && yg >= yg1 && yg <= (yg1+lg1))
                                    || (xg>=xg1 && xg<=(xg1+hg1) && (yg+lg) >= yg1 && (yg+lg) <= (yg1+lg1)) || ((xg+hg)>=xg1 && (xg+hg)<=(xg1+hg1) && (yg+lg) >= yg1 && (yg+lg) <= (yg1+lg1))){
                                toastSuccess();

                                txt_siete.setImageResource(R.drawable.toast_good);
                                img_siete.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                img_siete.setX(temp_x);
                                img_siete.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;
                    }
                    switch (v.getId()){
                        case R.id.img_nueve:
                            xn1=v.getX();
                            yn1=v.getY();
                            hn1=v.getWidth();
                            ln1=v.getHeight();

                            if ((xn>=xn1 && xn<=(xn1+hn1) && yn >= yn1 && yn <= (yn1+ln1)) || ((xn+hn)>=xn1 && xn<=(xn1+hn1) && yn >= yn1 && yn <= (yn1+ln1))
                                    || (xn>=xn1 && xn<=(xn1+hn1) && (yn+ln) >= yn1 && (yn+ln) <= (yn1+ln1)) || ((xn+hn)>=xn1 && (xn+hn)<=(xn1+hn1) && (yn+ln) >= yn1 && (yn+ln) <= (yn1+ln1))){
                                toastSuccess();

                                txt_nueve.setImageResource(R.drawable.toast_good);
                                img_nueve.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                img_nueve.setX(temp_x);
                                img_nueve.setY(temp_y);
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
            if (cont_good ==5) {
                Intent irMenu = new Intent(getApplication(), Nivel42Activity.class);
                startActivity(irMenu);
                finish();
            }if (cont_good==5 && cont_intentos ==5){
                Puntos puntos= new Puntos(id_user, 3);
                db.insertarPuntos(puntos);
                List<Puntos> pts=db.sumaPuntos(id_user);
                int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
                tv_puntos.setText(""+ p);
            }else if (cont_good==5 && (cont_intentos >5 || cont_intentos <8)){
                id_user =db.obtenerId(userName);
                Puntos puntos= new Puntos(id_user, 2);
                db.insertarPuntos(puntos);
                List<Puntos> pts=db.sumaPuntos(id_user);
                int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
                tv_puntos.setText(""+ p);
            }else if (cont_good==5 && (cont_intentos >=8 || cont_intentos <=11)){
                id_user =db.obtenerId(userName);
                Puntos puntos= new Puntos(id_user, 1);
                db.insertarPuntos(puntos);
                List<Puntos> pts=db.sumaPuntos(id_user);
                int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
                tv_puntos.setText(""+ p);
            }else if (cont_good<4 && cont_intentos >11){
                toastWarning();
            }
        }
    };

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


    public void irCuatroDos(View view) {
        Intent irCuatroDos=new Intent(this,Nivel42Activity.class);
        startActivity(irCuatroDos);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_ayuda) {
            loadSplash();
        }
    }
}
