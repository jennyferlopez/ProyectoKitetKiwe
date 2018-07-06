package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelseis;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.activities.MenuActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcinco.Nivel5Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.Nivel4Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelsiete.Nivel7Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

public class QuizFinal6Activity extends AppCompatActivity implements View.OnClickListener{
    TextView tvPreguntaUno, tvPreguntaDos;
    RadioGroup rgPreUno, rgPreDos;
    RadioButton rbNumUnoP, rbNumUnoN, rbNumUnoN2, rbNumUnoN3, rbNumDosP, rbNumDosN, rbNumDosN2, rbNumDosN3;
    TextView tvPreguntaTres, tvPreguntaCuatro;
    RadioGroup rgPreTres, rgPreCuatro;
    RadioButton rbNumTresP, rbNumTresN, rbNumTresN2, rbNumTresN3, rbNumCuatroP, rbNumCuatroN, rbNumCuatroN2, rbNumCuatroN3;
    GestorBd db;
    SharedPreferences preferences;
    String userName;
    int id_user, cont_good=0, cont_fail=0, cont_intentos=0;
    int conCB=0, conCM=0,conDOB=0, conDOM=0,conDIB=0, conDIM=0 , conDUB=0, conDUM=0, conNum=0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_final6);
        db=new GestorBd(getApplication());

        tvPreguntaUno =(TextView)  findViewById(R.id.pregUnoNum);
        tvPreguntaDos =(TextView)  findViewById(R.id.pregDosNum);
        rgPreUno =(RadioGroup)  findViewById(R.id.rgPreguntaUno);
        rgPreDos =(RadioGroup)  findViewById(R.id.rgPreguntados);
        rbNumUnoP =(RadioButton)  findViewById(R.id.rbNumUnoP);
        rbNumUnoN =(RadioButton)  findViewById(R.id.rbNumUnoN);
        rbNumUnoN2 =(RadioButton)  findViewById(R.id.rbNumUnoN2);
        rbNumUnoN3 =(RadioButton)  findViewById(R.id.rbNumUnoN3);
        rbNumDosP =(RadioButton)  findViewById(R.id.rbNumDosP);
        rbNumDosN =(RadioButton)  findViewById(R.id.rbNumDosN);
        rbNumDosN2 =(RadioButton)  findViewById(R.id.rbNumDosN2);
        rbNumDosN3 =(RadioButton)  findViewById(R.id.rbNumDosN3);
        tvPreguntaTres =(TextView)  findViewById(R.id.pregTresNum);
        tvPreguntaCuatro =(TextView)  findViewById(R.id.pregCuatroNum);
        rgPreTres =(RadioGroup)  findViewById(R.id.rgPreguntaTres);
        rgPreCuatro =(RadioGroup)  findViewById(R.id.rgPreguntaCuatro);
        rbNumTresP =(RadioButton)  findViewById(R.id.rbNumTresP);
        rbNumTresN =(RadioButton)  findViewById(R.id.rbNumTresN);
        rbNumTresN2 =(RadioButton)  findViewById(R.id.rbNumTresN2);
        rbNumTresN3 =(RadioButton)  findViewById(R.id.rbNumTresN3);
        rbNumCuatroP =(RadioButton)  findViewById(R.id.rbNumCuatroP);
        rbNumCuatroN =(RadioButton)  findViewById(R.id.rbNumCuatroN);
        rbNumCuatroN2 =(RadioButton)  findViewById(R.id.rbNumCuatroN2);
        rbNumCuatroN3 =(RadioButton)  findViewById(R.id.rbNumCuatroN3);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tvPreguntaUno.setTypeface(font);
        tvPreguntaDos.setTypeface(font);
        tvPreguntaTres.setTypeface(font);
        tvPreguntaCuatro.setTypeface(font);

        rbNumUnoP.setOnClickListener(this);
        rbNumUnoN. setOnClickListener(this);
        rbNumUnoN2. setOnClickListener(this);
        rbNumUnoN3. setOnClickListener(this);
        rbNumDosP. setOnClickListener(this);
        rbNumDosN. setOnClickListener(this);
        rbNumDosN2. setOnClickListener(this);
        rbNumDosN3. setOnClickListener(this);
        rbNumTresP. setOnClickListener(this);
        rbNumTresN. setOnClickListener(this);
        rbNumTresN2. setOnClickListener(this);
        rbNumTresN3. setOnClickListener(this);
        rbNumCuatroP. setOnClickListener(this);
        rbNumCuatroN. setOnClickListener(this);
        rbNumCuatroN2. setOnClickListener(this);
        rbNumCuatroN3. setOnClickListener(this);
        loadDatos();
    }
    private void loadDatos() {
        preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        userName =preferences.getString(Preference.USER_NAME, "");
        id_user =db.obtenerId(userName);
        List<Puntos> pts=db.sumaPuntos(id_user);
        pts=db.sumaPuntos(id_user);
        int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
    }
    @Override
    public void onClick(View v) {
        int id =v.getId();
        if (id== R.id.rbNumUnoP){
            conCB=1;
            conNum=conNum+1;
            enabledNumUno();
            puntosBuenos();
        }else if (id== R.id.rbNumUnoN || id== R.id.rbNumUnoN2 || id== R.id.rbNumUnoN3) {
            enabledNumUno();
            cont_intentos=cont_intentos+1;
            conCM=1;
            conNum=conNum+1;
            irPantallaErrores();
        }else if (id== R.id.rbNumDosP ){
            enabledNumDos();
            conDOB=1;
            conNum=conNum+1;
            puntosBuenos();
        }else if (id== R.id.rbNumDosN || id== R.id.rbNumDosN2 || id== R.id.rbNumDosN3){
            enabledNumDos();
            cont_intentos=cont_intentos+1;
            conDOM=1;
            conNum=conNum+1;
            irPantallaErrores();
        }if (id== R.id.rbNumTresP){
            enabledNumTres();
            conDIB=1;
            conNum=conNum+1;
            puntosBuenos();
        }else if (id== R.id.rbNumTresN || id== R.id.rbNumTresN2 || id== R.id.rbNumTresN3) {
            enabledNumTres();
            cont_intentos=cont_intentos+1;
            conDIM=1;
            conNum=conNum+1;
            irPantallaErrores();
        }else if (id== R.id.rbNumCuatroP ){
            enabledNumCuatro();
            conDUB=1;
            conNum=conNum+1;
            puntosBuenos();
        }else if (id== R.id.rbNumCuatroN || id== R.id.rbNumCuatroN2 || id== R.id.rbNumCuatroN3){
            enabledNumCuatro();
            conDUM=1;
            conNum=conNum+1;
            irPantallaErrores();
        }
    }

    private void irPantallaErrores() {
        if (conNum==4){
            if ((conCM==1 && conDOB==1 && conDIB==1 && conDUB==1) || (conCB==1 && conDOM==1 && conDIB==1 && conDUB==1) || (conCB==1 && conDOB==1 && conDIM==1 && conDUB==1) || (conDUM==1 &&conCB==1 && conDOB==1 && conDIB==1) ||
                    (conCM==1 && conDOM==1 && conDIB==1 && conDUB==1) || (conCM==1 && conDOB==1 && conDIM==1 && conDUB==1) || (conCM==1 && conDOB==1 && conDIB==1 && conDUM==1)  ||
                    (conCB==1 && conDOM==1 && conDIM==1 && conDUB==1) || (conCB==1 && conDOM==1 && conDIB==1 && conDUM==1) || (conCB==1 && conDOB==1 && conDIM==1 && conDUM==1) ||
                    (conCM==1 && conDOM==1 && conDIM==1 && conDUB==1) ||(conCM==1 && conDOM==1 && conDIB==1 && conDUM==1) ||(conCM==1 && conDOB==1 && conDIM==1 && conDUM==1) ||(conCB==1 && conDOM==1 && conDIM==1 && conDUM==1) ||
                    (conCM==1 && conDOM==1 && conDIM==1 && conDUM==1)) {
                Puntos puntos= new Puntos(id_user, 2);
                db.insertarPuntos(puntos);
                irActivity();
            }
            else {
                if (cont_intentos>=2){
                    Toast.makeText(this, "Quiz no superado debes repetir el nivel 6", Toast.LENGTH_LONG).show();
                    Intent irNivelDos=new Intent(this, Nivel6Activity.class);
                    startActivity(irNivelDos);
                }
            }
        }}

    private void puntosBuenos() {
        if (conNum==4){
            if (conCB==1 && conDOB==1 && conDIB==1 && conDUB==1){
                Puntos puntos= new Puntos(id_user, 3);
                db.insertarPuntos(puntos);
                irActivity();
            }
        }
    }


    private void irActivity() {
        Intent irNivelCuatro=new Intent(this, Nivel7Activity.class);
        startActivity(irNivelCuatro);
        finish();
    }

    private void enabledNumUno() {
        rbNumUnoP.setEnabled(false);
        rbNumUnoN.setEnabled(false);
        rbNumUnoN2.setEnabled(false);
        rbNumUnoN3.setEnabled(false);
        rgPreDos.setEnabled(false);
    }

    private void enabledNumDos() {
        rbNumDosP.setEnabled(false);
        rbNumDosN.setEnabled(false);
        rbNumDosN2.setEnabled(false);
        rbNumDosN3.setEnabled(false);
        rgPreDos.setEnabled(false);
    }

    private void enabledNumTres() {
        rbNumTresP.setEnabled(false);
        rbNumTresN.setEnabled(false);
        rbNumTresN2.setEnabled(false);
        rbNumTresN3.setEnabled(false);
        rgPreTres.setEnabled(false);
    }

    private void enabledNumCuatro() {
        rbNumCuatroP.setEnabled(false);
        rbNumCuatroN.setEnabled(false);
        rbNumCuatroN2.setEnabled(false);
        rbNumCuatroN3.setEnabled(false);
        rgPreCuatro.setEnabled(false);
    }
}
