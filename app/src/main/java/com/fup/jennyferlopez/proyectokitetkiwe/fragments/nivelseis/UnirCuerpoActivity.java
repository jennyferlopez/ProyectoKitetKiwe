package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelseis;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.activities.SplashTodosActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.LaberintoActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.Niveles12Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

public class UnirCuerpoActivity extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles;
    TextView tv_title;
    private int modificarX = 0;
    private int modificary = 0;
    boolean detectView;
    float x, x1, y, y1, h, h1, l, l1;
    float xi, xi1, yi, yi1, hi, hi1, li, li1;
    float xm, xm1, ym, ym1, hm, hm1, lm, lm1;
    float xg, xg1, yg, yg1, hg, hg1, lg, lg1;

    GestorBd db;

    ImageView  manoDerUnir, manoIzqUnir, pieDerUnir, pieIzqUnir, imgAyuda;
    int cont_intentos = 0, cont_good = 0, cont_fail = 0, id_user;
    float temp_x, temp_y;
    List<Puntos> pts;
    TextView manoIzq, pieIzq, pieDer,manoDer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unir_cuerpo);

        db = new GestorBd(getApplication());

        manoDer = (TextView) findViewById(R.id.manoDer);
        manoIzq = (TextView) findViewById(R.id.manoIzq);
        pieIzq = (TextView) findViewById(R.id.pieIzq);
        pieDer = (TextView) findViewById(R.id.pieDer);
        manoDerUnir = (ImageView) findViewById(R.id.manoDerUnir);
        manoIzqUnir = (ImageView) findViewById(R.id.manoIzqUnir);
        pieDerUnir = (ImageView) findViewById(R.id.pieDerUnir);
        pieIzqUnir = (ImageView) findViewById(R.id.pieIzqUnir);
        manoDerUnir.setVisibility(View.INVISIBLE);
        manoIzqUnir.setVisibility(View.INVISIBLE);
        pieDerUnir.setVisibility(View.INVISIBLE);
        pieIzqUnir.setVisibility(View.INVISIBLE);

        manoDer.setOnTouchListener(handlerMover);
        manoDer.setOnLongClickListener(detect);

        manoIzq.setOnTouchListener(handlerMover);
        manoIzq.setOnLongClickListener(detect);

        pieIzq.setOnTouchListener(handlerMover);
        pieIzq.setOnLongClickListener(detect);

        pieDer.setOnTouchListener(handlerMover);
        pieDer.setOnLongClickListener(detect);


        tv_title = (TextView) findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        String font_url = "font/dklemonyellowsun.otf";
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
        b.putString("text_uno", "Con click sostenido arrastra la palabra a la parte de cuerpo correspondiente");
        b.putString("text_dos", "");
        b.putInt("img_uno", 0);
        b.putInt("img_dos", R.drawable.mano_izq);
        Intent irActivity= new Intent(UnirCuerpoActivity.this, SplashTodosActivity.class);
        irActivity.putExtras(b);
        startActivity(irActivity);
    }


    private void loadPreference() {
        preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        avatarSeleccionado = preferences.getString(Preference.AVATAR_SEECCIONADO, "");
        userName = preferences.getString(Preference.USER_NAME, "");

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

    View.OnLongClickListener detect = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            detectView = true;
            return false;
        }
    };
    View.OnTouchListener handlerMover = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {
            PointF DownPT = new PointF();
            PointF StartPT = new PointF();

            x = manoDerUnir.getX();
            y = manoDerUnir.getY();
            h = manoDerUnir.getWidth();
            l = manoDerUnir.getHeight();

            xi = manoIzqUnir.getX();
            yi = manoIzqUnir.getY();
            hi = manoIzqUnir.getWidth();
            li = manoIzqUnir.getHeight();

            xm = pieDerUnir.getX();
            ym = pieDerUnir.getY();
            hm = pieDerUnir.getWidth();
            lm = pieDerUnir.getHeight();

            xg = pieIzqUnir.getX();
            yg = pieIzqUnir.getY();
            hg = pieIzqUnir.getWidth();
            lg = pieIzqUnir.getHeight();

            int eid = motionEvent.getAction();

            switch (eid) {
                case MotionEvent.ACTION_MOVE:
                    if (detectView) {
                        StartPT = new PointF(v.getX(), v.getY());
                        PointF mv = new PointF(motionEvent.getX() - DownPT.x, motionEvent.getY() - DownPT.y);
                        v.setX((StartPT.x + mv.x) - modificarX);
                        v.setY((StartPT.y + mv.y) - modificary);
                    }

                    break;
                case MotionEvent.ACTION_DOWN:
                    DownPT.x = motionEvent.getX();
                    DownPT.y = motionEvent.getY();
                    int contT = 0;
                    if (contT == 0) {
                        int id = v.getId();
                        if (id == R.id.manoDer) {
                            temp_x = DownPT.x = manoDer.getX();
                            temp_y = DownPT.y = manoDer.getY();
                            contT = contT + 1;
                        } else if (id == R.id.manoIzq) {
                            temp_x = DownPT.x = manoIzq.getX();
                            temp_y = DownPT.y = manoIzq.getY();
                            contT = contT + 1;
                        } else if (id == R.id.pieIzq) {
                            temp_x = DownPT.x = pieIzq.getX();
                            temp_y = DownPT.y = pieIzq.getY();
                            contT = contT + 1;
                        } else if (id == R.id.pieDer) {
                            temp_x = DownPT.x = pieDer.getX();
                            temp_y = DownPT.y = pieDer.getY();
                            contT = contT + 1;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:

                    switch (v.getId()) {
                        case R.id.manoDer:
                            x1 = v.getX();
                            y1 = v.getY();
                            h1 = v.getWidth();
                            l1 = v.getHeight();

                            if ((x >= x1 && x <= (x1 + h1) && y >= y1 && y <= (y1 + l1)) || ((x + h) >= x1 && x <= (x1 + h1) && y >= y1 && y <= (y1 + l1))
                                    || (x >= x1 && x <= (x1 + h1) && (y + l) >= y1 && (y + l) <= (y1 + l1)) || ((x + h) >= x1 && (x + h) <= (x1 + h1) && (y + l) >= y1 && (y + l) <= (y1 + l1))) {
                                toastSuccess();

                                manoDerUnir.setVisibility(View.VISIBLE);
                                manoDer.setVisibility(View.INVISIBLE);
                                cont_good = cont_good + 1;
                                cont_intentos = cont_intentos + 1;
                            } else {
                                toastFail();
                                manoDer.setX(temp_x);
                                manoDer.setY(temp_y);
                                cont_fail = cont_fail + 1;
                                cont_intentos = cont_intentos + 1;
                            }
                            break;

                    }
                    switch (v.getId()) {
                        case R.id.manoIzq:
                            xi1 = v.getX();
                            yi1 = v.getY();
                            hi1 = v.getWidth();
                            li1 = v.getHeight();

                            if ((xi >= xi1 && xi <= (xi1 + hi1) && yi >= yi1 && yi <= (yi1 + li1)) || ((xi + hi) >= xi1 && xi <= (xi1 + hi1) && yi >= yi1 && yi <= (yi1 + li1))
                                    || (xi >= xi1 && xi <= (xi1 + hi1) && (yi + li) >= yi1 && (yi + li) <= (yi1 + li1)) || ((xi + hi) >= xi1 && (xi + hi) <= (xi1 + hi1) && (yi + li) >= yi1 && (yi + li) <= (yi1 + li1))) {
                                toastSuccess();
                                manoIzqUnir.setVisibility(View.VISIBLE);
                                manoIzq.setVisibility(View.INVISIBLE);
                                cont_good = cont_good + 1;
                                cont_intentos = cont_intentos + 1;
                            } else {
                                toastFail();
                                manoIzq.setX(temp_x);
                                manoIzq.setY(temp_y);
                                cont_fail = cont_fail + 1;
                                cont_intentos = cont_intentos + 1;
                            }
                            break;

                    }
                    switch (v.getId()) {
                        case R.id.pieIzq:
                            xm1 = v.getX();
                            ym1 = v.getY();
                            hm1 = v.getWidth();
                            lm1 = v.getHeight();

                            if ((xm >= xm1 && xm <= (xm1 + hm1) && ym >= ym1 && ym <= (ym1 + lm1)) || ((xm + hm) >= xm1 && xm <= (xm1 + hm1) && ym >= ym1 && ym <= (ym1 + lm1))
                                    || (xm >= xm1 && xm <= (xm1 + hm1) && (ym + lm) >= ym1 && (ym + lm) <= (ym1 + lm1)) || ((xm + hm) >= xm1 && (xm + hm) <= (xm1 + hm1) && (ym + lm) >= ym1 && (ym + lm) <= (ym1 + lm1))) {
                                toastSuccess();

                                pieIzqUnir.setVisibility(View.VISIBLE);
                                pieIzq.setVisibility(View.INVISIBLE);
                                cont_good = cont_good + 1;
                                cont_intentos = cont_intentos + 1;
                            } else {
                                toastFail();
                                pieIzq.setX(temp_x);
                                pieIzq.setY(temp_y);
                                cont_fail = cont_fail + 1;
                                cont_intentos = cont_intentos + 1;
                            }
                            break;

                    }
                    switch (v.getId()) {
                        case R.id.pieDer:
                            xg1 = v.getX();
                            yg1 = v.getY();
                            hg1 = v.getWidth();
                            lg1 = v.getHeight();

                            if ((xg >= xg1 && xg <= (xg1 + hg1) && yg >= yg1 && yg <= (yg1 + lg1)) || ((xg + hg) >= xg1 && xg <= (xg1 + hg1) && yg >= yg1 && yg <= (yg1 + lg1))
                                    || (xg >= xg1 && xg <= (xg1 + hg1) && (yg + lg) >= yg1 && (yg + lg) <= (yg1 + lg1)) || ((xg + hg) >= xg1 && (xg + hg) <= (xg1 + hg1) && (yg + lg) >= yg1 && (yg + lg) <= (yg1 + lg1))) {
                                toastSuccess();
                                pieDerUnir.setVisibility(View.VISIBLE);
                                pieDer.setVisibility(View.INVISIBLE);
                                cont_good = cont_good + 1;
                                cont_intentos = cont_intentos + 1;
                            } else {
                                toastFail();
                                pieDer.setX(temp_x);
                                pieDer.setY(temp_y);
                                cont_fail = cont_fail + 1;
                                cont_intentos = cont_intentos + 1;
                            }
                            break;
                    }
                    detectView = false;
                    break;

                default:
                    break;

            }
            cargarPuntos();
            return false;
        }

        private void cargarPuntos() {
            if (cont_good == 4) {
                Intent irMenu = new Intent(getApplication(), Nivel62Activity.class);
                startActivity(irMenu);
                finish();
            }
            if (cont_good == 4 && cont_intentos == 4) {
                Puntos puntos = new Puntos(id_user, 3);
                db.insertarPuntos(puntos);
                List<Puntos> pts = db.sumaPuntos(id_user);
                int p = Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
                tv_puntos.setText("" + p);
            } else if (cont_good == 4 && (cont_intentos > 4 || cont_intentos < 7)) {
                id_user = db.obtenerId(userName);
                Puntos puntos = new Puntos(id_user, 2);
                db.insertarPuntos(puntos);
                List<Puntos> pts = db.sumaPuntos(id_user);
                int p = Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
                tv_puntos.setText("" + p);
            } else if (cont_good == 4 && (cont_intentos >= 7 || cont_intentos <= 10)) {
                id_user = db.obtenerId(userName);
                Puntos puntos = new Puntos(id_user, 1);
                db.insertarPuntos(puntos);
                List<Puntos> pts = db.sumaPuntos(id_user);
                int p = Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
                tv_puntos.setText("" + p);
            } else if (cont_good < 4 && cont_intentos > 10) {
                toastWarning();
            }
        }
    };

    private void cargarTextV() {
        id_user = db.obtenerId(userName);
        List<Puntos> pts = db.sumaPuntos(id_user);
        pts = db.sumaPuntos(id_user);
        int p = Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
        tv_puntos.setText("" + p);
    }

    private void toastWarning() {
        Toast toasta = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_personalizado,
                (ViewGroup) findViewById(R.id.lytLayout));

        TextView txtMsg = (TextView) layout.findViewById(R.id.tvMsjToast);
        ImageView imgToast = (ImageView) layout.findViewById(R.id.imgToast);
        imgToast.setBackgroundResource(R.drawable.toast_warning);
        txtMsg.setText("te recomendamos volver al nivel de reconocimiento tienes " + cont_intentos + "fallidos");
        String font_url = "font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        txtMsg.setTypeface(font);
        toasta.setDuration(Toast.LENGTH_SHORT);
        toasta.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        toasta.setView(layout);
        toasta.show();
    }

    private void toastFail() {
        Toast toasta = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_personalizado,
                (ViewGroup) findViewById(R.id.lytLayout));

        TextView txtMsg = (TextView) layout.findViewById(R.id.tvMsjToast);
        ImageView imgToast = (ImageView) layout.findViewById(R.id.imgToast);
        imgToast.setBackgroundResource(R.drawable.toast_fail);
        txtMsg.setText(R.string.toast_fail);
        String font_url = "font/dklemonyellowsun.otf";
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

        TextView txtMsg = (TextView) layout.findViewById(R.id.tvMsjToast);
        ImageView imgToast = (ImageView) layout.findViewById(R.id.imgToast);
        imgToast.setBackgroundResource(R.drawable.toast_good);
        txtMsg.setText(R.string.col_good);
        String font_url = "font/dklemonyellowsun.otf";
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
        }
    }
}
