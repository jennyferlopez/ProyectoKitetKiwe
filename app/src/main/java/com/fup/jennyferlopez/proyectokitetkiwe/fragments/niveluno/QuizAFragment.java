package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

public class QuizAFragment extends Fragment implements View.OnClickListener {

    TextView tvPreguntaUno, tvPreguntaDos;
    RadioGroup rgPreUno, rgPreDos;
    RadioButton rbOralesP, rbOralesN, rbOralesN2, rbOralesN3, rbNasalesP, rbNasalesN, rbNasalesN2, rbNasalesN3;

    GestorBd db;
    SharedPreferences preferences;
    String userName;
    int id_user, cont_good=0, cont_fail=0, cont_intentos=0;
    int conOB=0, conOM=0;
    public QuizAFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_quiz_a, container, false);
        db=new GestorBd(getActivity());

        tvPreguntaUno =(TextView) view.findViewById(R.id.pregUnoVoc);
        tvPreguntaDos =(TextView) view.findViewById(R.id.pregDosVoc);
        rgPreUno =(RadioGroup) view.findViewById(R.id.rgPreguntaUno);
        rgPreDos =(RadioGroup) view.findViewById(R.id.rgPreguntados);
        rbOralesP =(RadioButton) view.findViewById(R.id.rbOralesP);
        rbOralesN =(RadioButton) view.findViewById(R.id.rbOralesN);
        rbOralesN2 =(RadioButton) view.findViewById(R.id.rbOralesN2);
        rbOralesN3 =(RadioButton) view.findViewById(R.id.rbOralesN3);
        rbNasalesP =(RadioButton) view.findViewById(R.id.rbNasalesP);
        rbNasalesN =(RadioButton) view.findViewById(R.id.rbNasalesN);
        rbNasalesN2 =(RadioButton) view.findViewById(R.id.rbNasalesN2);
        rbNasalesN3 =(RadioButton) view.findViewById(R.id.rbNasalesN3);

        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tvPreguntaUno.setTypeface(font);
        tvPreguntaDos.setTypeface(font);
        rbOralesP.setTypeface(font);
        rbOralesN.setTypeface(font);
        rbOralesN2.setTypeface(font);
        rbOralesN3.setTypeface(font);
        rbNasalesP.setTypeface(font);
        rbNasalesN.setTypeface(font);
        rbNasalesN2.setTypeface(font);
        rbNasalesN3.setTypeface(font);


        rbOralesP.setOnClickListener(this);
        rbOralesN.setOnClickListener(this);
        rbOralesN2.setOnClickListener(this);
        rbOralesN3.setOnClickListener(this);
        rbNasalesP.setOnClickListener(this);
        rbNasalesN.setOnClickListener(this);
        rbNasalesN2.setOnClickListener(this);
        rbNasalesN3.setOnClickListener(this);

        loadDatos();
        return view;
    }

    private void loadDatos() {
        preferences = getActivity().getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        userName =preferences.getString(Preference.USER_NAME, "");
        id_user =db.obtenerId(userName);
        List<Puntos> pts=db.sumaPuntos(id_user);
        pts=db.sumaPuntos(id_user);
        int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
    }


    private void irActivity() {
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(R.id.fragmentA, new QuizBFragment());
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.addToBackStack(null);
        trans.commit();
        ocultarDatos();

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
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            if (conOM==1 || conOB==1){
                irActivity();
            }
        }else if (id== R.id.rbNasalesN || id== R.id.rbNasalesN2 || id== R.id.rbNasalesN3){
            enabledNasales();
            if (conOM==1 || conOB==1) {
                irActivity();
            }
        }if (conOM==1 && id== R.id.rbNasalesP)
        {
            enabledNasales();
            irActivity();

        }

    }
}
