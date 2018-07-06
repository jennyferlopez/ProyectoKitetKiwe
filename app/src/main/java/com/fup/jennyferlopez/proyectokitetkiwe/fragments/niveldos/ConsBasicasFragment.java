package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos;


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
public class ConsBasicasFragment extends Fragment implements View.OnClickListener {

    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles;
    TextView tv_title;
    int idUsuario;
    GestorBd db;
    int id_user;
    ImageView con_p, con_m, con_t, con_n, con_c, con_b, con_k, con_d, con_g, con_z, con_s, con_y;

    ServicioUsuario servicioUsuario;
    public ConsBasicasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cons_basicas, container, false);

        con_p =(ImageView) view.findViewById(R.id.c_basic_p);
        con_m =(ImageView) view.findViewById(R.id.c_basic_m);
        con_t =(ImageView) view.findViewById(R.id.c_basic_t);
        con_n =(ImageView) view.findViewById(R.id.c_basic_n);
        con_c =(ImageView) view.findViewById(R.id.c_basic_c);
        con_b =(ImageView) view.findViewById(R.id.c_basic_b);
        con_k =(ImageView) view.findViewById(R.id.c_basic_k);
        con_d =(ImageView) view.findViewById(R.id.c_basic_d);
        con_g =(ImageView) view.findViewById(R.id.c_basic_g);
        con_z =(ImageView) view.findViewById(R.id.c_basic_z);
        con_s =(ImageView) view.findViewById(R.id.c_basic_s);
        con_y =(ImageView) view.findViewById(R.id.c_basic_y);

        con_p.setOnClickListener(this);
        con_m.setOnClickListener(this);
        con_t.setOnClickListener(this);
        con_c.setOnClickListener(this);
        con_n.setOnClickListener(this);
        con_b.setOnClickListener(this);
        con_k.setOnClickListener(this);
        con_d.setOnClickListener(this);
        con_g.setOnClickListener(this);
        con_z.setOnClickListener(this);
        con_s.setOnClickListener(this);
        con_y.setOnClickListener(this);

        tv_title = (TextView) view.findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) view.findViewById(R.id.ic_avatarNiveles);
        tv_puntos = (TextView) view.findViewById(R.id.tv_puntos);
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
        if (v.getId() == R.id.c_basic_p) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.cons_basic_p);
            mp.start();
        }else if (v.getId() == R.id.c_basic_m) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.cons_basic_m);
            mp.start();
        }else if (v.getId() == R.id.c_basic_t) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.cons_basic_t);
            mp.start();
        }else if (v.getId() == R.id.c_basic_n) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.cons_basic_n);
            mp.start();
        }else if (v.getId() == R.id.c_basic_c) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.cons_basic_c);
            mp.start();
        }else if (v.getId() == R.id.c_basic_b) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.cons_basic_b);
            mp.start();
        }else if (v.getId() == R.id.c_basic_k) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.cons_basic_k);
            mp.start();
        }else if (v.getId() == R.id.c_basic_d) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.cons_basic_d);
            mp.start();
        }else if (v.getId() == R.id.c_basic_g) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.cons_basic_g);
            mp.start();
        }else if (v.getId() == R.id.c_basic_z) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.cons_basic_z);
            mp.start();
        }else if (v.getId() == R.id.c_basic_s) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.cons_basic_s);
            mp.start();
        }else if (v.getId() == R.id.c_basic_y) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.cons_basic_y);
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
