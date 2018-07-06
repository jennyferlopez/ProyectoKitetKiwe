package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcinco;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel2Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelseis.Nivel6Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizAnimPlanBFragment extends Fragment implements CompoundButton.OnCheckedChangeListener{

    TextView tvPreguntaTres, tvPreguntaCuatro;
    RadioGroup rgPreTres, rgPreCuatro;
    RadioButton rbAspirP, rbAspirN, rbAspirN2, rbAspirN3, rbAlargP, rbAlargN, rbAlargN2, rbAlargN3;
    GestorBd db;
    SharedPreferences preferences;
    String userName;
    int id_user, cont_good=0, cont_fail=0, cont_intentos=0;


    public QuizAnimPlanBFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_quiz_anim_plan_b, container, false);
        db=new GestorBd(getActivity());
        tvPreguntaTres =(TextView) view.findViewById(R.id.pregTresVoc);
        tvPreguntaCuatro =(TextView) view.findViewById(R.id.pregCuatroVoc);
        rgPreTres =(RadioGroup) view.findViewById(R.id.rgPreguntaTres);
        rgPreCuatro =(RadioGroup) view.findViewById(R.id.rgPreguntaCuatro);
        rbAspirP =(RadioButton) view.findViewById(R.id.rbAspirP);
        rbAspirN =(RadioButton) view.findViewById(R.id.rbAspirN);
        rbAspirN2 =(RadioButton) view.findViewById(R.id.rbAspirN2);
        rbAspirN3 =(RadioButton) view.findViewById(R.id.rbAspirN3);
        rbAlargP =(RadioButton) view.findViewById(R.id.rbAlargP);
        rbAlargN =(RadioButton) view.findViewById(R.id.rbAlargN);
        rbAlargN2 =(RadioButton) view.findViewById(R.id.rbAlargN2);
        rbAlargN3 =(RadioButton) view.findViewById(R.id.rbAlargN3);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tvPreguntaTres.setTypeface(font);
        tvPreguntaCuatro.setTypeface(font);
        rbAspirP.setTypeface(font);
        rbAspirN.setTypeface(font);
        rbAspirN2.setTypeface(font);
        rbAspirN3.setTypeface(font);
        rbAlargP.setTypeface(font);
        rbAlargN.setTypeface(font);
        rbAlargN2.setTypeface(font);
        rbAlargN3.setTypeface(font);

        rbAspirP.setOnCheckedChangeListener(this);
        rbAspirN.setOnCheckedChangeListener(this);
        rbAspirN2.setOnCheckedChangeListener(this);
        rbAspirN3.setOnCheckedChangeListener(this);
        rbAlargP.setOnCheckedChangeListener(this);
        rbAlargN.setOnCheckedChangeListener(this);
        rbAlargN2.setOnCheckedChangeListener(this);
        rbAlargN3.setOnCheckedChangeListener(this);

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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (rbAspirP.isChecked() && rbAlargP.isChecked())
        {
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            Toast.makeText(getActivity(), "Correcto ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else  if (rbAspirP.isChecked()){
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            cont_good=cont_good+1;
            irActivity();
            Toast.makeText(getActivity(), "Muy bien! ", Toast.LENGTH_SHORT).show();
            enabledAspiradas();
        }else  if (rbAlargP.isChecked()){
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            cont_intentos=cont_intentos+1;
            irActivity();
            Toast.makeText(getActivity(), "Muy bien! ", Toast.LENGTH_SHORT).show();
            enabledAlargadas();
        }else if (rbAspirN.isChecked()){
            enabledAspiradas();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbAspirN2.isChecked() ){
            enabledAspiradas();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbAspirN3.isChecked()){
            enabledAspiradas();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbAlargN.isChecked()){
            enabledAlargadas();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbAlargN2.isChecked()){
            enabledAlargadas();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbAlargN3.isChecked()){
            enabledAlargadas();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }
    }

    private void irActivity() {
        if (cont_intentos>=1 && cont_good>=1){
            Intent irNivelDos=new Intent(getActivity(), Nivel6Activity.class);
            startActivity(irNivelDos);
            getActivity().finish();
        }
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


}
