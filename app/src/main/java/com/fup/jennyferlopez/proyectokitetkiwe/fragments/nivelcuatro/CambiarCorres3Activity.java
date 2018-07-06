
package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

public class CambiarCorres3Activity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles, img_e_r, img_cambiar_letras, img_uno, img_tres, img_seis;
    TextView tv_title;
    GestorBd db;
    int cont_intentos=0, cont_good=0, id_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_corres3);
        db=new GestorBd(getApplication());

        tv_title = (TextView) findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        img_e_r = (ImageView) findViewById(R.id.e_r);
        img_cambiar_letras = (ImageView) findViewById(R.id.img_seis_nc);
        img_uno = (ImageView) findViewById(R.id.k_r);
        img_tres = (ImageView) findViewById(R.id.tres);
        img_seis = (ImageView) findViewById(R.id.seis);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);

        img_e_r.setOnClickListener(this);
        img_tres.setOnClickListener(this);
        img_uno.setOnClickListener(this);
        img_seis.setOnClickListener(this);


        loadPreference();
        cargarTextV();
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


    private void cargarPuntos() {
        if (cont_good ==4) {
            Intent irMenu = new Intent(getApplication(), QuizFinal4Activity.class);
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

    @Override
    public void onClick(View v) {

        int id= v.getId();
        if (id==R.id.k_r){
            img_cambiar_letras.setBackgroundResource(R.drawable.img_diez_cr);
            Thread timerThread = new Thread(){
                public void run(){
                    try{
                        sleep(1200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }finally{
                        Intent ir=new Intent(getApplicationContext(), QuizFinal4Activity.class);
                        startActivity(ir);
                        finish();
                    }
                }
            };
            timerThread.start();
        }else {
            Toast.makeText(this, "Intentelo de nuevo!", Toast.LENGTH_SHORT).show();
        }

    }
}
