package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.Niveles11Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ServicioUsuario;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsAspiradasFragment extends Fragment implements View.OnClickListener{

    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles;
    TextView tv_title;
    Button btnSiguiente;
    int id_user;
    GestorBd db;
    ImageView c_asp_ch, c_asp_kh, c_asp_th, c_asp_ph, c_aspal_cxh, c_aspal_kxh, c_aspal_pxh, c_aspal_txh;

    ServicioUsuario servicioUsuario;
    public ConsAspiradasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cons_aspiradas, container, false);
        db=new GestorBd(getActivity());
        btnSiguiente= (Button) view.findViewById(R.id.fab);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irNivel= new Intent(getActivity(), Nivel21Activity.class);
                startActivity(irNivel);
                getActivity().finish();
            }
        });
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) view.findViewById(R.id.ic_avatarNiveles);
        tv_puntos = (TextView) view.findViewById(R.id.tv_puntos);

        c_asp_ch=(ImageView) view.findViewById(R.id.c_asp_ch);
        c_asp_kh=(ImageView) view.findViewById(R.id.c_asp_kh);
        c_asp_ph=(ImageView) view.findViewById(R.id.c_asp_ph);
        c_asp_th=(ImageView) view.findViewById(R.id.c_asp_th);
        c_aspal_cxh=(ImageView) view.findViewById(R.id.c_aspal_cxh);
        c_aspal_kxh=(ImageView) view.findViewById(R.id.c_aspal_kxh);
        c_aspal_txh=(ImageView) view.findViewById(R.id.c_aspal_txh);
        c_aspal_pxh=(ImageView) view.findViewById(R.id.c_aspal_pxh);

        c_asp_ch.setOnClickListener(this);
        c_asp_kh.setOnClickListener(this);
        c_asp_th.setOnClickListener(this);
        c_asp_ph.setOnClickListener(this);
        c_aspal_cxh.setOnClickListener(this);
        c_aspal_kxh.setOnClickListener(this);
        c_aspal_txh.setOnClickListener(this);
        c_aspal_pxh.setOnClickListener(this);

        String font_url = "font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);

        loadPreference();
        loadRealm();
        actualizarPuntos();
        return view;
    }

    private void loadRealm() {
        Realm.init(getActivity());

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("Test1")
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        servicioUsuario = new ServicioUsuario(Realm.getDefaultInstance());

    }
    private void loadPreference() {
        preferences = getActivity().getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.c_asp_ch) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_aspi_ch);
            mp.start();
        }else if (v.getId() == R.id.c_asp_kh) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_aspi_kh);
            mp.start();
        }else if (v.getId() == R.id.c_asp_ph) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_aspi_ph);
            mp.start();
        }else if (v.getId() == R.id.c_asp_th) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_aspi_th);
            mp.start();
        }else if (v.getId() == R.id.c_aspal_cxh) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_aspal_cxh);
            mp.start();
        }else if (v.getId() == R.id.c_aspal_kxh) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_aspal_kxh);
            mp.start();
        }else if (v.getId() == R.id.c_aspal_txh) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_aspal_txh);
            mp.start();
        }else if (v.getId() == R.id.c_aspal_pxh) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.c_aspal_pxh);
            mp.start();
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

}
