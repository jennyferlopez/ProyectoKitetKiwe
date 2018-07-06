package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PointF;
import android.graphics.Typeface;
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
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ServicioUsuario;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class GlobosActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView  tv_puntos;
    ImageView icAvatarNiveles, imgAyuda;
    TextView tv_title;

    ImageView vcg_a, vcg_e,vcg_i, vcg_u, vng_a, vng_e, vng_i, vng_u, v_uno, v_dos, v_tres, img_canasta;
    int cont_intentos=0, cont_good=0, cont_fail=0, id_user;

    private int modificarX=0;
    private int modificary=0;
    boolean detectView;
    float vca_x, vca_x1, vca_y, vca_y1, vca_h,  vca_l, vca_l1;
    float vce_x1, vce_y1, vce_l, vce_l1;
    float vci_x1, vci_y1, vci_h, vci_l1;
    float  vcu_x1, vcu_y1, vcu_h, vcu_l1;
    float  vna_x1, vna_y1, vna_h, vna_l1;
    float  vne_x1, vne_y1, vne_h, vne_l1;
    float  vni_x1, vni_y1, vni_h, vni_l1;
    float  vnu_x1, vnu_y1, vnu_h, vnu_l1;
    float temp_x, temp_y;

    ServicioUsuario servicioUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_globos);

        tv_title = (TextView) findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        imgAyuda = (ImageView) findViewById(R.id.img_ayuda);
        imgAyuda.setOnClickListener(this);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);

        vcg_a= (ImageView) findViewById(R.id.globo_siete);
        vcg_e= (ImageView) findViewById(R.id.globo_cinco);
        vcg_i= (ImageView) findViewById(R.id.globo_uno);
        vcg_u= (ImageView) findViewById(R.id.globo_seis);
        vng_a= (ImageView) findViewById(R.id.globo_nueve);
        vng_e= (ImageView) findViewById(R.id.globo_cuatro);
        vng_i= (ImageView) findViewById(R.id.globo_tres);
        vng_u= (ImageView) findViewById(R.id.globo_dos);
        v_uno= (ImageView) findViewById(R.id.globo_ocho);
        v_dos= (ImageView) findViewById(R.id.globo_diez);
        v_tres= (ImageView) findViewById(R.id.globo_once);
        img_canasta= (ImageView) findViewById(R.id.img_canasta);

        vcg_a.setOnTouchListener(handlerMover);
        vcg_a.setOnLongClickListener(detect);

        vcg_e.setOnTouchListener(handlerMover);
        vcg_e.setOnLongClickListener(detect);

        vcg_i.setOnTouchListener(handlerMover);
        vcg_i.setOnLongClickListener(detect);

        vcg_u.setOnTouchListener(handlerMover);
        vcg_u.setOnLongClickListener(detect);

        vng_a.setOnTouchListener(handlerMover);
        vng_a.setOnLongClickListener(detect);

        vng_e.setOnTouchListener(handlerMover);
        vng_e.setOnLongClickListener(detect);

        vng_i.setOnTouchListener(handlerMover);
        vng_i.setOnLongClickListener(detect);

        vng_u.setOnTouchListener(handlerMover);
        vng_u.setOnLongClickListener(detect);

        v_uno.setOnTouchListener(handlerMover);
        v_uno.setOnLongClickListener(detect);

        v_dos.setOnTouchListener(handlerMover);
        v_dos.setOnLongClickListener(detect);

        v_tres.setOnTouchListener(handlerMover);
        v_tres.setOnLongClickListener(detect);
        loadPreference();
        loadSplash();
        loadRealm();
        loadPuntos();
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

    private void loadSplash() {
        final Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        imgAyuda.startAnimation(zoomAnimation);
        Bundle b= new Bundle();
        b.putString("text_uno", "Con click sostenido arrastra el globo con las vocales con cierre glotal y las vocales nasales con cierre glotal");
        b.putString("text_dos", "hacia el centro de la canasta");
        b.putInt("img_uno", R.drawable.globo_ncga);
        b.putInt("img_dos", R.drawable.canasta_globos);
        Intent irActivity= new Intent(GlobosActivity.this, SplashTodosActivity.class);
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

            vca_x =img_canasta.getX();
            vca_y=img_canasta.getY();
            vca_h=img_canasta.getWidth();
            vca_l=img_canasta.getHeight();

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
                        if (id==R.id.globo_siete){
                            temp_x = DownPT.x=vcg_a.getX();
                            temp_y = DownPT.y=vcg_a.getY();
                            contT=contT+1;
                        }else if (id==R.id.globo_cinco){
                            temp_x = DownPT.x=vcg_e.getX();
                            temp_y = DownPT.y=vcg_e.getY();
                            contT=contT+1;
                        }else if (id==R.id.globo_uno){
                            temp_x = DownPT.x=vcg_i.getX();
                            temp_y = DownPT.y=vcg_i.getY();
                            contT=contT+1;
                        }else if (id==R.id.globo_seis){
                            temp_x = DownPT.x=vcg_u.getX();
                            temp_y = DownPT.y=vcg_u.getY();
                            contT=contT+1;
                        }else if (id==R.id.globo_nueve){
                            temp_x = DownPT.x=vng_a.getX();
                            temp_y = DownPT.y=vng_a.getY();
                            contT=contT+1;
                        }else if (id==R.id.globo_cuatro){
                            temp_x = DownPT.x=vng_e.getX();
                            temp_y = DownPT.y=vng_e.getY();
                            contT=contT+1;
                        }else if (id==R.id.globo_tres){
                            temp_x = DownPT.x=vng_i.getX();
                            temp_y = DownPT.y=vng_i.getY();
                            contT=contT+1;
                        }else if (id==R.id.globo_dos){
                            temp_x = DownPT.x=vng_u.getX();
                            temp_y = DownPT.y=vng_u.getY();
                            contT=contT+1;
                        }else if (id==R.id.globo_ocho){
                            temp_x = DownPT.x=v_uno.getX();
                            temp_y = DownPT.y=v_uno.getY();
                            contT=contT+1;
                        }else if (id==R.id.globo_diez){
                            temp_x = DownPT.x=v_dos.getX();
                            temp_y = DownPT.y=v_dos.getY();
                            contT=contT+1;
                        }else if (id==R.id.globo_once){
                            temp_x = DownPT.x=v_tres.getX();
                            temp_y = DownPT.y=v_tres.getY();
                            contT=contT+1;
                        }

                    }
                    break;
                case MotionEvent.ACTION_UP:

                    switch (v.getId()){
                        case R.id.globo_siete:
                            vca_x1 =v.getX();
                            vca_y1=v.getY();
                           // vca_h1=v.getWidth();
                            vca_l1=v.getHeight();

                            if ((vca_x>=vca_x1  && vca_y >= vca_y1) || ((vca_x+vca_h)>=vca_x1  && vca_y >= vca_y1 && vca_y <= (vca_y1+vca_l1))
                                        || (vca_x>=vca_x1 && (vca_y+vca_l) >= vca_y1 && (vca_y+vca_l) <= (vca_y1+vca_l1)) || ((vca_x+vca_h)>=vca_x1 && (vca_y+vca_l) >= vca_y1 && (vca_y+vca_l) <= (vca_y1+vca_l1))){
                                toastSuccess();
                                vcg_a.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                vcg_a.setX(temp_x);
                                vcg_a.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;

                        case R.id.globo_cinco:
                            vce_x1 =v.getX();
                            vce_y1=v.getY();
                           // vce_h1=v.getWidth();
                            vce_l1=v.getHeight();

                            if ((vca_x>=vce_x1 && vca_y >= vce_y1 && vca_y <= (vce_y1+vce_l1)) || ((vca_x+vca_h)>=vce_x1  && vca_y >= vce_y1 && vca_y <= (vce_y1+vce_l1))
                                    || (vca_x>=vce_x1 && (vca_y+vca_l) >= vce_y1 && (vca_y+vca_l) <= (vce_y1+vce_l1)) || ((vca_x+vca_h)>=vce_x1 && (vca_y+vca_l) >= vce_y1 && (vca_y+vca_l) <= (vce_y1+vce_l1))){
                                toastSuccess();
                                vcg_e.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                vcg_e.setX(temp_x);
                                vcg_e.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;
                        case R.id.globo_uno:
                            vci_x1 =v.getX();
                            vci_y1=v.getY();
                            //vci_h1=v.getWidth();
                            vci_l1=v.getHeight();

                            if ((vca_x>=vci_x1  && vca_y >= vci_y1 && vca_y <= (vci_y1+vci_l1)) || ((vca_x+vci_h)>=vci_x1  && vca_y >= vci_y1 && vca_y <= (vci_y1+vci_l1))
                                    || (vca_x>=vci_x1  && (vca_y+vca_l) >= vci_y1 && (vca_y+vca_l) <= (vci_y1+vci_l1)) || ((vca_x+vca_h)>=vci_x1  && (vca_y+vca_l) >= vci_y1 && (vca_y+vca_l) <= (vci_y1+vci_l1))){
                                toastSuccess();
                                vcg_i.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                vcg_i.setX(temp_x);
                                vcg_i.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;
                        case R.id.globo_seis:
                            vcu_x1 =v.getX();
                            vcu_y1=v.getY();
                            //vci_h1=v.getWidth();
                            vcu_l1=v.getHeight();

                            if ((vca_x>=vcu_x1  && vca_y >= vcu_y1 && vca_y <= (vcu_y1+vcu_l1)) || ((vca_x+vcu_h)>=vcu_x1  && vca_y >= vcu_y1 && vca_y <= (vcu_y1+vcu_l1))
                                    || (vca_x>=vcu_x1  && (vca_y+vca_l) >= vcu_y1 && (vca_y+vca_l) <= (vcu_y1+vcu_l1)) || ((vca_x+vca_h)>=vcu_x1  && (vca_y+vca_l) >= vcu_y1 && (vca_y+vca_l) <= (vcu_y1+vcu_l1))){
                                toastSuccess();
                                vcg_u.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                vcg_u.setX(temp_x);
                                vcg_u.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;
                        case R.id.globo_nueve:
                            vna_x1 =v.getX();
                            vna_y1=v.getY();
                            // vca_h1=v.getWidth();
                            vna_l1=v.getHeight();

                            if ((vca_x>=vna_x1  && vca_y >= vna_y1 && vca_y <= (vna_y1+vna_l1)) || ((vca_x+vna_h)>=vna_x1  && vca_y >= vna_y1 && vca_y <= (vna_y1+vna_l1))
                                    || (vca_x>=vna_x1  && (vca_y+vca_l) >= vna_y1 && (vca_y+vca_l) <= (vna_y1+vna_l1)) || ((vca_x+vca_h)>=vna_x1  && (vca_y+vca_l) >= vna_y1 && (vca_y+vca_l) <= (vna_y1+vna_l1))){
                                toastSuccess();
                                vng_a.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                vng_a.setX(temp_x);
                                vng_a.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;

                        case R.id.globo_cuatro:
                            vne_x1 =v.getX();
                            vne_y1=v.getY();
                            // vce_h1=v.getWidth();
                            vne_l1=v.getHeight();

                            if ((vca_x>=vne_x1 && vca_y >= vne_y1 && vca_y <= (vne_y1+vne_l1)) || ((vca_x+vca_h)>=vne_x1  && vca_y >= vne_y1 && vca_y <= (vne_y1+vne_l1))
                                    || (vca_x>=vne_x1 && (vca_y+vca_l) >= vne_y1 && (vca_y+vca_l) <= (vne_y1+vne_l1)) || ((vca_x+vca_h)>=vne_x1 && (vca_y+vca_l) >= vne_y1 && (vca_y+vca_l) <= (vne_y1+vne_l1))){
                                toastSuccess();
                                vng_e.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                vng_e.setX(temp_x);
                                vng_e.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;
                        case R.id.globo_tres:
                            vni_x1 =v.getX();
                            vni_y1=v.getY();
                            //vci_h1=v.getWidth();
                            vni_l1=v.getHeight();

                            if ((vca_x>=vni_x1  && vca_y >= vni_y1 && vca_y <= (vni_y1+vni_l1)) || ((vca_x+vni_h)>=vni_x1  && vca_y >= vni_y1 && vca_y <= (vni_y1+vni_l1))
                                    || (vca_x>=vni_x1  && (vca_y+vca_l) >= vni_y1 && (vca_y+vca_l) <= (vni_y1+vni_l1)) || ((vca_x+vca_h)>=vni_x1  && (vca_y+vca_l) >= vni_y1 && (vca_y+vca_l) <= (vni_y1+vni_l1))){
                                toastSuccess();
                                vng_i.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                vng_i.setX(temp_x);
                                vng_i.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;
                        case R.id.globo_dos:
                            vnu_x1 =v.getX();
                            vnu_y1=v.getY();
                            //vci_h1=v.getWidth();
                            vnu_l1=v.getHeight();

                            if ((vca_x>=vnu_x1  && vca_y >= vnu_y1 && vca_y <= (vnu_y1+vnu_l1)) || ((vca_x+vnu_h)>=vnu_x1  && vca_y >= vnu_y1 && vca_y <= (vnu_y1+vnu_l1))
                                    || (vca_x>=vnu_x1  && (vca_y+vca_l) >= vnu_y1 && (vca_y+vca_l) <= (vnu_y1+vnu_l1)) || ((vca_x+vca_h)>=vnu_x1  && (vca_y+vca_l) >= vnu_y1 && (vca_y+vca_l) <= (vnu_y1+vnu_l1))){
                                toastSuccess();
                                vng_u.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                vng_u.setX(temp_x);
                                vng_u.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;
                        case R.id.globo_ocho:
                            v_uno.setX(temp_x);
                            v_uno.setY(temp_y);
                            cont_fail=cont_fail+1;
                            cont_intentos=cont_intentos+1;
                            break;
                        case R.id.globo_diez:
                            v_dos.setX(temp_x);
                            v_dos.setY(temp_y);
                            cont_fail=cont_fail+1;
                            cont_intentos=cont_intentos+1;
                            break;
                        case R.id.globo_once:
                            v_tres.setX(temp_x);
                            v_tres.setY(temp_y);
                            cont_fail=cont_fail+1;
                            cont_intentos=cont_intentos+1;
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
            if (cont_good ==8) {
                Intent irMenu = new Intent(getApplication(), Niveles13Activity.class);
                startActivity(irMenu);
                finish();
                }if (cont_good==8 && cont_intentos ==8){
                    SumarPuntos(3);
                    puntosGanados(3);
                }else if (cont_good==8 && (cont_intentos >8 || cont_intentos <11)){
                    SumarPuntos(2);
                    puntosGanados(2);
                }else if (cont_good==8 && (cont_intentos >=11 || cont_intentos <=14)){
                    SumarPuntos(1);
                    puntosGanados(1);
                }else if (cont_good<4 && cont_intentos >14){
                    toastWarning();
                }
        }
    };
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
    private void loadPuntos() {
        userName =preferences.getString(Preference.USER_NAME, "");
        User usuario_por_id = servicioUsuario.obtenerUsuarioPorId(userName);
        if (usuario_por_id!=null) {
            servicioUsuario.actualizaractivity(usuario_por_id,"VocalesColiActivity");
            int p=Integer.parseInt(String.valueOf(usuario_por_id.getPuntos()));
            tv_puntos.setText(""+ p);
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
    private void puntosGanados(int puntos) {
        Toast toasta = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_personalizado,
                (ViewGroup) findViewById(R.id.lytLayout));

        TextView txtMsg = (TextView)layout.findViewById(R.id.tvMsjToast);
        ImageView imgToast =(ImageView) layout.findViewById(R.id.imgToast);
        imgToast.setBackgroundResource(R.drawable.ic_oros);
        txtMsg.setText("Ganaste "+ puntos +" semillas");
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        txtMsg.setTypeface(font);
        toasta.setDuration(Toast.LENGTH_SHORT);
        toasta.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0,0);
        toasta.setView(layout);
        toasta.show();
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_ayuda) {
            loadSplash();
        }
    }
}
