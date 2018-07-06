package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno;


import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
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
public class VocalesUnoFragment extends Fragment implements View.OnClickListener {


    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles;
    TextView tv_title;
    int idUsuario;
    GestorBd db;
    int id_user;
    ImageView oral_a, oral_e, oral_i, oral_u, nasal_a, nasal_e, nasal_i, nasal_u;

    ServicioUsuario servicioUsuario;
    public VocalesUnoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vocales_uno, container, false);

        tv_title = (TextView) view.findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) view.findViewById(R.id.ic_avatarNiveles);
        tv_puntos = (TextView) view.findViewById(R.id.tv_puntos);
        oral_a = (ImageView) view.findViewById(R.id.c_asp_ch);
        oral_e = (ImageView) view.findViewById(R.id.c_asp_kh);
        oral_i = (ImageView) view.findViewById(R.id.c_asp_ph);
        oral_u = (ImageView) view.findViewById(R.id.c_asp_th);
        nasal_a = (ImageView) view.findViewById(R.id.c_aspal_cxh);
        nasal_e = (ImageView) view.findViewById(R.id.c_aspal_kxh);
        nasal_i = (ImageView) view.findViewById(R.id.c_aspal_txh);
        nasal_u = (ImageView) view.findViewById(R.id.c_aspal_pxh);

        oral_a.setOnClickListener(this);
        oral_e.setOnClickListener(this);
        oral_i.setOnClickListener(this);
        oral_u.setOnClickListener(this);
        nasal_a.setOnClickListener(this);
        nasal_e.setOnClickListener(this);
        nasal_i.setOnClickListener(this);
        nasal_u.setOnClickListener(this);

        String font_url = "font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);
        db=new GestorBd(getContext());

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
        idUsuario = preferences.getInt(Preference.USER_ID, 0);

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
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.oral_a);
            mp.start();
        }else if (v.getId() == R.id.c_asp_kh) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.oral_e);
            mp.start();
        }else if (v.getId() == R.id.c_asp_ph) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.oral_i);
            mp.start();
        }else if (v.getId() == R.id.c_asp_th) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.oral_u);
            mp.start();
        }else if (v.getId() == R.id.c_aspal_cxh) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.nasal_a);
            mp.start();
        }else if (v.getId() == R.id.c_aspal_kxh) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.nasal_e);
            mp.start();
        }else if (v.getId() == R.id.c_aspal_txh) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.nasal_i);
            mp.start();
        }else if (v.getId() == R.id.c_aspal_pxh) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.nasal_u);
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
