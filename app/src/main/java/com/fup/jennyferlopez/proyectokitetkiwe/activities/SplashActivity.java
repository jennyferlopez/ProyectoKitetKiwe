package com.fup.jennyferlopez.proyectokitetkiwe.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcinco.Nivel5Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.Nivel41Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.Nivel42Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.Nivel43Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.Nivel44Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.Nivel4Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel21Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel22Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel23Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel24Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel2Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelseis.Nivel6Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres.Nivel32Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres.Nivel3Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres.Niveles31Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres.Niveles33Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres.Niveles34Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.Niveles11Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.Niveles12Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.Niveles13Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.Niveles14Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

public class SplashActivity extends AppCompatActivity {

    Boolean logged;
    TextView tvSplash;
    int idUser;
    GestorBd db;
    String activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tvSplash=(TextView)findViewById(R.id.tvSplash);
        db=new GestorBd(getApplicationContext());

        String font_url ="font/dklemonyellowsun.otf";

        Typeface font = Typeface.createFromAsset(SplashActivity.this.getResources().getAssets(), font_url);
        tvSplash.setTypeface(font);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    loadLogin();
                }
            }
        };
        timerThread.start();
    }

    private void loadLogin() {
        SharedPreferences preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        logged =preferences.getBoolean(Preference.IS_LOGGED, false);

        if (logged){
            idUser=preferences.getInt(Preference.USER_ID, 0);
            activity= db.buscarActivity(idUser);
            Intent ir = null;
            if (activity.equals("MenuActivity")) {
                ir = new Intent(this, MenuActivity.class);
            }else if (activity.equals("NivelesActivity")) {
                ir = new Intent(this, NivelesActivity.class);
            }else if (activity.equals("Niveles11Activity")) {
                ir = new Intent(this, Niveles11Activity.class);
            }else if (activity.equals("Niveles12Activity")) {
                ir = new Intent(this, Niveles12Activity.class);
            }else if (activity.equals("Niveles13Activity")) {
                ir = new Intent(this, Niveles13Activity.class);
            }else if (activity.equals("Niveles14Activity")) {
                ir = new Intent(this, Niveles14Activity.class);
            }else if (activity.equals("Nivel2Activity")) {
                ir = new Intent(this, Nivel2Activity.class);
            }else if (activity.equals("Nivel21Activity")) {
                ir = new Intent(this, Nivel21Activity.class);
            }else if (activity.equals("Nivel22Activity")) {
                ir = new Intent(this, Nivel22Activity.class);
            }else if (activity.equals("Nivel23Activity")) {
                ir = new Intent(this, Nivel23Activity.class);
            }else if (activity.equals("Nivel24Activity")) {
                ir = new Intent(this, Nivel24Activity.class);
            }else if (activity.equals("Nivel3Activity")) {
                ir = new Intent(this, Nivel3Activity.class);
            }else if (activity.equals("Niveles31Activity")) {
                ir = new Intent(this, Niveles31Activity.class);
            }else if (activity.equals("Nivel32Activity")) {
                ir = new Intent(this, Nivel32Activity.class);
            }else if (activity.equals("Niveles33Activity")) {
                ir = new Intent(this, Niveles33Activity.class);
            }else if (activity.equals("Niveles34Activity")) {
                ir = new Intent(this, Niveles34Activity.class);
            }else if (activity.equals("Nivel4Activity")) {
                ir = new Intent(this, Nivel4Activity.class);
            }else if (activity.equals("Nivel41Activity")) {
                ir = new Intent(this, Nivel41Activity.class);
            }else if (activity.equals("Nivel42Activity")) {
                ir = new Intent(this, Nivel42Activity.class);
            }else if (activity.equals("Nivel43Activity")) {
                ir = new Intent(this, Nivel43Activity.class);
            }else if (activity.equals("Nivel44Activity")) {
                ir = new Intent(this, Nivel44Activity.class);
            }else if (activity.equals("Nivel5Activity")) {
                ir = new Intent(this, Nivel5Activity.class);
            }else if (activity.equals("Nivel6Activity")) {
                ir = new Intent(this, Nivel6Activity.class);
            }
            startActivity(ir);
        }else {
            Intent ir=new Intent(this, LogingActivity.class);
            startActivity(ir);
            finish();
        }

    }
}
