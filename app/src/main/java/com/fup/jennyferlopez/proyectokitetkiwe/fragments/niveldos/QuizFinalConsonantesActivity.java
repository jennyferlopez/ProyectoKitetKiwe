package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.activities.NivelesActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres.Nivel3Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ServicioUsuario;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class QuizFinalConsonantesActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvPreguntaUno, tvPreguntaDos;
    RadioGroup rgPreUno, rgPreDos;
    RadioButton rbBasicasP, rbBasicasN, rbBasicasN2, rbBasicasN3, rbPalatalaesP, rbPalatalaesN, rbPalatalaesN2, rbPalatalaesN3;
    TextView tvPreguntaTres, tvPreguntaCuatro, tv_puntos;
    RadioGroup rgPreTres, rgPreCuatro;
    RadioButton rbAspCP, rbAspCN, rbAspCN2, rbAspCN3, rbPalCP, rbPalCN, rbPalCN2, rbPalCN3;
    GestorBd db;
    SharedPreferences preferences;
    String userName;
    int id_user, cont_good=0, cont_fail=0, cont_intentos=0;
    int conBB=0, conBM=0,conPB=0, conPM=0,conAB=0, conAM=0 ;
//1231

    ServicioUsuario servicioUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_final_consonantes);
        db=new GestorBd(this);

        tv_puntos =(TextView) findViewById(R.id.tv_puntos);
        tvPreguntaUno =(TextView) findViewById(R.id.pregUnoCon);
        tvPreguntaDos =(TextView) findViewById(R.id.pregDosCon);
        rgPreUno =(RadioGroup) findViewById(R.id.rgPreguntaUnoC);
        rgPreDos =(RadioGroup) findViewById(R.id.rgPreguntadosC);
        rbBasicasP =(RadioButton) findViewById(R.id.rbBasicasP);
        rbBasicasN =(RadioButton) findViewById(R.id.rbBasicasN);
        rbBasicasN2 =(RadioButton) findViewById(R.id.rbBasicasN2);
        rbBasicasN3 =(RadioButton) findViewById(R.id.rbBasicasN3);
        rbPalatalaesP =(RadioButton) findViewById(R.id.rbPalatalesP);
        rbPalatalaesN =(RadioButton) findViewById(R.id.rbPalatalesN);
        rbPalatalaesN2 =(RadioButton) findViewById(R.id.rbPalatalesN2);
        rbPalatalaesN3 =(RadioButton) findViewById(R.id.rbPalatalesN3);
        tvPreguntaTres =(TextView) findViewById(R.id.pregTresVoc);
        tvPreguntaCuatro =(TextView) findViewById(R.id.pregCuatroVoc);
        rgPreTres =(RadioGroup) findViewById(R.id.rgPreguntaTres);
        rgPreCuatro =(RadioGroup) findViewById(R.id.rgPreguntaCuatro);
        rbAspCP =(RadioButton) findViewById(R.id.rbAspCP);
        rbAspCN =(RadioButton) findViewById(R.id.rbAspCN);
        rbAspCN2 =(RadioButton) findViewById(R.id.rbAspCN2);
        rbAspCN3 =(RadioButton) findViewById(R.id.rbAspCN3);
        rbPalCP =(RadioButton) findViewById(R.id.rbPalCP);
        rbPalCN =(RadioButton) findViewById(R.id.rbPalCN);
        rbPalCN2 =(RadioButton) findViewById(R.id.rbPalCN2);
        rbPalCN3 =(RadioButton) findViewById(R.id.rbPalCN3);

        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tvPreguntaUno.setTypeface(font);
        tvPreguntaDos.setTypeface(font);

        tv_puntos.setTypeface(font);
        /*rbBasicasP.setTypeface(font);
        rbBasicasN.setTypeface(font);
        rbBasicasN2.setTypeface(font);
        rbBasicasN3.setTypeface(font);
        rbPalatalaesP.setTypeface(font);
        rbPalatalaesN.setTypeface(font);
        rbPalatalaesN2.setTypeface(font);
        rbPalatalaesN3.setTypeface(font);*/
        tvPreguntaTres.setTypeface(font);
        tvPreguntaCuatro.setTypeface(font);
        /*rbAspCP.setTypeface(font);
        rbAspCN.setTypeface(font);
        rbAspCN2.setTypeface(font);
        rbAspCN3.setTypeface(font);
        rbPalCP.setTypeface(font);
        rbPalCN.setTypeface(font);
        rbPalCN2.setTypeface(font);
        rbPalCN3.setTypeface(font);*/

        rbBasicasP.setOnClickListener(this);
        rbBasicasN.setOnClickListener(this);
        rbBasicasN2.setOnClickListener(this);
        rbBasicasN3.setOnClickListener(this);
        rbPalatalaesP.setOnClickListener(this);
        rbPalatalaesN.setOnClickListener(this);
        rbPalatalaesN2.setOnClickListener(this);
        rbPalatalaesN3.setOnClickListener(this);
        rbAspCP.setOnClickListener(this);
        rbAspCN.setOnClickListener(this);
        rbAspCN2.setOnClickListener(this);
        rbAspCN3.setOnClickListener(this);
        rbPalCP.setOnClickListener(this);
        rbPalCN.setOnClickListener(this);
        rbPalCN2.setOnClickListener(this);
        rbPalCN3.setOnClickListener(this);
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
        List<Puntos> pts=db.sumaPuntos(id_user);
        pts=db.sumaPuntos(id_user);
        int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));

    }

    private void irActivity() {
        Intent irNivelDos=new Intent(this, Nivel3Activity.class);
        startActivity(irNivelDos);
        finish();
    }
    @Override
    public void onClick(View v) {
        int id =v.getId();
        if (id== R.id.rbBasicasP){
            conBB=1;
            enabledOrales();
        }else if (id== R.id.rbBasicasN || id== R.id.rbBasicasN2 || id== R.id.rbBasicasN3) {
            enabledOrales();
            cont_intentos=cont_intentos+1;
            conBM=1;
        }else if (id== R.id.rbPalatalesP ){
            enabledNasales();
            conPB=1;
        }else if (id== R.id.rbPalatalesN || id== R.id.rbPalatalesN2 || id== R.id.rbPalatalesN3){
            enabledNasales();
            cont_intentos=cont_intentos+1;
            conPM=1;
        }if (id== R.id.rbAspCP){
            enabledAspiradasC();
            conAB=1;
        }else if (id== R.id.rbAspCN || id== R.id.rbAspCN2 || id== R.id.rbAspCN3) {
            enabledAspiradasC();
            cont_intentos=cont_intentos+1;
            conAM=1;
        }else if (id== R.id.rbPalCP ){
            if (conBB==1 && conPB==1 && conAB==1){
                Puntos puntos= new Puntos(id_user, 3);
                db.insertarPuntos(puntos);
                irActivity();
            }
        }else if (id== R.id.rbPalCN || id== R.id.rbPalCN2 || id== R.id.rbPalCN3){
            enabledAlargadasC();
            if ((conBM==1 && conPB==1 && conAB==1) || (conBB==1 && conPM==1 && conAB==1) || (conBB==1 && conPB==1 && conAM==1)) {
                Puntos puntos= new Puntos(id_user, 2);
                db.insertarPuntos(puntos);
                irActivity();
            }
            else {
                if (cont_intentos>=2){
                    Toast.makeText(this, "Quiz no superado debes repetir el nivel 1", Toast.LENGTH_LONG).show();
                    Intent irNivelDos=new Intent(this, Nivel2Activity.class);
                    startActivity(irNivelDos);
                }
            }
        }
    }

    private void enabledOrales() {
        rbBasicasP.setEnabled(false);
        rbBasicasN.setEnabled(false);
        rbBasicasN2.setEnabled(false);
        rbBasicasN3.setEnabled(false);
        rgPreDos.setEnabled(false);
    }

    private void enabledNasales() {
        rbPalatalaesP.setEnabled(false);
        rbPalatalaesN.setEnabled(false);
        rbPalatalaesN2.setEnabled(false);
        rbPalatalaesN3.setEnabled(false);
        rgPreDos.setEnabled(false);

    }


    private void enabledAspiradasC() {
        rbAspCP.setEnabled(false);
        rbAspCN.setEnabled(false);
        rbAspCN2.setEnabled(false);
        rbAspCN3.setEnabled(false);
    }

    private void enabledAlargadasC() {
        rbPalCP.setEnabled(false);
        rbPalCN.setEnabled(false);
        rbPalCN2.setEnabled(false);
        rbPalCN3.setEnabled(false);
    }
    private void actualizarPuntos() {
        userName =preferences.getString(Preference.USER_NAME, "");
        User usuario_por_id = servicioUsuario.obtenerUsuarioPorId(userName);
        if (usuario_por_id!=null) {
            int p=Integer.parseInt(String.valueOf(usuario_por_id.getPuntos()));
            tv_puntos.setText(""+ p);
        }
    }

}
