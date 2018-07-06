package com.fup.jennyferlopez.proyectokitetkiwe.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.VocalesColiActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.ImagenR;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

public class SplashTodosActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences preferences;
    private String avatarSeleccionado, userName, pass;
    TextView  tv_instruccion,tv_instruccion_Dos;
    private ImageView icAvatarNiveles, primera, segunda;
    GestorBd db;
    int img_uno, img_dos;
    String textInstrucciones,textInstruccion_dos;
    String activity;
    ImagenR imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_todos);
        db=new GestorBd(getApplication());
        imagen= new ImagenR();
        int img=imagen.getImg_uno();
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        primera = (ImageView) findViewById(R.id.img_primera);
        segunda = (ImageView) findViewById(R.id.img_segunda);
        tv_instruccion = (TextView) findViewById(R.id.textInstruccion);
        tv_instruccion_Dos = (TextView) findViewById(R.id.textInstruccionDos);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_instruccion.setTypeface(font);
        tv_instruccion_Dos.setTypeface(font);
        loadPreference();
        cargarInstruccion();
    }
    private void cargarInstruccion() {
        Bundle b= this.getIntent().getExtras();
        assert b != null;
        tv_instruccion.setText(b.getString("text_uno"));
        tv_instruccion_Dos.setText(b.getString("text_dos"));
        primera.setBackgroundResource(b.getInt("img_uno"));
        segunda.setBackgroundResource(b.getInt("img_dos"));
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(6000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    finish();
                }
            }
        };
        timerThread.start();
    }

    private void loadPreference() {
        preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        avatarSeleccionado = preferences.getString(Preference.AVATAR_SEECCIONADO, "");
        userName =preferences.getString(Preference.USER_NAME, "");

        if (avatarSeleccionado.equals(null)) {
            icAvatarNiveles.setBackgroundResource(Integer.parseInt(null));
        } else if (avatarSeleccionado.equals("1")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_uno);
        } else if (avatarSeleccionado.equals("2")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_dos);
        } else if (avatarSeleccionado.equals("3")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_tres);
        } else if (avatarSeleccionado.equals("4")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_uno);
        } else if (avatarSeleccionado.equals("5")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_dos);
        } else if (avatarSeleccionado.equals("6")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_tres);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.correAvatar){
            Intent irColVocales= new Intent(this, VocalesColiActivity.class);
            startActivity(irColVocales);
            finish();
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent irMenu=new Intent(getApplication(), MenuActivity.class);
            startActivity(irMenu);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
