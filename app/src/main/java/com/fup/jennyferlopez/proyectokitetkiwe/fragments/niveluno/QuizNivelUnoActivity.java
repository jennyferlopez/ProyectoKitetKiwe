package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno;

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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.activities.NivelesActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel2Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ServicioUsuario;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class QuizNivelUnoActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvPreguntaUno, tvPreguntaDos, tv_puntos;
    RadioGroup rgPreUno, rgPreDos;
    RadioButton rbOralesP, rbOralesN, rbOralesN2, rbOralesN3, rbNasalesP, rbNasalesN, rbNasalesN2, rbNasalesN3;
    GestorBd db;
    SharedPreferences preferences;
    String userName;
    int id_user, cont_good=0, cont_fail=0, cont_intentos=0;
    int conOB=0, conOM=0,conNB=0, conNM=0,conAB=0, conAM=0 ;
    TextView tvPreguntaTres, tvPreguntaCuatro;
    RadioGroup rgPreTres, rgPreCuatro;
    RadioButton rbAspirP, rbAspirN, rbAspirN2, rbAspirN3, rbAlargP, rbAlargN, rbAlargN2, rbAlargN3;
    //1321

    ServicioUsuario servicioUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_nivel_uno);
        db=new GestorBd(getApplication());

        tv_puntos =(TextView) findViewById(R.id.tv_puntos);
        tvPreguntaUno =(TextView) findViewById(R.id.pregUnoVoc);
        tvPreguntaDos =(TextView) findViewById(R.id.pregDosVoc);
        rgPreUno =(RadioGroup) findViewById(R.id.rgPreguntaUno);
        rgPreDos =(RadioGroup) findViewById(R.id.rgPreguntados);
        rbOralesP =(RadioButton) findViewById(R.id.rbOralesP);
        rbOralesN =(RadioButton) findViewById(R.id.rbOralesN);
        rbOralesN2 =(RadioButton) findViewById(R.id.rbOralesN2);
        rbOralesN3 =(RadioButton) findViewById(R.id.rbOralesN3);
        rbNasalesP =(RadioButton) findViewById(R.id.rbNasalesP);
        rbNasalesN =(RadioButton) findViewById(R.id.rbNasalesN);
        rbNasalesN2 =(RadioButton) findViewById(R.id.rbNasalesN2);
        rbNasalesN3 =(RadioButton) findViewById(R.id.rbNasalesN3);
        tvPreguntaTres =(TextView) findViewById(R.id.pregTresVoc);
        tvPreguntaCuatro =(TextView) findViewById(R.id.pregCuatroVoc);
        rgPreTres =(RadioGroup) findViewById(R.id.rgPreguntaTres);
        rgPreCuatro =(RadioGroup) findViewById(R.id.rgPreguntaCuatro);
        rbAspirP =(RadioButton) findViewById(R.id.rbAspirP);
        rbAspirN =(RadioButton) findViewById(R.id.rbAspirN);
        rbAspirN2 =(RadioButton) findViewById(R.id.rbAspirN2);
        rbAspirN3 =(RadioButton) findViewById(R.id.rbAspirN3);
        rbAlargP =(RadioButton) findViewById(R.id.rbAlargP);
        rbAlargN =(RadioButton) findViewById(R.id.rbAlargN);
        rbAlargN2 =(RadioButton) findViewById(R.id.rbAlargN2);
        rbAlargN3 =(RadioButton) findViewById(R.id.rbAlargN3);

        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tvPreguntaUno.setTypeface(font);
        tvPreguntaDos.setTypeface(font);
        tv_puntos.setTypeface(font);
        //rbOralesP.setTypeface(font);
        //rbOralesN.setTypeface(font);
        //rbOralesN2.setTypeface(font);
        //rbOralesN3.setTypeface(font);
        //rbNasalesP.setTypeface(font);
        //rbNasalesN.setTypeface(font);
        //rbNasalesN2.setTypeface(font);
        //rbNasalesN3.setTypeface(font);
        tvPreguntaTres.setTypeface(font);
        tvPreguntaCuatro.setTypeface(font);
        //rbAspirP.setTypeface(font);
        //rbAspirN.setTypeface(font);
        //rbAspirN2.setTypeface(font);
        //rbAspirN3.setTypeface(font);
        //rbAlargP.setTypeface(font);
        //rbAlargN.setTypeface(font);
        //rbAlargN2.setTypeface(font);
        //rbAlargN3.setTypeface(font);

        rbOralesP.setOnClickListener(this);
        rbOralesN.setOnClickListener(this);
        rbOralesN2.setOnClickListener(this);
        rbOralesN3.setOnClickListener(this);
        rbNasalesP.setOnClickListener(this);
        rbNasalesN.setOnClickListener(this);
        rbNasalesN2.setOnClickListener(this);
        rbNasalesN3.setOnClickListener(this);
        rbAspirP.setOnClickListener(this);
        rbAspirN.setOnClickListener(this);
        rbAspirN2.setOnClickListener(this);
        rbAspirN3.setOnClickListener(this);
        rbAlargP.setOnClickListener(this);
        rbAlargN.setOnClickListener(this);
        rbAlargN2.setOnClickListener(this);
        rbAlargN3.setOnClickListener(this);

        loadDatos();
        loadRealm();
        actualizarPuntos();
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

    private void loadDatos() {
        preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        userName =preferences.getString(Preference.USER_NAME, "");
        id_user =db.obtenerId(userName);
    }

    private void ocultarDatos() {
        tvPreguntaUno.setVisibility(View.INVISIBLE);
        tvPreguntaDos.setVisibility(View.INVISIBLE);
        rgPreUno.setVisibility(View.INVISIBLE);
        rgPreDos.setVisibility(View.INVISIBLE);
        rbOralesP.setVisibility(View.INVISIBLE);
        rbOralesN.setVisibility(View.INVISIBLE);
        rbOralesN2.setVisibility(View.INVISIBLE);
        rbOralesN3.setVisibility(View.INVISIBLE);
        rbNasalesP.setVisibility(View.INVISIBLE);
        rbNasalesN.setVisibility(View.INVISIBLE);
        rbNasalesN2.setVisibility(View.INVISIBLE);
        rbNasalesN3.setVisibility(View.INVISIBLE);
    }

    private void enabledOrales() {
        rbOralesP.setEnabled(false);
        rbOralesN.setEnabled(false);
        rbOralesN2.setEnabled(false);
        rbOralesN3.setEnabled(false);
        rgPreDos.setEnabled(false);
    }

    private void enabledNasales() {
        rbNasalesP.setEnabled(false);
        rbNasalesN.setEnabled(false);
        rbNasalesN2.setEnabled(false);
        rbNasalesN3.setEnabled(false);
        rgPreDos.setEnabled(false);
    }

    private void irActivity() {
        Intent irNivelDos=new Intent(this, Nivel2Activity.class);
        startActivity(irNivelDos);
        finish();
    }

    private void enabledAspiradas() {
        rbAspirP.setEnabled(false);
        rbAspirN.setEnabled(false);
        rbAspirN2.setEnabled(false);
        rbAspirN3.setEnabled(false);
    }

    private void enabledAlargadas() {
        rbAlargP.setEnabled(false);
        rbAlargN.setEnabled(false);
        rbAlargN2.setEnabled(false);
        rbAlargN3.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        int id =v.getId();
        if (id== R.id.rbOralesP){
            conOB=1;
            enabledOrales();
        }else if (id== R.id.rbOralesN || id== R.id.rbOralesN2 || id== R.id.rbOralesN3) {
            enabledOrales();
            cont_intentos=cont_intentos+1;
            conOM=1;
        }else if (id== R.id.rbNasalesP ){
            enabledNasales();
            conNB=1;
        }else if (id== R.id.rbNasalesN || id== R.id.rbNasalesN2 || id== R.id.rbNasalesN3){
            enabledNasales();
            cont_intentos=cont_intentos+1;
            conNM=1;
        }if (id== R.id.rbAspirP){
            enabledAspiradas();
            conAB=1;
        }else if (id== R.id.rbAspirN || id== R.id.rbAspirN2 || id== R.id.rbAspirN3) {
            enabledAspiradas();
            cont_intentos=cont_intentos+1;
            conAM=1;
        }else if (id== R.id.rbAlargP ){
            if (conOB==1 && conNB==1 && conAB==1){
                SumarPuntos(3);
                puntosGanados(3);
                irActivity();
            }
        }else if (id== R.id.rbAlargN || id== R.id.rbAlargN2 || id== R.id.rbAlargN3){
            enabledAlargadas();
            if ((conOM==1 && conNB==1 && conAB==1) || (conOB==1 && conNM==1 && conAB==1) || (conOB==1 && conNB==1 && conAM==1)) {
                SumarPuntos(2);
                puntosGanados(2);
                irActivity();
            }
            else {
                if (cont_intentos>=2){
                    Toast.makeText(this, "Quiz no superado debes repetir el nivel 1", Toast.LENGTH_LONG).show();
                    Intent irNivelDos=new Intent(this, NivelesActivity.class);
                    startActivity(irNivelDos);
                }
            }
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
    private void actualizarPuntos() {
        userName =preferences.getString(Preference.USER_NAME, "");
        User usuario_por_id = servicioUsuario.obtenerUsuarioPorId(userName);
        if (usuario_por_id!=null) {
            int p=Integer.parseInt(String.valueOf(usuario_por_id.getPuntos()));
            tv_puntos.setText(""+ p);
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
}
